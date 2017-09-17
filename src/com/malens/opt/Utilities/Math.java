package com.malens.opt.Utilities;

/**
 * Created by malens on 2017-09-17.
 */
public class Math {

    public static Integer pow (Integer a, Integer b){

            if ( b == 0) return 1;
            if ( b == 1) return a;
            if (b%2==0)return pow (a * a,b/2); //even a=(a^2)^b/2
            else return a * pow (a * a,b/2); //odd  a=a*(a^2)^b/2
    }

}
