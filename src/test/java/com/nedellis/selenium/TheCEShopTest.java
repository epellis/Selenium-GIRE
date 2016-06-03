package com.nedellis.selenium;

import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import io.ddavison.conductor.Locomotive;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Config (
        browser = Browser.CHROME,
        url = "http://gire.theceshop.com/account/"
)

public class TheCEShopTest extends Locomotive {

//    @Test
//    public void testLoginExists() {
//        validatePresent(TheCEShop.LOC_TXT_EMAIL);
//        validatePresent(TheCEShop.LOC_TXT_PASSWORD);
//    }

    @Test
    public void testLoginFunctions() {
        setText(TheCEShop.LOC_TXT_EMAIL, TheCEShop.email);
        setText(TheCEShop.LOC_TXT_PASSWORD, TheCEShop.password);
        click(TheCEShop.LOC_LNK_CONTINUE);

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }

}