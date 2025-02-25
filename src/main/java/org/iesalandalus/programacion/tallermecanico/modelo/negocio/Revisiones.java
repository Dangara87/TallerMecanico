package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {

    private final List<Revision> coleccionRevision;

    public Revisiones() {
        coleccionRevision = new ArrayList<>();
    }

    public List<Revision> get() {
        return new ArrayList<>(coleccionRevision);
    }

    public List<Revision> get(Cliente cliente) {
        List<Revision> revisionClientes = new ArrayList<>();
        for (Revision revision : coleccionRevision) {
            if (revision.getCliente().equals(cliente)) {
                revisionClientes.add(revision);
            }
        }
        return revisionClientes;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        List<Revision> revisionVehiculo = new ArrayList<>();
        for (Revision revision : coleccionRevision) {
            if (revision.getVehiculo().equals(vehiculo)) {
                revisionVehiculo.add(revision);
            }
        }
        return revisionVehiculo;
    }

    public void insertar(Revision revision) {
        Objects.requireNonNull(revision, "No se puede insertar una revisión nula.");
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        coleccionRevision.add(revision);
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) {
        for (Revision revision : coleccionRevision) {
            if (!revision.estaCerrada()) {
                if (revision.getCliente().equals(cliente)) {
                    throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
                } else if (revision.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
                }
            } else {
                if (revision.getCliente().equals(cliente) && !fechaRevision.isAfter(revision.getFechaFin())) {
                    throw new TallerMecanicoExcepcion("El cliente tiene una revisión posterior.");
                } else if (revision.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(revision.getFechaFin())) {
                    throw new TallerMecanicoExcepcion("El vehículo tiene una revisión posterior.");
                }
            }
        }
    }

    public Revision anadirHoras(Revision revision, int horas) {
        Revision revisionEncontrada = getRevision(revision);
        revisionEncontrada.anadirHoras(horas);
        return revisionEncontrada;
    }

    private Revision getRevision(Revision revision) {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        Revision revisionEncontrada = buscar(revision);
        if (revisionEncontrada == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        return revisionEncontrada;
    }

    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) {
        Revision revisionEncontrada = getRevision(revision);
        revisionEncontrada.anadirPrecioMaterial(precioMaterial);
        return revisionEncontrada;
    }

    public Revision cerrar(Revision revision, LocalDate fechaFin) {
        Revision revisionEncontrada = getRevision(revision);
        revisionEncontrada.cerrar(fechaFin);
        return revisionEncontrada;
    }

    public Revision buscar(Revision revision) {
        Objects.requireNonNull(revision, "No se puede buscar una revisión nula.");
        int indice = coleccionRevision.indexOf(revision);
        return (indice == -1) ? null : coleccionRevision.get(indice);
    }

    public void borrar(Revision revision) {
        Objects.requireNonNull(revision, "No se puede borrar una revisión nula.");
        if (!coleccionRevision.contains(revision)) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        coleccionRevision.remove(revision);

    }
}
