package com.nedellis.selenium;

import org.openqa.selenium.By;

public class TheCEShop {
    // Login Information
    public static final String email = "brentcollinson@gmail.com";
    public static final String password = "hudson";

    // Text Selectors
    public static final By LOC_TXT_EMAIL = By.xpath("//*[@id='middleSection']/div/div[2]/div/form/fieldset/div[2]/section/label/input");
    public static final By LOC_TXT_PASSWORD = By.xpath("//*[@id='middleSection']/div/div[2]/div/form/fieldset/div[3]/section/label/input");

    // Buttons
    public static final By LOC_LNK_CONTINUE = By.xpath("//*[@id='middleSection']/div/div[2]/div/form/fieldset/div[5]/section/input");
}
