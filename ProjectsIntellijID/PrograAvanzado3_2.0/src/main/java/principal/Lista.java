package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public sealed interface Lista<T> permits Empty, Const {
    Lista Empty = new Empty();

    boolean isEmpty();

    T head();

    Lista<T> tail();

    public static <T> Lista<T> of(T head, Lista<T> tail) {
        return new Const(head, tail);
    }

    public static <T> Lista<T> of(T... values) {
        Lista<T> fin = Empty;
        for (int i = values.length - 1; i >= 0; i--) {
            Lista<T> tmp = Lista.of(values[i], fin);
            fin = tmp;
        }
        return fin;
    }

    default Lista<T> invertir() {
        var tmp = this;
        Lista<T> ret = Empty;
        while (!tmp.isEmpty()) {
            ret = Lista.of(tmp.head(), ret);
            tmp = tmp.tail();
        }
        return ret;
    }

    default Lista<T> removeFrist() {
        return this.tail();
    }

    default int count() {
        return isEmpty() ? 0 : 1 + tail().count();
    }

    default void forEach(Consumer<T> con) {
        if (!this.isEmpty()) {
            con.accept(this.head());
            this.tail().forEach(con);
        }
    }

    default Lista<T> prepend(T value) {
        //
        return Lista.of(value, this);
    }

    default Lista<T> append(T elemento) {
        return this
                .invertir()
                .prepend(elemento)
                .invertir();
    }

    default boolean contains(T elemento) {
        return !isEmpty() && this.head().equals(elemento);
    }

    default Lista<T> set(int indice, T elemento) {
        if (indice < 0) throw new IndexOutOfBoundsException();

        return this.isEmpty()
                ? throwIndex()
                : indice == 0
                ? Lista.of(elemento, this.tail())
                : Lista.of(this.head(), this.tail().set(indice - 1, elemento));
    }

    private static <T> Lista<T> throwIndex() {
        throw new IndexOutOfBoundsException();
    }

    static <T> List<T> copy(List<T> myList) {
        return new ArrayList<>(myList);
    }

    default <U> Lista<U> map(Function<T, U> fn) {
        return this.isEmpty()
                ? Lista.Empty
                : Lista.of(fn.apply(this.head()), this.tail().map(fn));
    }

    default T reduce(T identidad, Function<T, Function<T, T>> fn) {
        //VARIABLE ACUMULADORA
        T accumulator = identidad;
        var tmp = this;
        while (!tmp.isEmpty()) {
            accumulator = fn.apply(accumulator).apply(tmp.head());
            tmp = tmp.tail();
        }
        return accumulator;
    }

    default T get(int index) {
        return index == 0
                ? this.head()
                : this.tail().get(index - 1);
    }

    default Lista<T> drop(int n) {
        return n <= 0 || this.isEmpty()
                ? this
                : this.tail().drop(n - 1);

    }

    default Lista<T> take(int n) {
        //toma los n primeros elementos y los devuelve en una nuevalista

        // Si n es 0 (o menos) o la lista está vacía, devolvemos una lista vacía.
        if (n <= 0 || this.isEmpty()) {
            return Lista.Empty;
        }
        // Construimos una nueva lista con la cabeza actual y seguimos tomando n-1 elementos del resto.
        return Lista.of(this.head(), this.tail().take(n - 1));
    }

    default Lista<T> concat(Lista<T> lis) {
        // Caso base: Si "esta" lista (this) está vacía, el resultado es la lista que queremos pegar (lis).
        if (this.isEmpty()) {
            return lis;
        }
        // Caso recursivo: Reconstruimos la lista actual nodo por nodo.
        // Cuando lleguemos al final de "this", el caso base pegará "lis" al final.
        return Lista.of(this.head(), this.tail().concat(lis));
    }

    default <U> U foldLeft(U identity, Function<U, Function<T, U>> fn){
        U res = identity;
        var tmp = this;
        while (!tmp.isEmpty()){
            res = fn.apply(res).apply(tmp.head());
            tmp = tmp.tail();
        }
        return res;
    }

    default <U> U foldRight(U identity, Function<T, Function<U, U>> fn){
        return this.isEmpty()
                ? identity
                : fn.apply(this.head()).apply(this.tail().foldRight(identity, fn));
    }


}

record Const<T>(T head, Lista<T> tail) implements Lista<T> {

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

