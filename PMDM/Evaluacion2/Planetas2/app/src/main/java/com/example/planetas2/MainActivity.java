package com.example.planetas2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerPlanetas;
    private TextView textViewEleccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewEleccion = findViewById(R.id.textView_eleccion);
        spinnerPlanetas = findViewById(R.id.spinner_planetas);
        spinnerPlanetas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                textViewEleccion.setText("Elección: " + adapterView.getItemAtPosition(i)+
                                         "\nEstá en la posición "+ (i+1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
