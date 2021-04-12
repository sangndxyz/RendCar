/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controllers.cart;

import com.app.daos.CarDAO;
import com.app.daos.OrderDAO;
import com.app.dtos.Car;
import com.app.dtos.CarInCart;
import com.app.dtos.Cart;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutController"})
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "MainController?action=view";
    private static final String SUCCESS = "MainController?action=payment";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {

            CarDAO carDAO = new CarDAO();
            HttpSession session = request.getSession();

            Cart shoppingCart = (Cart) session.getAttribute("shoppingCart");
            String userId = shoppingCart.getCustomerName();
            OrderDAO orderDAO = new OrderDAO();
            String orderID = orderDAO.getLastOrderIDByUser();

            if (orderID == null) {
                orderID = "OD-" + userId + "-1";
            } else {
                String[] tmp = orderID.split("-");
                int count = Integer.parseInt(tmp[2]);
                orderID = "OD-" + userId + "-" + (count + 1);
            }

            List<CarInCart> listCarInCart = new ArrayList<>(shoppingCart.getShoppingCart());//get values in cart bo vao list de check
            //yamaha novo, honda winner

            String outOfStockResult = "";

//            for (CarInCart carInCart : listCarInCart) {
//                List<Car> listCarAvailable = carDAO.checkCarAvailable(carInCart.getCarName(), carInCart.getCateID(), carInCart.getCheckIn(), carInCart.getCheckOut(), 0);
//
//                if (carInCart.getQuantity() > listCarAvailable.get(0).getQuantity()) {
//                    outOfStockResult += "Car " + carInCart.getCarName() + " not enough quantity, only have " + listCarAvailable.get(0).getQuantity() + "</br>";
//                }
//
//            }
            for (int i=0;i<listCarInCart.size();i++) {
                List<Car> listCarAvailable = carDAO.checkCarAvailable(listCarInCart.get(i).getCarName(),listCarInCart.get(i).getCateID(), listCarInCart.get(i).getCheckIn(),listCarInCart.get(i).getCheckOut(), 0);

                if (listCarInCart.get(i).getQuantity() > listCarAvailable.get(i).getQuantity()) {
                    outOfStockResult += "Car " + listCarInCart.get(i).getCarName() + " not enough quantity, only have " + listCarAvailable.get(i).getQuantity() + "</br>";
                }

            }

            request.setAttribute("OUT_OF_STOCK", outOfStockResult);
            session.setAttribute("ORDERID", orderID);

            if (outOfStockResult.isEmpty()) {

                //discountPercent
                session.setAttribute("discountPercent", 0);

                url = SUCCESS;
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
