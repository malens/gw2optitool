package com.malens.opt.Modifiers;

import com.malens.opt.Utilities.CondiBonus;
import com.malens.opt.Utilities.Stats;

import java.util.function.Function;

/**
 * Created by malens on 2017-09-15.
 */
public class Rune {
    int id;
    String name;
    CondiBonus condiBonus;

    public CondiBonus getCondiBonus() {
        return condiBonus;
    }

    public Rune(int id, String name, Function<Stats, Stats> getStats, CondiBonus condiBonus) {

        this.id = id;
        this.name = name;
        this.condiBonus = condiBonus;
        this.getStats = getStats;
    }

    public Rune(int id, String name, Function<Stats, Stats> getStats) {
        this.id = id;
        this.name = name;
        this.getStats = getStats;
    }

    public int getId() {
        return id;
    }

    public Stats getAllStats(Stats stats){
        return getStats.apply(stats);
    }

    public Function<Stats, Stats> getStats;


    public String getName() {
        return name;
    }
}
