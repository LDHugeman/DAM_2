package com.example.preferenciascompartidas_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botonPreferencias;
    Button botonObtenerPreferencias;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonPreferencias = findViewById(R.id.boton_Preferencias);
        botonObtenerPreferencias = findViewById(R.id.boton_obtenerPreferencias);
        botonPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);
                startActivity(intent);
            }
        });

        botonObtenerPreferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                boolean checkbox = sharedPreferences.getBoolean("opcion1", false);
                String texto = sharedPreferences.getString("opcion2", "");
                String color = sharedPreferences.getString("opcion3", "");
                boolean switchPreference = sharedPreferences.getBoolean("opcion4", false);
                String tono = sharedPreferences.getString("opcion5", "");

                Toast.makeText(MainActivity.this, "Checkbox: " + checkbox +"\n"
                        + " EditText: " + texto +"\n"
                        + " List: " + color +"\n"
                        + " Switch: " + switchPreference +"\n"
                        + " Tono: " + tono, Toast.LENGTH_LONG).show();
            }
        });
    }
}
