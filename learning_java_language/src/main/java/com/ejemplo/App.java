package com.ejemplo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class App {

  public static void main(String[] args) {

    // Definición de pasajeros
    Pasajero pasajero1 = new Pasajero(
        "Luis",
        "González",
        "Martínez",
        LocalDate.of(2001, Month.FEBRUARY, 10),
        Genero.HOMBRE);
    Pasajero pasajero2 = new Pasajero(
        "Mariana",
        "Páez",
        "López",
        LocalDate.of(2000, Month.AUGUST, 2),
        Genero.MUJER);
    Pasajero pasajero3 = new Pasajero(
        "Juan",
        "Díaz",
        "Palacios",
        LocalDate.of(2001, Month.MARCH, 30),
        Genero.HOMBRE);
    Pasajero pasajero4 = new Pasajero(
        "Beatriz",
        "Benítez",
        "Peláez",
        LocalDate.of(2005, Month.NOVEMBER, 20),
        Genero.MUJER);
    Pasajero pasajero5 = new Pasajero(
        "Rafael",
        "Montero",
        "Álvarez",
        LocalDate.of(2001, Month.FEBRUARY, 10),
        Genero.HOMBRE);
    Pasajero pasajero6 = new Pasajero(
        "Julia",
        "Gómez",
        "Hernández",
        LocalDate.of(1991, Month.JANUARY, 22),
        Genero.MUJER);
    Pasajero pasajero7 = new Pasajero(
        "José Luis",
        "Fernández",
        "García",
        LocalDate.of(1999, Month.OCTOBER, 3),
        Genero.HOMBRE);
    Pasajero pasajero8 = new Pasajero(
        "Irene",
        "Pérez",
        "Jiménez",
        LocalDate.of(1994, Month.APRIL, 19),
        Genero.MUJER);

    // Definición de vuelos
    Vuelo vuelo1 = Vuelo.builder()
        .destino(Destino.NUEVA_YORK)
        .precio(new BigDecimal(750.25))
        .fechaSalida(LocalDate.of(2026, Month.JULY, 1))
        .horaSalida(LocalTime.of(12, 30))
        .fechaLlegada(LocalDate.of(2026, Month.JULY, 1))
        .horaLlegada(LocalTime.of(19, 30))
        .numeroPlazas(3)
        .build();
    Vuelo vuelo2 = Vuelo.builder()
        .destino(Destino.TOKIO)
        .precio(new BigDecimal(850.75))
        .fechaSalida(LocalDate.of(2026, Month.JUNE, 8))
        .horaSalida(LocalTime.of(9, 20))
        .fechaLlegada(LocalDate.of(2026, Month.JUNE, 8))
        .horaLlegada(LocalTime.of(19, 40))
        .numeroPlazas(2)
        .build();
    Vuelo vuelo3 = Vuelo.builder()
        .destino(Destino.BARCELONA)
        .precio(new BigDecimal(95.50))
        .fechaSalida(LocalDate.of(2026, Month.MAY, 30))
        .horaSalida(LocalTime.of(23, 45))
        .fechaLlegada(LocalDate.of(2026, Month.MAY, 31))
        .horaLlegada(LocalTime.of(0, 55))
        .numeroPlazas(3)
        .build();
    Vuelo vuelo4 = Vuelo.builder()
        .destino(Destino.BERLIN)
        .precio(new BigDecimal(150.25))
        .fechaSalida(LocalDate.of(2026, Month.MAY, 10))
        .horaSalida(LocalTime.of(13, 00))
        .fechaLlegada(LocalDate.of(2026, Month.MAY, 10))
        .horaLlegada(LocalTime.of(14, 55))
        .numeroPlazas(3)
        .build();
    Vuelo vuelo5 = Vuelo.builder()
        .destino(Destino.BUENOS_AIRES)
        .precio(new BigDecimal(550.75))
        .fechaSalida(LocalDate.of(2026, Month.MAY, 22))
        .horaSalida(LocalTime.of(21, 00))
        .fechaLlegada(LocalDate.of(2026, Month.MAY, 23))
        .horaLlegada(LocalTime.of(9, 25))
        .numeroPlazas(3)
        .build();
    Vuelo vuelo6 = Vuelo.builder()
        .destino(Destino.PRAGA)
        .precio(new BigDecimal(125.50))
        .fechaSalida(LocalDate.of(2026, Month.MAY, 31))
        .horaSalida(LocalTime.of(10, 20))
        .fechaLlegada(LocalDate.of(2026, Month.MAY, 31))
        .horaLlegada(LocalTime.of(12, 15))
        .numeroPlazas(3)
        .build();
    // Definición de listados
    List<Pasajero> pasajeros = List.of(pasajero1, pasajero2, pasajero3, pasajero4, pasajero5, pasajero6, pasajero7,
        pasajero8);
    List<Vuelo> vuelos = List.of(vuelo1, vuelo2, vuelo3, vuelo4, vuelo5, vuelo6);
    System.out.println(pasajeros.size());
    // System.out.println(vuelos);
    // vuelos.stream().map(Vuelo::minutosVuelo).forEach(System.out::println);

    // Alta de pasajeros en los vuelos
    vuelo1.incluirPasajero(pasajero1);
    // vuelo1.incluirPasajero(pasajero1);
    // vuelo1.excluirPasajero(pasajero1);
    // vuelo1.excluirPasajero(pasajero1);
    vuelo1.incluirPasajero(pasajero2);
    vuelo2.incluirPasajero(pasajero3);
    vuelo2.incluirPasajero(pasajero4);
    vuelo3.incluirPasajero(pasajero5);
    vuelo4.incluirPasajero(pasajero6);
    // vuelos.stream().map(Vuelo::plazasDisponibles).forEach(System.out::println);
    // vuelos.stream().forEach(Vuelo::descripcion);
    // vuelos.stream().map(p -> p.esPasajeroVuelo(pasajero1)).forEach(System.out::println);

    // Vuelos con más pasajeros: SOLUCIÓN 1
    System.out.println("*** Vuelos con más pasajeros: SOLUCIÓN 1 ***");
    List<Vuelo> vuelosMasPasajeros = new ArrayList<>();
    int mayorNumeroPasajeros = 0;
    for (Vuelo vuelo : vuelos) {
      int numeroPasajeros = vuelo.getPasajeros().size();
      if (numeroPasajeros > mayorNumeroPasajeros) {
        mayorNumeroPasajeros = numeroPasajeros;
        vuelosMasPasajeros.clear();
      }
      if (numeroPasajeros == mayorNumeroPasajeros) {
        vuelosMasPasajeros.add(vuelo);
      }
    }
    System.out.println("Hay " + vuelosMasPasajeros.size() + " vuelos con " + mayorNumeroPasajeros + " pasajeros: "
        + vuelosMasPasajeros);

    // Vuelos con más pasajeros: SOLUCIÓN 2
    System.out.println("*** Vuelos con más pasajeros: SOLUCIÓN 2 ***");
    Map<Integer, List<Vuelo>> pasajerosVuelo1 = vuelos.stream().collect(
        Collectors.groupingBy(v -> v.getPasajeros().size()));
    SortedMap<Integer, List<Vuelo>> pasajerosVueloInverso = new TreeMap<>(Comparator.reverseOrder());
    pasajerosVueloInverso.putAll(pasajerosVuelo1);
    int mayor1 = pasajerosVueloInverso.firstKey();
    System.out.println("Para " + mayor1 + " hay " + pasajerosVueloInverso.get(mayor1));

    // Vuelos con más pasajeros: SOLUCIÓN 3
    System.out.println("*** Vuelos con más pasajeros: SOLUCIÓN 3 ***");
    SortedMap<Integer, List<Vuelo>> pasajerosVuelo2 = vuelos.stream().collect(
      Collectors.groupingBy(
        v -> v.getPasajeros().size(),
        TreeMap::new,
        Collectors.toList()
      )
    );
    int mayor2 = pasajerosVuelo2.lastKey();
    System.out.println("Para " + mayor2 + " hay " + pasajerosVueloInverso.get(mayor2));

    // Vuelos con más pasajeros: SOLUCIÓN 4
    System.out.println("*** Vuelos con más pasajeros: SOLUCIÓN 4 ***");
    SortedMap<Integer, List<Vuelo>> pasajerosVuelo3 = vuelos.stream().collect(
      Collectors.toMap(
        v -> v.getPasajeros().size(),
        b -> new ArrayList<Vuelo>(List.of(b)),
        (list1, list2) -> {
          list1.addAll(list2);
          return list1;
        },
        TreeMap::new
      )
    );
    System.out.println("Última clave: " + pasajerosVuelo3.lastKey());

    // Vuelos con más pasajeros: SOLUCIÓN 5
    System.out.println("*** Vuelos con más pasajeros: SOLUCIÓN 5 ***");
    Optional<Vuelo> a = vuelos.stream().collect(
        Collectors.maxBy(Comparator.comparingInt(v -> v.getPasajeros().size()))
    );
    System.out.println("Índice: " + a.get().getPasajeros().size());

    System.exit(0);

    // 1. Obtener un listado de los vuelos que tienen el número de plazas completo.
    System.out.println("*** 1. Obtener un listado de los vuelos que tienen el número de plazas completo.");
    vuelos.stream().filter(v -> v.plazasDisponibles() == 0).forEach(System.out::println);

    // 2. Obtener un listado de los vuelos que tienen fecha de salida prevista para el día de hoy.
    System.out
        .println("*** 2. Obtener un listado de los vuelos que tienen fecha de salida prevista para el día de hoy.");
    vuelos.stream().filter(v -> v.getFechaSalida().isEqual(LocalDate.now())).forEach(System.out::println);

    // 3. Obtener un listado de los vuelos cuya duración sea mayor de 10 horas.
    System.out
        .println("*** 3. Obtener un listado de los vuelos cuya duración sea mayor de 10 horas.");
    vuelos.stream().filter(v -> v.minutosVuelo() > 600).forEach(System.out::println);

    // 4. Obtener un listado de los vuelos que pueden demorar más de un día en llegar a su destino.
    System.out
        .println("*** 4. Obtener un listado de los vuelos que pueden demorar más de un día en llegar a su destino.");
    vuelos.stream().filter(v -> !v.getFechaSalida().isEqual(v.getFechaLlegada())).forEach(System.out::println);

    // 5. Obtener una colección que almacene un listado de pasajeros agrupado por el destino del vuelo.
    System.out
        .println(
            "*** 5. Obtener una colección que almacene un listado de pasajeros agrupado por el destino del vuelo.");
    Map<Destino, Set<Pasajero>> pasajerosPorDestino = vuelos.stream().collect(
      Collectors.groupingBy(
          Vuelo::getDestino,
          Collectors.flatMapping(v -> v.getPasajeros().stream(), Collectors.toSet()
        )
      )
    );
    pasajerosPorDestino.entrySet().forEach(System.out::println);

    // 6.​ Crear una colección que almacene los vuelos que están programados para salir en los últimos 10 días del mes en curso.
    System.out
        .println(
            "*** 6. Crear una colección que almacene los vuelos que están programados para salir en los últimos 10 días del mes en curso.");
    LocalDate ultimoDiaMesActual = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
    LocalDate fechaAnterior = ultimoDiaMesActual.minusDays(10);
    SortedMap<LocalDate, List<Vuelo>> vuelosUltimosDias = vuelos.stream().filter(
      v -> {
        LocalDate fecha = v.getFechaSalida();
        return fecha.isEqual(ultimoDiaMesActual) || fecha.isBefore(ultimoDiaMesActual) && fecha.isAfter(
            fechaAnterior);
      }).collect(
          Collectors.groupingBy(
            Vuelo::getFechaSalida,
            TreeMap::new,
            Collectors.toList()
          )
        );
    System.out.println(vuelosUltimosDias);

    // 7. Crear una colección que almacene los pasajeros, por el genero y la edad del pasajero.
    System.out
        .println(
            "*** 7. Crear una colección que almacene los pasajeros, por el genero y la edad del pasajero.");
    SortedMap<Genero, SortedMap<Integer, List<Pasajero>>> pasajerosPorGeneroEdad = vuelos
      .stream()
      .flatMap(v -> v.getPasajeros().stream())
      .collect(
        Collectors.groupingBy(
          Pasajero::genero,
          TreeMap::new,
          Collectors.groupingBy(
            Pasajero::edad,
            TreeMap::new,
            Collectors.toList()
          )
        )
      );
    System.out.println(pasajerosPorGeneroEdad);

    // 8. Mostrar la colección anterior ordenada por el nombre y los apellidos de los pasajeros en orden natural.
    System.out
        .println(
            "*** 8. Mostrar la colección anterior ordenada por el nombre y los apellidos de los pasajeros en orden natural.");
    Comparator<Pasajero> cmprtr = (Pasajero p1, Pasajero p2) -> {
      int i1 = p1.nombre().compareTo(p2.nombre());
      int i2 = p1.primerApellido().compareTo(p2.primerApellido());
      return i1 == 0 ? i2 : i1;
    };
    SortedMap<Genero, SortedMap<Integer, List<Pasajero>>> pasajerosPorGeneroEdadOrdenado = vuelos
      .stream()
      .flatMap(v -> v.getPasajeros().stream())
      .sorted(cmprtr)
      .collect(
        Collectors.groupingBy(
          Pasajero::genero,
          TreeMap::new,
          Collectors.groupingBy(
            Pasajero::edad,
            TreeMap::new,
            Collectors.toList()
          )
        )
      );
    System.out.println(pasajerosPorGeneroEdadOrdenado);

    // 9. Mostrar la colección del punto 7 ordenada en orden alfabético inverso por el primer apellido, sin modificar el orden natural de la clase Pasajero.
    System.out
        .println(
            "*** 9. Mostrar la colección del punto 7 ordenada en orden alfabético inverso por el primer apellido, sin modificar el orden natural de la clase Pasajero.");
    SortedMap<Genero, SortedMap<Integer, List<Pasajero>>> pasajerosPorGeneroEdadOrdenadoInv = vuelos
      .stream()
      .flatMap(v -> v.getPasajeros().stream())
      .sorted(cmprtr.reversed())
      .collect(
        Collectors.groupingBy(
          Pasajero::genero,
          TreeMap::new,
          Collectors.groupingBy(
            Pasajero::edad,
            TreeMap::new,
            Collectors.toList()
          )
        )
      );
    System.out.println(pasajerosPorGeneroEdadOrdenadoInv);

    // 10. ​Obtener una colección que almacene el nombre y el apellido de los pasajeros, agrupado por las horas de duración de su viaje.
    System.out
        .println(
            "*** 10. Obtener una colección que almacene el nombre y el apellido de los pasajeros, agrupado por las horas de duración de su viaje.");
    SortedMap<Double, List<Map<String, String>>> nombreApellidosPorDuración = vuelos.stream().collect(
      Collectors.groupingBy(
        v -> Double.valueOf(v.minutosVuelo()) / 60,
        TreeMap::new,
        Collectors.flatMapping(
          v -> v.getPasajeros().stream().map(
            p -> Map.of(
              "nombre", p.nombre(),
              "primerApellido", p.primerApellido(),
              "segundoApellido", p.segundoApellido()
            )
          ),
          Collectors.toList()
        )
      )
    );
    System.out.println(nombreApellidosPorDuración);

  }
}
