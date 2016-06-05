package com.nedellis.selenium;

import java.util.ArrayList;

public class SelectBank {

    static ArrayList<selectQuestion> selectBank = new ArrayList<selectQuestion>();

    public static void addSelect(selectQuestion question) {
        selectBank.add(question);
    }

    public static boolean isPresent(int index, String search) {
        return selectBank.get(index).containsString(search);
    }

    public static boolean alreadyCreated(int index) {
        return (index < selectBank.size());
    }

    public static selectQuestion getSelectQuestion(int index) {
        return selectBank.get(index);
    }

}
