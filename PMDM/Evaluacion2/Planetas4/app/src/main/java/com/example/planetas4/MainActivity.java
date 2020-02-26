package com.example.planetas4;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewEleccion = findViewById(R.id.textView_eleccion);
        spinnerPlanetas = findViewById(R.id.spinner_planetas);
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.planetas, android.R.layout.simple_spinner_item);
        spinnerPlanetas.setAdapter(adaptador);
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
