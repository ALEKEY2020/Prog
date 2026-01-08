package com.programacion;

import com.programacion.model.EnviarCorreo;
class Resultado {
    int valor;
    String mensaje;
    public Resultado(int valor, String mensaje) {}
}
public class AppCorreo {
    public static void main(String[] args) {
        EnviarCorreo e = new EnviarCorreo();
        e.testMail("");


    }
}
