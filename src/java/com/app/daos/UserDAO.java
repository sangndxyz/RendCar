/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.daos;

import com.app.dtos.Account;
import com.app.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author nds72
 */
public class UserDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public boolean checkEmail(String email) throws Exception {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select fullName from tblUser where userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return check;
    }

    public Account checkAccount(String email, String password) throws Exception {
        Account account = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select userID, fullName, roleId,status,createDate,address,phone from tblUser where userID=? and password=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleId");
                    String status = rs.getString("status");
                    Date createDate = rs.getDate("createDate");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    account = new Account(email, fullName, roleID);
                    account.setStatus(status);
                    account.setCreateDate(createDate);
                    account.setPhone(phone);
                    account.setAddress(address);
                }
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return account;
    }

    //CREATE ACCOUNT FUNCION 3
    public boolean createAccount(Account account) throws Exception {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "insert into tblUser(userID,fullName,password,roleID,status,createDate,address,phone) values(?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, account.getUserID());
                ps.setString(2, account.getFullName());
                ps.setString(3, account.getPassword());
                ps.setString(4, account.getRolID());
                ps.setString(5, account.getStatus());
                ps.setTimestamp(6, new Timestamp(account.getCreateDate().getTime()));
                ps.setString(7, account.getAddress());
                ps.setString(8, account.getPhone());
                check = ps.executeUpdate() > 0;
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return check;
    }

    //UPDATE STATUS FUNCION 3
    public boolean updateStatus(String userID) throws Exception {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "update tblUser set status=? where userID=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "Active");
                ps.setString(2, userID);
                check = ps.executeUpdate() > 0;
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return check;
    }

}
