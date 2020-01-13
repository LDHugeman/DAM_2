package ejerciciopropuesto;

public class Persona {

    String nombre;
    int longitud;
    String iniciales;

    public Persona(String nombre, int longitud, String iniciales) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.iniciales = iniciales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getIniciales() {
        return iniciales;
    }

    public void setIniciales(String iniciales) {
        this.iniciales = iniciales;
    }

    @Override
    public boolean equals(Object obj) {
        boolean eq = false;
        if (obj instanceof  Persona){
            eq = nombre.equalsIgnoreCase(((Persona) obj).getNombre());
        }
        return eq;

    }

    @Override
    public String toString() {
        return nombre + " " + longitud + " " + iniciales;
    }
}
