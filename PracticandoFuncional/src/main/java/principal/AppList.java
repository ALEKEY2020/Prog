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

        Lista<Integer> nuevaList = miLista.take(2);
        nuevaList.forEach(System.out::println);

        System.out.println("-----------------------");

        Lista<Integer> listConcat = miLista.concat(nuevaList);
        listConcat.forEach(System.out::println);

        Function<Integer, String> fn = valor -> "   "+valor.toString();

        Lista<String> listaEnString = miLista.map(fn);
        System.out.println(listaEnString);

        Function<Double, Function<Lista<Integer>,Double>> promedio = a -> b ->{
            if (b.isEmpty()) {
                return 0.0;
            }

            double suma = a;
            int contador = 0;

            // Usamos una variable temporal para recorrer sin modificar 'lista'
            var actual = b;

            while (!actual.isEmpty()) {
                suma += actual.head(); // Sumamos el valor (Java lo convierte a double automáticamente)
                contador++;
                actual = actual.tail();
            }

            return suma / contador;
        };

        Lista<String> nombres = Lista.of("Alexis", "Vladimir");

        Function<Lista<String>, String> nombreCompleto = new Function<Lista<String>, String>() {
            @Override
            public String apply(Lista<String> stringLista) {
                if (!stringLista.isEmpty()) {

                }
                return "";
            }
        };

        System.out.println("-------- Probando filter ---------------");
        Lista<Integer> numeros = Lista.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Lista<Integer> pares = numeros.filter(n -> n % 2 == 0);
        System.out.println("Numeros pares: " + pares);

        System.out.println("-------- Probando foldLeft (Producto) ---------------");
        Lista<Integer> numsParaProducto = Lista.of(2, 3, 4);
        Integer producto = numsParaProducto.foldLeft(1, acc -> val -> acc * val);
        System.out.println("Producto de " + numsParaProducto + " es: " + producto);

        System.out.println("-------- Ejercicio: Agrupar por primera letra (foldLeft) ---------------");
        Lista<String> palabras = Lista.of("azul", "rojo", "amarillo", "botella", "ballena", "raton", "remolacha", "anaranjado");

        Map<Character, List<String>> agrupado = palabras.foldLeft(new HashMap<>(), map -> palabra -> {
            if (!palabra.isEmpty()) {
                char letra = palabra.charAt(0);
                // computeIfAbsent es muy util: si la clave no existe, crea la lista, la mete al mapa y la devuelve.
                // si ya existe, solo la devuelve.
                map.computeIfAbsent(letra, k -> new ArrayList<>()).add(palabra);
            }
            return map;
        });

        System.out.println(agrupado);



    }
}