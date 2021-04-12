/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controllers;

import com.app.daos.FeedBackDAO;
import com.app.dtos.Account;
import com.app.dtos.FeedBack;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nds72
 */
@WebServlet(name = "FeedBackController", urlPatterns = {"/FeedBackController"})
public class FeedBackController extends HttpServlet {

    private static final String ERROR = "MainController?action=feedback";
    private static final String SUCCESS = "DetailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String err = "";
            String notify = "";
            int point = -1;
            String strPoint = request.getParameter("txtPoint");
            if (strPoint.isEmpty()) {
                err = "Please input point to feed back";
            } else {
                point = Integer.parseInt(request.getParameter("txtPoint"));
            }
            String comment = request.getParameter("comment");

            String carID = request.getParameter("carID");
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("LOGIN_USER");
            String userID = account.getUserID();
            FeedBackDAO feedBackDAO = new FeedBackDAO();
            if (err.isEmpty()) {
                FeedBack feedBack = new FeedBack(point, comment, userID, carID);
                if (feedBackDAO.checkFeedBack(userID, carID)) {
                    feedBackDAO.updateFeedBack(feedBack);
                    notify = "Update feedback success !";
                } else {
                    feedBackDAO.insertFeedBack(feedBack);
                    notify = "feedback success !";
                }
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR_POINT", err);
            }
            request.setAttribute("FEEDBACK_NOTIFY", notify);

        } catch (Exception e) {
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
