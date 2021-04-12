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
public class CarInCart extends Car {

    private Date checkIn;
    private Date checkOut;
    private int numDay;

    public CarInCart(Car car, int quantity, Date checkIn, Date checkOut, int numDay) {
        super(car.getCarID(), car.getCarName(), car.getColor(), car.getYear(), car.getPrice(), car.getImage(), quantity, car.getCateID());

        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.quantity = quantity;
        this.numDay = numDay;
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

    public int getNumDay() {
//        long diffDate = checkOut.getTime() - checkIn.getTime();
//        numDay = TimeUnit.DAYS.convert(diffDate, TimeUnit.MILLISECONDS);
        return numDay;
    }

    public void setNumDay(int numDay) {
        this.numDay = numDay;
    }
}
