package com.malens.opt;
import com.malens.opt.Set;
import com.malens.opt.Utilities.Stats;
import com.sun.jndi.ldap.Ber;
import org.omg.CORBA.COMM_FAILURE;

import java.util.ArrayList;

import static java.lang.Integer.max;

/**
 * Created by malens on 2017-08-29.
 */
public class Sets {
    private Set Berserker = new Set("Berserker");
    private Set Commander = new Set("Commander");
    private Set Assassin = new Set("Assassin");
    private Set Sinister = new Set("Sinister");
    private Set Viper = new Set("Viper");
    private Set Minstrel = new Set ("Minstrel");
    private Set Nomad = new Set("Nomad");

    private ArrayList<Set> SetsArray = new ArrayList<>();

    public void AddByName (String name){
        switch (name.toLowerCase()){
            case "berserker" :
                AddZerk();
                SetsArray.add(Berserker);
                break;
            case "assassin" :
                AddAss();
                SetsArray.add(Assassin);
                break;
            case "commander" :
                AddComm();
                SetsArray.add(Commander);
                break;
            case "viper" :
                AddViper();
                SetsArray.add(Viper);
                break;
            case"sinister":
                AddSinister();
                SetsArray.add(Sinister);
                break;
            case "minstrel":
                AddMinstrel();
                SetsArray.add(Minstrel);
                break;
            case "nomad":
                AddNomad();
                SetsArray.add(Nomad);
                break;
            default:
                return;
        }
    }

    private void AddZerk() {
        Berserker.addToSet(new Item(new Stats(63, 45, 45, 0, 0, 0, 0, 0, 0), "Helm"));
        Berserker.addToSet(new Item(new Stats(47, 34, 34, 0, 0, 0,0,0, 0), "Shoulder"));
        Berserker.addToSet(new Item(new Stats(141, 101, 101, 0, 0,0,0, 0, 0), "Chest"));
        Berserker.addToSet(new Item(new Stats(47, 34, 34, 0, 0, 0,0,0, 0), "Hands"));
        Berserker.addToSet(new Item(new Stats(94, 67, 67, 0, 0, 0,0,0, 0), "Legs"));
        Berserker.addToSet(new Item(new Stats(47, 34, 34, 0, 0, 0,0,0, 0), "Boots"));
        Berserker.addToSet(new Item(new Stats(157, 108, 108, 0, 0,0,0, 0, 0), "Amulet"));
        Berserker.addToSet(new Item(new Stats(126, 85, 85, 0, 0,0,0, 0, 0), "Ring1"));
        Berserker.addToSet(new Item(new Stats(126, 85, 85, 0, 0,0,0, 0, 0), "Ring2"));
        Berserker.addToSet(new Item(new Stats(110, 74, 74, 0, 0,0,0, 0, 0), "Earring1"));
        Berserker.addToSet(new Item(new Stats(110, 74, 74, 0, 0,0,0, 0, 0), "Earring2"));
        Berserker.addToSet(new Item(new Stats(63, 40, 40, 0, 0, 0,0,0, 0), "Back"));
        Berserker.addToSet(new Item(new Stats(125, 90, 90, 0, 0,0,0, 0, 0), "Mainhand"));
        Berserker.addToSet(new Item(new Stats(125, 90, 90, 0, 0,0,0, 0, 0), "Offhand"));
    }

