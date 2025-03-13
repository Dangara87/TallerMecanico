package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola() {}

    public static void mostrarCabecera(String mensaje) {
        Objects.requireNonNull(mensaje);
        System.out.printf("%n%s%n", mensaje);
        System.out.printf(String.format("%s%n", ("-").repeat(mensaje.length())));
    }

    public static void mostrarMenu() {
        mostrarCabecera("MENÚ");
        for (Opcion opcion : Opcion.values()) {
            System.out.printf("%s%n", opcion);
        }
        System.out.println();
    }

    public static Opcion elegirOpcion() {
        Opcion opcion = null;
        do {
            try {
                opcion = Opcion.get(leerEntero("Elige una opción: "));
            } catch (IllegalArgumentException e) {
                System.out.printf("%s%n%n", e.getMessage());
            }
        } while (opcion == null);
        return opcion;
    }

    private static int leerEntero(String mensaje) {
        Objects.requireNonNull(mensaje);
        System.out.print(mensaje);
        return Entrada.entero();
    }

    private static float leerReal(String mensaje) {
        Objects.requireNonNull(mensaje);
        System.out.print(mensaje);
        return Entrada.real();
    }

    private static String leerCadena(String mensaje) {
        Objects.requireNonNull(mensaje);
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    private static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaCorrecta = false;
        do {
            try {
                fecha = LocalDate.parse(leerCadena(mensaje), DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA));
                fechaCorrecta = true;
            } catch (DateTimeParseException ignored) {
                System.out.printf("La fecha introducida tiene un formato inválido (dd/MM/yyyy).%n");
            }
        } while (!fechaCorrecta);
        return fecha;
    }

    public static Cliente leerCliente() {
        return new Cliente(leerCadena("Dime el nombre del cliente: "), leerCadena("Dime el dni del cliente: "), leerCadena("Dime el teléfono del cliente: "));
    }

    public static Cliente leerClienteDni() {
        return new Cliente(Cliente.get(leerCadena("Dime el DNI del cliente: ")));
    }

    public static String leerNuevoNombre() {
        String nombre;
        nombre = leerCadena("Dime el nuevo nombre del cliente: ");
        return nombre;
    }

    public static String leerNuevoTelefono() {
        String telefono;
        telefono = leerCadena("Dime el nuevo teléfono del cliente: ");
        return telefono;
    }

    public static Vehiculo leerVehiculo() {
        return new Vehiculo(leerCadena("Dime la marca del vehículo: "), leerCadena("Dime el modelo del vehículo"), leerCadena("Dime la matrícula del vehículo: "));
    }

    public static Vehiculo leerVehiculoMatricula() {
        return Vehiculo.get(leerCadena("Dime la matrícula del vehículo: "));
    }

    public static Revision leerRevision() {
        return new Revision(leerClienteDni(), leerVehiculoMatricula(), leerFecha("Dime la fecha de inicio de trabajo: "));
    }

    public static int leerHoras() {
        return leerEntero("Dime las horas que quieras añadir: ");
    }

    public static float leerPrecioMaterial() {
        return leerReal("Dime el precio que quieres añadir: ");
    }

    public static LocalDate leerFechaCierre() {
        return leerFecha("Dime la fecha de cierre: ");
    }
}