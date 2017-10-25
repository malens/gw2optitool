package com.malens.opt;

import com.malens.opt.Utilities.CondiBonus;
import com.malens.opt.Utilities.Stats;

import java.util.ArrayList;

/**
 * Created by malens on 2017-09-01.
 */
public class Parser {

    public class ParsedArgs {
        public ParsedArgs() {
            this.countUtil = false;
            this.countFood = false;
            this.excludeSet = new ExcludeSet();
            this.sets = new Sets();
            this.stats = new Stats();
            this.forFotm = false;
            this.countSigils = false;
            this.countRunes = false;
        }


        boolean countRunes = false;
        boolean countSigils = false;
        boolean forFotm = false;
        boolean countUtil = false;
        boolean countFood = false;
        ExcludeSet excludeSet;
        Sets sets;
        Stats stats;
        boolean help = false;
        double dps;

        public double getDps() {
            return dps;
        }

        public void setDps(double dps) {
            this.dps = dps;
        }

        double targetBoonDuration = 0.99;

        CondiBonus condiPercentages = new CondiBonus();

        CondiBonus condiBonuses = new CondiBonus();

        CondiBonus condiDMGBonuses = new CondiBonus();

        public CondiBonus getCondiPercentages() {
            return condiPercentages;
        }

        public CondiBonus getCondiBonuses() {
            return condiBonuses;
        }

        public CondiBonus getCondiDMGBonuses() {
            return condiDMGBonuses;
        }

        public double CondiDmg = 0;

        public double getTargetBoonDuration() {
            return targetBoonDuration;
        }

        public double EffectivePower;

        public double getEffectivePower() {
            return EffectivePower;
        }

        public void setEffectivePower(double effectivePower) {
            EffectivePower = effectivePower;
        }

        public double getCondiDmg() {
            return CondiDmg;
        }

        public boolean isCountRunes() {
            return countRunes;
        }

        public void setCountRunes(boolean countRunes) {
            this.countRunes = countRunes;
        }

        public boolean isCountSigils() {
            return countSigils;
        }

        public void setCountSigils(boolean countSigils) {
            this.countSigils = countSigils;
        }

        public boolean isForFotm() {
            return forFotm;
        }

        public void setForFotm(boolean forFotm) {
            this.forFotm = forFotm;
        }

        public boolean isHelp(){
            return help;
        }

        public void setHelp(boolean val){
            help = val;
        }

        public boolean isCountUtil() {
            return countUtil;
        }

        public void setCountUtil(boolean countUtil) {
            this.countUtil = countUtil;
        }

        public boolean isCountFood() {
            return countFood;
        }

        public void setCountFood(boolean countFood) {
            this.countFood = countFood;
        }

        public ExcludeSet getExcludeSet() {
            return excludeSet;
        }

        public void setExcludeSet(ExcludeSet excludeSet) {
            this.excludeSet = excludeSet;
        }

        public Sets getSets() {
            return sets;
        }

        public void setSets(Sets sets) {
            this.sets = sets;
        }

        public Stats getStats() {
            return stats;
        }

        public void setStats(Stats stats) {
            this.stats = stats;
        }
    }

    /*

    -cu/--countUtil [0/1]
    -cf/--countFood [0/1]
    -e/--exclude [Head/Shoulders...etc]
    -i/--include [Berserker/Assasin...etc]
    -p/--power int etc

    */
    private ParsedArgs parsed;

    public ParsedArgs getParsedArgs (){
        return this.parsed;
    }

    private double getVal(String split)
    {
        double val = 0;
        for (int i = 0; i< split.length(); i++){
            Character a = split.toCharArray()[i];
            if (a=='.') {
                int tmp = 10;
                for (int j = i+1; j < split.length(); j++){
                    a = split.toCharArray()[j];
                    val += ((double)(a.charValue()-48))/tmp;
                    tmp*=10;
                }
                break;
            } else val = val * 10 + a.charValue()-48;
        }
        return val;
    }