    private void AddMinstrel() {
        Minstrel.addToSet(new Item(new Stats(0, 0, 0, 54, 30, 30, 0, 54, 0), "Helm"));
        Minstrel.addToSet(new Item(new Stats(0, 0, 0, 40, 22, 22, 0, 40, 0), "Shoulder"));
        Minstrel.addToSet(new Item(new Stats(0, 0, 0, 121, 67, 67, 0, 121, 0), "Chest"));
        Minstrel.addToSet(new Item(new Stats(0, 0, 0, 40, 22, 22, 0, 40, 0), "Hands"));
        Minstrel.addToSet(new Item(new Stats(0, 0, 0, 81, 44, 44, 0, 81, 0), "Legs"));
        Minstrel.addToSet(new Item(new Stats(0, 0, 0, 40, 22, 22, 0, 40, 0), "Boots"));
        Minstrel.addToSet(new Item(new Stats(0, 0, 0, 133, 71, 71, 0, 133, 0), "Amulet"));
        Minstrel.addToSet(new Item(new Stats(0, 0, 0, 106, 56, 56, 0, 106, 0), "Ring1"));
        Minstrel.addToSet(new Item(new Stats(0, 0, 0, 106, 56, 56, 0, 106, 0), "Ring2"));
        Minstrel.addToSet(new Item(new Stats(0, 0, 0, 92, 49, 49, 0, 92, 0), "Earring1"));
        Minstrel.addToSet(new Item(new Stats(0, 0, 0, 92, 49, 49, 0, 92, 0), "Earring2"));
        Minstrel.addToSet(new Item(new Stats(0, 0, 0, 52, 27, 27, 0, 52, 0), "Back"));
        Minstrel.addToSet(new Item(new Stats(0, 0, 0, 108, 59, 59, 0, 108, 0), "Mainhand"));
        Minstrel.addToSet(new Item(new Stats(0, 0, 0, 108, 59, 59, 0, 108, 0), "Offhand"));
    }


    private void AddComm() {
        Commander.addToSet(new Item(new Stats(54, 54, 0, 30, 0, 30, 0, 0, 0), "Helm"));
        Commander.addToSet(new Item(new Stats(40, 40, 0, 22, 0, 22, 0, 0, 0), "Shoulder"));
        Commander.addToSet(new Item(new Stats(121, 121, 0, 67, 0, 67, 0, 0, 0), "Chest"));
        Commander.addToSet(new Item(new Stats(40, 40, 0, 22, 0, 22, 0, 0, 0), "Hands"));
        Commander.addToSet(new Item(new Stats(81, 81, 0, 44, 0, 44, 0, 0, 0), "Legs"));
        Commander.addToSet(new Item(new Stats(40, 40, 0, 22, 0, 22, 0, 0, 0), "Boots"));
        Commander.addToSet(new Item(new Stats(133, 133, 0, 71, 0, 71, 0, 0, 0), "Amulet"));
        Commander.addToSet(new Item(new Stats(106, 106, 0, 56, 0, 56, 0, 0, 0), "Ring1"));
        Commander.addToSet(new Item(new Stats(106, 106, 0, 56, 0, 56, 0, 0, 0), "Ring2"));
        Commander.addToSet(new Item(new Stats(92, 92, 0, 49, 0, 49, 0, 0, 0), "Earring1"));
        Commander.addToSet(new Item(new Stats(92, 92, 0, 49, 0, 49, 0, 0, 0), "Earring2"));
        Commander.addToSet(new Item(new Stats(52, 52, 0, 27, 0, 27, 0, 0, 0), "Back"));
        Commander.addToSet(new Item(new Stats(108, 108, 0, 59, 0, 59, 0, 0, 0), "Mainhand"));
        Commander.addToSet(new Item(new Stats(108, 108, 0, 59, 0, 59, 0, 0, 0), "Offhand"));
    }

