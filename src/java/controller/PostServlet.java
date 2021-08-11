/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PostDao;
import dao.TopicDao;
import java.io.IOException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Post;
import model.Topic;

/**
 *
 * @author KUCA
 */
@WebServlet(name = "PostServlet", urlPatterns = {"/post"})
public class PostServlet extends HttpServlet {

    PostDao postDao = new PostDao();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

            if (request.getParameter("create") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                String text = request.getParameter("text");
                Topic t = new TopicDao().getById(id);

                Post p = new Post();
                p.setText(text);
                p.setCreationDate(new Date());
                p.setTopics(t);
                postDao.save(p);
                request.setAttribute("topic", t);

                response.sendRedirect("post?post=" + id);
            }

            if (request.getParameter("edit") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                int topic = Integer.parseInt(request.getParameter("topic"));
                Topic t = new TopicDao().getById(topic);
                Post post = postDao.getById(id);
                request.setAttribute("topic", t);
                RequestDispatcher rd = request.getRequestDispatcher("readPost.jsp");
                request.setAttribute("post", post);
                rd.forward(request, response);
            }

            if (request.getParameter("update") != null) {

                int id = Integer.parseInt(request.getParameter("id"));
                String text = request.getParameter("text");
                int topic = Integer.parseInt(request.getParameter("topic"));

                Topic t = new TopicDao().getById(topic);

                Post p = new Post();
                p.setId(id);
                p.setText(text);
                p.setTopics(t);
                p.setCreationDate(new Date());
                postDao.update(p);

                response.sendRedirect("post?post=" + topic);
            }

            if (request.getParameter("del") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                int topic = Integer.parseInt(request.getParameter("topic"));
                Post t = postDao.getById(id);
                postDao.delete(t);
                response.sendRedirect("post?post=" + topic);
            }
        }

        if (request.getParameter("post") != null) {
            int id = Integer.parseInt(request.getParameter("post"));

            Topic topic = new TopicDao().getById(id);
            request.setAttribute("topic", topic);

            RequestDispatcher rd = request.getRequestDispatcher("readPost.jsp");
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
