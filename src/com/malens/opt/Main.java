package com.malens.opt;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

import static java.lang.Integer.min;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Main {

    public static void main(String[] args) {

        class Food{
            int id;
            String name;
            public Food(int id, String name) {
                this.id = id;
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }
        }


        class Result implements Comparable<Result>{
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

            public int compareTo2(Result o) {

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

            int hash;
            double EffectivePower;
            double CritChance;
            double BoonDuration;
            double TotalStats;
            Food myFood;

            public Result(int hash, double effectivePower, double critChance, double boonDuration, double totalStats, Food food) {
                this.hash = hash;
                EffectivePower = effectivePower;
                CritChance = critChance;
                BoonDuration = boonDuration;
                TotalStats = totalStats;
                myFood = food;
            }
            public Result(int hash, double effectivePower, double critChance, double boonDuration, double totalStats) {
                this.hash = hash;
                EffectivePower = effectivePower;
                CritChance = critChance;
                BoonDuration = boonDuration;
                TotalStats = totalStats;
                myFood = new Food(0,"Not Applicable");
            }


            Comparator<Result> byPower = new ResultComparator();

            class ResultComparator implements Comparator<Result>{

                @Override
                public int compare(Result o1, Result o2) {

                    if (o1.EffectivePower == o2.EffectivePower) {
                        if (o1.BoonDuration == o2.BoonDuration) return 0;
                        if (o1.BoonDuration < o2.BoonDuration) return -1;
                        else return 1;
                    };
                    if (o1.EffectivePower < o2.EffectivePower) return -1;
                    else return 1;
                }
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

        boolean countUtil = false;
        boolean countFood = true;
        boolean predefined = true;

        Sets sets = new Sets();
        int totalConc = 1500;
        int totalPrec = 2995;
        ArrayList<Result> results = new ArrayList<>();
        //exclude certain pieces (for example not wanting to statswap pieces with runes/sigils)
        ExcludeSet excludeSet = new ExcludeSet();
        if (predefined)
        {
            excludeSet = new ExcludeSet(0,1,2,3,4,5,13);
        }

        int utilNum = 1;
        ArrayList<Food> utils = new ArrayList<Food>();
        if(countUtil){
            utils.add(new Food(0, "Toxic Maintenance Oil"));
            utils.add(new Food(1, "Magnanimous Maintenance Oil"));
            utils.add(new Food(2, "Peppermint Oil"));
            utilNum = utils.size();
        }



     for (int m = 0; m <utilNum; m++)
        for (Integer i = 0; i <= pow(sets.getSetsArray().size(), 14); i++){
            //if (i%100==0) System.out.println(i);
            double myPower = 36 + 150 + 170 + 1000 + 30*25;// + 100;
            double myFerocity = 36 + 170;// + 70;
            double myPrecision = 36 + 420 + 150 + 170 + 1000;
            double myConc = 33*15 + 30*15 + 100;
            /*/manually add stats for predecided pieces, or fotm stats

             */
            if (predefined){
                myPower += 551;
                myFerocity += 338;
                myPrecision += 419;
                myConc += 44;
                myConc += 225;
                myPrecision += 225;
            }

            //*/
            if (countUtil){
                switch (m){
                    case 0 : myConc += (1036) * 0.03;
                        break;
                    case 1 : myConc += (2072) * 0.03;
                        break;
                    case 2 : myConc += (1072) * 0.03;
                        break;
                    default: break;
                }
            }



            Integer j = i;
            for (int k = 0; k < 14; k++) {
                if ((!excludeSet.contains(k))||(!predefined)) {
                    myPower += sets.getSetsArray().get(j % sets.getSetsArray().size()).getSet().get(k).getPower();
                    myPrecision += sets.getSetsArray().get(j % sets.getSetsArray().size()).getSet().get(k).getPrecision();
                    myFerocity += sets.getSetsArray().get(j % sets.getSetsArray().size()).getSet().get(k).getFerocity();
                    myConc += sets.getSetsArray().get(j % sets.getSetsArray().size()).getSet().get(k).getConcentration();
                    if (countUtil) {
                        switch (m) {
                            case 0:
                                myConc += sets.getSetsArray().get(j % sets.getSetsArray().size()).getSet().get(k).getPower() * 0.03;
                                break;
                            case 1:
                                myConc += sets.getSetsArray().get(j % sets.getSetsArray().size()).getSet().get(k).getToughness() * 0.03;
                                break;
                            case 2:
                                myConc += sets.getSetsArray().get(j % sets.getSetsArray().size()).getSet().get(k).getPrecision() * 0.03;
                                break;
                            default:
                                break;
                        }
                    }


                }
                j/=sets.getSetsArray().size();
            }



            double critChance = Double.min ((myPrecision - 895)/21, 100) / 100;
            double feroModifier = (150 + myFerocity/ 15) / 100;

            double effectivePower = myPower * (1 * (1 - critChance) +  feroModifier * critChance);

            if (abs(myConc-1500) < 15){// && abs(myPrecision-2995) < 20) {
                if (countUtil){
                    results.add(new Result(i, effectivePower, Double.min(100, ((myPrecision - 895) / 21)), myConc/15, myPower + Double.min(myPrecision, 2995) + myFerocity + myConc, utils.get(m)));
                } else results.add(new Result(i, effectivePower, Double.min(100, ((myPrecision - 895) / 21)), myConc/15, myPower + Double.min(myPrecision, 2995) + myFerocity + myConc));

                //System.out.println(i+" "+ effectivePower + " " + Double.min(100, ((myPrecision - 895) / 21)) + " " + myConc/15 + " prec: " + myPrecision);
            }
        }


        System.out.println(results.size());
        int size = results.size();
        for (int i = 0; i<size; i++){
            for (int j = i; j<size; j++){
                if (abs(results.get(i).EffectivePower - results.get(j).EffectivePower) < 0.5 && abs(results.get(i).BoonDuration - results.get(j).BoonDuration) < 0.05 &&
                        results.get(i).myFood.getId() == results.get(j).myFood.getId())
                {
                    results.remove(j);
                    size--;
                }

            }
        }
        results.sort(Result::compareTo);
        System.out.println(results.size());

        DecimalFormat form = new DecimalFormat("0.00");
        String space = "\n";

        for (int i = 0; i<results.size(); i++){
            //System.out.println(results.get(i).toString());
            System.out.println("EP: "
                    + form.format(results.get(i).EffectivePower) + space + "Crit%: "
                    + form.format(results.get(i).CritChance) + space + "Boon%: "
                    + form.format(results.get(i).BoonDuration) + space + "Total: "
                    + form.format(results.get(i).TotalStats) + space + "Food: "
                    + results.get(i).myFood.getName()
                    + sets.printSet(results.get(i).hash));
        }





    }
}
