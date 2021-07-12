package com.planitestexam.bdd.implementation;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public abstract class Product {

    private String name;
    private float price;
    private int productDiscount;

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setProductDiscount(int productDiscount) {
        this.productDiscount = productDiscount;
    }

    @Override
    public String toString() {
        return "Product{" +
              "name='" + name + '\'' +
              ", price=" + price +
              ", discount=" + productDiscount +
              '}';
    }

    public abstract int calculateShippingCost();
}
