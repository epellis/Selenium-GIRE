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

    /* Integer with the score that will allow the while loop to complete and the method completeExam to terminate */
    private static final int scoreThreshold = 70;

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
            Thread.sleep(2000);                 //1000 milliseconds is one second.
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


        System.out.print("Currently on window: ");
        System.out.println(driver.getWindowHandle());

        /* Wait a maxiumum of 10 seconds for the page to load */
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);


        /* Wait a maxiumum of 10 seconds for the page to load */
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        System.out.println();

    }

    private void completeMultipleChoice() {
        /* Array with quiz answers */
        int[] quizAnswers = {1, 1, 1, 1};

        System.out.println("Attempting to answer multiple choice question...");
        System.out.println();

        /* Wait a maxiumum of 10 seconds for the page to load */
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        try {
            Thread.sleep(2000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        validatePresent(MyEasyTrack.getQuizOptionLink(1, 1));

        while (isPresent(MyEasyTrack.getQuizOptionLink(1, 1))) {

            /* Iterate through each answer to fill it in */
            for (int i = 1; i <= quizAnswers.length; i++) {

                /* If the answer exists, click it */
                if (isPresent(MyEasyTrack.getQuizOptionLink(i, quizAnswers[i - 1]))) {
                    click(MyEasyTrack.getQuizOptionLink(i, quizAnswers[i - 1]));
                }

            }

            click(MyEasyTrack.LOC_LNK_CHECKANSWER);

            try {
                Thread.sleep(2000);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            if (isPresent(MyEasyTrack.LOC_LNK_TRYAGAIN) || isPresent(MyEasyTrack.LOC_LNK_TRYHEREAGAIN)) {

                System.out.println("Quiz not passed, retrying...");

                /* Iterate through all of the answers */
                for (int i = 1; i <= quizAnswers.length; i++) {

                    System.out.println("Improving question: " + i);

                    if (quizQuestionIsWrong(i)) {
                        quizAnswers[i - 1]++;
                    }
                }

                /* Try the quiz again */
                click(MyEasyTrack.LOC_LNK_TRYHEREAGAIN);

            } else {
                System.out.println();
            }

        }

    }

    private boolean quizQuestionIsWrong(int index) {
        By feedbackLink = MyEasyTrack.getCorrectAnswerText(index);

        if (isPresent(feedbackLink)) {

            String feedback = getText(feedbackLink);

            feedback = feedback.substring(0, 14);

            System.out.println(feedback);

            return feedback.equals("Correct Answer");
        } else return false;
    }

    @Test
    public void webdriverRunner() {

        loginToLatestCourse();

        int examCount = 2;

        try {
            Thread.sleep(2000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        /* While loop designed to complete an entire course while the link still exists */
        while (isPresent(MyEasyTrack.LOC_LNK_GONEXT) || isPresent(MyEasyTrack.LOC_LNK_LATESTCOURSE) || isPresent(MyEasyTrack.getExamLink(examCount))) {

            try {
                Thread.sleep(2000);                 //1000 milliseconds is one second.
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
                completeMultipleChoice();
            }

            /* Wait for 3500 +/- 5000 milliseconds (5 Seconds) */
            try {
                Thread.sleep(70000 + randomTime);                 //1000 milliseconds is one second.
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

                if (getPercentage(MyEasyTrack.LOC_TXT_EXAMPERCENTAGE) >= scoreThreshold) {
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
        int examIndex = 1;

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

            completeExam();

        }

    }

    /**
     * Method to iterate through questios and answers to improve database accuracy and "learn"
     * @param maxQuestion is an integer that is the index of the last question. Used for loops.
     */
    private void improveAnswers(int maxQuestion) {

        /* Iterate through all of the questions until the last question is reached */
        for (int i = 1; i <= maxQuestion; i++) {

            boolean hasNotChanged = true; // Useful for this while loop as it is possible to exit after the answer is found.
            int count = 1; // Start at the first index

            /* Iterate through all of the answers until special text is detected. */
            while (hasNotChanged && count <= 4) {

                /* Sends the question and count to the method answeriswrong to detect if the answer is (you guessed it) wrong */
                if (answerIsWrong(i, count)) {

                    /* Grab the question from method getQuestionText by sending the index of the question to look up and retrieve */
                    String quest = getQuestionText(i);

                    /* Send the text to the exambank class method getquestionindex to retrieve the correct object index with this question */
                    int indexToChange = ExamBank.getQuestionIndex(quest);

                    /* Debugging yo */
                    System.out.println("Changing question # " + indexToChange);

                    /* Change the question to the next possible index */
                    ExamBank.changeAnswer(indexToChange);

                    /* Exit the while loop because the work here is done */
                    hasNotChanged = false;
                }

                /* After the if statement is finished, increase the count by 1. */
                count++;
            }

            /* If the count goes over the maximum amount of answers is, print this out for future debugging */
            if (count > 4) {
                System.out.println("Couldn't find the answer because the count was too high, check your code... ");
            }

            System.out.println();

        }
    }

    /**
     * Method to retrieve the results of a quiz.
     * @param percentage is a By object type with an xpath to the text displaying the score.
     * @return (s) an integer with the score out of 100 to use in if statements in other methods
     */
    private int getPercentage(By percentage) {

        /* Make a new string with the text of the By object passed to the method */
        String percentageText = getText(percentage);

        /* A quick n' dirty way to find the percentage, requires the percentage to have 2 digits, but who would really score below a 10 on this? */
        percentageText = percentageText.substring(12, 14);

        /* Nice little debug text for checking to see if the program has gone bonkers. */
        System.out.println("Score: " + percentageText);

        /* Sneaky way to return the percentage if it is zero */
        if (percentageText.equals("0%")) {
            return 0;
        }

        /* Return this string as an integer */
        return Integer.parseInt(percentageText);
    }

    /**
     * Method to retrieve the amount of questions in an exam
     * @param amountLocation is a By object type containing the text that has the answer
     * @return s a number that equals the amount of questions in an exam
     */
    private int getNumberOfQuestions(By amountLocation) {

        /* String with the entire text found in the statement given by the By object passed to the method */
        String amountText = getText(amountLocation);

        /* To deal with different digits, this program finds the index of the first space and creates an integer from it */
        int spaceIndex = amountText.indexOf(32);

        /* If an index can not be found, because sometimes this object's string dosent have any (Yeah i don't know why), the substring will work at the length */
        if (spaceIndex < 0) {
            spaceIndex = amountText.length();
        }

        /* Change the string to only include the numbers */
        amountText = amountText.substring(0, spaceIndex);

        /* Return the string as an integer */
        return Integer.parseInt(amountText);

    }

    /* Method to detect text which indicates that the given answer was incorrect */
    private boolean answerIsWrong(int index, int count) {

        /* If statement confirming that the question exists */
        if (isPresent(By.xpath("//*[@class='activity-answers'][" + index + "]/li[" + count + "]/font[1]"))) {

            /* Take the entire question including the incorrect phrase */
            String response = getText(By.xpath("//*[@class='activity-answers'][" + index + "]/li[" + count + "]/font[1]"));

            /* Return a boolean if the statement contains the text */
            return response.equals("   This was your answer, which is incorrect.");
        }

        /* Otherwise, return false, if the if loop was never taken */
        else return false;
    }

    /**
     * Method to give other parts of the program text found in questions
     * @param index is an integer telling this method where to find the question in a list of other questions
     * @return the string of the question
     */
    private String getQuestionText(int index) {

        /* Makes an xpath link by combining css code and an index */
        String link = "//*[@class='activity-question'][" + index + "]";

        /* Cast a By object by the name of question to this code */
        By question = By.xpath(link);

        /* Get an index of the first space */
        int substringIndex = getText(MyEasyTrack.LOC_TXT_EXAMQUESTION).indexOf(32) + 1;

        /* Print the text of the question minus the numbers */
        System.out.println(getText(question).substring(substringIndex).trim());

        /* Return the string of this and trim it for good measure */
        return getText(question).substring(substringIndex).trim();
    }

}
