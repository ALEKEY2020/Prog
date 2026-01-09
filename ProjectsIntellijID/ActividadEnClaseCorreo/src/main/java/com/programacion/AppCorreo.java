package com.programacion;

import com.programacion.model.Ejecutable;
import com.programacion.model.EnviarCorreo;

import java.sql.SQLOutput;
import java.util.function.Consumer;

class Resultado {
    int valor;
    String mensaje;
    public Resultado(int valor, String mensaje) {}
}
public class AppCorreo {
    public static void main(String[] args) {
        EnviarCorreo e = new EnviarCorreo();
//        e.testMail("");

        Ejecutable ex = e.validate("prueba123.al@uce.edu.ec");
        ex.exec();

        Consumer<String> c = s -> System.out.println(" aaa " + s);


    }
}
