package com.planitestexam.bdd.implementation;

public class PhysicalProduct extends Product {

   //private int weight;

   public PhysicalProduct(String name, float price /*, int weight */) {
      super(name, price);
      //this.weight = weight;
   }

   @Override
   public int calculateShippingCost() {
      return 0;
   }

   // @Override
  // public int calculateShippingCost() {
  //     return Catalogue.SHIPPING_RATE * weight;
  // }
}
