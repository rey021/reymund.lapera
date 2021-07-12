package com.planitestexam.bdd.implementation;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class LineItem {

    private final Product product;
    private final int quantity;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public LineItem(LineItem li) {
        this(li.product, li.quantity);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return  product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "\n\t" + "LineItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", price=" + getPrice() +
                '}';
    }
}
