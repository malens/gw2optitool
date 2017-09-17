package com.malens.opt.Simulator;

import com.malens.opt.Modifiers.Food;
import com.malens.opt.Modifiers.Rune;
import com.malens.opt.Modifiers.Sigil;
import com.malens.opt.Modifiers.Util;
import com.malens.opt.Parser;
import com.malens.opt.Set;
import com.malens.opt.Sets;
import com.malens.opt.Utilities.Math;
import com.malens.opt.Utilities.Result;
import com.malens.opt.Utilities.Stats;

import javax.swing.text.Utilities;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

/**
 * Created by malens on 2017-09-17.
 */
public class Simulator {

    public static void simulate (Parser.ParsedArgs args, ArrayList<Util> utils, ArrayList<Food> foods, ArrayList<Rune> runes, ArrayList<Sigil> sigils){

        Sets sets = args.getSets();
        System.out.println(sets.getSetsArray().size());
        System.out.println(args.getSets().getSetsArray().size());
        ArrayList<Result> results = new ArrayList<>();


        Stats boons = new Stats(25*30,420,0,0,0,0,0,0,0);
        Stats otherbuffs = new Stats(0,150,0,0,0,0,0,0,0);
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

                            double critChance = Double.min((myStats.Precision - 895) / 21, 100) / 100;
                            double feroModifier = (150 + myStats.Ferocity / 15) / 100;

                            double effectivePower = myStats.Power * (1 * (1 - critChance) + feroModifier * critChance);

                            if (abs(myStats.Concentration - 1500) < 15) {// && abs(myPrecision-2995) < 20) {
                                results.add(new Result(
                                        i,
                                        effectivePower,
                                        Double.min(100, ((myStats.Precision - 895) / 21)),
                                        myStats.Concentration / 15,
                                        myStats.Power + Double.min(myStats.Precision, 2995) + myStats.Ferocity + myStats.Concentration,
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

        ////////////////////////////////////////////////////////////
        // remove duplicates
        ////////////////////////////////////////////////////////////
        System.out.println("\n");

        System.out.println(results.size());
        int size = results.size();
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (abs(results.get(i).getEffectivePower() - results.get(j).getEffectivePower()) < 0.5
                        && abs(results.get(i).getBoonDuration() - results.get(j).getBoonDuration()) < 0.05
                        && results.get(i).getMyFood().getId() == results.get(j).getMyFood().getId()
                        && results.get(i).getMyUtil().getId() == results.get(j).getMyUtil().getId()) {
                    results.remove(j);
                    size--;
                }

            }
        }
        System.out.println("Sorting...");
        ////////////////////////////////////////////////////////
        // sort results by efficiency
        ////////////////////////////////////////////////////////

        results.sort(Result::compareTo);
        System.out.println(results.size());

        DecimalFormat form = new DecimalFormat("0.00");
        String space = "\n";


        ////////////////////////////////////////////////////////////
        // print out results
        ////////////////////////////////////////////////////////////

/*
        for (int i = 0; i<results.size(); i++){
            System.out.println(
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
        }*/






    }

 }



