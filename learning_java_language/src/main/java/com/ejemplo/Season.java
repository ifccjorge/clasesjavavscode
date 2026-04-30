package com.ejemplo;

public enum Season {

    SPRING("Media") {
        @Override
        public String getHours() {
            return ("9:00 - 17:00");
        }
    },
    SUMMER("Baja") {
        @Override
        public String getHours() {
            return ("8:00 - 18:00");
        }
    },
    FALL("Alta") {
        @Override
        public String getHours() {
            return ("10:00 - 16:00");
        }
    },
    WINTER("Media") {
        @Override
        public String getHours() {
            return ("10:00 - 15:00");
        }
    };

    private final String expectedVisitors;

    private Season(String expectedVisitors) {
        this.expectedVisitors = expectedVisitors;
    }

    public void printExpectedVisitors() {
        System.out.println(expectedVisitors);
    }

    public abstract String getHours();

}