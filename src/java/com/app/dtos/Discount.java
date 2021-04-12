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
public class Discount {
    private String discountId;
    private String discountCode;
    private Date expiryDate;
    private float discountPercent;

    public Discount() {
    }

    public Discount(String discountId, String discountCode, Date expiryDate, float discountPercent) {
        this.discountId = discountId;
        this.discountCode = discountCode;
        this.expiryDate = expiryDate;
        this.discountPercent = discountPercent;
    }
    

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public float getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        this.discountPercent = discountPercent;
    }
      
}
