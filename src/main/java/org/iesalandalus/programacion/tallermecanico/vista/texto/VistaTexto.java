package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import java.time.LocalDate;
import java.util.List;

public class VistaTexto implements Vista {

    private final GestorEventos gestorEventos = new GestorEventos(Evento.values());

    public GestorEventos getGestorEventos() { return gestorEventos;}

    public void comenzar() {
        Evento opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Evento.SALIR);
    }

    private void ejecutar(Evento opcion) {
        Consola.mostrarCabecera(opcion.toString());
        gestorEventos.notificar(opcion);
    }

    public void terminar() {
        System.out.println("Adios.");
    }
    public Cliente leerCliente() {
        return new Cliente(Consola.leerCadena("Dime el nombre del cliente: "), Consola.leerCadena("Dime el dni del cliente: "), Consola.leerCadena("Dime el teléfono del cliente: "));
    }

    public Cliente leerClienteDni() {
        return Cliente.get(Consola.leerCadena("Dime el DNI del cliente: "));
    }

    public String leerNuevoNombre() {
        return Consola.leerCadena("Introduce el nuevo nombre: ");
    }

    public String leerNuevoTelefono() {
        return Consola.leerCadena("Introduce el nuevo teléfono: ");
    }

    public Vehiculo leerVehiculo() {
        return new Vehiculo(Consola.leerCadena("Dime la marca del vehículo: "), Consola.leerCadena("Dime el modelo del vehículo"), Consola.leerCadena("Dime la matrícula del vehículo: "));
    }

    public Vehiculo leerVehiculoMatricula() {
        return Vehiculo.get(Consola.leerCadena("Dime la matrícula del vehículo: "));
    }

    public Trabajo leerRevision() {
        return new Revision(leerClienteDni(), leerVehiculoMatricula(), Consola.leerFecha("Dime la fecha de inicio: "));
    }

    public Trabajo leerMecanico() {
        return new Mecanico(leerClienteDni(), leerVehiculoMatricula(), Consola.leerFecha("Dime la fecha de inicio: "));
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        return Trabajo.get(leerVehiculoMatricula());
    }

    public int leerHoras() {
        return Consola.leerEntero("Dime las horas que quieras añadir: ");
    }

    public float leerPrecioMaterial() {
        return Consola.leerReal("Dime el precio que quieres añadir: ");
    }

    public LocalDate leerFechaCierre() {
        return Consola.leerFecha("Dime la fecha de cierre: ");
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {
        if (exito) {
            System.out.println(texto);
        } else {
            System.out.printf("Error: %s%n", texto);
        }
    }

    @Override
    public void mostrarCliente(Cliente cliente) {
        System.out.println((cliente != null) ? cliente : "No existe ningún cliente con dicho DNI.");
    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {
        System.out.println((vehiculo != null) ? vehiculo : "No existe ningún vehiculo con dicha matricula.");
    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {
        System.out.println((trabajo != null) ? trabajo : "No existe ningún trabajo para ese cliente, vehículo y fecha.");
    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {
        if (!clientes.isEmpty()) {
            for (Cliente cliente: clientes) {
                System.out.println(cliente);
            }
        } else {
            System.out.println("No hay clientes que mostrar.");
        }
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        if (!vehiculos.isEmpty()) {
            for (Vehiculo vehiculo: vehiculos) {
                System.out.println(vehiculo);
            }
        } else {
            System.out.println("No hay vehiculos que mostrar.");
        }
    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {
        if (!trabajos.isEmpty()) {
            for (Trabajo trabajo: trabajos) {
                System.out.println(trabajo);
            }
        } else {
            System.out.println("No hay trabajos que mostrar.");
        }
    }

    public void mostrarTrabajosCliente(List<Trabajo> trabajosCliente) {
        if (!trabajosCliente.isEmpty()) {
            for (Trabajo trabajo: trabajosCliente) {
                System.out.println(trabajo);
            }
        } else {
            System.out.println("No hay trabajos que mostrar para dicho cliente.");
        }
    }

    public void mostrarTrabajosVehiculo(List<Trabajo> trabajosVehiculo) {
        if (!trabajosVehiculo.isEmpty()) {
            for (Trabajo trabajo: trabajosVehiculo) {
                System.out.println(trabajo);
            }
        } else {
            System.out.println("No hay trabajos que mostrar para dicho vehiculo.");
        }
    }
}
