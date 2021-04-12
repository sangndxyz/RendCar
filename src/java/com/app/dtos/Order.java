/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author nds72
 */
public class Order implements Serializable {

    private String orderID;
    private String userID;
    private Date creatDate;
    private float total;
    private String discountID;


    public Order() {
    }

    public Order(String orderID, String userID, Date creatDate, float total) {
        this.orderID = orderID;
        this.userID = userID;
        this.creatDate = creatDate;
        this.total = total;
    }

    public Order(String orderID, String userID, Date creatDate, float total, String discountID) {
        this.orderID = orderID;
        this.userID = userID;
        this.creatDate = creatDate;
        this.total = total;
        this.discountID = discountID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    

}
