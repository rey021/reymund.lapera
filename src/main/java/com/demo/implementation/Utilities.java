package com.demo.implementation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    public BigDecimal getTotalCostToBigDecimal(ShoppingCart cart){
        BigDecimal totalCostToBigDecimal = new BigDecimal(Double.toString(cart.getTotalCost()));
        totalCostToBigDecimal = totalCostToBigDecimal.setScale(2, RoundingMode.HALF_UP);

        return totalCostToBigDecimal;
    }

    public String regex(String value, String regex)
    {
        Pattern p = Pattern.compile(regex);//. represents single character
        Matcher m = p.matcher(value);
        String result = "";

        if (m.find()){
            return m.group(0);
        }
        return null;
    } // End of method regex

    public boolean checkIfOneDecimal(String totalCost) {

        boolean status = false;
        int indexOfDecimal = totalCost.indexOf(".")+1;

        if(((totalCost.length() - indexOfDecimal)) == 1) {
            status = true;
        }
        return status;
    } // End of checkIfOneDecimal

}
