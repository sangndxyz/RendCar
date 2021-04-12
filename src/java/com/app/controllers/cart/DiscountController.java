/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controllers.cart;

import com.app.daos.DiscoutDAO;
import com.app.dtos.Discount;
import java.io.IOException;
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
@WebServlet(name = "DiscountController", urlPatterns = {"/DiscountController"})
public class DiscountController extends HttpServlet {

    private static final String SUCCESS = "MainController?action=payment";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = SUCCESS;
        try {
            String discountCode = request.getParameter("txtDiscount");

            DiscoutDAO discoutDAO = new DiscoutDAO();
            try {
                Discount discount = discoutDAO.getDiscountByCode(discountCode);
                HttpSession session = request.getSession();
                Date expirydate = discount.getExpiryDate();
//            if(expirydate.after(new Date()))
                Date todayDate = new Date();
                java.util.Date jDate = new java.sql.Date(todayDate.getTime());
                if (discount != null) {
                    //2021-04-03  2021-03-05
                    if (expirydate.after(jDate)) {
                        session.setAttribute("discountPercent", discount.getDiscountPercent());
                        session.setAttribute("discountId", discount.getDiscountId());
                        // Cart shoppingCart=(Cart) session.getAttribute("shoppingCart");
                    } else {
                        request.setAttribute("NOTIFY_EXPIRY", "Discount code expirydate");
                    }

                }
            } catch (Exception e) {
                request.setAttribute("NOTIFY_DISCOUNT_ERROR", "Discount incorect");
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
