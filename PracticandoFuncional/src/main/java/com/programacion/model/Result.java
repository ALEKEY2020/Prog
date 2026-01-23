package com.programacion.model;

import java.util.function.Function;

public interface Result<T> {
    void bind(Effect<T> success, Effect<T> failure);

    <U> Result<U> flatMap(Function<T, Result<U>> fn);

    static <T> Result<T> success(T values) {
        return new Success<>(values);
    }
    static <T> Result<T> failure(T msn) {
        return new Failure<>(msn);
    }
}

class Success<T> implements Result<T> {
    private final T value;
    public Success(T t) {
        this.value = t;
    }

    @Override
    public void bind(Effect<T> success, Effect<T> failure) {
        success.apply(value);
    }

    @Override
    public <U> Result<U> flatMap(Function<T, Result<U>> fn) {
        return fn.apply(value);
    }
}

class Failure<T> implements Result<T> {
    private final T msn;
    public Failure(T t) {
        this.msn = t;
    }
    @Override
    public void bind(Effect<T> success, Effect<T> failure) {
        failure.apply(msn);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U> Result<U> flatMap(Function<T, Result<U>> fn) {
        // Nota: Esto asume que el tipo de error T se puede castear a U.
        // Idealmente, Failure debería almacenar un tipo de error fijo (String o Exception).
        return Result.failure((U) msn);
    }

}