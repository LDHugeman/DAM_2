package objetos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a18luisdvp
 */
public class Hotel implements Serializable {
    
    private int idHotel;
    private String nombre;
    private String telefono;
    private Set<Habitacion> habitaciones;
    private Set<Reserva> reservas;
    
    public Hotel(){        
    }

    public Hotel(int idHotel, String nombre, String telefono) {
        this.idHotel = idHotel;
        this.nombre = nombre;
        this.telefono = telefono;
        this.habitaciones = new HashSet<>();
        this.reservas = new HashSet<>();
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Set<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }
}
