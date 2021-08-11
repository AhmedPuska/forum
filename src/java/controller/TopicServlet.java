/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TopicDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Topic;

/**
 *
 * @author KUCA
 */
@WebServlet(name = "TopicServlet", urlPatterns = {"/topic"})
public class TopicServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    TopicDao topicDao = new TopicDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        if (request.getParameter("getTopic") != null) {
            List<Topic> allTopics = topicDao.getList();
            request.setAttribute("allTopics", allTopics);

            RequestDispatcher rd = request.getRequestDispatcher("readTopics.jsp");
            rd.forward(request, response);
        }

        int status = (int) httpSession.getAttribute("status");

        if ((allowedStatuses & status) != 0) {

            if (request.getParameter("create") != null) {
                String title = request.getParameter("title");
                String description = request.getParameter("description");

                Topic t = new Topic();
                t.setTitle(title);
                t.setDescription(description);
                t.setCreationDate(new Date());
                topicDao.save(t);

                response.sendRedirect("topic?getTopic");
            }

            if (request.getParameter("edit") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Topic topic = topicDao.getById(id);
                System.out.println(topic);
                RequestDispatcher rd = request.getRequestDispatcher("topic.jsp");
                request.setAttribute("topic", topic);
                rd.forward(request, response);
            }

            if (request.getParameter("update") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                String title = request.getParameter("title");
                String description = request.getParameter("description");

                Topic t = new Topic();
                t.setId(id);
                t.setTitle(title);
                t.setDescription(description);
                t.setCreationDate(new Date());
                topicDao.update(t);

                response.sendRedirect("topic?getTopic");
            }

            if (request.getParameter("del") != null) {

                int id = Integer.parseInt(request.getParameter("id"));
                Topic t = topicDao.getById(id);
                topicDao.delete(t);
                System.out.println("Ispisuje readTopic unosi u bazu");
                response.sendRedirect("topic?getTopic");
            }
        }

        response.setContentType("text/html;charset=UTF-8");
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
