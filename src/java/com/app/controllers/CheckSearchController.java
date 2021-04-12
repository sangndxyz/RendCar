/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controllers;

import com.app.dtos.CheckSearchError;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "CheckSearchController", urlPatterns = {"/CheckSearchController"})
public class CheckSearchController extends HttpServlet {

    private static final String ERROR = "MainController?action=shopping";
    private static final String SUCCESS = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String name = request.getParameter("txtName");
            String category = request.getParameter("txtCategory");
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            Date checkin = null;
            Date checkout = null;
            CheckSearchError err = new CheckSearchError();
            boolean check = true;

            if (name.isEmpty() && category.equals("-1")) {
                request.setAttribute("ERROR_SEARCH", "You must input name or select Category");
                check = false;
            }
            if (name.isEmpty()) {
                name = "/^/^";
            }
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            if (from.isEmpty()) {
                err.setCheckIn("You must input valid date ");
                check = false;
            } else {
                try {
                    checkin = simpleDateFormat.parse(from);
                } catch (Exception e) {
                    err.setCheckIn("You must input valid date");
                    check = false;
                }
            }
            if (to.isEmpty()) {
                err.setCheckOut("You must input valid date ");
                check = false;
            } else {
                try {
                    checkout = simpleDateFormat.parse(to);
                } catch (Exception e) {
                    err.setCheckOut("You must input valid date ");
                    check = false;
                }
            }
            try {
                if (!from.isEmpty() && !to.isEmpty() && checkin.after(checkout)) {
                    request.setAttribute("ERROR_DATE", "Checkout date must greater than checkin");
                    check = false;
                }
                if (checkin.equals(checkout)) {
                    request.setAttribute("ERROR_DATE", "Checkout date must greater than checkin");
                    check = false;
                }
            } catch (Exception e) {
            }
            int amount = Integer.parseInt(request.getParameter("amount"));
            if (!check) {
                request.setAttribute("ERROR", err);
            } else {
                url = SUCCESS;
                HttpSession session = request.getSession();
                session.setAttribute("CHECK_IN", checkin);
                session.setAttribute("CHECK_OUT", checkout);
                session.setAttribute("NAME", name);
                session.setAttribute("CATE", category);
                session.setAttribute("AMOUT", amount);
            }
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
