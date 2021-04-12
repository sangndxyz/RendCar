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
public class CreateAccourError implements Serializable {

    private String userIDError;
    private String fullNameError;
    private String passwordError;
    private String confirmError;
    private String addressError;
    private String phoneError;

    public CreateAccourError() {
    }

    public CreateAccourError(String userIDError, String fullNameError, String passwordError, String confirmError) {
        this.userIDError = userIDError;
        this.fullNameError = fullNameError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }
    

}
