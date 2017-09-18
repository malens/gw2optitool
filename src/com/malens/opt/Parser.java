package com.malens.opt;

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

    public void parse (String[] args){
        String all = "";
        for (String s:args){
            all +=s + " ";
        }
        //System.out.println(all);
        String splitArgs[] = all.split("-");
        this.parsed = new ParsedArgs();
        int val;
        for (String s:splitArgs) {
            String split[] = s.split(" ");
            for (String x:split){
                //System.out.println(x);
            }
            switch (split[0].toLowerCase()) {
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
                    if (s.toLowerCase().contains("offhand")) parsed.getExcludeSet().add(13);

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
                    val = 0;
                    for (Character a : split[1].toCharArray()){
                        val = val * 10 + a.charValue()-48;
                    }
                    parsed.stats.setPower(val);
                    break;
                case "pr":
                case "precision":
                    val = 0;
                    for (Character a : split[1].toCharArray()){
                        val = val * 10 + a.charValue()-48;
                    }
                    parsed.stats.setPrecision(val);
                    break;
                case "f":
                case "ferocity":
                    val = 0;
                    for (Character a : split[1].toCharArray()){
                        val = val * 10 + a.charValue()-48;
                    }
                    parsed.stats.setFerocity(val);
                    break;

                case "t":
                case "toughness":
                    val = 0;
                    for (Character a : split[1].toCharArray()){
                        val = val * 10 + a.charValue()-48;
                    }
                    parsed.stats.setToughness(val);
                    break;
                case "v":
                case "vitality":
                    val = 0;
                    for (Character a : split[1].toCharArray()){
                        val = val * 10 + a.charValue()-48;
                    }
                    parsed.stats.setVitality(val);
                    break;

                case "c":
                case "concentration":
                    val = 0;
                    for (Character a : split[1].toCharArray()){
                        val = val * 10 + a.charValue()-48;
                    }
                    parsed.stats.setConcentration(val);
                    break;

                case "exp":
                case "expertise":
                    val = 0;
                    for (Character a : split[1].toCharArray()){
                        val = val * 10 + a.charValue()-48;
                    }
                    parsed.stats.setExpertise(val);
                    break;
                case "hp":
                case "healingpower":
                    val = 0;
                    for (Character a : split[1].toCharArray()){
                        val = val * 10 + a.charValue()-48;
                    }
                    parsed.stats.setHealingPower(val);
                    break;

                case "cd":
                case "conditiondamage":
                    val = 0;
                    for (Character a : split[1].toCharArray()){
                        val = val * 10 + a.charValue()-48;
                    }
                    parsed.stats.setConditionDamage(val);
                    break;

                case "cr":
                case "countrunes":
                    parsed.setCountRunes(true);
                    break;

                case "cs":
                case "countsigils":
                    parsed.setCountSigils(true);
                    break;

                default:
                    break;


            }
        }
    }





}
