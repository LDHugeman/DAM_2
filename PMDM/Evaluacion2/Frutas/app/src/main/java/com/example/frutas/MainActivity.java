package com.example.frutas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFruta;
    private Button botonAñadirFruta;
    private Button botonAcabar;
    private ArrayList<String> frutas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextFruta = findViewById(R.id.editText_fruta);
        botonAñadirFruta = findViewById(R.id.boton_añadirFruta);
        botonAcabar = findViewById(R.id.boton_acabar);
        frutas = new ArrayList<>();
        botonAñadirFruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fruta = editTextFruta.getText().toString();
                if(!fruta.isEmpty()){
                    frutas.add(fruta);
                    editTextFruta.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Escribe una fruta", Toast.LENGTH_LONG).show();
                }
            }
        });

        botonAcabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!frutas.isEmpty()){
                    Intent intent = new Intent(MainActivity.this, Secundaria.class);
                    intent.putStringArrayListExtra("frutas", frutas);
                    startActivity(intent);
                    botonAñadirFruta.setEnabled(false);
                    botonAcabar.setEnabled(false);
                } else {
                    Toast.makeText(MainActivity.this, "Añada frutas para visualizar", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
