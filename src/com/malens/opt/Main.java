package com.malens.opt;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

import static java.lang.Integer.min;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

import com.malens.opt.Simulator.Simulator;
import com.malens.opt.Utilities.CondiBonus;
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
                    "-i/--include [Berserker | Assassin | Commander]\n" +
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
                    "-cs/--countsigils\n" +
                    "-ff/--fotm\n" +
                    "-boon% target boon %\n";

    static Parser parser = new Parser();

    public static void main(String[] args) {

        boolean chrono = false;
        boolean condi = false;

        switch (args[0]){
            case "condi" :
                condi = true;
                break;

            case "chrono" :
                chrono = true;
                break;

            default: return;
        }

        String arg[] = System.console().readLine().split(" ");

        parser.parse(arg);


        //for (String key : parser.getParsedArgs().getCondiPercentages().getBonuses().keySet()){
        //    System.out.println(key + " " + parser.getParsedArgs().getCondiPercentages().get(key));
        //}


        if (parser.getParsedArgs().isHelp()) {
            System.out.println(USAGE);
            return;
        }


        if (chrono) {
            ArrayList<Util> utils = new ArrayList<Util>();
            if (parser.getParsedArgs().isCountUtil()) {
                utils.add(new Util(0, "Toxic Maintenance Oil", p -> new Stats(0, 0, 0, 0, 0, (int)java.lang.Math.round(p.Power * 0.03) + (int)java.lang.Math.round(p.ConditionDamage * 0.06), 0, 0, 0)));
                utils.add(new Util(1, "Magnanimous Maintenance Oil", p -> new Stats(0, 0, 0, 0, 0, (int)java.lang.Math.round(p.Toughness * 0.03) + (int)java.lang.Math.round(p.Vitality * 0.03), 0, 0, 0)));
                utils.add(new Util(2, "Peppermint Oil", p -> new Stats(0, 0, 0, 0, 0, (int)java.lang.Math.round((p.Precision * 0.03 + p.HealingPower * 0.06)), 0, 0, 0)));
                utils.add(new Util(3, "Tin of Fruitcake", p -> new Stats((int)java.lang.Math.round(p.Precision * 0.03) + (int)java.lang.Math.round(p.Ferocity * 0.06), 0, 0, 0, 0, 0, 0, 0, 0)));

            } else utils.add(new Util(0, "Not counted in sim", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0)));


            ArrayList<Food> foods = new ArrayList<>();
            if (parser.getParsedArgs().isCountFood()) {
                foods.add(new Food(0, "Boon dur", p -> new Stats(0, 0, 0, 0, 0, 100, 0, 0, 0)));
                foods.add(new Food(1, "Power/fero", p -> new Stats(100, 0, 70, 0, 0, 0, 0, 0, 0)));
            } else foods.add(new Food(0, "Not counted in sim", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0)));

            ArrayList<Rune> runes = new ArrayList<>();
            if (parser.getParsedArgs().isCountRunes()) {
                runes.add(new Rune(0, "Rune of Leadership", p -> new Stats(36, 36, 36, 36, 36, 30 * 15, 0, 36, 36)));
                runes.add(new Rune(1, "Rune of Firebrand", p -> new Stats(0, 0, 0, 0, 0, 35 * 15, 0, 0, 175)));
                runes.add(new Rune(2, "Scholar", p -> new Stats(175, 0, 100, 0, 0, 0, 0, 0, 0)));
                runes.add(new Rune(3, "Herald", p -> new Stats(0, 0, 0, 175, 0, 15*15, 0, 0, 0)));
            } else runes.add(new Rune(0, "Not counted in sim", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0)));

            ArrayList<Sigil> sigils = new ArrayList<>();
            if (parser.getParsedArgs().isCountSigils()) {
                sigils.add(new Sigil(0, "Sigil of Concentration", p -> new Stats(0, 0, 0, 0, 0, 33 * 15, 0, 0, 0)));
            } else sigils.add(new Sigil(0, "Not counted in sim", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0)));


            Simulator.simulate(parser.getParsedArgs(), utils, foods, runes, sigils);
        }

        if (condi){
            ArrayList<Util> utils = new ArrayList<Util>();
            utils.add(new Util(0, "Not counted in sim", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0)));


            ArrayList<Food> foods = new ArrayList<>();
            if (parser.getParsedArgs().isCountFood()) {
                foods.add(new Food(0, "Condi dur", p -> new Stats(0, 0, 0, 0, 0, 0, 100, 0, 70)));
            } else foods.add(new Food(0, "Not counted in sim", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0)));

            ArrayList<Rune> runes = new ArrayList<>();
            if (parser.getParsedArgs().isCountRunes()) {
                CondiBonus x = new CondiBonus();
                runes.add(new Rune(0, "Nightmare/Trapper", p -> new Stats(0, 0, 0, 0, 0, 0, 25*15, 0, 100)));
                x.add("Torment", 0.45);
                runes.add(new Rune(1, "Rune of Tormenting", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, 175), x));
                CondiBonus y = new CondiBonus();
                y.add("Bleeding", 0.45);
                runes.add(new Rune(2, "Rune of the Krait", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, 175), y));
                runes.add(new Rune(3, "Rune of Berserker", p -> new Stats((int)java.lang.Math.round((p.getPower()*0.05)) + 100, 0, 0, 0, 0, 0, 0, 0, (int)java.lang.Math.round((p.getConditionDamage()*0.05 + 175)))));


            } else runes.add(new Rune(0, "Not counted in sim", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0)));



            ArrayList<Sigil> sigils = new ArrayList<>();
            if (parser.getParsedArgs().isCountSigils()){
                CondiBonus x = new CondiBonus();
                x.add("Burning", 0.25);
                sigils.add(new Sigil(0, "Smoldering", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0), x));
                CondiBonus x1 = new CondiBonus();
                x1.add("Poison", 0.25);
                sigils.add(new Sigil(1, "Venom", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0), x1));
                CondiBonus x2 = new CondiBonus();
                x2.add("Bleeding", 0.25);
                sigils.add(new Sigil(2, "Agony", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0), x2));
                sigils.add(new Sigil(3, "Malice", p -> new Stats(0, 0, 0, 0, 0, 0, 150, 0, 0)));
                sigils.add(new Sigil(4, "Bursting", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, (int)java.lang.Math.round((p.getConditionDamage()*0.06)))));
                sigils.add(new Sigil(5, "Geomancy", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0)));
            } else sigils.add(new Sigil(0, "Not counted in sim", p -> new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0)));




            Simulator.simulate_condi(parser.getParsedArgs(), utils, foods, runes, sigils);
        }

    }
}