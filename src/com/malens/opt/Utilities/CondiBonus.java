package com.malens.opt.Utilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static jdk.nashorn.internal.objects.NativeMath.min;

/**
 * Created by malens on 2017-09-18.
 */
public class CondiBonus {
    private Map<String, Double> bonuses = new HashMap<>();

    public CondiBonus(Map<String, Double> bonuses) {
        this.bonuses = bonuses;
    }

    public void set(String name, double val){
        bonuses.put(name, val);
    }

    public void add(String name, double val){
        if (bonuses.containsKey(name))
            bonuses.put(name, bonuses.get(name) + val);
        else this.set(name, val);
    }

    public CondiBonus(){
    }

    public double get(String name){
        return bonuses.containsKey(name) ? Double.min(bonuses.get(name), 1.0) : 0.0;
    }

    public Map<String, Double> getBonuses() {
        return bonuses;
    }

    public void setBonuses(Map<String, Double> bonuses) {
        this.bonuses = bonuses;
    }

    public void addBonus(CondiBonus other){
        for (String x:bonuses.keySet()){
            if (other.getBonuses().containsKey(x)){
                this.add(x, other.getBonuses().get(x));
            }
        }
    }

    public void addBonuses(CondiBonus other){
        if (other == null) return;
        if (other.getBonuses().isEmpty()) return;
        for (String x:other.getBonuses().keySet()){
            this.add(x, other.get(x));
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (String x:bonuses.keySet()){
            s += x + " " + bonuses.get(x) + "\n";
        }
        return s;
    }
}
