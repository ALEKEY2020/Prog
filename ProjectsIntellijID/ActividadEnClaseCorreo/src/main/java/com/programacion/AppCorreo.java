package com.programacion;

import com.programacion.model.Effect;
import com.programacion.model.Ejecutable;
import com.programacion.model.EnviarCorreo;
import com.programacion.model.Result;

import java.sql.SQLOutput;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;


public class AppCorreo {

    static final Pattern emailPattern =
            Pattern.compile("^[A-Za-z0-9+_%.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$");

    public static void main(String[] args) {
        Effect<String> success = s -> System.out.println("Correo de verificacion enviado a: " + s);
        Effect<String> error = s -> System.out.println("Error: " + s);
        final Function<String, Result<String>> emailChecker = s ->
                s == null ? Result.failure("Correo no debe ser null")
                        : s.length() == 0 ? Result.failure("Correo no debe ser vacio")
                        : emailPattern.matcher(s).matches()?
                        Result.success(s)
                        :Result.failure("Correo no es valido");
        emailChecker.apply("prueba.dos@hotmail.com").bind(success, error);

    }
}
