package com.nedellis.selenium;

import java.util.ArrayList;

public class selectQuestion extends SelectBank {

    ArrayList<String> containedAnswers = new ArrayList<String>();

    int index;

    public selectQuestion(int i) {
        index = i;
    }

    public selectQuestion(int i, String answer) {
        index = i;
        containedAnswers.add(answer);
    }

    public void addSelectAnswer(String answer) {
        containedAnswers.add(answer);
    }

    public boolean containsString(String search) {
        for (String string : containedAnswers) {
            if (containedAnswers.equals(search)) {
                return true;
            }
        }
        return false;
    }
}
