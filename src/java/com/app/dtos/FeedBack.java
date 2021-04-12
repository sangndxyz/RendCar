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
public class FeedBack implements Serializable {

    private String feedBackID;
    private int point;
    private String comment;
    private String userID;
    private String carID;

    public FeedBack() {
    }
    
    public FeedBack(int point, String comment, String userID, String carID) {
        this.point = point;
        this.comment = comment;
        this.userID = userID;
        this.carID = carID;
    }

    public FeedBack(String feedBackID, int point, String comment, String userID, String carID) {
        this.feedBackID = feedBackID;
        this.point = point;
        this.comment = comment;
        this.userID = userID;
        this.carID = carID;
    }

    public String getFeedBackID() {
        return feedBackID;
    }

    public void setFeedBackID(String feedBackID) {
        this.feedBackID = feedBackID;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

}
