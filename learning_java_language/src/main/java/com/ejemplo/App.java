package com.ejemplo;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
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
        Locale locale = Locale.of("ja", "JP");
        System.out.println(fecha.getMonth().getDisplayName(TextStyle.FULL, locale));
        System.out.println(ChronoUnit.YEARS.between(fecha, today));
        LocalDate proximo = fecha.withYear(today.getYear());
        if (proximo.isBefore(today) || proximo.isEqual(today)) {
            proximo = proximo.plusYears(1);
        }
        Period p1 = Period.between(today, proximo);
        long p2 = ChronoUnit.DAYS.between(today, proximo);
        System.out.println("There are " + p1.getMonths() + " months, and "
                + p1.getDays() + " days until your next birthday. ("
                + p2 + " total)");
    }
}
