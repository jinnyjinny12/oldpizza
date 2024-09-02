package com.ohgiraffers.pizzahouse.utill;

import java.util.regex.Pattern;

public class Utills {

    public static boolean regex(String input){
        String regex = "^[가-힣]+$";
        boolean result = Pattern.matches(regex, input);
        return result;
    }
}
