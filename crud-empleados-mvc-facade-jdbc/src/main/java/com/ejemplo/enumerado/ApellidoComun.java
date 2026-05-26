package com.ejemplo.enumerado;

import java.util.Random;

public enum ApellidoComun {
  GARCIA("García"),
  RODRIGUEZ("Rodríguez"),
  GONZALEZ("González"),
  FERNANDEZ("Fernández"),
  LOPEZ("López"),
  MARTINEZ("Martínez"),
  SANCHEZ("Sánchez"),
  PEREZ("Pérez"),
  GOMEZ("Gómez"),
  MARTIN("Martín"),
  JIMENEZ("Jiménez"),
  HERNANDEZ("Hernández"),
  RUIZ("Ruiz"),
  DIAZ("Díaz"),
  MORENO("Moreno"),
  MUN_OZ("Muñoz"),
  ALVAREZ("Álvarez"),
  ROMERO("Romero"),
  GUTIERREZ("Gutiérrez"),
  ALONSO("Alonso"),
  TORRES("Torres"),
  NAVARRO("Navarro"),
  DOMINGUEZ("Domínguez"),
  RAMIREZ("Ramírez"),
  RAMOS("Ramos"),
  VAZQUEZ("Vázquez"),
  GIL("Gil"),
  SERRANO("Serrano"),
  MORALES("Morales"),
  MOLINA("Molina"),
  SUAREZ("Suárez"),
  CASTRO("Castro"),
  BLANCO("Blanco"),
  DELGADO("Delgado"),
  ORTEGA("Ortega"),
  ORTIZ("Ortiz"),
  MARIN("Marín"),
  RUBIO("Rubio"),
  MEDINA("Medina"),
  NUN_EZ("Nuñez"),
  CASTILLO("Castillo"),
  SANZ("Sanz"),
  CORTES("Cortés"),
  IGLESIAS("Iglesias"),
  SANTOS("Santos"),
  GARRIDO("Garrido"),
  GUERRERO("Guerrero"),
  LOZANO("Lozano"),
  FLORES("Flores"),
  CANO("Cano"),
  CRUZ("Cruz"),
  MENDEZ("Méndez"),
  HERRERA("Herrera"),
  PEN_A("Peña"),
  PRIETO("Prieto"),
  LEON("León"),
  CABRERA("Cabrera"),
  MARQUEZ("Márquez"),
  REYES("Reyes"),
  GALLEGO("Gallego"),
  VIDAL("Vidal"),
  CALVO("Calvo"),
  CAMPOS("Campos"),
  VEGA("Vega"),
  FUENTES("Fuentes"),
  AGUILAR("Aguilar"),
  CARRASCO("Carrasco"),
  VARGAS("Vargas"),
  CABALLERO("Caballero"),
  DIEZ("Diez"),
  NIETO("Nieto"),
  SANTANA("Santana"),
  GIMENEZ("Giménez"),
  HIDALGO("Hidalgo"),
  MONTERO("Montero"),
  ROJAS("Rojas"),
  BENITEZ("Benítez"),
  PASCUAL("Pascual"),
  HERRERO("Herrero"),
  ARIAS("Arias"),
  SANTIAGO("Santiago"),
  LORENZO("Lorenzo"),
  DURAN("Durán"),
  MORA("Mora"),
  IBAN_EZ("Ibañez"),
  FERRER("Ferrer"),
  CARMONA("Carmona"),
  VICENTE("Vicente"),
  SOTO("Soto"),
  ROMAN("Román"),
  CRESPO("Crespo"),
  RIVERA("Rivera"),
  PARRA("Parra"),
  SILVA("Silva"),
  VELASCO("Velasco"),
  PASTOR("Pastor"),
  BRAVO("Bravo"),
  SAEZ("Sáez"),
  MOYA("Moya"),
  MENDOZA("Mendoza");

  private final String apellido;
  private static final Random PRNG = new Random();

  private ApellidoComun(String apellido) {
    this.apellido = apellido;
  }

  public String getTexto() {
    return apellido;
  }

  public static ApellidoComun randomDepartamento() {
    ApellidoComun[] directions = values();
    return directions[PRNG.nextInt(directions.length)];
  }

}