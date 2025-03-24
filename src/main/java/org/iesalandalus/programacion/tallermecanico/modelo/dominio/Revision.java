package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision extends Trabajo{

    private static final float FACTOR_HORA = 35F;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
    }

    public Revision(Revision revision) { super(revision); }

    public float getPrecioEspecifico() { return (estaCerrado()) ? FACTOR_HORA * getHoras() : 0; }

    @Override
    public String toString() {
        String cadena;
        if (!estaCerrado()) {
            cadena = String.format("Revisión -> %s - %s (%s - ): %s horas", getCliente(), getVehiculo(), getFechaInicio().format(FORMATO_FECHA), getHoras());
        } else {
            cadena = String.format("Revisión -> %s - %s (%s - %s): %s horas, %.2f € total", getCliente(), getVehiculo(), getFechaInicio().format(FORMATO_FECHA), getFechaFin().format(FORMATO_FECHA), getHoras(), getPrecio());
        }
        return cadena;
    }
}
