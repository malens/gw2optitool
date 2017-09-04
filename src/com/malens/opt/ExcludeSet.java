package com.malens.opt;

import java.util.ArrayList;

/**
 * Created by malens on 2017-08-29.
 */
public class ExcludeSet {

    private ArrayList<Integer> list = new ArrayList<>();

    public ExcludeSet(int... i) {
        for (int k : i) {
            list.add(k);
        }
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public boolean contains(int k){
        for (int i = 0; i < list.size(); i++){
            if (k == list.get(i)) return true;
        }
        return false;
    }

    //public boolean divides (Integer i){
    //for (int j = 0; j < list.size(); j++){
    //        if
    //    }
    //}
}
