package com.malens.opt;

/**
 * Created by malens on 2017-09-09.
 */
public class test {
    public static void main(String[] args) {
        String str = "123";
        int val = 0;
        for (Character a : str.toCharArray()) {
            val = val * 10 + (a.charValue() - 48);
        }

        System.out.println(val);
    }
}
