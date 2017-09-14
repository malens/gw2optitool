package com.malens.opt;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

import static java.lang.Integer.min;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import com.malens.opt.Utilities.Result;
import com.malens.opt.Modifiers.*;
import com.malens.opt.Utilities.Stats;


public class Main {
    static String USAGE =
        "-cu/--countUtil [0/1] \n" +
        "-cf/--countFood [0/1]\n" +
        "-exc/--exclude [Head | Shoulders | Chest | Legs | Boots " +
                "| Hands | Back | Amulet | Ring1 | Ring2 | Accessory1 " +
                "| Accessory2 | Mainhand | Offhand]\n" +
        "-i/--include [Berserker/Assasin...etc]\n" +
        "-po/--power [int]\n" +
        "-pr/--precision\n" +
        "-f/--ferocity\n" +
        "-t/--toughness\n" +
        "-v/--vitality\n" +
        "-c/--concentration\n" +
        "-exp/--expertise\n" +
        "-hp/--healingpower\n" +
        "-cd/--conditiondamage\n" +
        "-cr/--countrunes\n" +
        "-cs/--countsigils";

    static Parser parser = new Parser();
    public static void main(String[] args) {

        parser.parse(args);

        if (args.length < 2 || parser.getParsedArgs().isHelp()) {
            System.out.println(USAGE);
            return;
        }



        //boolean countUtil = false;
        //boolean countFood = true;
        //boolean predefined = true;

        //Sets sets = new Sets();
        Sets sets = parser.getParsedArgs().getSets();
        System.out.println(sets.getSetsArray().size());
        System.out.println(parser.getParsedArgs().getSets().getSetsArray().size());
        ArrayList<Result> results = new ArrayList<>();


        int utilNum = 1;
        ArrayList<Util> utils = new ArrayList<Util>();
        if(parser.getParsedArgs().isCountUtil()){
            utils.add(new Util(0, "Toxic Maintenance Oil", p -> new Stats(0,0,0,0,0,(p.Power * 0.03 + p.ConditionDamage * 0.06) ,0,0,0)));
            utils.add(new Util(1, "Magnanimous Maintenance Oil", p -> new Stats(0,0,0,0,0,(p.Toughness * 0.03 + p.Vitality * 0.03) ,0,0,0)));
            utils.add(new Util(2, "Peppermint Oil", p -> new Stats(0,0,0,0,0,(p.Precision * 0.03 + p.HealingPower * 0.06) ,0,0,0)));
            utilNum = utils.size();
        } else utils.add(new Util(0, "Not counted in sim", p -> new Stats(0,0,0,0,0,0,0,0,0)));


        ArrayList<Food> foods = new ArrayList<>();
        if(parser.getParsedArgs().isCountFood()){
            foods.add(new Food(0, "Boon dur", p -> new Stats(0,0,0,0,0,100,0,0,0)));
            foods.add(new Food(1, "Power/fero", p -> new Stats(100,0,70,0,0,0,0,0,0)));
        } else foods.add(new Food(0, "Not counted in sim", p -> new Stats(0,0,0,0,0,0,0,0,0)));

        ArrayList<Rune> runes = new ArrayList<>();
        if(parser.getParsedArgs().isCountRunes()){
            runes.add(new Rune(0, "Rune of Leadership", p -> new Stats(36,36,36,36,36,30*15,0,36,36)));
            runes.add(new Rune(1, "Rune of Firebrand", p -> new Stats(0,0,0,0,0,35*15,0,0,175)));
        } else runes.add(new Rune(0, "Not counted in sim", p -> new Stats(0,0,0,0,0,0,0,0,0)));

        ArrayList<Sigil> sigils = new ArrayList<>();
        if(parser.getParsedArgs().isCountSigils()){
            sigils.add(new Sigil(0, "Sigil of Concentration", p -> new Stats(0,0,0,0,0,33*15,0,0,0)));
        } else sigils.add(new Sigil(0, "Not counted in sim", p -> new Stats(0,0,0,0,0,0,0,0,0)));


        Stats boons = new Stats(25*30,420,0,0,0,0,0,0,0);
        Stats otherbuffs = new Stats(0,150,0,0,0,0,0,0,0);
        Stats banners = new Stats(170,170,170,0,0,0,0,0,170);
        Stats fotm = new Stats (0,225,0,225,0,225,0,0,0);
        //Stats specialStats = new Stats (0,0,0,0, 63*15, 0,0,0,0);
        for (Rune currentRune : runes) {
            for (Sigil currentSigil : sigils) {
                for (Food currentFood : foods) {
                    for (Util currentUtil : utils) {
                        for (Integer i = 0; i <= pow(parser.getParsedArgs().getSets().getSetsArray().size(), 14); i++) {

                            ////////////////////////////////////////////////////////////
                            // initialize with base stats + runes and sigils
                            // (everything that counts towards food bonus)
                            ////////////////////////////////////////////////////////////

                            Stats baseStats = new Stats(1000, 1000, 0, 1000, 1000, 0, 0, 0, 0);
                            baseStats.add(currentRune.getAllStats(baseStats));
                            baseStats.add(currentSigil.getAllStats(baseStats));
                            baseStats.add(parser.getParsedArgs().getStats());

                            Stats myStats = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0);

                            ////////////////////////////////////////////////////////////
                            // add all gear stats
                            // (it also counts towards food bonuses)
                            ////////////////////////////////////////////////////////////

                            Integer j = i;
                            for (int k = 0; k < 14; k++) {
                                if ((!parser.getParsedArgs().getExcludeSet().contains(k))) {
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

                            if (parser.getParsedArgs().isForFotm()) {
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
                                        currentUtil)
                                );
                            }
                        }

                        ////////////////////////////////////////////////////////////
                        // remove duplicates
                        ////////////////////////////////////////////////////////////

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
                    }
                }
            }
        }

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


            for (int i = 0; i<results.size(); i++){
                System.out.println(
                        "EP: "
                        + form.format(results.get(i).getEffectivePower()) + space + "Crit%: "
                        + form.format(results.get(i).getCritChance()) + space + "Boon%: "
                        + form.format(results.get(i).getBoonDuration()) + space + "Total: "
                        + form.format(results.get(i).getTotalStats()) + space + "Food: "
                        + results.get(i).getMyFood().getName() + space + "Util: "
                        + results.get(i).getMyUtil().getName()
                        + sets.printSet(results.get(i).getHash()));
            }






    }
}
