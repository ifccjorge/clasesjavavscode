package com.ejemplo.enumerado;

import java.util.Random;

public enum NombreMasculinoComun {
  ANTONIO("Antonio"),
  MANUEL("Manuel"),
  JOSE("José"),
  FRANCISCO("Francisco"),
  DAVID("David"),
  JAVIER("Javier"),
  DANIEL("Daniel"),
  JUAN("Juan"),
  JOSE_ANTONIO("José Antonio"),
  FRANCISCO_JAVIER("Francisco Javier"),
  JOSE_LUIS("José Luis"),
  CARLOS("Carlos"),
  ALEJANDRO("Alejandro"),
  JESUS("Jesús"),
  JOSE_MANUEL("José Manuel"),
  MIGUEL("Miguel"),
  MIGUEL_ANGEL("Miguel Ángel"),
  PABLO("Pablo"),
  RAFAEL("Rafael"),
  SERGIO("Sergio"),
  ANGEL("Ángel"),
  PEDRO("Pedro"),
  JORGE("Jorge"),
  FERNANDO("Fernando"),
  JOSE_MARIA("José María"),
  ALBERTO("Alberto"),
  LUIS("Luis"),
  ALVARO("Álvaro"),
  ADRIAN("Adrián"),
  JUAN_CARLOS("Juan Carlos"),
  DIEGO("Diego"),
  JUAN_JOSE("Juan José"),
  RAUL("Raúl"),
  IVAN("Iván"),
  RUBEN("Rubén"),
  JUAN_ANTONIO("Juan Antonio"),
  OSCAR("Óscar"),
  ENRIQUE("Enrique"),
  JUAN_MANUEL("Juan Manuel"),
  MARIO("Mario"),
  SANTIAGO("Santiago"),
  ANDRES("Andrés"),
  RAMON("Ramón"),
  VICTOR("Víctor"),
  VICENTE("Vicente"),
  JOAQUIN("Joaquín"),
  EDUARDO("Eduardo"),
  HUGO("Hugo"),
  MARCOS("Marcos"),
  ROBERTO("Roberto"),
  JAIME("Jaime"),
  FRANCISCO_JOSE("Francisco José"),
  IGNACIO("Ignacio"),
  JORDI("Jordi"),
  MOHAMED("Mohamed"),
  ALFONSO("Alfonso"),
  RICARDO("Ricardo"),
  MARTIN("Martín"),
  MARC("Marc"),
  SALVADOR("Salvador"),
  GABRIEL("Gabriel"),
  GUILLERMO("Guillermo"),
  GONZALO("Gonzalo"),
  EMILIO("Emilio"),
  JOSE_MIGUEL("José Miguel"),
  NICOLAS("Nicolás"),
  LUCAS("Lucas"),
  JULIO("Julio"),
  JULIAN("Julián"),
  TOMAS("Tomás"),
  SAMUEL("Samuel"),
  AGUSTIN("Agustín"),
  ISMAEL("Ismael"),
  CRISTIAN("Cristián"),
  JOSE_RAMON("José Ramón"),
  JOAN("Joan"),
  AITOR("Aitor"),
  HECTOR("Héctor"),
  ALEX("Álex"),
  MATEO("Mateo"),
  FELIX("Félix"),
  IKER("Iker"),
  JUAN_FRANCISCO("Juan Francisco"),
  JOSE_CARLOS("José Carlos"),
  SEBASTIAN("Sebastián"),
  RODRIGO("Rodrigo"),
  CESAR("César"),
  JOSEP("Josep"),
  JOSE_ANGEL("José Ángel"),
  ALFREDO("Alfredo"),
  VICTOR_MANUEL("Víctor Manuel"),
  MARIANO("Mariano"),
  JOSE_IGNACIO("José Ignacio"),
  DOMINGO("Domingo"),
  FELIPE("Felipe"),
  LUIS_MIGUEL("Luis Miguel"),
  PAU("Pau"),
  MOHAMMED("Mohammed"),
  IZAN("Izan"),
  XAVIER("Xavier");

  private final String nombre;
  private static final Random PRNG = new Random();

  private NombreMasculinoComun(String nombre) {
    this.nombre = nombre;
  }

  public String getTexto() {
    return nombre;
  }

  public static NombreMasculinoComun randomDepartamento() {
    NombreMasculinoComun[] directions = values();
    return directions[PRNG.nextInt(directions.length)];
  }

}