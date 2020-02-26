package existdbactividad8;

import objetos.Departamento;

/**
 *
 * @author a18luisdvp
 */
public class Altas {

    public static void nuevoDepartamento(Departamento departamento) {
        String accion = "update insert "
                + "<filedepar>"
                + "<dept_no>" + departamento.getNumero() + "</dept_no>"
                + "<dnombre>" + departamento.getNombre() + "</dnombre>"
                + "<loc>" + departamento.getLocalidad() + "</loc>"
                + "</filedepar>"
                + "into /departamentos";
        Menu.ejecutarAccion(accion);
    }
}
