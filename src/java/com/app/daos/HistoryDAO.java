/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.daos;

import com.app.dtos.Discount;
import com.app.dtos.OrderHistory;
import com.app.dtos.OrderHistoryDetail;
import com.app.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nds72
 */
public class HistoryDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

//    public List<OrderHistory> getHistoryOrder(String userID) throws Exception {
//        List<OrderHistory> listOrder = null;
//        OrderHistory orderHistory = null;
//        try {
//            con = DBUtils.getConnection();
//            if (con != null) {
//                String sql = "select orderID,convert(varchar,createDate,105)as date,total, discountId from tblOrder where "
//                        + "userID=? order by createDate asc";
//                
//               
//                ps = con.prepareStatement(sql);
//                ps.setString(1, userID);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    String orderID = rs.getString("orderID");
//                    String date = rs.getString("date");
//                    float total = rs.getFloat("total");
//                    if (listOrder == null) {
//                        listOrder = new ArrayList<>();
//                    }
//                    int discountId=rs.getInt("discountId");
//                    
//                    //lay discountId tu resultSet
//                     DiscoutDAO discoutDAO = new DiscoutDAO();
//                     Discount discount = discoutDAO.getDiscountById(discountId);
//                
//                    orderHistory = new OrderHistory(orderID, userID, date, total - (total * discount.getDiscountPercent()));
//                    listOrder.add(orderHistory);
//                }
//            }
//        } finally {
//            DBUtils.closeConnection(rs, ps, con);
//        }
//        return listOrder;
//    }
    public List<OrderHistory> getHistoryOrder(String userID, String carName, Date orderDate) throws Exception {
        List<OrderHistory> listOrder = null;
        OrderHistory orderHistory = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select DISTINCT  A.orderID as orderID ,createDate, total,A.status as status,discountId from tblOrder A ,tblOrderDetail B, tblCars C where  A.userID=? "
                        + "and (  (C.carName like ?) or(datediff (day,?,createDate)=0)) and A.orderID=B.orderID and B.carID=C.carID "
                        + "order by createDate asc";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, "%" + carName + "%");
                ps.setDate(3, new java.sql.Date(orderDate.getTime()));
                rs = ps.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    Date date = rs.getDate("createDate");
                    float total = rs.getFloat("total");
                    String status = rs.getString("status");
                    if (listOrder == null) {
                        listOrder = new ArrayList<>();
                    }
                    try {
                        int discountId = rs.getInt("discountId");

                        //lay discountId tu resultSet
                        DiscoutDAO discoutDAO = new DiscoutDAO();
                        Discount discount = discoutDAO.getDiscountById(discountId);
                        orderHistory = new OrderHistory(orderID, userID, date, total - (total * discount.getDiscountPercent()));
                        orderHistory.setStatus(status);
                        listOrder.add(orderHistory);
                    } catch (Exception e) {
                        orderHistory = new OrderHistory(orderID, userID, date, total);
                        orderHistory.setStatus(status);
                        listOrder.add(orderHistory);
                    }

                }
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return listOrder;
    }

    public List<OrderHistoryDetail> getHistoryOrderDetail(String orderID) throws Exception {
        List<OrderHistoryDetail> listOrderDetail = null;
        OrderHistoryDetail orderHistoryDetail = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " select A.orderDetailID as orderDetailID, A.carID as carID, B.carName as carName ,"
                        + " A.price as price,A.quantity as quantity, checkIn ,"
                        + " checkOut "
                        + " from tblOrderDetail A, tblCars B   where   A.carId=B.carID and \n"
                        + " A.orderID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, orderID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String orderDetailID = rs.getString("orderDetailID");
                    String carID = rs.getString("carID");
                    String carName = rs.getString("carName");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date checkIn = rs.getDate("checkIn");
                    Date checkOut = rs.getDate("checkOut");
                    if (listOrderDetail == null) {
                        listOrderDetail = new ArrayList<>();
                    }
                    orderHistoryDetail = new OrderHistoryDetail(orderDetailID, orderID, carID, carName, price, quantity, checkIn, checkOut);
                    listOrderDetail.add(orderHistoryDetail);
                }
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return listOrderDetail;
    }
}
