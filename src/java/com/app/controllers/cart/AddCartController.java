/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controllers.cart;

import com.app.daos.CarDAO;
import com.app.dtos.Account;
import com.app.dtos.Car;
import com.app.dtos.CarInCart;
import com.app.dtos.Cart;
import com.app.dtos.PlusMinus;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
@WebServlet(name = "AddCartController", urlPatterns = {"/AddCartController"})
public class AddCartController extends HttpServlet {

    private static final String ERROR = "MainController?action=loginAccount";
    private static final String SUCCESS = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("LOGIN_USER");
            if (account == null) {
                request.setAttribute("NOT_LOGIN", "Please login to rend car");
            } else {
                if (account.getRolID().equals("AD")) {
                    request.setAttribute("ADMIN_LOGIN", "Admin is not allow to buy");
                } else {
                    Cart shoppingCart = (Cart) session.getAttribute("shoppingCart");
                    if (shoppingCart == null) {
                        shoppingCart = new Cart(account.getUserID());
                    }

                    String id = request.getParameter("txtCarID");
                    CarDAO carDAO = new CarDAO();
                    Car car = carDAO.getCarByID(id);

//                    Car carSession = (Car) session.getAttribute("CAR_SESSION");
                    //lay checkin checkout set no roi moi add
                    Date checkIn = (Date) session.getAttribute("CHECK_IN");
                    Date checkOut = (Date) session.getAttribute("CHECK_OUT");

                    Integer amount = (Integer) session.getAttribute("AMOUT");

                    PlusMinus plusMinus = new PlusMinus(id, amount);
                    session.setAttribute("Quantity", plusMinus);
                    
                    long diffDate = checkOut.getTime() - checkIn.getTime();
//                    long numDayLong = TimeUnit.MILLISECONDS.convert(diffDate, TimeUnit.MILLISECONDS);
//                    long noOfDaysBetween = ChronoUnit.DAYS.between(checkIn.getTime(), checkOut.getTime());
//                    int numDay = (int) numDayLong;
                    long date = TimeUnit.DAYS.convert(diffDate, TimeUnit.MILLISECONDS);
                    CarInCart carInCart = new CarInCart(car, amount, checkIn, checkOut, 1 + (int) date);
                    shoppingCart.addCart(carInCart);
                    session.setAttribute("shoppingCart", shoppingCart);
                    url = SUCCESS;
                }
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
