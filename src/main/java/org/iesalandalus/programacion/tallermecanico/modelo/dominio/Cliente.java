package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;
import java.util.regex.Pattern;

public class Cliente {
    private static final String ER_NOMBRE = "^([A-ZÁÉÍÓÚ][a-záéíóú]+)( [A-ZÁÉÍÓÚ][a-záéíóú]+)*$";
    private static final String ER_DNI = "(\\d{8})([A-Z])";
    private static final String ER_TELEFONO = "\\d{9}";
    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "No es posible copiar un cliente nulo.");
        nombre = cliente.nombre;
        dni = cliente.dni;
        telefono = cliente.telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre,"El nombre no puede ser nulo.");
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }

        if (!Pattern.matches(ER_NOMBRE, nombre)) {
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }

        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        Objects.requireNonNull(dni,"El DNI no puede ser nulo.");
        if (dni.isBlank()) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }

        if (!Pattern.matches(ER_DNI, dni)) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }

        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }

        this.dni = dni;
    }

    public boolean comprobarLetraDni(String dni) {
        String digitoControl = "TRWAGMYFPDXBNJZSQVHLCKE";
        return dni.charAt(8) == digitoControl.charAt(Integer.parseInt(dni.substring(0, 8)) % 23);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono,"El teléfono no puede ser nulo.");
        if (telefono.isBlank()) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }

        if (!Pattern.matches(ER_TELEFONO, telefono)) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }

        this.telefono = telefono;
    }

    public static Cliente get(String dni) {
        return new Cliente("Patricio Estrella",dni,"950111111");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nombre, cliente.nombre) && Objects.equals(dni, cliente.dni) && Objects.equals(telefono, cliente.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, dni, telefono);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", nombre, dni, telefono);
    }
}
