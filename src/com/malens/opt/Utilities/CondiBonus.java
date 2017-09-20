package com.malens.opt.Utilities;

import java.util.Map;

import static jdk.nashorn.internal.objects.NativeMath.min;

/**
 * Created by malens on 2017-09-18.
 */
public class CondiBonus {
    private Map<String, Double> bonuses;

    public CondiBonus(Map<String, Double> bonuses) {
        this.bonuses = bonuses;
    }

    public void set(String name, Double val){
        bonuses.put(name, val);
    }

    public void add(String name, Double val){
        if (bonuses.containsKey(name))
            bonuses.put(name, bonuses.get(name) + val);
        else set(name, val);
    }

    public double get(String name){
        return bonuses.containsKey(name) ? min(bonuses.get(name), 1) : 0;
    }

    public Map<String, Double> getBonuses() {
        return bonuses;
    }

    public void setBonuses(Map<String, Double> bonuses) {
        this.bonuses = bonuses;
    }
}
