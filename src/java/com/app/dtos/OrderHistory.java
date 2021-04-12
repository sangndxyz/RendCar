/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dtos;

import java.util.Date;

/**
 *
 * @author nds72
 */
public class OrderHistory {

    private String orderID;
    private String userID;
    private Date creatDate;
    private float total;
    private String status;

    public OrderHistory() {
    }

    public OrderHistory(String orderID, String userID, Date creatDate, float total) {
        this.orderID = orderID;
        this.userID = userID;
        this.creatDate = creatDate;
        this.total = total;
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

//    public String getCreatDate() {
//        String patter = "dd/MM/yyyy";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patter);
//        String date = simpleDateFormat.format(creatDate);
//        return date;
//    }

//    public void setCreatDate(String creatDate) {
//
//    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

}
