package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModeloCascada implements Modelo {
    IClientes clientes;
    IVehiculos vehiculos;
    ITrabajos trabajos;

    public ModeloCascada(FabricaFuenteDatos fabricaFuenteDatos) {
        Objects.requireNonNull(fabricaFuenteDatos, "La factoría de la fuente de datos no puede ser nula.");
        IFuenteDatos fuenteDatos = fabricaFuenteDatos.crear();
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
        trabajos = fuenteDatos.crearTrabajos();
    }

    public void comenzar() {
        System.out.println("Modelo comenzado.");
    }

    public void terminar() {
        System.out.println("Modelo terminado.");
    }

    public void insertar(Cliente cliente) {
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) {
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede insertar un trabajo nulo.");
        Trabajo trabajo1 = null;
        if (trabajo instanceof Revision) {
            trabajo1 = new Revision(clientes.buscar(trabajo.getCliente()), vehiculos.buscar(trabajo.getVehiculo()), trabajo.getFechaInicio());
        }
        if (trabajo instanceof Mecanico) {
            trabajo1 = new Mecanico(clientes.buscar(trabajo.getCliente()), vehiculos.buscar(trabajo.getVehiculo()), trabajo.getFechaInicio());
        }
        trabajos.insertar(trabajo1);
    }

    public Cliente buscar(Cliente cliente) {
        return new Cliente(clientes.buscar(cliente));
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    public Trabajo buscar(Trabajo trabajo) {
        return Trabajo.copiar(trabajos.buscar(trabajo));
    }

    public Cliente modificar(Cliente cliente, String nombre, String telefono) {
        return clientes.modificar(cliente, nombre, telefono);
    }

    public Trabajo anadirHoras(Trabajo trabajo, int horas) {
        return trabajos.anadirHoras(trabajo, horas);
    }

    public Trabajo anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) {
        return trabajos.anadirPrecioMaterial(trabajo, precioMaterial);
    }

    public Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) {
        return trabajos.cerrar(trabajo, fechaFin);
    }

    public void borrar(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        for (Trabajo trabajoCliente : trabajos.get(cliente)) {
            vehiculos.borrar(trabajoCliente.getVehiculo());
            trabajos.borrar(trabajoCliente);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        for (Trabajo trabajoVehiculo : trabajos.get(vehiculo)) {
            trabajos.borrar(trabajoVehiculo);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Trabajo trabajo) {
        trabajos.borrar(trabajo);
    }

    public List<Cliente> getClientes() {
        List<Cliente> copiaClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            copiaClientes.add(new Cliente(cliente));
        }
        return copiaClientes;
    }

    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos.get());
    }

    public List<Trabajo> getTrabajos() {
        List<Trabajo> copiaTrabajos = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get()) {
            copiaTrabajos.add(Trabajo.copiar(trabajo));
        }
        return copiaTrabajos;
    }

    public List<Trabajo> getTrabajos(Cliente cliente) {
        List<Trabajo> trabajoCliente = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(cliente)) {
            trabajoCliente.add(Trabajo.copiar(trabajo));
        }
        return trabajoCliente;
    }

    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        List<Trabajo> trabajoVehiculo = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            trabajoVehiculo.add(Trabajo.copiar(trabajo));
        }
        return trabajoVehiculo;
    }
}
