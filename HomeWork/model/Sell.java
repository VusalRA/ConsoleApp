package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Sell {

    private int sellId;
    private ArrayList<Product> purchasedProducts;
    private double totalPrice;
    private LocalDate date;

    public Sell() {
    }

    public Sell(int sellId, ArrayList<Product> purchasedProducts, double totalPrice, LocalDate date) {
        this.sellId = sellId;
        this.purchasedProducts = purchasedProducts;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public int getSellId() {
        return sellId;
    }

    public void setSellId(int sellId) {
        this.sellId = sellId;
    }

    public ArrayList<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(ArrayList<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
