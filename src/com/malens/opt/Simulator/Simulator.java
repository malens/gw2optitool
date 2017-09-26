package com.malens.opt.Simulator;

import com.malens.opt.Modifiers.Food;
import com.malens.opt.Modifiers.Rune;
import com.malens.opt.Modifiers.Sigil;
import com.malens.opt.Modifiers.Util;
import com.malens.opt.Parser;
import com.malens.opt.Sets;
import com.malens.opt.Utilities.CondiBonus;
import com.malens.opt.Utilities.Math;
import com.malens.opt.Utilities.Result;
import com.malens.opt.Utilities.Stats;
import javafx.collections.transformation.SortedList;


import javax.sound.midi.Track;
import java.io.File;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.*;

import static java.lang.Double.min;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

/**
 * Created by malens on 2017-09-17.
 */
public class Simulator {

    public static void simulate (Parser.ParsedArgs args, ArrayList<Util> utils, ArrayList<Food> foods, ArrayList<Rune> runes, ArrayList<Sigil> sigils){

        Sets sets = args.getSets();
        //System.out.println(sets.getSetsArray().size());
        //System.out.println(args.getSets().getSetsArray().size());
        ArrayList<Result> results = new ArrayList<>();
        SortedSet<Result> bestResults = new TreeSet<>(Result::compareTo);

        Stats boons = new Stats(25*30,420,0,0,0,0,0,0,0);
        Stats otherbuffs = new Stats(150,150,0,0,0,0,0,0,0);
        Stats banners = new Stats(170,170,170,0,0,0,0,0,170);
        Stats fotm = new Stats (0,225,0,225,0,225,0,0,0);
        //Stats specialStats = new Stats (0,0,0,0, 63*15, 0,0,0,0);

        for (int a = 0; a<runes.size(); a++) {
            for (int b = 0; b<sigils.size(); b++) {
                for (int c = 0; c<foods.size(); c++) {
                    for (int d = 0; d<utils.size(); d++) {
                        for (Integer i = 0; i <= pow(args.getSets().getSetsArray().size(), 14); i++) {
                            Rune currentRune = runes.get(a);
                            Sigil currentSigil = sigils.get(b);
                            Food currentFood = foods.get(c);
                            Util currentUtil = utils.get(d);

                            if (i%1000==0){
                                System.out.print("[");

                                DecimalFormat form1 = new DecimalFormat("0");



                                Integer totalI = Math.pow(args.getSets().getSetsArray().size(),14);

                                Integer totalIt = runes.size()*sigils.size()*foods.size()*utils.size()*totalI;

                                Integer currentIt = i +
                                        d * totalI +
                                        c * utils.size() * totalI +
                                        b * utils.size() * totalI * foods.size() +
                                        a * sigils.size() * foods.size() * utils.size() * totalI;

                                double currentPercentage = currentIt.doubleValue()/totalIt.doubleValue();

                                for (double l = 0; l<30; l++){
                                    if (l/30 < currentPercentage) System.out.print("=");
                                    else System.out.print(" ");
                                }
                                System.out.print("]" + form1.format(currentPercentage * 100) + "%\r");
                            }
                            ////////////////////////////////////////////////////////////
                            // initialize with base stats + runes and sigils
                            // (everything that counts towards food bonus)
                            ////////////////////////////////////////////////////////////

                            Stats baseStats = new Stats(1000, 1000, 0, 1000, 1000, 0, 0, 0, 0);
                            baseStats.add(currentRune.getAllStats(baseStats));
                            baseStats.add(currentSigil.getAllStats(baseStats));
                            baseStats.add(args.getStats());

                            Stats myStats = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0);

                            ////////////////////////////////////////////////////////////
                            // add all gear stats
                            // (it also counts towards food bonuses)
                            ////////////////////////////////////////////////////////////

                            Integer j = i;
                            for (int k = 0; k < 14; k++) {
                                if ((!args.getExcludeSet().contains(k))) {
                                    baseStats.add(sets.
                                            getSetsArray().get(
                                                    j % sets.getSetsArray().size()).
                                            getSet().
                                            get(k).
                                            getStats());
                                }
                                j /= sets.getSetsArray().size();
                            }

                            ////////////////////////////////////////////////////////////
                            // add all other stats
                            ////////////////////////////////////////////////////////////

                            myStats.add(currentFood.getAllStats(baseStats));
                            myStats.add(currentUtil.getAllStats(baseStats));
                            myStats.add(baseStats);
                            myStats.add(boons);
                            myStats.add(otherbuffs);
                            myStats.add(banners);

                            if (args.isForFotm()) {
                                myStats.add(fotm);
                            }


                            ////////////////////////////////////////////////////////////
                            // filter results to fit expectations
                            ////////////////////////////////////////////////////////////

                            double critChance = min((myStats.Precision - 895) / 21, 100) / 100;
                            double feroModifier = (150 + myStats.Ferocity / 15) / 100;

                            double effectivePower = myStats.Power * (1 * (1 - critChance) + feroModifier * critChance);

                            if (currentRune.getName() == "Scholar"){
                                effectivePower *= 1.07;
                            }


                            if ( myStats.Concentration/1500 >= args.getTargetBoonDuration()) {// && abs(myPrecision-2995) < 20) {
                                Result result = new Result(
                                        i,
                                        effectivePower,
                                        min(100, ((myStats.Precision - 895) / 21)),
                                        myStats.Concentration / 15,
                                        effectivePower * 0.355 + min(myStats.Concentration,1500),
                                        currentFood,
                                        currentUtil,
                                        currentRune,
                                        currentSigil,
                                        myStats);

                                results.add(result);

                                if (bestResults.size()<10){
                                    bestResults.add(result);
                                } else if (bestResults.first().compareTo(result) < 0){
                                    bestResults.remove(bestResults.first());
                                    bestResults.add(result);
                                }
                            }
                        }




                    }
                }
            }
        }


