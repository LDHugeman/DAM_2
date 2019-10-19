
package objetos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author a18luisdvp
 */
public class Empleado implements Serializable{
    
    private String numeroSegSocial;
    private String nombre;
    private String oficio;
    private String direccion;
    private Date fechaAlta;
    private float salario;
    private float comision;
    private int numeroDepartamento;
    
    public Empleado(){       
    }

    public Empleado(String numeroSegSocial, String nombre, String oficio, String direccion, Date fechaAlta, float salario, float comision, int numeroDepartamento) {
        this.numeroSegSocial = numeroSegSocial;
        this.nombre = nombre;
        this.oficio = oficio;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.salario = salario;
        this.comision = comision;
        this.numeroDepartamento = numeroDepartamento;
    }
        
    public String getNumeroSegSocial() {
        return numeroSegSocial;
    }

    public void setNumeroSegSocial(String numeroSegSocial) {
        this.numeroSegSocial = numeroSegSocial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }
    
    public int getNumeroDepartamento() {
        return numeroDepartamento;
    }

    public void setNumeroDepartamento(int numeroDepartamento) {
        this.numeroDepartamento = numeroDepartamento;
    }
}
