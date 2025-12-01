package com.programando.model;

import java.util.function.Function;

public class Ejemplo {

    public static void aMethod(){
        final double taxRate = 0.12;
        Function<Double,Double> addTax = price -> price + taxRate * price;
    }
}
