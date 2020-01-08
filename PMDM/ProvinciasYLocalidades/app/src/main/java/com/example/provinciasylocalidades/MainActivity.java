package com.example.provinciasylocalidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerProvincias;
    private Spinner spinnerLocalidades;
    private ArrayAdapter<CharSequence> adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerProvincias = findViewById(R.id.spinner_provincias);
        spinnerLocalidades = findViewById(R.id.spinner_localidades);
        spinnerProvincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                if (spinnerProvincias.getSelectedItem().equals("A Coruña")){
                    adaptador = ArrayAdapter.createFromResource(MainActivity.this, R.array.localidadesACoruña, android.R.layout.simple_spinner_item);
                    spinnerLocalidades.setAdapter(adaptador);
                } else if (spinnerProvincias.getSelectedItem().equals("Lugo")){
                    adaptador = ArrayAdapter.createFromResource(MainActivity.this, R.array.localidadesLugo, android.R.layout.simple_spinner_item);
                    spinnerLocalidades.setAdapter(adaptador);
                } else if(spinnerProvincias.getSelectedItem().equals("Ourense")){
                    adaptador = ArrayAdapter.createFromResource(MainActivity.this, R.array.localidadesOurense, android.R.layout.simple_spinner_item);
                    spinnerLocalidades.setAdapter(adaptador);
                }else if(spinnerProvincias.getSelectedItem().equals("Pontevedra")){
                    adaptador = ArrayAdapter.createFromResource(MainActivity.this, R.array.localidadesPontevedra, android.R.layout.simple_spinner_item);
                    spinnerLocalidades.setAdapter(adaptador);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerLocalidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                Toast.makeText(MainActivity.this, "Provincia: "+spinnerProvincias.getSelectedItem().toString()+"\n"+"Localidad: "+adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