        System.out.println("[==============================]100%");
/*
        ////////////////////////////////////////////////////////
        // sort results by efficiency
        ////////////////////////////////////////////////////////


        System.out.println(results.size());
        System.out.println("Sorting...");
        //results.sort(Result::compareTo);
        results.sort(Result::compareByTotalStats);

        ////////////////////////////////////////////////////////////
        // remove duplicates
        ////////////////////////////////////////////////////////////

        System.out.println("Removing duplicates...");
        int size = results.size();
        boolean flag = false;
        int removed = 0;
        for (int i = 0; i < size; i++) {
            if (results.get(i).getEffectivePower() == 0) continue;
            for (int j = i+1; j < size; j++) {
                if (j==size) break;
                if (abs(results.get(i).getEffectivePower() - results.get(j).getEffectivePower()) < 0.5
                        && abs(results.get(i).getBoonDuration() - results.get(j).getBoonDuration()) < 0.05
                        && results.get(i).getMyFood().getId() == results.get(j).getMyFood().getId()
                        && results.get(i).getMyUtil().getId() == results.get(j).getMyUtil().getId()) {
                    flag = true;
                    removed++;
                    results.set(j, new Result(0, 0, 0, 0, 0, new Food(0, "NaN", null), new Util(0, "NaN", null), new Rune(0,"NaN",null),new Sigil(0, "NaN", null)));
                } else if(flag) {
                    flag = false;
                    break;
                }

            }
        }

        results.sort(Result::compareTo);

        System.out.println(results.size()-removed);
        System.out.println("Done");


        DecimalFormat form = new DecimalFormat("0.00");
        String space = "\n";

        DecimalFormat form2 = new DecimalFormat("000");
        ////////////////////////////////////////////////////////////
        // print out results
        ////////////////////////////////////////////////////////////

        File file = new File("1");
        int i = 1;
        try{
            while (!file.createNewFile()){
                file = new File ("out_" + form2.format(i) + ".out");
                i++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        PrintStream fileout;
        try{
            fileout = new PrintStream(file);
            i = 0;
            //while (i<results.size()&&results.get(i).getEffectivePower()<1){
            //    i++;
            //}
            for (i = results.size()-50; i<results.size(); i++){
                fileout.println(
                        "EP: "
                                + form.format(results.get(i).getEffectivePower()) + space + "Crit%: "
                                + form.format(results.get(i).getCritChance()) + space + "Boon%: "
                                + form.format(results.get(i).getBoonDuration()) + space + "Total: "
                                + form.format(results.get(i).getTotalStats()) + space + "Food: "
                                + results.get(i).getMyFood().getName() + space + "Util: "
                                + results.get(i).getMyUtil().getName() + space + "Runes: "
                                + results.get(i).getMyRune().getName() + space + "Sigils: "
                                + results.get(i).getMySigil().getName() + space + "Stats: " + space + "Power: "
                                + results.get(i).getStats().getPower() + space + "Precision: "
                                + results.get(i).getStats().getPrecision() + space + "Ferocity: "
                                + results.get(i).getStats().getFerocity() + space + "Concentration: "
                                + results.get(i).getStats().getConcentration() + space + "Set: "
                                + sets.printSet(results.get(i).getHash()));
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
*/      //results.sort(Result::compareByTooughness);
        printResults(bestResults, sets);
        //printResults(results, sets);
        //results.sort(Result::compareTo);
        //printResults(results.subList(results.size()-11, results.size()), sets);

    }


    public static double get_condi_damage(double current_condi_duration, double current_condi_dmg,
                                    double base_damage, double factor, double condi_percentage,
                                    double condi_dmg, double condi_dur){
        //System.out.println(current_condi_duration + " " + current_condi_dmg + " " + base_damage + " " + factor  + " " + condi_percentage  + " " +  condi_dmg  + " " + condi_dur);
        double new_base = condi_percentage / (1+condi_dur) * (base_damage/(base_damage + condi_dmg * factor)) * (1+current_condi_duration);
        double new_bonus = condi_percentage / (1+condi_dur) * (current_condi_dmg * factor / (current_condi_dmg * factor + base_damage)) * (1+current_condi_duration);
        //System.out.println(new_base + new_bonus);
        if (condi_percentage==0 && new_base+new_bonus != 0) System.out.println(new_base+new_bonus);
        return new_base + new_bonus;
    }



    public static void simulate_condi (Parser.ParsedArgs args, ArrayList<Util> utils, ArrayList<Food> foods, ArrayList<Rune> runes, ArrayList<Sigil> sigils){
int fuckoff = 1;
        Sets sets = args.getSets();

        ArrayList<Result> results = new ArrayList<>();


        Stats boons = new Stats(25*30,420,0,0,0,0,0,0,0);
        Stats otherbuffs = new Stats(0,150,0,0,0,0,0,0,0);
        Stats banners = new Stats(170,170,170,0,0,0,0,0,170);
        Stats fotm = new Stats (0,225,0,225,0,225,0,0,0);
        //Stats specialStats = new Stats (0,0,0,0, 63*15, 0,0,0,0);
        double currenthighest = 1;
        for (int a = 0; a<runes.size(); a++) {
            for (int b = 0; b<sigils.size(); b++) {
                for (int c = 0; c<foods.size(); c++) {
                    for (int d = 0; d<utils.size(); d++) {
                        for (Integer i = 0; i <= pow(args.getSets().getSetsArray().size(), 14); i++) {
                            Rune currentRune = runes.get(a);
                            Sigil currentSigil = sigils.get(b);
                            Food currentFood = foods.get(c);
                            Util currentUtil = utils.get(d);

                            CondiBonus myBonus = new CondiBonus();
                            myBonus.addBonus(currentRune.getCondiBonus());
                            myBonus.addBonus(currentSigil.getCondiBonus());
                            myBonus.addBonus(currentFood.getCondiBonus());
                            myBonus.addBonus(currentUtil.getCondiBonus());
                            myBonus.addBonus(args.getCondiBonuses());

                            fuckoff = 2;
                            if (i%1000==0){
                                fuckoff = 3;
                                System.out.print("[");

                                DecimalFormat form1 = new DecimalFormat("0");



                                Integer totalI = Math.pow(args.getSets().getSetsArray().size(),14);

                                Integer totalIt = runes.size()*sigils.size()*foods.size()*utils.size()*totalI;

                                Integer currentIt = i +
                                        d * totalI +
                                        c * utils.size() * totalI +
                                        b * utils.size() * totalI * foods.size() +
                                        a * sigils.size() * foods.size() * utils.size() * totalI;

                                double currentPercentage = currentIt.doubleValue()/totalIt.doubleValue();

                                for (double l = 0; l<30; l++){
                                    if (l/30 < currentPercentage) System.out.print("=");
                                    else System.out.print(" ");
                                }
                                System.out.print("]" + form1.format(currentPercentage * 100) + "%\r");
                            }
                            ////////////////////////////////////////////////////////////
                            // initialize with base stats + runes and sigils
                            // (everything that counts towards food bonus)
                            ////////////////////////////////////////////////////////////



                            Stats baseStats = new Stats(1000, 1000, 0, 1000, 1000, 0, 0, 0, 0);
                            baseStats.add(currentRune.getAllStats(baseStats));
                            baseStats.add(currentSigil.getAllStats(baseStats));
                            baseStats.add(args.getStats());

                            Stats myStats = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0);

                            ////////////////////////////////////////////////////////////
                            // add all gear stats
                            // (it also counts towards food bonuses)
                            ////////////////////////////////////////////////////////////

                            Integer j = i;
                            for (int k = 0; k < 14; k++) {
                                if ((!args.getExcludeSet().contains(k))) {
                                    baseStats.add(sets.getSetsArray().get(j % sets.getSetsArray().size()).getSet().get(k).getStats());
                                }
                                j /= sets.getSetsArray().size();
                            }

                            ////////////////////////////////////////////////////////////
                            // add all other stats
                            ////////////////////////////////////////////////////////////

                            myStats.add(currentFood.getAllStats(baseStats));
                            myStats.add(currentUtil.getAllStats(baseStats));
                            myStats.add(baseStats);
                            myStats.add(boons);
                            myStats.add(otherbuffs);
                            myStats.add(banners);

                            if (args.isForFotm()) {
                                myStats.add(fotm);
                            }
                            ////////////////////////////////////////////////////////////
                            // filter results to fit expectations
                            ////////////////////////////////////////////////////////////

                            double critChance = min((myStats.Precision - 895) / 21, 100) / 100;
                            double feroModifier = (150 + myStats.Ferocity / 15) / 100;
                            double effectivePower = myStats.Power * (1 * (1 - critChance) + feroModifier * critChance);
                            double condiDuration = (myStats.Expertise/15)/100;

                            double effectiveCondiDamage = get_condi_damage(min(condiDuration + myBonus.get("Bleeding"),1), myStats.ConditionDamage, 22,
                                    0.06, args.getCondiPercentages().get("Bleeding"), args.getCondiDmg(), args.getCondiBonuses().get("Bleeding")) +
                                    get_condi_damage(min(condiDuration + myBonus.get("Burning"),1), myStats.ConditionDamage, 131,
                                            0.155, args.getCondiPercentages().get("Burning"), args.getCondiDmg(), args.getCondiBonuses().get("Burning")) +
                                    get_condi_damage(min(condiDuration + myBonus.get("Confusion"),1), myStats.ConditionDamage, 22,
                                            0.06, args.getCondiPercentages().get("Confusion"), args.getCondiDmg(), args.getCondiBonuses().get("Confusion")) +
                                    get_condi_damage(min(condiDuration + myBonus.get("Poison"),1), myStats.ConditionDamage, 33.5,
                                            0.06, args.getCondiPercentages().get("Poison"), args.getCondiDmg(), args.getCondiBonuses().get("Poison")) +
                                    get_condi_damage(min(condiDuration + myBonus.get("Torment"),1), myStats.ConditionDamage, 22,
                                            0.06, args.getCondiPercentages().get("Torment"), args.getCondiDmg(), args.getCondiBonuses().get("Torment"));


                            if (effectiveCondiDamage>currenthighest) {// && abs(myPrecision-2995) < 20) {
                                currenthighest = effectiveCondiDamage;
                                results.add(new Result(
                                        i,
                                        effectiveCondiDamage,
                                        min(100, ((myStats.Precision - 895) / 21)),
                                        myStats.Concentration / 15,
                                        myStats.Power + min(myStats.Precision, 2995) + myStats.Ferocity + myStats.Concentration,
                                        currentFood,
                                        currentUtil,
                                        currentRune,
                                        currentSigil)
                                );
                            }
                        }




                    }
                }
            }
        }


        System.out.println("[==============================]100%");

        ////////////////////////////////////////////////////////
        // sort results by efficiency
        ////////////////////////////////////////////////////////


        System.out.println(results.size());
        System.out.println("Sorting...");
        results.sort(Result::compareTo);

        ////////////////////////////////////////////////////////////
        // remove duplicates
        ////////////////////////////////////////////////////////////

        System.out.println("Removing duplicates...");
        int size = results.size();
        boolean flag = false;
        int removed = 0;
        for (int i = 0; i < size; i++) {fuckoff = 4;
            if (results.get(i).getEffectivePower() == 0) continue;
            for (int j = i+1; j < size; j++) {fuckoff = 5;
                if (j==size) break;
                if (abs(results.get(i).getEffectivePower() - results.get(j).getEffectivePower()) < 0.5
                        && abs(results.get(i).getBoonDuration() - results.get(j).getBoonDuration()) < 0.05
                        && results.get(i).getMyFood().getId() == results.get(j).getMyFood().getId()
                        && results.get(i).getMyUtil().getId() == results.get(j).getMyUtil().getId()) {
                    flag = true;
                    removed++;
                    results.set(j, new Result(0, 0, 0, 0, 0, new Food(0, "NaN", null), new Util(0, "NaN", null), new Rune(0,"NaN",null),new Sigil(0, "NaN", null)));
                } else if(flag) {
                    flag = false;
                    break;
                }

            }
        }

        results.sort(Result::compareTo);

        System.out.println(results.size()-removed);
        System.out.println("Done");


        DecimalFormat form = new DecimalFormat("0.00");
        String space = "\n";


        ////////////////////////////////////////////////////////////
        // print out results
        ////////////////////////////////////////////////////////////

        File file = new File("out.out");
        int i = 1;
        try{
            while (!file.createNewFile()){
                file.renameTo(new File("out_" + i + ".out"));
                i++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        PrintStream fileout;
        try{
            fileout = new PrintStream(file);
            i = 0;
            while (i<results.size()&&results.get(i).getEffectivePower()<1){
                i++;
            }
            for (; i<results.size(); i++){
                fileout.println(
                        "EP: "
                                + form.format(results.get(i).getEffectivePower()) + space + "Crit%: "
                                + form.format(results.get(i).getCritChance()) + space + "Boon%: "
                                + form.format(results.get(i).getBoonDuration()) + space + "Total: "
                                + form.format(results.get(i).getTotalStats()) + space + "Food: "
                                + results.get(i).getMyFood().getName() + space + "Util: "
                                + results.get(i).getMyUtil().getName() + space + "Runes: "
                                + results.get(i).getMyRune().getName() + space + "Sigils: "
                                + results.get(i).getMySigil().getName()
                                + sets.printSet(results.get(i).getHash()));
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }

    }


    public static void printResults(Collection<Result> results, Sets sets){
        DecimalFormat form = new DecimalFormat("0.00");
        DecimalFormat form2 = new DecimalFormat("000");
        String space = "\n";
        File file = new File("out_000.out");
        int i = 1;
        try{
            while (!file.createNewFile()){
                file = new File ("out_" + form2.format(i) + ".out");
                i++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        PrintStream fileout;
        try{
            fileout = new PrintStream(file);
            i = 0;
            //while (i<results.size()&&results.get(i).getEffectivePower()<1){
            //    i++;
            //}
            for (Result x: results){
                fileout.println(
                        "EP: "
                                + form.format(x.getEffectivePower()) + space + "Crit%: "
                                + form.format(x.getCritChance()) + space + "Boon%: "
                                + form.format(x.getBoonDuration()) + space + "Total: "
                                + form.format(x.getTotalStats()) + space + "Food: "
                                + x.getMyFood().getName() + space + "Util: "
                                + x.getMyUtil().getName() + space + "Runes: "
                                + x.getMyRune().getName() + space + "Sigils: "
                                + x.getMySigil().getName() + space + "Stats: " + space + "Power: "
                                + x.getStats().getPower() + space + "Precision: "
                                + x.getStats().getPrecision() + space + "Ferocity: "
                                + x.getStats().getFerocity() + space + "Concentration: "
                                + x.getStats().getConcentration() + space + "Toughness: "
                                + x.getStats().getToughness() + space + "Set: "
                                + sets.printSet(x.getHash()));
            }
            fileout.println("end");
        } catch (Exception exception){
                exception.printStackTrace();
        }
    }


 }



