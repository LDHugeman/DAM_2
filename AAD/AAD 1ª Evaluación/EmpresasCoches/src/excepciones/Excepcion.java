
package excepciones;

/**
 *
 * @author luisd
 */
public class Excepcion extends Exception{
    
    private String error;
    
    public Excepcion(String message){
        super(message);
        this.error = message;
    }
    
    public String getError (){
        return error;
    }
}
