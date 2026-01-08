package com.programacion.model;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

interface Resultado {
    public  class Exito implements Resultado {}
    public  class Falla implements Resultado {
        private String msn;
        public Falla(String msn) {
            this.msn = msn;
        }
    }
}

public class EnviarCorreo {
    final Pattern emailPattern =
            Pattern.compile("^[A-Za-z0-9+_%.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$");

    private void enviarCorreo(String email){
        System.out.println("Correo de verificacion enviado a: " + email);
    }

    private void desplegarMensajeError(String txt){
        System.out.printf( "ERROR: %s\\n",txt);
    }


    public void testMail(String email){
        if(emailPattern.matcher(email).matches()){
            enviarCorreo(email);
        }else {
            desplegarMensajeError(String.format("email %s no valido", email));
        }
    }

    /*final Function<String, Boolean> emailChecker = s -> emailPattern.matcher(s).matches();
    final Predicate<String> emailOk = s -> emailPattern.matcher(s).matches();*/

    final Function<String, Resultado> emailChecker = s -> {
      if(s == null){
          return new Resultado.Falla("Email no puede ser nulo");
      } else if (s.length()==0) {
          return new Resultado.Falla("El correo no puede estar vacio");
      } else if (emailPattern.matcher(s).matches()) {
          return new Resultado.Exito();
      }else  {
        return new Resultado.Falla("El correo es invalido");
      }
    };


}
