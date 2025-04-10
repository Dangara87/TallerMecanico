package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Consola {
    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola() {}

    static void mostrarCabecera(String mensaje) {
        System.out.printf("%n%s%n", mensaje);
        String formatoStr = "%0" + mensaje.length() + "d%n";
        System.out.println(String.format(formatoStr, 0).replace("0","-"));
    }

    static void mostrarMenu() {
        mostrarCabecera("Gestión de un taller mecánico");
        for (Evento opcion : Evento.values()) {
            System.out.printf("%d.- %s%n", opcion.getCodigo(), opcion);
        }
    }

    static Evento elegirOpcion() {
        Evento opcion = null;
        do {
            try {
                opcion = Evento.get(leerEntero("\nElige una opción: "));
            } catch (IllegalArgumentException e) {
                System.out.printf("%s%n%n", e.getMessage());
            }
        } while (opcion == null);
        return opcion;
    }

    static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    static float leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

    static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    static LocalDate leerFecha(String mensaje) {
        LocalDate fecha;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        mensaje = String.format("%s (%s): ", mensaje, CADENA_FORMATO_FECHA);
        try {
            fecha = LocalDate.parse(leerCadena(mensaje), formatoFecha);
        } catch (DateTimeParseException e) {
            fecha = null;
        }
        return fecha;
    }
/*
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

 */
}