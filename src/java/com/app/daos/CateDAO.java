/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.daos;

import com.app.dtos.Category;
import com.app.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nds72
 */
public class CateDAO {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<Category> getAllCategory() throws Exception {
        List<Category> result = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "select cateid, catename from tblCategory";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String cateID = rs.getString("cateid");
                    String cateName = rs.getString("catename");
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new Category(cateID, cateName));
                }
            }
        } finally {
            DBUtils.closeConnection(rs, ps, con);
        }
        return result;
    }
}
