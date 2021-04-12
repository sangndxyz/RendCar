/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.daos;

import com.app.dtos.Car;
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
public class CarDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public Car getCarByID(String id) throws Exception {
        Car car = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select carID,carName,color,year,price,image,quantity,cateid from tblCars where carid=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String carName = rs.getString("carName");
                    String color = rs.getString("color");
                    String year = rs.getString("year");
                    float price = rs.getFloat("price");
                    String image = rs.getString("image");
                    int quantity = rs.getInt("quantity");
                    String cateID = rs.getString("cateid");
                    car = new Car(id, carName, color, year, price, image, quantity, cateID);
                }
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return car;
    }
    //COUNT CAR AVAILABLE TO REND CAR

    public List<Car> checkCarAvailable(String name, String category, Date checkIn, Date checkOut, int amount) throws Exception {
        List<Car> listCar = null;
        Car car = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " SELECT carID,carName,color,year,price,image,cateid,"
                        + " quantity=quantity-( isnull(((SELECT sum( C.quantity) from tblorder B, tblOrderdetail C "
                        + " where B.orderID=C.orderID and a.carID=c.carID and not((c.checkin > ? ) or"
                        + " (c.checkout < ?)) and B.status='active')),0)) from tblCars A where  (A.carName  like ? and A.quantity >=?) or"
                        + " (A.cateID=? and A.quantity >=?)";

                ps = con.prepareStatement(sql);
                ps.setDate(1, new java.sql.Date(checkOut.getTime()));
                ps.setDate(2, new java.sql.Date(checkIn.getTime()));
                ps.setString(3, "%" + name + "%");
                ps.setInt(4, amount);
                ps.setString(5, category);
                ps.setInt(6, amount);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("carID");
                    String carName = rs.getString("carName");
                    String color = rs.getString("color");
                    String year = rs.getString("year");
                    float price = rs.getFloat("price");
                    String image = rs.getString("image");
                    int quantity = rs.getInt("quantity"); //so luong xe con cho thue
                    String cateID = rs.getString("cateid");
                    if (listCar == null) {
                        listCar = new ArrayList<>();
                    }
                    if (quantity < amount) {
//                        listCar = null;
//                        break;
                        continue;
                    }
                    car = new Car(id, carName, color, year, price, image, quantity, cateID);
                    listCar.add(car);
                }
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return listCar;
    }

    public List<Car> getSearchCar(String name, String category, Date checkIn, Date checkOut, int amount, int pageSize, int index) throws Exception {
        List<Car> listCar = null;
        Car car = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " SELECT carID,carName,color,year,price,image,cateid, "
                        + " quantity=quantity-( isnull(((SELECT sum( C.quantity) from tblorder B, tblOrderdetail C "
                        + " where B.orderID=C.orderID and a.carID=c.carID and not((c.checkin > ? ) or"
                        + " (c.checkout < ?)) and B.status='active' )),0)) from tblCars A where  (A.carName  like ? and A.quantity >=?) or"
                        + " (A.cateID=? and A.quantity >=?) order by carID offset ?* (?-1) rows fetch next ? row only ";

                ps = con.prepareStatement(sql);
                ps.setDate(1, new java.sql.Date(checkOut.getTime()));
                ps.setDate(2, new java.sql.Date(checkIn.getTime()));
                ps.setString(3, "%" + name + "%");
                ps.setInt(4, amount);
                ps.setString(5, category);
                ps.setInt(6, amount);
                ps.setInt(7, pageSize);
                ps.setInt(8, index);
                ps.setInt(9, pageSize);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("carID");
                    String carName = rs.getString("carName");
                    String color = rs.getString("color");
                    String year = rs.getString("year");
                    float price = rs.getFloat("price");
                    String image = rs.getString("image");
                    int quantity = rs.getInt("quantity");
                    String cateID = rs.getString("cateid");
                    FeedBackDAO feedBackDAO = new FeedBackDAO();
                    int point = feedBackDAO.avgFeedBack(id);
                    if (listCar == null) {
                        listCar = new ArrayList<>();
                    }
                    if (quantity < amount) {
//                        listCar = null;
//                        break;
                        continue;
                    }
                    car = new Car(id, carName, color, year, price, image, quantity, cateID, point);
                    listCar.add(car);
                }
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return listCar;
    }
    
    
    

}
