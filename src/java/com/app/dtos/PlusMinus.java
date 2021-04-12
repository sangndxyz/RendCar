/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dtos;

/**
 *
 * @author nds72
 */
public class PlusMinus {
   private String carID;
   private int quantity;

    public PlusMinus(String carID, int quantity) {
        this.carID = carID;
        this.quantity = quantity;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
   
}
