package com.programando.listas;

import java.util.*;
import java.util.function.Function;

public class AppList {

    public static<T> List<T> add(List<T> list, T element) {
        List<T> tmp = new ArrayList<T>(list);
        tmp.add(element);
        return Collections.unmodifiableList(tmp);
    }

    public static<T> List<T> remove(List<T> list, T element) {
        List<T> tmp = new ArrayList<T>(list);
        tmp.remove(element);
        return Collections.unmodifiableList(tmp);
    }

    public static int factorial(int n){
        return n==0?1:n*factorial(n-1);
    }

    public static Function<Integer,Integer> fact = null;
    static{
        fact = n-> n == 0 ? 1: n*fact.apply(n-1);
    }

    public static int sum(List<Integer> list) {
        return list.isEmpty()
                ? 0
                : list.get(0) + sum(list.subList(1, list.size()));
    }
    public static Function<List<Integer>,Integer> sum = null;
    static{
        sum = miList -> miList.isEmpty()? 0: miList.get(0) + sum(miList.subList(1, miList.size()));
    }

    public static void main(String[] args) {

        List<Integer> datos = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> nuevo=add(datos,8);
        System.out.println(datos.toString());
        System.out.println(nuevo);
        List<Integer> n = remove(nuevo,5);
        System.out.println(n);

        List<Integer> numeros = Arrays.asList(1,2,3);
        System.out.println(sum(numeros));

        Lista<Integer> numerosEnLista = Lista.of(1,2,3);
        System.out.println(numerosEnLista);

    }
}
