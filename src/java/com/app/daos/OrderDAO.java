/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.daos;

import com.app.dtos.Order;
import com.app.dtos.OrderDetail;
import com.app.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nds72
 */
public class OrderDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public String getLastOrderIDByUser() throws Exception {
        String id = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select OrderID from tblOrder where createDate=(select max(createDate) from tblOrder)";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    id = rs.getString("orderID");
                }
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return id;
    }

    public boolean insertOrderDetail(OrderDetail orderDetail) throws Exception {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "insert tblOrderDetail(orderDetailID,orderID,carID,price,quantity,checkin,checkout) values(?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, orderDetail.getOrderDetailID());
                ps.setString(2, orderDetail.getOrderID());
                ps.setString(3, orderDetail.getCarID());
                ps.setFloat(4, orderDetail.getPrice());
                ps.setInt(5, orderDetail.getQuantity());
                ps.setDate(6, new java.sql.Date(orderDetail.getCheckIn().getTime()));
                ps.setDate(7, new java.sql.Date(orderDetail.getCheckOut().getTime()));

                check = ps.executeUpdate() > 0;
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return check;
    }

    public boolean insertOrder(Order order) throws Exception {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "insert tblOrder(orderID,userID,createDate,total,discountId,status) values(?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, order.getOrderID());
                ps.setString(2, order.getUserID());
                ps.setTimestamp(3, new Timestamp(order.getCreatDate().getTime()));
                ps.setFloat(4, order.getTotal());
                ps.setString(5, order.getDiscountID());
                ps.setString(6, "active");
                check = ps.executeUpdate() > 0;
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return check;
    }

//    public boolean insertOrder(Order order) throws Exception {
//        boolean check = false;
//        try {
//            con = DBUtils.getConnection();
//            if (con != null) {
//                String sql = "insert tblOrder(orderID,userID,createDate,checkin,checkout,total) values(?,?,?,?,?,?)";
//                ps = con.prepareStatement(sql);
//                ps.setString(1, order.getOrderID());
//                ps.setString(2, order.getUserID());
//                ps.setTimestamp(3, new Timestamp(order.getCreatDate().getTime()));
//               // ps.setDate(4, new java.sql.Date(order.getCheckIn().getTime()));
//                //ps.setDate(5, new java.sql.Date(order.getCheckOut().getTime()));
//                ps.setFloat(6, order.getTotal());
//                check = ps.executeUpdate() > 0;
//            }
//        } finally {
//            DBUtils.closeConnection(rs, ps, con);
//        }
//        return check;
//    }
//     public boolean insertOrderDetail(OrderDetail orderDetail) throws Exception {
//        boolean check = false;
//        try {
//            con = DBUtils.getConnection();
//            if (con != null) {
//                String sql = "insert tblOrderDetail(orderDetailID,orderID,carID,price,quantity) values(?,?,?,?,?)";
//                ps = con.prepareStatement(sql);
//                ps.setString(1, orderDetail.getOrderDetailID());
//                ps.setString(2, orderDetail.getOrderID());
//                ps.setString(3, orderDetail.getCarID());
//                ps.setFloat(4, orderDetail.getPrice());
//                ps.setInt(5, orderDetail.getQuantity());
//                check = ps.executeUpdate() > 0;
//            }
//        } finally {
//            DBUtils.closeConnection(rs, ps, con);
//        }
//        return check;
//    }
    public boolean insertOrderDetails(List<OrderDetail> orderDetails) throws Exception {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "insert tblOrderDetail(orderDetailID,orderID,carID,price,quantity,checkin,checkout) values(?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);

                for (OrderDetail orderDetail : orderDetails) {
                    ps.setString(1, orderDetail.getOrderDetailID());
                    ps.setString(2, orderDetail.getOrderID());
                    ps.setString(3, orderDetail.getCarID());
                    ps.setFloat(4, orderDetail.getPrice());
                    ps.setInt(5, orderDetail.getQuantity());
                    ps.setDate(6, new java.sql.Date(orderDetail.getCheckIn().getTime()));
                    ps.setDate(7, new java.sql.Date(orderDetail.getCheckOut().getTime()));

                    ps.addBatch();
                }

                check = ps.executeBatch().length > 0;
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }

        return check;
    }

    public boolean updateOrder(String orderID) throws Exception {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update tblOrder set status=? where orderId=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "delete");
                ps.setString(2, orderID);
                check = ps.executeUpdate() > 0;
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return check;
    }

    public List<OrderDetail> getOrderDetailByOrderID(String orderID) throws Exception {
        OrderDetail orderDetail = null;
        List<OrderDetail> listOrderDetails = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select orderDetailID,A.orderID as OrderID ,carID,price,quantity,checkin,checkout from tblOrderDetail A, tblOrder B where A.orderID=B.orderID\n"
                        + "and A.orderID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, orderID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String orderDetailID = rs.getString("orderDetailID");
                    String carId = rs.getString("carID");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    Date checkin = rs.getDate("checkin");
                    Date checkOut = rs.getDate("checkout");
                    orderDetail = new OrderDetail(orderDetailID, orderID, carId, price, quantity, checkin, checkOut);
                    if(listOrderDetails==null){
                        listOrderDetails=new ArrayList<>();
                    }
                    listOrderDetails.add(orderDetail);
                }
            }
        } finally {

        }
        return listOrderDetails;
    }
}
