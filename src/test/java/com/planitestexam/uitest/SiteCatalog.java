package com.planitestexam.uitest;

import java.util.ArrayList;
import java.util.List;

public class SiteCatalog {

    ArrayList<String> site =new ArrayList<String>();

    public void addSite(String url) {
        site.add(url);
        System.out.println("added site " + url);
    }
}
