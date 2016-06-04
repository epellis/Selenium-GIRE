package com.nedellis.selenium;

import org.openqa.selenium.By;

public class MyEasyTrack {

    // Links
    public static final By LOC_LNK_LATESTCOURSE = By.partialLinkText("Go to last page you viewed.");
    public static final By LOC_LNK_GONEXT = By.xpath("//*[@class='footer-hook']/div/div/div/img[2]");
    public static final By LOC_LNK_CHECKANSWER = By.xpath("//*[@type='submit']");
    public static final By LOC_LNK_TRYAGAIN = By.xpath("//*[@id='activity']/div[2]/a");
    public static final By LOC_LNK_TRYHEREAGAIN = By.partialLinkText("Click here to try again");
    public static final By LOC_LNK_BEGINEXAM = By.xpath("//*[@class='slide-content']/p[7]/input");
    public static final By LOC_LNK_NEXTEXAM = By.xpath("//*[@name='next']");
    public static final By LOC_LNK_EXAMSUBMIT = By.xpath("//*[@name='manualSubmit']");
    public static final By LOC_LNK_CONTINUECOURSE = By.xpath("//*[@value='Continue Course']");
    public static final By LOC_LNK_RETRYEXAM = By.xpath("//*[@class='slide-content']/p[4]/input[1]");
    public static final By LOC_LNK_RETAKEFINALEXAM = By.xpath("//*[@class='slide-content']/p[3]/input[1]");

    // Buttons
    public static final By LOC_CLK_FIRSTOPTION = By.xpath("//*[@value='1']");

    // Special text workaround, will make better when it breaks in the next few minutes
    // Note: hasnt broken yet, still functional after 18 hours, will keep you readers posted
    // Looks like we are making methods now. Shame.
    public static final String LOC_CLK_OPTIONXPATH = "//*[@value='";

    // Text
    public static final By LOC_TXT_COMPLETE = By.xpath("//*[@class='footer-hook']/div/div/div/div");
    public static final By LOC_TXT_EXAMQUESTION = By.xpath("//*[@class='activity-question']");
    public static final By LOC_TEXT_EXAMNUMBER = By.xpath("//*[@class='slide-content']/p[2]/font/strong");
    public static final By LOC_TXT_EXAMPERCENTAGE = By.xpath("//*[@class='col eight-col']/div/h2");
    public static final By LOC_TXT_WRONGANSWER = By.xpath("//*[@src='/images/slide_content/incorrect.png']");
    public static final By LOC_TXT_RIGHTANSWER = By.xpath("//*[@src='/images/slide_content/correct.png']");

    public static By getExamLink(int count) {

        return By.xpath("(//*[@class='row lesson exam']/li/div/div/a)[" + count + "]");
    }

    public static By getExamOptionLink(int count){
        String examLink;

        examLink = "//*[@class='activity-answers']/li[" + count + "]/input";

        return By.xpath(examLink);
    }

    public static By getQuizOptionLink(int index, int count) {
        String link = "(//*[@name='a" + index + "\'])[" + count + "]";

        return By.xpath(link);
    }

    public static By getCorrectAnswerText(int index) {
        String link = "//*[@class='data-table']/tbody/tr[" + index + "]/td/div/font";

        return By.xpath(link);
    }

}
