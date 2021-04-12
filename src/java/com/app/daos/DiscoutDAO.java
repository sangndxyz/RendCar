/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.daos;

import com.app.dtos.Discount;
import com.app.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author nds72
 */
public class DiscoutDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public Discount getDiscountById(int id) throws Exception {
        Discount discount = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select discountId,discountCode,expirydate,discountPercent from tblDiscount where discountId=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String discountId = rs.getString("discountId");
                    String discountCode = rs.getString("discountCode");
                    Date expirydate = rs.getDate("expirydate");
                    float discountPercent = rs.getFloat("discountPercent");
                    discount = new Discount(discountId, discountCode, expirydate, discountPercent);
                }
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return discount;

    }

    public Discount getDiscountByCode(String code) throws Exception {
        Discount discount = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select discountId,discountCode,expirydate,discountPercent from tblDiscount where discountCode=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, code);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String discountId = rs.getString("discountId");
                    String discountCode = rs.getString("discountCode");
                    Date expiryDate = rs.getDate("expirydate");
                    float discountPercent = rs.getFloat("discountPercent");
                    discount = new Discount(discountId, discountCode, expiryDate, discountPercent);
                }
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return discount;
    }
}
