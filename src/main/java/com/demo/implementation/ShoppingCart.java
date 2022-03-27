package com.demo.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ShoppingCart {
    private List<LineItem> lineItems = new ArrayList<>();

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public List<LineItem> getLineItems() {
        return lineItems.stream()
                .map(LineItem::new)
                .collect(Collectors.toList());
    }

    public double getTotalCost() {
        return lineItems.stream()
                .mapToDouble(LineItem::getPrice)
                .sum();
    }


    @Override
    public String toString() {
        return "ShoppingCart{" +
                "lineItems=" + lineItems +
                '}';
    }
}
