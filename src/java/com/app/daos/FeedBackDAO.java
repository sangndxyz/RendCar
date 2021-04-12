/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.daos;

import com.app.dtos.FeedBack;
import com.app.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author nds72
 */
public class FeedBackDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public boolean insertFeedBack(FeedBack feedBack) throws Exception {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "insert into  tblFeedback(point,comment,userID,carID) values(?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setInt(1, feedBack.getPoint());
                ps.setString(2, feedBack.getComment());
                ps.setString(3, feedBack.getUserID());
                ps.setString(4, feedBack.getCarID());
                check = ps.executeUpdate() > 0;
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return check;
    }

    public boolean checkFeedBack(String userID, String carID) throws Exception {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select feedBackID from tblFeedback where userID=? and carID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, carID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    check = rs.getInt("feedBackID") > 0;
                }
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return check;
    }

    public boolean updateFeedBack(FeedBack feedBack) throws Exception {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update tblFeedback set point=?, comment=? where userID=? and carID=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, feedBack.getPoint());
                ps.setString(2, feedBack.getComment());
                ps.setString(3, feedBack.getUserID());
                ps.setString(4, feedBack.getCarID());
                check = ps.executeUpdate() > 0;
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return check;
    }

    public int avgFeedBack(String carID) throws Exception {
        int count = -1;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select avg(point) as point,count(feedBackID) as numberFeedBack from "
                        + "  tblFeedback A, tblCars B where A.carID=B.carID and A.carID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, carID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("point");
                }
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return count;
    }

}
