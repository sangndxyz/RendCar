/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dtos;

import java.io.Serializable;

/**
 *
 * @author nds72
 */
public class Car implements Serializable {

    private String carID;
    private String carName;
    private String color;
    private String year;
    private float price;
    private String image;
    public int quantity;
    private String cateID;
    private int point;

//    private Date checkIn;
//    private Date checkOut;
    public Car() {
    }

    public Car(String carID, String carName, String color, String year, float price, String image, int quantity, String cateID) {
        this.carID = carID;
        this.carName = carName;
        this.color = color;
        this.year = year;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.cateID = cateID;
    }

    public Car(String carID, String carName, String color, String year, float price, String image, int quantity, String cateID, int point) {
        this.carID = carID;
        this.carName = carName;
        this.color = color;
        this.year = year;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.cateID = cateID;
        this.point = point;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }
    

//    public Date getCheckIn() {
//        return checkIn;
//    }
//
//    public void setCheckIn(Date checkIn) {
//        this.checkIn = checkIn;
//    }
//
//    public Date getCheckOut() {
//        return checkOut;
//    }
//
//    public void setCheckOut(Date checkOut) {
//        this.checkOut = checkOut;
//    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
