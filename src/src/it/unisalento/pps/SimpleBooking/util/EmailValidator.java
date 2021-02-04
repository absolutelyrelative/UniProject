package it.unisalento.pps.SimpleBooking.util;

import java.util.regex.*;

public class EmailValidator {
    //TODO: TEST
    //Tra l'utilizzo della libreria di apache per validazione e-mail e l'utilizzo di String pattern in Java
    //Ho scelto di utilizzare la seconda per semplicità.
    //Ho provato a sviluppare il pattern da me stesso, ma alcuni false-positive uscivano. Perciò mi sono affidato a
    //emailregex.com, e la loro pattern più robusta.

    private static String email_pattern_string = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    private static Pattern email_pattern = Pattern.compile(email_pattern_string);

    public boolean validateEmail(String email) {
        if (email.isEmpty() || email == null) {
            return false;
        } else {
            Matcher match = email_pattern.matcher(email);
            //if
            return match.matches();
        }
    }
}
