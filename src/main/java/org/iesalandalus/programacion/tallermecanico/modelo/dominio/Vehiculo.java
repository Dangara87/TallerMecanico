package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;
import java.util.regex.Pattern;

public record Vehiculo(String marca, String modelo, String matricula) {

    private static final String ER_MARCA = "^([A-Z][a-z]+)?([A-Z]+)?[ -]?([A-Z][a-z]+)?$";
    private static final String ER_MATRICULA = "(\\d{4})([B-Z]{3})";

    public Vehiculo {
        validarMarca(marca);
        validarModelo(modelo);
        validarMatricula(matricula);
    }

    public void validarMarca(String marca) {
        if (marca == null) {
            throw new NullPointerException("La marca no puede ser nula.");
        }
        if (marca.isBlank()) {
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }
        if (!Pattern.matches(ER_MARCA, marca)) {
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }

    }

    public void validarModelo(String modelo) {
        if (modelo == null) {
            throw new NullPointerException("El modelo no puede ser nulo.");
        }
        if (modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar en blanco.");
        }
    }

    public void validarMatricula(String matricula) {
        if (matricula == null) {
            throw new NullPointerException("La matrícula no puede ser nula.");
        }
        if (matricula.isBlank()) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
        if (!Pattern.matches(ER_MATRICULA, matricula)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
    }

    public static Vehiculo get(String matricula) {
        return new Vehiculo("Renault","Megane", matricula);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(marca, vehiculo.marca) && Objects.equals(modelo, vehiculo.modelo) && Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marca, modelo, matricula);
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s", marca, modelo, matricula);
    }
}
