/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.controllers;

import com.app.daos.CarDAO;
import com.app.dtos.Car;
import java.io.IOException;
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
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    private static final String SUCCESS = "MainController?action=shopping";
    private static final String ERROR = "MainController?action=shopping";

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
        String url = ERROR;
        try {
//            HttpSession session = request.getSession();
//            String name = (String) session.getAttribute("NAME");
//            String category ="";
////                    (String) session.getAttribute("CATEGORY");
////            int amount = (Integer) session.getAttribute("AMOUT");
//            int amount = 1;
//            Date checkIn = (Date) session.getAttribute("CHECK_IN");
//            Date checkOut = (Date) session.getAttribute("CHECK_OUT");
//            
////            int endPage=
//              CarDAO carDAO=new CarDAO();
//              List<Car> listCar=carDAO.getSearchCar(name,"hon",checkIn,checkOut,amount);
//              request.setAttribute("CAR", listCar);

            int index = 1;
            int pageSize = 5;
            if (request.getParameter("index") != null) {
                index = Integer.parseInt(request.getParameter("index"));
            }

            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("NAME");
            String category = (String) session.getAttribute("CATE");
            int amount = (Integer) session.getAttribute("AMOUT");
            Date checkIn = (Date) session.getAttribute("CHECK_IN");
            Date checkOut = (Date) session.getAttribute("CHECK_OUT");

            CarDAO carDAO = new CarDAO();
            int numberOfCarAvailable = -1;
            List<Car> listCar = null;
            try {
                numberOfCarAvailable = carDAO.checkCarAvailable(name, category, checkIn, checkOut, amount).size();
                listCar = carDAO.getSearchCar(name, category, checkIn, checkOut, amount, pageSize, index);
            } catch (Exception e) {
                numberOfCarAvailable = 0;
            }

            int endPage = numberOfCarAvailable / 5;
            if (numberOfCarAvailable % pageSize != 0) {
                endPage++;
            }
            if (numberOfCarAvailable == 0 && listCar == null) {
                request.setAttribute("NO_RECORD", "No Record to display");
            }
            request.setAttribute("CAR", listCar);
            session.setAttribute("CAR_SESSION", listCar);
            request.setAttribute("endPage", endPage);
            url = SUCCESS;

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