    private void AddAss() {
        Assassin.addToSet(new Item(new Stats(45, 63, 45, 0, 0, 0, 0,0,0), "Helm"));
        Assassin.addToSet(new Item(new Stats(34, 47, 34, 0, 0, 0, 0,0,0), "Shoulder"));
        Assassin.addToSet(new Item(new Stats(101, 141, 101, 0, 0, 0, 0, 0, 0), "Chest"));
        Assassin.addToSet(new Item(new Stats(34, 47, 34, 0, 0, 0, 0, 0, 0), "Hands"));
        Assassin.addToSet(new Item(new Stats(67, 94, 67, 0, 0, 0, 0, 0, 0), "Legs"));
        Assassin.addToSet(new Item(new Stats(34, 47, 34, 0, 0, 0, 0, 0, 0), "Boots"));
        Assassin.addToSet(new Item(new Stats(108, 157, 108, 0, 0, 0, 0, 0, 0), "Amulet"));
        Assassin.addToSet(new Item(new Stats(85, 126, 85, 0, 0, 0, 0, 0, 0), "Ring1"));
        Assassin.addToSet(new Item(new Stats(85, 126, 85, 0, 0, 0, 0, 0, 0), "Ring2"));
        Assassin.addToSet(new Item(new Stats(74, 110, 74, 0, 0, 0, 0, 0, 0), "Earring1"));
        Assassin.addToSet(new Item(new Stats(74, 110, 74, 0, 0, 0, 0, 0, 0), "Earring2"));
        Assassin.addToSet(new Item(new Stats(40, 63, 40, 0, 0, 0, 0, 0, 0), "Back"));
        Assassin.addToSet(new Item(new Stats(90, 125, 90, 0, 0, 0, 0, 0, 0), "Mainhand"));
        Assassin.addToSet(new Item(new Stats(90, 125, 90, 0, 0, 0, 0, 0, 0), "Offhand"));
    }

    private void AddViper() {
        Viper.addToSet(new Item(new Stats(54, 30, 0, 0, 0, 0, 30,0,54), "Helm"));
        Viper.addToSet(new Item(new Stats(40, 22, 0, 0, 0, 0, 22,0,40), "Shoulder"));
        Viper.addToSet(new Item(new Stats(121, 67, 0, 0, 0, 0, 67, 0, 121), "Chest"));
        Viper.addToSet(new Item(new Stats(40, 22, 0, 0, 0, 0, 22, 0, 40), "Hands"));
        Viper.addToSet(new Item(new Stats(81, 44, 0, 0, 0, 0, 44, 0, 81), "Legs"));
        Viper.addToSet(new Item(new Stats(40, 22, 0, 0, 0, 0, 22, 0, 40), "Boots"));
        Viper.addToSet(new Item(new Stats(133, 71, 0, 0, 0, 0, 71, 0, 133), "Amulet"));
        Viper.addToSet(new Item(new Stats(106, 56, 0, 0, 0, 0, 56, 0, 106), "Ring1"));
        Viper.addToSet(new Item(new Stats(106, 56, 0, 0, 0, 0, 56, 0, 106), "Ring2"));
        Viper.addToSet(new Item(new Stats(92, 49, 0, 0, 0, 0, 49, 0, 92), "Earring1"));
        Viper.addToSet(new Item(new Stats(92, 49, 0, 0, 0, 0, 49, 0, 92), "Earring2"));
        Viper.addToSet(new Item(new Stats(52, 27, 0, 0, 0, 0, 27, 0, 52), "Back"));
        Viper.addToSet(new Item(new Stats(108, 59, 0, 0, 0, 0, 59, 0, 108), "Mainhand"));
        Viper.addToSet(new Item(new Stats(108, 59, 0, 0, 0, 0, 59, 0, 108), "Offhand"));
    }

