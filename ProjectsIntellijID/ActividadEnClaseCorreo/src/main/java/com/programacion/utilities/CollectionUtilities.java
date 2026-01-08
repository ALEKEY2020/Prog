package com.programacion.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

public class CollectionUtilities {
    public static <T> List<T> list(){
        return Collections.emptyList();
    }
    public static <T> List<T> list(T t) {
        return Collections.singletonList(t);
    }
    public static <T> List<T> list(List<T> ts){
        return Collections.unmodifiableList(new ArrayList<T>(ts));
    }
    @SafeVarargs
    public static <T> List<T> list(T...ts){
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(ts, ts.length)));
    }
    public static <T> T head(List<T> list){
        if(list.size() == 0) {
            throw new IllegalStateException("head of empty list");
        }
        return list.get(0);
    }
    public static <T> List<T> tail(List<T> list){
        if(list.size() == 0) {
            throw new IllegalStateException("tail of empty list");
        }
        List<T> aux = copy(list);
        aux.remove(0);
        return Collections.unmodifiableList(aux);
    }
    public static<T> List<T> copy(List<T> list){
        return new ArrayList<T>(list);
    }
    public static<T> List<T> append(List<T> li, T t){
        List<T> aux = copy(li);
        aux.add(t);
        return Collections.unmodifiableList(aux);
    }
    public static <T> List<T> invertir(List<T> obj){
        List<T> aux = new ArrayList<T>(obj);
        for(int i= obj.size()-1; i>=0 ; i--) {
            aux.add(obj.get(i));
        }
        return aux;
    }
    public static<T> List<T> prepend(List<T> obj, T t){
        return foldLeft(obj, list(t),
                a -> b -> append(a, b));
    }
    public static <T,U> U foldLeft(List<T> obj, U valIni, Function<U, Function<T, U>> fn) {
        U valor = valIni;
        for(T v:obj) {
            valor = fn.apply(valor).apply(v);
        }
        return valor;
    }
    public static<T,U> U foldRight(List<T> obj, U vi, Function<T,Function<U, U>> fn) {
        U valor = vi;
        for(int i= obj.size() -1; i >=0; i--) {
            T v = obj.get(i);
            valor = fn.apply(v).apply(valor);
        }
        return valor;
    }
    public static<T,U> U foldRigthRecursivo(List<T> obj, U vi, Function<T, Function<U, U>> fn) {
        return obj.isEmpty()?
                vi:
                fn.apply(head(obj))
                        .apply(foldRigthRecursivo(tail(obj), vi, fn));
    }
    public static<T> T redLeft(List<T> obj, T vi, Function<T,Function<T, T>> fn ) {
        T valor = vi;
        for(T v:obj) {
            valor = fn.apply(valor).apply(v);
        }
        return valor;
    }
    public static<T> T redRight(List<T> obj, T vi, Function<T,Function<T, T>> fn ) {
        T valor = vi;
        for(int i = obj.size() -1; i >=0 ; i--) {
            T v =  obj.get(i);
            valor = fn.apply(v).apply(valor);
        }
        return valor;
    }
    public static<T> T redRightRecursivo(List<T> obj, T vi, Function<T,Function<T, T>> fn) {
        return obj.isEmpty() ?
                vi:
                fn.apply(head(obj)).apply(redRightRecursivo(tail(obj), vi, fn));
    }
    public static<T,U> List<U> map(List<T> lis, Function<T, U> fn){
        return foldLeft(lis, list(), x-> y-> append(x, fn.apply(y)));
    }
}