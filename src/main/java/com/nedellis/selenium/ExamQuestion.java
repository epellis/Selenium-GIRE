package com.nedellis.selenium;

public class ExamQuestion extends ExamBank {

    private String question;

    private int answer;

    public ExamQuestion(String quest, int ans) {
        question = quest.trim();
        answer = ans;
    }

    public ExamQuestion(String quest) {
        question = quest.trim();
        answer = (int)(Math.random() * 4) + 1; // Randomize between 1 and 4
    }

    public String getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }

    public void changeAnswerToCount(int count) {
        answer = count;
    }
}
