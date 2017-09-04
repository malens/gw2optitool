package com.malens.opt;
import com.malens.opt.Set;
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
    private ArrayList<Set> SetsArray = new ArrayList<>();

    private void AddZerk() {
        Berserker.addToSet(new Item(63, 45, 45, 0, 0, 0, 0, "Helm"));
        Berserker.addToSet(new Item(47, 34, 34, 0, 0, 0, 0, "Shoulder"));
        Berserker.addToSet(new Item(141, 101, 101, 0, 0, 0, 0, "Chest"));
        Berserker.addToSet(new Item(47, 34, 34, 0, 0, 0, 0, "Hands"));
        Berserker.addToSet(new Item(94, 67, 67, 0, 0, 0, 0, "Legs"));
        Berserker.addToSet(new Item(47, 34, 34, 0, 0, 0, 0, "Boots"));
        Berserker.addToSet(new Item(157, 108, 108, 0, 0, 0, 0, "Amulet"));
        Berserker.addToSet(new Item(126, 85, 85, 0, 0, 0, 0, "Ring1"));
        Berserker.addToSet(new Item(126, 85, 85, 0, 0, 0, 0, "Ring2"));
        Berserker.addToSet(new Item(110, 74, 74, 0, 0, 0, 0, "Earring1"));
        Berserker.addToSet(new Item(110, 74, 74, 0, 0, 0, 0, "Earring2"));
        Berserker.addToSet(new Item(63, 40, 40, 0, 0, 0, 0, "Back"));
        Berserker.addToSet(new Item(125, 90, 90, 0, 0, 0, 0, "Mainhand"));
        Berserker.addToSet(new Item(125, 90, 90, 0, 0, 0, 0, "Offhand"));
    }

    private void AddComm() {
        Commander.addToSet(new Item(54, 54, 0, 30, 30, 0, 0, "Helm"));
        Commander.addToSet(new Item(40, 40, 0, 22, 22, 0, 0, "Shoulder"));
        Commander.addToSet(new Item(121, 121, 0, 67, 67, 0, 0, "Chest"));
        Commander.addToSet(new Item(40, 40, 0, 22, 22, 0, 0, "Hands"));
        Commander.addToSet(new Item(81, 81, 0, 44, 44, 0, 0, "Legs"));
        Commander.addToSet(new Item(40, 40, 0, 22, 22, 0, 0, "Boots"));
        Commander.addToSet(new Item(133, 133, 0, 71, 71, 0, 0, "Amulet"));
        Commander.addToSet(new Item(106, 106, 0, 56, 56, 0, 0, "Ring1"));
        Commander.addToSet(new Item(106, 106, 0, 56, 56, 0, 0, "Ring2"));
        Commander.addToSet(new Item(92, 92, 0, 49, 49, 0, 0, "Earring1"));
        Commander.addToSet(new Item(92, 92, 0, 49, 49, 0, 0, "Earring2"));
        Commander.addToSet(new Item(52, 52, 0, 27, 27, 0, 0, "Back"));
        Commander.addToSet(new Item(108, 108, 0, 59, 59, 0, 0, "Mainhand"));
        Commander.addToSet(new Item(108, 108, 0, 59, 59, 0, 0, "Offhand"));
    }

    private void AddAss() {
        Assassin.addToSet(new Item(45, 63, 45, 0, 0, 0, 0, "Helm"));
        Assassin.addToSet(new Item(34, 47, 34, 0, 0, 0, 0, "Shoulder"));
        Assassin.addToSet(new Item(101, 141, 101, 0, 0, 0, 0, "Chest"));
        Assassin.addToSet(new Item(34, 47, 34, 0, 0, 0, 0, "Hands"));
        Assassin.addToSet(new Item(67, 94, 67, 0, 0, 0, 0, "Legs"));
        Assassin.addToSet(new Item(34, 47, 34, 0, 0, 0, 0, "Boots"));
        Assassin.addToSet(new Item(108, 157, 108, 0, 0, 0, 0, "Amulet"));
        Assassin.addToSet(new Item(85, 126, 85, 0, 0, 0, 0, "Ring1"));
        Assassin.addToSet(new Item(85, 126, 85, 0, 0, 0, 0, "Ring2"));
        Assassin.addToSet(new Item(74, 110, 74, 0, 0, 0, 0, "Earring1"));
        Assassin.addToSet(new Item(74, 110, 74, 0, 0, 0, 0, "Earring2"));
        Assassin.addToSet(new Item(40, 63, 40, 0, 0, 0, 0, "Back"));
        Assassin.addToSet(new Item(90, 125, 90, 0, 0, 0, 0, "Mainhand"));
        Assassin.addToSet(new Item(90, 125, 90, 0, 0, 0, 0, "Offhand"));
    }

    public Sets() {
        AddZerk();
        AddComm();
        AddAss();
        SetsArray.add(Berserker);
        SetsArray.add(Commander);
        SetsArray.add(Assassin);
    }

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
