/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controllers;

import com.app.daos.HistoryDAO;
import com.app.dtos.Account;
import com.app.dtos.OrderHistory;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "HistoryController", urlPatterns = {"/HistoryController"})
public class HistoryController extends HttpServlet {

    private static final String SUCCESS = "MainController?action=history";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
//            String id = request.getParameter("userID");
//            HistoryDAO historyDAO = new HistoryDAO();
//            List<OrderHistory> listOrderHistory = historyDAO.getHistoryOrder(id);
//            request.setAttribute("LIST_ORDER", listOrderHistory);

            String carName = "/^/^";
            if (request.getParameter("txtCarName") != null) {
                carName = request.getParameter("txtCarName");
            }
            String strDate = request.getParameter("txtDate");
            Date orderDate = null;
            try {
                String pattern = "yyyy-mm-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                orderDate = simpleDateFormat.parse(strDate);
            } catch (Exception e) {
                orderDate = new Date();
            }
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("LOGIN_USER");
            String userID = account.getUserID();
            HistoryDAO historyDAO = new HistoryDAO();
            List<OrderHistory> listOrderHistory = null;
            try {
                listOrderHistory = historyDAO.getHistoryOrder(userID, carName, orderDate);
                if (listOrderHistory == null) {
                    request.setAttribute("NO_RECORD", "No record to display");
                }
            } catch (Exception e) {

            }
            request.setAttribute("LIST_ORDER", listOrderHistory);
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
