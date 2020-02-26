package com.example.planetas3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerPlanetas;
    private TextView textViewEleccion;
    private String[] arrayPlanetas = {"Mercurio","Venus","Tierra","Marte","Júpiter","Saturno","Urano","Neptuno"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewEleccion = findViewById(R.id.textView_eleccion);
        spinnerPlanetas = findViewById(R.id.spinner_planetas);
        ArrayAdapter<String> adaptador =  new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, arrayPlanetas);
        ArrayAdapter<String> adaptador2 =  new ArrayAdapter<String> (this, android.R.layout.simple_dropdown_item_1line, arrayPlanetas);
        ArrayAdapter<String> adaptador3 =  new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, arrayPlanetas);
        spinnerPlanetas.setAdapter(adaptador3);
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
