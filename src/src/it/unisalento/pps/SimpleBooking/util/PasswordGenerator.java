package it.unisalento.pps.SimpleBooking.util;

import java.lang.Math;

public class PasswordGenerator {
    private double multiplier = 4.0;

    public String generatePassword() {
        //Using Oracle's library, a Linear Congruent Generator
        double LGC_random = Math.random();
        LGC_random = Math.floor(LGC_random * Math.pow(10, multiplier));
        int temp = (int) LGC_random;
        return Integer.toString(temp);
    }
}
