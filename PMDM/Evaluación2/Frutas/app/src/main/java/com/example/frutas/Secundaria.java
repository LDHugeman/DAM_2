package com.example.frutas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Secundaria extends AppCompatActivity {

    Spinner spinnerFrutas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);
        spinnerFrutas = findViewById(R.id.spinner_frutas);
        Intent intent = getIntent();
        ArrayList<String> frutas = intent.getStringArrayListExtra("frutas");
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(Secundaria.this, android.R.layout.simple_spinner_item, frutas);
        spinnerFrutas.setAdapter(adaptador);
        spinnerFrutas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                String fruta = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Secundaria.this, fruta, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


}
