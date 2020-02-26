package com.example.preferenciascompartidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button botonGuardarPreferencia;
    Button botonRecuperarPreferencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonGuardarPreferencia = findViewById(R.id.boton_guardar);
        botonRecuperarPreferencia = findViewById(R.id.boton_recuperar);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        botonGuardarPreferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("email","luisdavid.vp@gmail.com");
                editor.putString("nombre","David");
                editor.apply();
            }
        });

        botonRecuperarPreferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = sharedPreferences.getString("email","emailDefectivo");
                String nombre = sharedPreferences.getString("nombre", "nombreDefectivo");
                Log.i("Preferencias","Correo: "+ email);
                Log.i("Preferencias","Nombre: "+nombre);
            }
        });
    }
}
