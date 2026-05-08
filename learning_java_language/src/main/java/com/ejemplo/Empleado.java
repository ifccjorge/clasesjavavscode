package com.ejemplo;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Empleado extends Persona implements Comparable<Empleado> {

    private Dpto dpto;
    private BigDecimal salario;
    private LocalDate fechaAlta;

    @Override
    public int compareTo(Empleado emp) {
        return this.fechaAlta.compareTo(emp.fechaAlta);
    }
}
