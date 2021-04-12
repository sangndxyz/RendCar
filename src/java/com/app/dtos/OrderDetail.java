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
public class OrderDetail implements Serializable{
    private String orderDetailID;
    private String orderID;
    private String carID;
    private float price;
    private int quantity;
    private Date checkIn;
    private Date checkOut;

    public OrderDetail() {
    }

    public OrderDetail(String orderDetailID, String orderID, String carID, float price, int quantity, Date checkIn, Date checkOut) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.carID = carID;
        this.price = price;
        this.quantity = quantity;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    
    
}
