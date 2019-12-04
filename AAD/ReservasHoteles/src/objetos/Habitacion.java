
package objetos;

import java.beans.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a18luisdvp
 */
public class Habitacion implements Serializable, PropertyChangeListener{
    
    private int idHabitacion;
    private String tipo;
    private float precio;
    private boolean ocupado;
    private Hotel hotel;
    private Set<Reserva> reservas;
    
    public Habitacion(){        
    }

    public Habitacion(int idHabitacion, String tipo, float precio, boolean ocupado, Hotel hotel, Set<Reserva> reservas) {
        this.idHabitacion = idHabitacion;
        this.tipo = tipo;
        this.precio = precio;
        this.ocupado = ocupado;
        this.hotel = hotel;
        this.reservas = new HashSet<>();
    }
    
    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setOcupado(true);
    }
}
