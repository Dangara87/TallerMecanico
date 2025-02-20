package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30F;
    private static final float PRECIO_DIA = 10F;
    private static final float PRECIO_MATERIAL = 1.50F;
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yy");
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;
    private Cliente cliente;
    private Vehiculo vehiculo;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }

    public Revision(Revision revision) {
        if (revision == null) {
            throw new NullPointerException("La revisión no puede ser nula.");
        }
        setCliente(revision.cliente);
        setVehiculo(revision.vehiculo);
        setFechaInicio(revision.fechaInicio);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = Objects.requireNonNull(cliente,"El cliente no puede ser nulo.");
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = Objects.requireNonNull(vehiculo,"El vehículo no puede ser nulo.");
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");

        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }

        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");

        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }

        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public void anadirHoras(int horas) {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        if (estaCerrada()) {
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        this.horas = horas + getHoras();
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) {
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        if (estaCerrada()) {
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        this.precioMaterial = precioMaterial + getPrecioMaterial();
    }

    public boolean estaCerrada() {
        return false;
    }

    public void cerrar(LocalDate fechaFin) {

    }

    public float getPrecio() {
        return (getHoras() * PRECIO_HORA) + (getDias() * PRECIO_DIA) + (getPrecioMaterial() * PRECIO_MATERIAL);
    }

    public float getDias() {
        return 2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return horas == revision.horas && Float.compare(precioMaterial, revision.precioMaterial) == 0 && Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(fechaFin, revision.fechaFin) && Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, fechaFin, horas, precioMaterial, cliente, vehiculo);
    }

    @Override
    public String toString() {
        return (fechaFin == null) ? String.format("%s - %s: (%s - ), %s horas, %.2f € en material", cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), horas, precioMaterial) : String.format("%s - %s: (%s - %s), %s horas, %.2f € en material, %s € total", cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), fechaFin.format(FORMATO_FECHA), horas, precioMaterial, getPrecio());
    }
}
