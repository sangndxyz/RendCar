/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.daos;

import com.app.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author nds72
 */
public class PaymentDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public boolean insertPayment(String address, String phone, String orderID) throws Exception {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "insert tblPayment(address,phone,orderID) values(?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, address);
                ps.setString(2, phone);
                ps.setString(3, orderID);
                check = ps.executeUpdate() > 0;
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return check;
    }
}
