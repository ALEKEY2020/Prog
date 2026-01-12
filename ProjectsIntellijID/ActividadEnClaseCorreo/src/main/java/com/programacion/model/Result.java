package com.programacion.model;

public interface Result<T> {
    void bind(Effect<T> success, Effect<T> failure);
    static <T> Result<T> success(T values) {
        return new Success(values);
    }
    static <T> Result<T> failure(T msn) {
        return new Failure(msn);
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

}