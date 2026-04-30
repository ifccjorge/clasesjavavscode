package com.ejemplo;

public class App {

    public static void main(String[] args) {
        for (Season season : Season.values()) {
            System.out.println("Nombre de la constante: " + season.name() + ", ordinal: " + season.ordinal());
        }
        Season.SPRING.printExpectedVisitors();
        System.out.println(Season.SUMMER.getHours());
    }

}