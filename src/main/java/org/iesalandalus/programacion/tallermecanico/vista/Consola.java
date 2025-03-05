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

    public void mostrarCabecera(String mensaje) {
        Objects.requireNonNull(mensaje);
        System.out.printf("%n%s%n", mensaje);
        System.out.printf(String.format("%s%n", ("-").repeat(mensaje.length())));
    }

    public void mostrarMenu() {
        mostrarCabecera("MENÚ");
        for (Opcion opcion : Opcion.values()) {
            System.out.printf("%s%n", opcion);
        }
        System.out.println();
    }

    public Opcion elegirOpcion() {
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

    private int leerEntero(String mensaje) {
        Objects.requireNonNull(mensaje);
        System.out.print(mensaje);
        return Entrada.entero();
    }

    private float leerReal(String mensaje) {
        Objects.requireNonNull(mensaje);
        System.out.print(mensaje);
        return Entrada.real();
    }

    private String leerCadena(String mensaje) {
        Objects.requireNonNull(mensaje);
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    private LocalDate leerFecha(String mensaje) {
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

    public Cliente leerCliente() {
        return new Cliente(leerCadena("Dime el nombre del cliente: "), leerCadena("Dime el dni del cliente: "), leerCadena("Dime el teléfono del cliente: "));
    }

    public Cliente leerClienteDni() {
        return new Cliente(Cliente.get(leerCadena("Dime el DNI del cliente: ")));
    }

    public String leerNuevoNombre() {
        String nombre;
        nombre = leerCadena("Dime el nuevo nombre del cliente: ");
        if (!nombre.isBlank()) {
            new Cliente(nombre, )
        }
    }

    public String leerNuevoTelefono() {

    }

    public Vehiculo leerVehiculo() {

    }

    public Vehiculo leerVehiculoMatricula() {

    }

    public Revision leerRevision() {

    }

    public int leerHoras() {

    }

    public float leerPrecioMaterial() {

    }

    public LocalDate leerFechaCierre() {
        
    }
}
