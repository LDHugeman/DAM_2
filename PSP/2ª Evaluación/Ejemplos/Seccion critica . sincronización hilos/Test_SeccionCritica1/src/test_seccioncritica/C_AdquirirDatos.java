/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_seccioncritica;

/**
 *
 * @author a14miguelfc
 */
public class C_AdquirirDatos extends Thread{
    private C_Datos m;
    /* constructor*/
    public C_AdquirirDatos(C_Datos mdatos){
        m=mdatos; /*recibimos el objeto**/
    }
    
    
    /* LA TAREA DE HILO ES : INTENTAR LLENAR EL ARRAY, EJECUTANDO CÁLCULOS TODO EL TIEMPO
      HASTA QUE EL ARRAY SE LLENE.
      PERO NO ES UNO SOLO HACIENDO ESTO, SON N HILOS 
    */
    public void run(){
        int i=0;
       
        do{
              i=m.calculos(getName());             
                
        }while(i<m.tamano);
    }
}
