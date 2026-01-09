package com.programacion.model;

public interface Ejecutable {
    void exec();
}
class EjecutableOk implements Ejecutable {
    @Override
    public void exec() {
        System.out.println("Correo de verificacion enviado a: ");
    }
}
class EjecutableFalla implements Ejecutable {
    @Override
    public void exec() {
        System.out.printf( "ERROR: %s\\n");
    }
}