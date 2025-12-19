package principal;


import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class AppList {

    int x;

    public static<T> List<T> copy(List<T> list) {
        List<T> aux = new ArrayList<T>(list);
        return Collections.unmodifiableList(aux);
    }
    public static<T> List<T> addElem( List<T> list, T e) {
        List<T> aux = copy(list);
        aux.add(e);
        //Garantizar que no se modifique
        return Collections.unmodifiableList(aux);
    }
    public static<T> List<T> removeElem( List<T> list, T e) {
        List<T> aux = copy(list);
        aux.remove(e);
        //Garantizar que no se modifique
        return Collections.unmodifiableList(aux);
    }
    public static<T> Optional<T> head(List<T> list) {
        if(list.size()==0){
            return Optional.empty();
        }
        return Optional.of(list.get(0));
    }
    public static<T> Optional<List<T>> tail(List<T> list) {
        if(list.size()==0){
            return Optional.empty();
        } else if (list.size()==1) {
            return Optional.of(list);
        }
        List<T> aux = copy(list);
        aux.remove(list.get(0));
        //Garantizar que no se modifique
        return Optional.of(Collections.unmodifiableList(aux));
    }
    public static int factorial(int n) {
        return n==0?1:n*factorial(n-1);
    }

    public static Function<Integer,Integer> fact = null;
    //Bloques de inicializacion estaticos
    static{
        fact = n -> n == 0 ? 1 : n * fact.apply(n-1);
    }

    //5 EJEMPLOS RECURSIVOS

    //Fibonacci
    public static Function<Integer, Integer> fib = null;

    static {
        fib = n -> n <= 1 ? n : fib.apply(n - 1) + fib.apply(n - 2);
    }

    //Sumar n elementos
    public static Function<Integer, Integer> sumaN = null;

    static {
        sumaN = n -> n == 0 ? 0 : n + sumaN.apply(n - 1);
    }

    //Potenciacion
    public static BiFunction<Integer, Integer, Integer> potencia = null;

    static {
        potencia = (a, b) -> b == 0 ? 1 : a * potencia.apply(a, b - 1);
    }

    //Invertir un String
    public static Function<String, String> invertir = null;

    static {
        invertir = s -> s.isEmpty()
                ? ""
                : invertir.apply(s.substring(1)) + s.charAt(0);
    }

    //Contar las ocurrencias de un caracter
    public static BiFunction<String, Character, Integer> contarChar = null;

    static {
        contarChar = (s, c) ->
                s.isEmpty()
                        ? 0
                        : (s.charAt(0) == c ? 1 : 0) + contarChar.apply(s.substring(1), c);
    }




    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(1,2,3,4,5);
//        List<Integer> nuevo = addElem(list, 4);
//        System.out.println(nuevo);
//        List<Integer> nuevo2 = removeElem(list, 4);
//        System.out.println(nuevo2);
//
//        Function<Integer,Integer> factorial = fact;
//        System.out.println(fact.apply(7));
//
//        list.forEach(System.out::println);

        System.out.println("-------- Usando mi Lista ---------------");

        Lista<Integer> miLista = Lista.of(1,2,3,4,5);

        miLista.forEach(System.out::println);

    }
}