    private void AddSinister() {
        Sinister.addToSet(new Item(new Stats(45, 45, 0, 0, 0, 0, 0,0,63), "Helm"));
        Sinister.addToSet(new Item(new Stats(34, 34, 0, 0, 0, 0, 0,0,47), "Shoulder"));
        Sinister.addToSet(new Item(new Stats(101, 101, 0, 0, 0, 0, 0, 0, 141), "Chest"));
        Sinister.addToSet(new Item(new Stats(34, 34, 0, 0, 0, 0, 0, 0, 47), "Hands"));
        Sinister.addToSet(new Item(new Stats(67, 67, 0, 0, 0, 0, 0, 0, 94), "Legs"));
        Sinister.addToSet(new Item(new Stats(34, 34, 0, 0, 0, 0, 0, 0, 47), "Boots"));
        Sinister.addToSet(new Item(new Stats(108, 108, 0, 0, 0, 0, 0, 0, 157), "Amulet"));
        Sinister.addToSet(new Item(new Stats(85, 85, 0, 0, 0, 0, 0, 0, 126), "Ring1"));
        Sinister.addToSet(new Item(new Stats(85, 85, 0, 0, 0, 0, 0, 0, 126), "Ring2"));
        Sinister.addToSet(new Item(new Stats(74, 74, 0, 0, 0, 0, 0, 0, 110), "Earring1"));
        Sinister.addToSet(new Item(new Stats(74, 74, 0, 0, 0, 0, 0, 0, 110), "Earring2"));
        Sinister.addToSet(new Item(new Stats(40, 40, 0, 0, 0, 0, 0, 0, 63), "Back"));
        Sinister.addToSet(new Item(new Stats(90, 90, 0, 0, 0, 0, 0, 0, 125), "Mainhand"));
        Sinister.addToSet(new Item(new Stats(90, 90, 0, 0, 0, 0, 0, 0, 125), "Offhand"));
    }
    private void AddNomad() {
        Nomad.addToSet(new Item(new Stats(0, 0, 0, 63, 45, 0, 0,45,0), "Helm"));
        Nomad.addToSet(new Item(new Stats(0, 0, 0, 47, 34, 0, 0,34,0), "Shoulder"));
        Nomad.addToSet(new Item(new Stats(0, 0, 0, 141, 101, 0, 0, 101, 0), "Chest"));
        Nomad.addToSet(new Item(new Stats(0, 0, 0, 47, 34, 0, 0, 34, 0), "Hands"));
        Nomad.addToSet(new Item(new Stats(0, 0, 0, 94, 67, 0, 0, 67, 0), "Legs"));
        Nomad.addToSet(new Item(new Stats(0, 0, 0, 47, 34, 0, 0, 34, 0), "Boots"));
        Nomad.addToSet(new Item(new Stats(0, 0, 0, 157, 108, 0, 0, 108, 0), "Amulet"));
        Nomad.addToSet(new Item(new Stats(0, 0, 0, 126, 85, 0, 0, 85, 0), "Ring1"));
        Nomad.addToSet(new Item(new Stats(0, 0, 0, 126, 85, 0, 0, 85, 0), "Ring2"));
        Nomad.addToSet(new Item(new Stats(0, 0, 0, 110, 74, 0, 0, 74, 0), "Earring1"));
        Nomad.addToSet(new Item(new Stats(0, 0, 0, 110, 74, 0, 0, 74, 0), "Earring2"));
        Nomad.addToSet(new Item(new Stats(0, 0, 0, 63, 40, 0, 0, 40, 0), "Back"));
        Nomad.addToSet(new Item(new Stats(0, 0, 0, 125, 90, 0, 0, 90, 0), "Mainhand"));
        Nomad.addToSet(new Item(new Stats(0, 0, 0, 125, 90, 0, 0, 90, 0), "Offhand"));
    }





    public Sets() {
    }
     /*   AddZerk();
        AddComm();
        AddAss();
        SetsArray.add(Berserker);
        SetsArray.add(Commander);
        SetsArray.add(Assassin);
    }*/

    public ArrayList<Set> getSetsArray() {
        return SetsArray;
    }

    public Set getBerserker() {
        return Berserker;
    }

    public Set getCommander() {
        return Commander;
    }

    public Set getAssassin() {
        return Assassin;
    }

    public String printSet(int hash){
        String result = "";
        String space = "\n";
        for (int i = 0; i<14; i++){
            result += space + this.getSetsArray().get(hash%SetsArray.size()).getSet().get(i).getPiece() + " " + this.getSetsArray().get(hash%SetsArray.size()).getStatName();
            hash /= SetsArray.size();
        }
        return result;
    }
}
