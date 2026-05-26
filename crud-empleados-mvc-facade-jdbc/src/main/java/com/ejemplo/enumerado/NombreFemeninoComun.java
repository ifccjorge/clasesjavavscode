package com.ejemplo.enumerado;

import java.util.Random;

public enum NombreFemeninoComun {
  MARIA_CARMEN("María Carmen"),
  MARIA("María"),
  CARMEN("Carmen"),
  ANA_MARIA("Ana María"),
  LAURA("Laura"),
  MARIA_PILAR("María Pilar"),
  MARIA_DOLORES("María Dolores"),
  ISABEL("Isabel"),
  ANA("Ana"),
  MARIA_TERESA("María Teresa"),
  JOSEFA("Josefa"),
  MARTA("Marta"),
  CRISTINA("Cristina"),
  LUCIA("Lucía"),
  MARIA_ANGELES("María Ángeles"),
  MARIA_JOSE("María José"),
  MARIA_ISABEL("María Isabel"),
  FRANCISCA("Francisca"),
  ANTONIA("Antonia"),
  SARA("Sara"),
  PAULA("Paula"),
  DOLORES("Dolores"),
  ELENA("Elena"),
  MARIA_LUISA("María Luisa"),
  RAQUEL("Raquel"),
  ROSA_MARIA("Rosa María"),
  MANUELA("Manuela"),
  MARIA_JESUS("María Jesús"),
  JULIA("Julia"),
  PILAR("Pilar"),
  CONCEPCION("Concepción"),
  ALBA("Alba"),
  MERCEDES("Mercedes"),
  BEATRIZ("Beatriz"),
  SILVIA("Silvia"),
  NURIA("Nuria"),
  IRENE("Irene"),
  PATRICIA("Patricia"),
  ROCIO("Rocío"),
  ANDREA("Andrea"),
  ROSARIO("Rosario"),
  MONTSERRAT("Montserrat"),
  JUANA("Juana"),
  MONICA("Mónica"),
  TERESA("Teresa"),
  ENCARNACION("Encarnación"),
  ALICIA("Alicia"),
  MARIA_MAR("María Mar"),
  MARINA("Marina"),
  SANDRA("Sandra"),
  SONIA("Sonia"),
  NATALIA("Natalia"),
  SOFIA("Sofía"),
  SUSANA("Susana"),
  ANGELA("Ángela"),
  YOLANDA("Yolanda"),
  CLAUDIA("Claudia"),
  ROSA("Rosa"),
  CARLA("Carla"),
  EVA("Eva"),
  MARGARITA("Margarita"),
  MARIA_JOSEFA("María Josefa"),
  INMACULADA("Inmaculada"),
  ANA_ISABEL("Ana Isabel"),
  MARIA_MERCEDES("María Mercedes"),
  MARIA_ROSARIO("María Rosario"),
  NOELIA("Noelia"),
  DANIELA("Daniela"),
  ESTHER("Esther"),
  VERONICA("Verónica"),
  CAROLINA("Carolina"),
  MARTINA("Martina"),
  NEREA("Nerea"),
  INES("Inés"),
  MIRIAM("Miriam"),
  EVA_MARIA("Eva María"),
  MARIA_VICTORIA("María Victoria"),
  LORENA("Lorena"),
  MARIA_ELENA("María Elena"),
  ANA_BELEN("Ana Belén"),
  VICTORIA("Victoria"),
  MARIA_ROSA("María Rosa"),
  ALEJANDRA("Alejandra"),
  ANGELES("Ángeles"),
  MARIA_CONCEPCION("María Concepción"),
  CELIA("Celia"),
  LIDIA("Lidia"),
  FATIMA("Fátima"),
  MARIA_ANTONIA("María Antonia"),
  AMPARO("Amparo"),
  AINHOA("Ainhoa"),
  OLGA("Olga"),
  CATALINA("Catalina"),
  MARIA_NIEVES("María Nieves"),
  CLARA("Clara"),
  ADRIANA("Adriana"),
  VALERIA("Valeria"),
  ANNA("Anna"),
  MARIA_CRISTINA("María Cristina"),
  EMMA("Emma");

  private final String nombre;
  private static final Random PRNG = new Random();

  private NombreFemeninoComun(String nombre) {
    this.nombre = nombre;
  }

  public String getTexto() {
    return nombre;
  }

  public static NombreFemeninoComun randomDepartamento() {
    NombreFemeninoComun[] directions = values();
    return directions[PRNG.nextInt(directions.length)];
  }

}