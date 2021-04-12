/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dtos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author nds72
 */
public class Cart implements Serializable {

    private String customerName;
//    private HashMap<String, CarInCart> shoppingCart;
    private ArrayList<CarInCart> shoppingCart;

    public Cart(String custommerName) {
        this.customerName = custommerName;
        this.shoppingCart = new ArrayList<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<CarInCart> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ArrayList<CarInCart> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

//    public HashMap<String, CarInCart> getShoppingCart() {
//        return shoppingCart;
//    }
//
//    public void setShoppingCart(HashMap<String, CarInCart> shoppingCart) {
//        this.shoppingCart = shoppingCart;
//    }
    public void addCart(CarInCart car) throws Exception {
//        if (this.shoppingCart.containsKey(car.getCarID())) {
//            int quantity = this.shoppingCart.get(car.getCarID()).getQuantity() + 1;
//            car.setQuantity(quantity);
//        }
//        this.shoppingCart.put(car.getCarID(), car);
//        if (shoppingCart.contains(car)) {
//            CarInCart carInCart = shoppingCart.get(shoppingCart.indexOf(car));
//            carInCart.setQuantity(carInCart.getQuantity() + car.getQuantity());
//        } else {
//            shoppingCart.add(car);
//        }
//carInCart.getCheckIn().compareTo(car.getCheckIn())) && (carInCart.getCheckOut() == car.getCheckOut())
        for (int i = 0; i < shoppingCart.size(); i++) {
            CarInCart carInCart = shoppingCart.get(i);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String d1CheckIn = simpleDateFormat.format(carInCart.getCheckIn());
            String d2CheckIn = simpleDateFormat.format(car.getCheckIn());
            String d1CheckOut = simpleDateFormat.format(carInCart.getCheckOut());
            String d2CheckOut = simpleDateFormat.format(car.getCheckOut());
            if (carInCart.getCarID().equals(car.getCarID()) && (d1CheckIn.equals(d2CheckIn)) && (d1CheckOut.equals(d2CheckOut))) {
                carInCart.quantity += car.quantity;
//                System.out.println(carInCart.getCheckIn());
                return;
            }
        }
        shoppingCart.add(car);

    }

    public void delete(int index) throws Exception {
//    public void delete(String id, String index) throws Exception {
//        if (this.shoppingCart.containsKey(id)) {
//            this.shoppingCart.remove(id);
//        }
//        for (int i = 0; i < shoppingCart.size(); i++) {
//            CarInCart carInCart = shoppingCart.get(i);
//            if (carInCart.getCarID() == carInCart.getCarID()) {
//                shoppingCart.remove(carInCart);
//            }
//        }
        for (int i = 0; i < shoppingCart.size(); i++) {
//            CarInCart carInCart = shoppingCart.get(i);
//            CarInCart carInCartt = shoppingCart.get(index);
//            if (carInCart.getCarID() == carInCart.getCarID()) {
            if (index == i) {
                shoppingCart.remove(index);
            }

//            }
        }
    }

//    public void update(String id, int quantity) throws Exception {
////        if (this.shoppingCart.containsKey(id)) {
////            this.shoppingCart.get(id).setQuantity(quantity);
////        }
//        CarInCart car = null;
//
//        for (int i = 0; i < shoppingCart.size(); i++) {
//            CarInCart carInCart = shoppingCart.get(i);
////            if (carInCart.getCarID().equals(id)&& (carInCart.getCheckIn() == car.getCheckIn()) && (carInCart.getCheckOut() == car.getCheckOut())) {
//////                carInCart.quantity += car.quantity;
////                return;
////            }
//            if (carInCart.getCarID().equals(id)) {
//
//            }
//        }
//    }
    public void update(int index, int quantity) throws Exception {
//        if (this.shoppingCart.containsKey(id)) {
//            this.shoppingCart.get(id).setQuantity(quantity);
//        }
//        CarInCart car = null;

        for (int i = 0; i < shoppingCart.size(); i++) {
//            CarInCart carInCart = shoppingCart.get(i);
//            if (carInCart.getCarID().equals(id)&& (carInCart.getCheckIn() == car.getCheckIn()) 
//                    && (carInCart.getCheckOut() == car.getCheckOut())) {
//                carInCart.quantity += car.quantity;
//                return;
//            }
//        shoppingCart.get(index).setQuantity(quantity);
            if (index == i) {
                shoppingCart.get(index).setQuantity(quantity);
            }

        }
    }

//    public float getTotal(float discountPercent) {
//        float total = 0;
//        for (Car car : this.shoppingCart.values()) {
//            total += car.getPrice() * car.getQuantity();
//        }
//        return total - (total*discountPercent);
//    }
//    public float getTotal(float discountPercent) {
//        float total = 0;
////        for (CarInCart car : this.shoppingCart.values()) {
////            total += car.getPrice() * car.getQuantity() * car.getNumDay();
////        }
//        return total - (total * discountPercent);
//    }
    public float getTotal(float discountPercent) {
        float total = 0;
        for (CarInCart car : this.shoppingCart) {
            total += car.getPrice() * car.getQuantity() * car.getNumDay();
        }
        return total - (total * discountPercent);
    }
}
