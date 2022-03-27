package com.demo.hooks;

import com.demo.uitest.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends TestBase{

    @Before
    public void beforeMethod(){ System.out.println("Before Method"); }

    @After
    public void afterMethod(){ System.out.println("After Method"); }

}
