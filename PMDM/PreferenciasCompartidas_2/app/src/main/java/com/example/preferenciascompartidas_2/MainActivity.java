package com.example.preferenciascompartidas_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioButton radioButtonAzul;
    Button botonGuardar;
    TextView textViewTexto;
    String color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioButtonAzul = findViewById(R.id.radioButton_azul);
        botonGuardar = findViewById(R.id.boton_guardar);
        textViewTexto = findViewById(R.id.textView_texto);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        radioButtonAzul.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    textViewTexto.setTextColor(getResources().getColor(R.color.azul));
                    color = "Azul"; //idColor=R.color.azul
                } else {
                    textViewTexto.setTextColor(getResources().getColor(R.color.rojo));
                    color = "Rojo"; //idColor=R.color.rojo
                }
                //textView.setTextColor(getResources().getColor(idColor));
            }
        });
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("color",color);
                editor.apply();
            }
        });

        String color = sharedPreferences.getString("color", "colordefectivo"); //R.color.negro
        if(color.equals("Azul")){
            textViewTexto.setTextColor(getResources().getColor(R.color.azul));
        } else if(color.equals("Rojo")){
            textViewTexto.setTextColor(getResources().getColor(R.color.rojo));
        }
    }
}
