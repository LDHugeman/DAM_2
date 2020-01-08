package com.example.layout_codigo;
import android.app.Activity;
import android.os.Bundle;
// Se importa el paquete que define donde está declarada la clase TextView
import android.widget.TextView;
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//setContentView(R.layout.activity_main);
// Se sustituye la linea anterior por las siguientes
// Crear una instancia de la clase TextView
        TextView etiqueta1 = new TextView(this);
// Establecemos el valor del string
        etiqueta1.setText("TextView desde código");
// Modificamos aspecto
        etiqueta1.setTextSize(25);
        etiqueta1.setTextColor(0xFFFF0000);
// Colocamos la vista TextView en el layout de la actividad
        setContentView(etiqueta1);
    }
}
