package com.nedellis.selenium;

public class ExamQuestion extends ExamBank {

    private String question;

    private int answer;

    public ExamQuestion(String quest, int ans) {
        question = quest;
        answer = ans;
    }

    public ExamQuestion(String quest) {
        question = quest;
        answer = 1; // Defaults to an answer index of one.
    }

    public String getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }

    public void changeAnswer() {
        answer++;
    }
}
