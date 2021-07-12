package com.planitestexam.bdd.implementation;

import java.util.HashMap;
import java.util.Map;

public class Catalogue {

    public final static int SHIPPING_RATE = 5;

    public static Map<String,Product> productMap = new HashMap<>();

    public static Product getProduct(String productName) {
        return productMap.get(productName);
    }

    public static void setProduct(String item, Product product){
        productMap.put(item, product);
    }
}
