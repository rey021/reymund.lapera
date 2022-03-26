package com.planitestexam.bdd.pages;

import com.planitestexam.bdd.implementation.Product;
import com.planitestexam.bdd.implementation.ShoppingCart;
import com.planitestexam.bdd.uitest.BrowserActions;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static org.testng.Assert.assertEquals;


public class PagePlanitCart {

    private boolean status = false;

    public boolean calculateCart(ShoppingCart cart, Map<String,String> subTotalMapToXpath){
        int count = 1;
        cart.getLineItems().stream().forEach(
                items -> {

                    Product item = items.getProduct();
                    int counter = 1 ;
                    for (int x = counter; x < cart.getLineItems().size()+1; x++){
                        String displayItem = BrowserActions.getText(By.xpath("/html/body/div[2]/div/form/table/tbody/tr["
                                + counter + "]/td[1]"));

                        if (displayItem.equals(item.getName())){

                            String subTotalDisplay= BrowserActions.getText(By.xpath(subTotalMapToXpath.get("subTotalLine" + count)));

                            BigDecimal subTotalToBigDecimal = new BigDecimal(Double.toString(items.getPrice()));
                            subTotalToBigDecimal = subTotalToBigDecimal.setScale(2, RoundingMode.HALF_UP);

                            status = ((!subTotalDisplay.equals("$"+subTotalToBigDecimal)) ? false : true);

                            //assertEquals(subTotalDisplay,"$"+subTotalToBigDecimal); // Validate subtotal of each lineItem

                            break;
                        }
                    }
                }
        );
        return status;
    }
}
