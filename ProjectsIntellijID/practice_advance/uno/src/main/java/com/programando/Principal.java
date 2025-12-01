package com.programando;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Principal {
    public static String mensaje;
    public static void main(String[] args) {
        final double taxRate = 0.12;
        Function<Double,Double> addTax = price -> price + taxRate * price;

        List<Double> precioProductos = List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0);
        List<Double> totales = new ArrayList<>();

        Function<List<Double>,List<Double>> valosTotales = new Function<>() {
            @Override
            public List<Double> apply(List<Double> precioProductos) {
                for(double precio: precioProductos){
                    totales.add(addTax.apply(precio));
                }
                return totales;
            }
        };


        List<Double> precioTotales = valosTotales.apply(precioProductos);
        precioTotales.forEach(System.out::println);

        Function<Integer, Optional<Integer>> fn = x -> {
            if(x == 0){
                return Optional.empty();
            }else{
                return Optional.of(1/x);
            }
        };

        Optional<Integer> valor = fn.apply(0);
        System.out.println(valor);

        System.out.println("--------------------- Division x/y ------------------------------");

        Function<Integer, Function<Integer, Optional<Integer>>> dividir =
                x -> y -> {
                    if (y == 0) {
                        return Optional.empty();
                    } else {
                        return Optional.of(x / y);
                    }
                };

        Function<Integer, Optional<Integer>> dividirPor2 = dividir.apply(10); // x = 10

        Optional<Integer> resultado = dividirPor2.apply(2); // y = 2
        System.out.println(resultado); // Optional[5]

        Optional<Integer> error = dividir.apply(10).apply(0);
        System.out.println(error); // Optional.empty

        //Dada una cadena de caracteres, crear la funcion que devuelva el primer caracter de la cadena de caracteres
        System.out.println("----------- Primer caracter de la cadena de caracteres ------------");
        Function<String, Optional<Character>> primerCaracter =
                s -> (s == null || s.isEmpty())
                        ? Optional.empty()
                        : Optional.of(s.charAt(0));
        System.out.println(primerCaracter.apply("Hola"));
        System.out.println(primerCaracter.apply("A"));
        System.out.println(primerCaracter.apply(""));
        System.out.println(primerCaracter.apply(null));



    }
}
