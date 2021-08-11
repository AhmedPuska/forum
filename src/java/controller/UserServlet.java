/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author KUCA
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    UserDao userDao = new UserDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        boolean loggedIn = false;
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("ulogovan") != null && httpSession.getAttribute("useragent") != null) {
            String userAgent = request.getHeader("User-Agent");
            if (userAgent.equals(httpSession.getAttribute("useragent"))) {
                loggedIn = (boolean) httpSession.getAttribute("ulogovan");
            }
        }

        if (!loggedIn) {
            response.sendRedirect("login.jsp");
            return;
        }
        int allowedStatuses = 2 | 4;

        int status = (int) httpSession.getAttribute("status");
        if ((allowedStatuses & status) != 0) {

            if (request.getParameter("edit") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = userDao.getById(id);
                System.out.println(user);
                RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
                request.setAttribute("user", user);
                rd.forward(request, response);
            }
            if (request.getParameter("update") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                String userName = request.getParameter("userName");
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                User u = new User();
                u.setId(id);
                u.setUserName(userName);
                u.setEmail(email);
                u.setPassword(password);
                u.setStatus(2);
                userDao.update(u);

                response.sendRedirect("user?allUser");
            }

            if (request.getParameter("del") != null) {

                int id = Integer.parseInt(request.getParameter("id"));
                User u = userDao.getById(id);
                userDao.delete(u);
                System.out.println("Ispisuje readTopic unosi u bazu");
                response.sendRedirect("user?allUser");
            }

        }
        if (request.getParameter("register") != null) {
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int getStatus = Integer.parseInt(request.getParameter("status"));

            User user = new User();
            user.setUserName(userName);
            user.setEmail(email);
            user.setPassword(password);
            user.setStatus(getStatus);

            userDao.save(user);
        }

        if (request.getParameter("allUser") != null) {

            List<User> allUsers = userDao.getList();
            request.setAttribute("allUsers", allUsers);

            RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
