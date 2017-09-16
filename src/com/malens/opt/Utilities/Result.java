package com.malens.opt.Utilities;
import com.malens.opt.Modifiers.*;
import java.util.Comparator;

/**
 * Created by malens on 2017-09-04.
 */
public class Result implements Comparable<Result>{
    @Override
    public int compareTo(Result o) {

        if (this.EffectivePower == o.EffectivePower) {
            if (this.BoonDuration == o.BoonDuration) return 0;
            if (this.BoonDuration < o.BoonDuration) return -1;
            else return 1;
        };
        if (this.EffectivePower < o.EffectivePower) return -1;
        else return 1;
    }

    public int compareByTotalStats(Result o) {

        if (this.TotalStats == o.TotalStats) {
            if (this.EffectivePower == o.EffectivePower){
                if (this.BoonDuration == o.BoonDuration) return 0;
                if (this.BoonDuration < o.BoonDuration) return -1;
                else return 1;
            }
            if (this.EffectivePower < o.EffectivePower) return -1;
            else return 1;

        };
        if (this.TotalStats < o.TotalStats) return -1;
        else return 1;
    }

    public int getHash() {
        return hash;
    }

    public double getEffectivePower() {
        return EffectivePower;
    }

    public double getCritChance() {
        return CritChance;
    }

    public double getBoonDuration() {
        return BoonDuration;
    }

    public double getTotalStats() {
        return TotalStats;
    }

    public Food getMyFood() {
        return myFood;
    }

    public Util getMyUtil() {
        return myUtil;
    }

    public Rune getMyRune() {
        return myRune;
    }

    public Sigil getMySigil() {
        return mySigil;
    }

    private int hash;
    private double EffectivePower;
    private double CritChance;
    private double BoonDuration;
    private double TotalStats;
    private Food myFood;
    private Util myUtil;
    private Rune myRune;
    private Sigil mySigil;

    public Result(int hash, double effectivePower, double critChance, double boonDuration, double totalStats, Food myFood, Util myUtil, Rune myRune, Sigil mySigil) {
        this.hash = hash;
        EffectivePower = effectivePower;
        CritChance = critChance;
        BoonDuration = boonDuration;
        TotalStats = totalStats;
        this.myFood = myFood;
        this.myUtil = myUtil;
        this.mySigil = mySigil;
        this.myRune = myRune;
    }



    @Override
    public String toString() {
        return "Result{" +
                "hash=" + hash +
                ", EffectivePower=" + EffectivePower +
                ", CritChance=" + CritChance +
                ", BoonDuration=" + BoonDuration +
                ", TotalStats=" + TotalStats +
                '}';
    }
}