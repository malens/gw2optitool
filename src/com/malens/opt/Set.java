package com.malens.opt;

import java.util.ArrayList;

/**
 * Created by malens on 2017-08-29.
 */
public class Set {

    private ArrayList<Item> set;
    private String StatName;


    public Set(String statName) {
        set = new ArrayList<>();
        StatName = statName;
    }

    public Set(ArrayList<Item> set, String statName) {
        this.set = set;
        this.StatName = statName;
    }

    public String getStatName() {
        return StatName;
    }

    public ArrayList<Item> getSet() {
        return set;
    }

    public void setSet(ArrayList<Item> set) {
        this.set = set;
    }

    public int addToSet(Item item){
        this.set.add(item);
        return this.set.size();
    }
}
