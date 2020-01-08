
package objetos;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author a18luisdvp
 */
public class Reserva implements Serializable {
      
    private PropertyChangeSupport propertyChange;
    private Hotel hotel;
    private Habitacion habitacion;
    private Date fechaEntrada;
    private Date fechaSalida;
    private Cliente cliente;
    
    public Reserva(){        
    }   

    public Reserva(PropertyChangeSupport propertySupport, Hotel hotel, Habitacion habitacion, Date fechaEntrada, Date fechaSalida, Cliente cliente) {
        this.propertyChange = new PropertyChangeSupport(this);
        this.hotel = hotel;
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.cliente = cliente;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChange.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChange.removePropertyChangeListener(listener);
    }   
}
