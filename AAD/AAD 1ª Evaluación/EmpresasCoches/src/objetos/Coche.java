
package objetos;

import java.io.Serializable;

/**
 *
 * @author a18luisdvp
 */
public class Coche implements Serializable{
    
    private String codigo;
    private String marca;
    private String modelo;
    private Empresa empresa;
    
    public Coche(){        
    }

    public Coche(String codigo, String marca, String modelo, Empresa empresa) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.empresa = empresa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }  
}