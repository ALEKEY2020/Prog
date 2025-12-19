package principal;

import java.util.List;

public sealed interface Lista<T> permits Empty, Const{
    Lista Empty = new Empty();
    boolean isEmpty();
    T head();
    Lista<T> tail();

    public static <T> Lista<T> of(T head, Lista<T> tail){
        return new Const(head, tail);
    }

    public static <T> Lista<T> of(T... values) {
        Lista<T> fin = Empty;
        for(int i = values.length-1 ; i >=0; i--){
            Lista<T> tmp = Lista.of(values[i], fin);
            fin = tmp;
        }
        return fin;
    }

    default Lista<T> invertir(){
        var tmp = this;
        Lista<T> ret = Empty;
        while(!tmp.isEmpty()){
            ret = Lista.of(tmp.head(), ret);
            tmp = tmp.tail();
        }
        return ret;
    }
/*
    default Integer count() {

    }

    default Lista<T> append(T elemento){

    }

    default Lista<T> insert(int index, T elemento){

   }
*/
}

record Const<T>(T head, Lista<T> tail) implements Lista<T>{

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", head, tail);
    }
}

final class Empty implements Lista {

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Object head() {
        return null;
    }

    @Override
    public Lista tail() {
        return null;
    }

    @Override
    public String toString() {
        return "Empty";
    }
}

