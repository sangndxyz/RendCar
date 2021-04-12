/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nds72
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String CREATE = "CreateController";
    private static final String VERIFY = "VerifyController";
    private static final String CHECK_SEARCH = "CheckSearchController";
    private static final String CHECKOUT = "CheckOutController";
    private static final String ADD = "AddCartController";
    private static final String REMOVE = "RemoveCartController";
    private static final String UPDATE = "UpdateCartController";
    private static final String HISTORY = "HistoryController";
    private static final String DETAIL = "DetailController";
    private static final String PAYMENT = "PaymentController";
    private static final String USE_DISCOUNT = "DiscountController";
    private static final String SEARCH_ORDER = "HistoryController";
    private static final String FEED_BACK = "FeedBackController";
    private static final String DELETE_ORDER = "DeleteOrderController";
    private static final String LOGOUT = "LogoutController";

    private static final String REDIRECT_REGISTER = "create_account.jsp";
    private static final String REDIRECT_LOGIN = "login.jsp";
    private static final String REDIRECT_HISTORY = "history.jsp";
    private static final String REDIRECT_SHOPPING = "shopping.jsp";
    private static final String REDIRECT_VIEW = "view.jsp";
    private static final String REDIRECT_PAYMENT = "payment.jsp";
    private static final String REDIRECT_DETAIL = "detail.jsp";
    private static final String REDIRECT_FEEDBACK = "feedback.jsp";
    private static final String REDIRECT_VERIFY="verify.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("Register".equals(action)) {
                url = CREATE;
            } else if ("Verify".equals(action)) {
                url = VERIFY;
            } else if ("Search".equals(action)) {
                url = CHECK_SEARCH;
            } else if ("Add".equals(action)) {
                url = ADD;
            } else if ("Order".equals(action)) {
                url = CHECKOUT;
            } else if ("Remove".equals(action)) {
                url = REMOVE;
            } else if ("Update".equals(action)) {
                url = UPDATE;
            } else if ("History".equals(action)) {
                url = HISTORY;
            } else if ("Detail".equals(action)) {
                url = DETAIL;
            } else if ("Payment".equals(action)) {
                url = PAYMENT;
            } else if ("Use Discount".equals(action)) {
                url = USE_DISCOUNT;
            } else if ("Search Order".equals(action)) {
                url = SEARCH_ORDER;
            } else if ("FeedBack".equals(action)) {
                url = FEED_BACK;
            } else if ("Delete".equals(action)) {
                url = DELETE_ORDER;
            } else if ("Logout".equals(action)) {
                url = LOGOUT;
            } /*REDIRECT*/ else if ("registerAccount".equals(action)) {
                url = REDIRECT_REGISTER;
            } else if ("loginAccount".equals(action)) {
                url = REDIRECT_LOGIN;
            } else if ("history".equals(action)) {
                url = REDIRECT_HISTORY;
            } else if ("shopping".equals(action)) {
                url = REDIRECT_SHOPPING;
            } else if ("view".equals(action)) {
                url = REDIRECT_VIEW;
            } else if ("payment".equals(action)) {
                url = REDIRECT_PAYMENT;
            } else if ("detail".equals(action)) {
                url = REDIRECT_DETAIL;
            } else if ("feedback".equals(action)) {
                url = REDIRECT_FEEDBACK;
            }else if("verify".equals(action)){
                url=REDIRECT_VERIFY;
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
