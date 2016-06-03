/* What might be the shittiest java code ever written!!! */

package com.nedellis.selenium;

import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import io.ddavison.conductor.Locomotive;

import org.junit.Test;

import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import java.util.Set;

@Config(
        browser = Browser.CHROME,
        url = "http://gire.theceshop.com/account/"
)


/**
 * Main runner class for selenium-gire
 *
 * TODO: Make individual methods for each data type
 * TODO: Delete all testing and debugging methods
 * TODO: Optimize guessing algorithm
 * TODO: Randomize guessing algorithm
 */

public class RunnerTest extends Locomotive {

    private void loginToLatestCourse() {

        // ToDo: Close the overview window when focus is shifted to the child window

        /* Login to The CE Shop */
        setText(TheCEShop.LOC_TXT_EMAIL, TheCEShop.email);
        setText(TheCEShop.LOC_TXT_PASSWORD, TheCEShop.password);
        click(TheCEShop.LOC_LNK_CONTINUE);

        /* Wait a maxiumum of 10 seconds for the page to load */
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        System.out.println("Currently on window: ");
        System.out.println(driver.getWindowHandle());

//        /* Navigate to the started courses tab */
//        click(Account.LOC_LNK_STARTEDCOURSES);

        /* Open the first unfinished course */
        System.out.println(isPresent(Account.LOC_LNK_OPENCOURSE));
        click(Account.LOC_LNK_OPENCOURSE);

        /* Wait a maxiumum of 10 seconds for the page to load */
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        System.out.print("Currently on window: ");
        System.out.println(driver.getWindowHandle());

        try {
            Thread.sleep(5000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        /* Switch to the child window */
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }

//        System.out.println(Arrays.toString(handles.toArray()));

        System.out.print("Currently on window: ");
        System.out.println(driver.getWindowHandle());

        /* Wait a maxiumum of 10 seconds for the page to load */
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        /* Open the latest assignment page */
//        if (isPresent(MyEasyTrack.LOC_LNK_LATESTCOURSE)) {
//            click(MyEasyTrack.LOC_LNK_LATESTCOURSE);
//        }

        /* Wait a maxiumum of 10 seconds for the page to load */
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

//        /* Wait a maxiumum of 10 seconds for the page to load */
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        System.out.println();

//        try {
//            Thread.sleep(60000);                 //1000 milliseconds is one second.
//        } catch (InterruptedException ex) {
//            Thread.currentThread().interrupt();
//        }
    }

    @Test
    public void webdriverRunner() {

        loginToLatestCourse();

        int examCount = 1;

        try {
            Thread.sleep(5000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        /* While loop designed to complete an entire course while the link still exists */
        while (isPresent(MyEasyTrack.LOC_LNK_GONEXT) || isPresent(MyEasyTrack.LOC_LNK_LATESTCOURSE) || isPresent(MyEasyTrack.getExamLink(examCount))) {

            try {
                Thread.sleep(5000);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            /* Contingency plan for a reading exam */
            /* Index for question */
            if (isPresent((MyEasyTrack.getExamLink(examCount)))) {
                click(MyEasyTrack.getExamLink(examCount));
                completeExam();
                examCount++;
            }

            if (isPresent(MyEasyTrack.LOC_LNK_LATESTCOURSE)) {
                click(MyEasyTrack.LOC_LNK_LATESTCOURSE);
            }

            /* Randomly generate a number between 0 and 10000 */
            int randomTime = (int) (Math.random() * 30000);

            System.out.println(getText(MyEasyTrack.LOC_TXT_COMPLETE));
            System.out.print("Waiting for a total of ");
            System.out.print(90 + randomTime / 1000);
            System.out.println(" seconds");

            /* Contingency plan for multiple choice */
            if (isPresent(MyEasyTrack.LOC_CLK_FIRSTOPTION)) {
                int answerIndex = 1;

                System.out.println("Attempting to answer multiple choice question...");
                System.out.println();

                while ((isPresent(By.xpath(MyEasyTrack.LOC_CLK_OPTIONXPATH + answerIndex + "\']"))) && answerIndex <= 10) {

                    System.out.println("Attempting option: " + answerIndex);
                    click(By.xpath(MyEasyTrack.LOC_CLK_OPTIONXPATH + answerIndex + "\']"));
                    click(MyEasyTrack.LOC_LNK_CHECKANSWER);

                    /* Wait a maxiumum of 10 seconds for the page to load */
                    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

                    if (isPresent(MyEasyTrack.LOC_LNK_TRYAGAIN)) {
                        answerIndex++;
                        click(MyEasyTrack.LOC_LNK_TRYAGAIN);
                    } else {
                        System.out.println("Answer correct, continuing...");
                    }

                }

            }

            /* Wait for 3500 +/- 5000 milliseconds (5 Seconds) */
            try {
                Thread.sleep(90000 + randomTime);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            /* Advance to the next page */
            click(MyEasyTrack.LOC_LNK_GONEXT);

            System.out.println();

        }

        System.out.println(isPresent(MyEasyTrack.LOC_LNK_GONEXT));
        System.out.println(isPresent(MyEasyTrack.LOC_LNK_LATESTCOURSE));

    }

    private void completeExam() {

            /* Index for question */
        int questionIndex = 1;

            /* Amount of questions in this exam */
        int maxQuestions = getNumberOfQuestions(MyEasyTrack.LOC_TEXT_EXAMNUMBER);
        System.out.println("This quiz has " + maxQuestions + " questions(s)");

            /* Click on the first exam button */
        click(MyEasyTrack.LOC_LNK_BEGINEXAM);

        while (isPresent(MyEasyTrack.getExamOptionLink(1))) {

            System.out.print("Answering question: " + questionIndex + " ");

            int substringIndex = getText(MyEasyTrack.LOC_TXT_EXAMQUESTION).indexOf(32) + 1;

                /* Grab the first 30 characters of the question */
            String questionText = getText(MyEasyTrack.LOC_TXT_EXAMQUESTION).substring(substringIndex);

            ExamQuestion newQuestion = new ExamQuestion(questionText.trim());

                /* Search the exam bank for an identical question, if none exists, add the question to the database. */
            if (!newQuestion.questionExists(newQuestion.getQuestion())) {
                System.out.print("Question not found, adding: ");
                System.out.println("Question is: " + newQuestion.getQuestion());
                newQuestion.addQuestion(newQuestion);
            }
                /* Otherwise get the question from the database */
            else {
                System.out.println("Question found, grabbing value...");
                newQuestion = newQuestion.getSimilar(newQuestion.getQuestion().trim());
            }

                /* Click on the best available option */
            click(MyEasyTrack.getExamOptionLink(newQuestion.getAnswer()));

            try {
                Thread.sleep(5000);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            questionIndex++;

            if (isPresent(MyEasyTrack.LOC_LNK_EXAMSUBMIT) && questionIndex > maxQuestions) {

                    /* Submit the exam */
                click(MyEasyTrack.LOC_LNK_EXAMSUBMIT);
                System.out.println("Submitting Exam");

                    /* Wait a maxiumum of 10 seconds for the page to load */
                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

                if (getPercentage(MyEasyTrack.LOC_TXT_EXAMPERCENTAGE) >= 70) {
                    System.out.println("Exam sucessfully completed");

                        /* Continue the course */
                    click(MyEasyTrack.LOC_LNK_CONTINUECOURSE);
                } else {

                    improveAnswers(maxQuestions);

                        /* Retry the exam */
                    System.out.println("Score too low, retrying...");

                    if (isPresent(MyEasyTrack.LOC_LNK_RETRYEXAM)) {
                        click(MyEasyTrack.LOC_LNK_RETRYEXAM);
                    } else if (isPresent(MyEasyTrack.LOC_LNK_RETAKEFINALEXAM)) {
                        click(MyEasyTrack.LOC_LNK_RETAKEFINALEXAM);
                    }

                        /* Index for question */
                    questionIndex = 1;

                        /* Amount of questions in this exam */
                    maxQuestions = getNumberOfQuestions(MyEasyTrack.LOC_TEXT_EXAMNUMBER);
                    System.out.println(maxQuestions);

                        /* Click on the first exam button */
                    click(MyEasyTrack.LOC_LNK_BEGINEXAM);

                }

            } else {
                    /* Click the continue link to increment by one question */
                click(MyEasyTrack.LOC_LNK_NEXTEXAM);
            }

        }

        System.out.println();


    }

    @Test
    public void examRunner() {

        // ToDo: Add a way to check percentage from the overview screen and increment if percentage is above a threshold

        loginToLatestCourse();

        /* Integer to notate which exam is being taken */
        int examIndex = 6;

        try {
            Thread.sleep(5000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        /* Wait a maxiumum of 10 seconds for the page to load */
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        validatePresent(MyEasyTrack.getExamLink(examIndex));

        while (isPresent(MyEasyTrack.getExamLink(examIndex)) || isPresent(MyEasyTrack.LOC_LNK_BEGINEXAM) || isPresent(MyEasyTrack.LOC_TXT_EXAMQUESTION) || isPresent(MyEasyTrack.LOC_LNK_NEXTEXAM)) {

            click(MyEasyTrack.getExamLink(examIndex));

            /* Index for question */
            int questionIndex = 1;

            /* Amount of questions in this exam */
            int maxQuestions = getNumberOfQuestions(MyEasyTrack.LOC_TEXT_EXAMNUMBER);
            System.out.println("This quiz has " + maxQuestions + " questions(s)");

            /* Click on the first exam button */
            click(MyEasyTrack.LOC_LNK_BEGINEXAM);

            while (isPresent(MyEasyTrack.getExamOptionLink(1))) {

                System.out.print("Answering question: " + questionIndex + " ");

                int substringIndex = getText(MyEasyTrack.LOC_TXT_EXAMQUESTION).indexOf(32) + 1;

                /* Grab the first 30 characters of the question */
                String questionText = getText(MyEasyTrack.LOC_TXT_EXAMQUESTION).substring(substringIndex);

                ExamQuestion newQuestion = new ExamQuestion(questionText.trim());

                /* Search the exam bank for an identical question, if none exists, add the question to the database. */
                if (!newQuestion.questionExists(newQuestion.getQuestion())) {
                    System.out.print("Question not found, adding: ");
                    System.out.println("Question is: " + newQuestion.getQuestion());
                    newQuestion.addQuestion(newQuestion);
                }
                /* Otherwise get the question from the database */
                else {
                    System.out.println("Question found, grabbing value...");
                    newQuestion = newQuestion.getSimilar(newQuestion.getQuestion().trim());
                }

                /* Click on the best available option */
                click(MyEasyTrack.getExamOptionLink(newQuestion.getAnswer()));

                try {
                    Thread.sleep(5000);                 //1000 milliseconds is one second.
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                questionIndex++;

                if (isPresent(MyEasyTrack.LOC_LNK_EXAMSUBMIT) && questionIndex > maxQuestions) {

                    /* Submit the exam */
                    click(MyEasyTrack.LOC_LNK_EXAMSUBMIT);
                    System.out.println("Submitting Exam");

                    /* Wait a maxiumum of 10 seconds for the page to load */
                    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

                    if (getPercentage(MyEasyTrack.LOC_TXT_EXAMPERCENTAGE) >= 70) {
                        System.out.println("Exam sucessfully completed");
                        examIndex++;

                        /* Continue the course */
                        click(MyEasyTrack.LOC_LNK_CONTINUECOURSE);
                    } else {

                        improveAnswers(maxQuestions);

                        /* Retry the exam */
                        System.out.println("Score too low, retrying...");

                        if (isPresent(MyEasyTrack.LOC_LNK_RETRYEXAM)) {
                            click(MyEasyTrack.LOC_LNK_RETRYEXAM);
                        } else if (isPresent(MyEasyTrack.LOC_LNK_RETAKEFINALEXAM)) {
                            click(MyEasyTrack.LOC_LNK_RETAKEFINALEXAM);
                        }

                        /* Index for question */
                        questionIndex = 1;

                        /* Amount of questions in this exam */
                        maxQuestions = getNumberOfQuestions(MyEasyTrack.LOC_TEXT_EXAMNUMBER);
                        System.out.println(maxQuestions);

                        /* Click on the first exam button */
                        click(MyEasyTrack.LOC_LNK_BEGINEXAM);

                    }

                } else {
                    /* Click the continue link to increment by one question */
                    click(MyEasyTrack.LOC_LNK_NEXTEXAM);
                }

            }

            System.out.println();

        }

    }

    private void improveAnswers(int maxQuestion) {
        for (int i = 1; i <= maxQuestion; i++) {
            boolean hasNotChanged = true;
            int count = 1;

            while (hasNotChanged && count <= 4) {

                if (answerIsWrong(i, count)) {

                    String quest = getQuestion(i);

                    int indexToChange = ExamBank.getQuestionIndex(quest);

                    System.out.println("Changing question # " + indexToChange);

                    System.out.println(quest);

                    ExamBank.changeAnswer(indexToChange);

                    hasNotChanged = false;
                }
                count++;
            }

            if (count > 4) {
                System.out.println("Couldn't find the answer because the count was too high, check your code... ");
            }

            System.out.println();

        }
    }

    private int getPercentage(By percentage) {
        String percentageText = getText(percentage);

        percentageText = percentageText.substring(12, 14);
        System.out.println(percentageText);

        if (percentageText.equals("0%")) {
            return 0;
        }

        return Integer.parseInt(percentageText);
    }

    private int getNumberOfQuestions(By amountLocation) {
        String amountText = getText(amountLocation);

        int spaceIndex = amountText.indexOf(32);

        if (spaceIndex < 0) {
            spaceIndex = amountText.length();
        }

        amountText = amountText.substring(0, spaceIndex);

        return Integer.parseInt(amountText);

    }

    private boolean answerIsWrong(int index, int count) {
        if (isPresent(By.xpath("//*[@class='activity-answers'][" + index + "]/li[" + count + "]/font[1]"))) {

            String response = getText(By.xpath("//*[@class='activity-answers'][" + index + "]/li[" + count + "]/font[1]"));

            return response.equals("   This was your answer, which is incorrect.");
        } else return false;
    }

    private String getQuestion(int index) {
        String link = "//*[@class='activity-question'][" + index + "]";

        By question = By.xpath(link);

        System.out.println(getText(question).substring(3));

        return getText(question).substring(3);
    }

}
