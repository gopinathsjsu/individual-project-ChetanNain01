package com.sample.model;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static Cards singletonObject = null;
    List<Integer> cardList = new ArrayList<>();

    public List<Integer> getCardList() {
        return cardList;
    }

    public void setCardList(List<Integer> cardList) {
        this.cardList = cardList;
    }

    public static Cards getInstance() {
        if(singletonObject == null) {
            singletonObject = new Cards();
        }
        return singletonObject;
}
}