    public void parse (String[] args){
        String all = "";
        for (String s:args){
            all +=s + " ";
        }
        //System.out.println(all);
        String splitArgs[] = all.split("-");
        this.parsed = new ParsedArgs();
        for (String s:splitArgs) {
            String split[] = s.split(" ");
            for (String x:split){
                //System.out.println(x);
            }
            switch (split[0].toLowerCase()) {
                case "ep" :
                    parsed.setEffectivePower(getVal(split[1]));
                    break;
                case "dps":
                    parsed.dps = getVal(split[1]);
                    break;
                case "boon%":
                    parsed.targetBoonDuration = getVal(split[1]);
                    break;
                case "ff":
                case "fotm":
                    parsed.setForFotm(true);
                    break;

                case "h":
                case "help":
                    this.parsed.setHelp(true);
                    return;
                case "cu":
                case "countUtil":
                    if (split.length<2 || split[1] == "1")
                        parsed.setCountUtil(true);
                    break;
                case "cf":
                case "countFood":
                    if (split.length<2 || split[1] == "1")
                        parsed.setCountFood(true);
                    break;
                case "exc":
                case "exclude":
                    if (s.toLowerCase().contains("head")) parsed.getExcludeSet().add(0);
                    if (s.toLowerCase().contains("shoulders")) parsed.getExcludeSet().add(1);
                    if (s.toLowerCase().contains("chest")) parsed.getExcludeSet().add(2);
                    if (s.toLowerCase().contains("hands")) parsed.getExcludeSet().add(3);
                    if (s.toLowerCase().contains("legs")) parsed.getExcludeSet().add(4);
                    if (s.toLowerCase().contains("boots")) parsed.getExcludeSet().add(5);
                    if (s.toLowerCase().contains("amulet")) parsed.getExcludeSet().add(6);
                    if (s.toLowerCase().contains("ring1")) parsed.getExcludeSet().add(7);
                    if (s.toLowerCase().contains("ring2")) parsed.getExcludeSet().add(8);
                    if (s.toLowerCase().contains("earring1")) parsed.getExcludeSet().add(9);
                    if (s.toLowerCase().contains("earring2")) parsed.getExcludeSet().add(10);
                    if (s.toLowerCase().contains("backpiece")) parsed.getExcludeSet().add(11);
                    if (s.toLowerCase().contains("mainhand")) parsed.getExcludeSet().add(12);
                    if (s.toLowerCase().contains("offhand")) {
                        parsed.getExcludeSet().add(13);
                        System.out.println("offhand");
                    }

                    break;
                case "i":
                case "include":
                    for (int i = 1; i < split.length; i++){
                        //System.out.println(split[i]);
                        parsed.sets.AddByName(split[i]);
                        //System.out.println(parsed.sets.getSetsArray().size());
                    }
                    break;

                case "po":
                case "power":
                    parsed.stats.setPower((int)getVal(split[1]));
                    break;
                case "pr":
                case "precision":
                    parsed.stats.setPrecision((int)getVal(split[1]));
                    break;
                case "f":
                case "ferocity":
                    parsed.stats.setFerocity((int)getVal(split[1]));
                    break;

                case "t":
                case "toughness":
                    parsed.stats.setToughness((int)getVal(split[1]));
                    break;
                case "v":
                case "vitality":
                    parsed.stats.setVitality((int)getVal(split[1]));
                    break;

                case "c":
                case "concentration":
                    parsed.stats.setConcentration((int)getVal(split[1]));
                    break;

                case "exp":
                case "expertise":
                    parsed.stats.setExpertise((int)getVal(split[1]));
                    break;
                case "hp":
                case "healingpower":
                    parsed.stats.setHealingPower((int)getVal(split[1]));
                    break;

                case "cd":
                case "conditiondamage":
                    parsed.stats.setConditionDamage((int)getVal(split[1]));
                    break;

                case"cdo":
                    parsed.CondiDmg = getVal(split[1]);
                    break;

                case "cr":
                case "countrunes":
                    parsed.setCountRunes(true);
                    break;

                case "cs":
                case "countsigils":
                    parsed.setCountSigils(true);
                    break;

                case "bbd":
                case "bonusbleeddur":
                    parsed.condiBonuses.add("Bleeding", getVal(split[1]));
                    break;
                case "btd":
                case "bonustormentdur":
                    parsed.condiBonuses.add("Torment", getVal(split[1]));
                    break;
                case "bcd":
                case "bonusconfusiondur":
                    parsed.condiBonuses.add("Confusion", getVal(split[1]));
                    break;
                case "bburnd":
                case "bonusburningdur":
                    parsed.condiBonuses.add("Burning", getVal(split[1]));
                    break;

                case "bpd":
                case "bonuspoisondur":
                    parsed.condiBonuses.add("Poison", getVal(split[1]));
                    break;

                case "bbdmg":
                case "bonusbleeddmg":
                    parsed.condiDMGBonuses.add("Bleeding", getVal(split[1]));
                    break;

                case "btdmg":
                case "bonustormentdmg":
                    parsed.condiDMGBonuses.add("Torment", getVal(split[1]));
                    break;
                case "bcdmg":
                case "bonusconfusiondmg":
                    parsed.condiDMGBonuses.add("Confusion", getVal(split[1]));
                    break;
                case "bburndmg":
                case "bonusburningdmg":
                    parsed.condiDMGBonuses.add("Burning", getVal(split[1]));
                    break;

                case "bpdmg":
                case "bonuspoisondmg":
                    parsed.condiDMGBonuses.add("Poison", getVal(split[1]));
                    break;

                case "bleed%":
                    parsed.condiPercentages.add("Bleeding", getVal(split[1]));
                    break;

                case "torment%":
                    parsed.condiPercentages.add("Torment", getVal(split[1]));
                    break;
                case "confusion%":
                    parsed.condiPercentages.add("Confusion", getVal(split[1]));
                    break;
                case "burning%":
                    parsed.condiPercentages.add("Burning", getVal(split[1]));
                    break;
                case "poison%":
                    parsed.condiPercentages.add("Poison", getVal(split[1]));
                    break;
                case "power%":
                    parsed.condiPercentages.add("Power", getVal(split[1]));
                    break;

                default:
                    break;


            }
        }
    }





}
