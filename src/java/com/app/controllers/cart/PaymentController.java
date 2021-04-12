/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controllers.cart;

import com.app.daos.OrderDAO;
import com.app.daos.PaymentDAO;
import com.app.dtos.Account;
import com.app.dtos.CarInCart;
import com.app.dtos.Cart;
import com.app.dtos.Order;
import com.app.dtos.OrderDetail;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "PaymentController", urlPatterns = {"/PaymentController"})
public class PaymentController extends HttpServlet {

    private static final String SUCCESS = "MainController?action=shopping";
    private static final String ERROR = "MainController?action=payment";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String address = request.getParameter("txtAddress");
            String phone = request.getParameter("txtPhone");
            HttpSession session = request.getSession();
            String orderID = (String) session.getAttribute("ORDERID");
//            PaymentDAO paymentDAO = new PaymentDAO();
//            boolean check = paymentDAO.insertPayment(address, phone, orderID);
//            if (check) {
//                request.setAttribute("PAYMENT_SUCCESS", "Payment success");
//                url = SUCCESS;
//            }
            OrderDAO orderDAO = new OrderDAO();
            Account account = (Account) session.getAttribute("LOGIN_USER");
            String userID = account.getUserID();
            Date createDate = new Date();
            Cart shoppingCart = (Cart) session.getAttribute("shoppingCart");
            float total = shoppingCart.getTotal(0);

            String discountId = (String) session.getAttribute("discountId");

            Order order = new Order(orderID, userID, createDate, total, discountId);
            orderDAO.insertOrder(order);
            PaymentDAO paymentDAO = new PaymentDAO();
            boolean check = paymentDAO.insertPayment(address, phone, orderID);
            int count = 1;

            /*
            for (CarInCart car : shoppingCart.getShoppingCart().values()) {
                String orderDetailID = orderID + "-" + count++;
                OrderDetail orderDetail = new OrderDetail(orderDetailID, orderID, car.getCarID(), car.getPrice(), car.getQuantity(), car.getCheckIn(), car.getCheckOut());
                orderDAO.insertOrderDetail(orderDetail);
            }
             */
            List<OrderDetail> orderDetails = new ArrayList<>();
//            for (CarInCart car : shoppingCart.getShoppingCart().values()) {
            for (CarInCart car : shoppingCart.getShoppingCart()) {
                String orderDetailID = orderID + "-" + count++;
                OrderDetail orderDetail = new OrderDetail(orderDetailID, orderID, car.getCarID(), car.getPrice(), car.getQuantity(), car.getCheckIn(), car.getCheckOut());
                orderDetails.add(orderDetail);
            }

            orderDAO.insertOrderDetails(orderDetails);
            url = SUCCESS;
            request.setAttribute("PAYMENT_SUCCESS", "PAYMENT SUCCESS \r\n Thank for rend car");
            session.setAttribute("shoppingCart", null);
            session.setAttribute("CHECK_IN", null);
            session.setAttribute("CHECK_OUT", null);
            session.setAttribute("NAME", null);
            session.setAttribute("CATE", null);
            session.setAttribute("AMOUT", null);

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
