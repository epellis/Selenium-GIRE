package com.nedellis.selenium;

import java.util.ArrayList;

public class ExamBank {
    static ArrayList<ExamQuestion> QuestionBank = new ArrayList<ExamQuestion>();

    public void addQuestion(ExamQuestion quest) {
        QuestionBank.add(quest);
    }

    public boolean questionExists(String quest) {
        for (ExamQuestion question : QuestionBank) {
            if (question.getQuestion().equals(quest)) {
                return true;
            }
        }
        return false;
    }

    public ExamQuestion getSimilar(String quest) {
        for (ExamQuestion question : QuestionBank) {
            if (question.getQuestion().equals(quest)) {
                return question;
            }
        }
        return new ExamQuestion(quest);
    }

    public static int getQuestionIndex(String quest) {
        quest = quest.trim();

        for (int i = 0; i < QuestionBank.size(); i++) {
            if (QuestionBank.get(i).getQuestion().trim().equals(quest)) {
                return i;
            }
        }
        System.out.println("No question found... Sounds like the ExamBank could be alterernated");
        return -1;
    }

    public static void changeAnswer(int index, int count) {

        if (index >= 0) {
            QuestionBank.get(index).changeAnswerToCount(count);
        } else {
            System.out.println("Index is not valid!!!");
        }
    }

}
