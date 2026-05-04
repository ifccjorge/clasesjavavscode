package com.ejemplo;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class App {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("Hoy es " + today);
        LocalDate cobro = today.with(TemporalAdjusters.lastDayOfMonth()).minusDays(2);
        System.out.println("Fecha del cobro " + cobro);
        LocalDate fecha = LocalDate.of(1963, Month.JANUARY, 22).plusYears(1);
        System.out.println(fecha.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRANCE));
        // Locale locale = Locale.of("ja", "JP");
        // System.out.println(fecha.getMonth().getDisplayName(TextStyle.FULL, locale));
        System.out.println(ChronoUnit.YEARS.between(fecha, today));
    }
}