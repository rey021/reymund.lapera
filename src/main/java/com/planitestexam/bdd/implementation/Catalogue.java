package com.planitestexam.bdd.implementation;

import java.util.HashMap;
import java.util.Map;

public class Catalogue {

    public final static int SHIPPING_RATE = 5;

/*
    // Java 9 and later
    private static Map<String, Product> productMap = Map.of(
            "Electric Toothbrush", new PhysicalProduct("Electric Toothbrush", 5000, 400),
            "Baby Alarm", new PhysicalProduct("Baby Alarm", 5000, 500),
            "War and Peace (e-book)", new DigitalProduct("War and Peace (e-book)", 1000)
    );
*/

    // Java 8 and earlier
    public static Map<String,Product> productMap = new HashMap<>();
    //static {
     //   productMap.put("Electric Toothbrush", new PhysicalProduct("Electric Toothbrush", 3000));
      //  productMap.put("Baby Alarm", new PhysicalProduct("Baby Alarm", 5000);
     //   productMap.put("War and Peace (e-book)", new DigitalProduct("War and Peace (e-book)", 1000));
   // }

    public static Product getProduct(String productName) {
        return productMap.get(productName);
    }

    public static void setProduct(String item, Product product){
        productMap.put(item, product);
    }
}
