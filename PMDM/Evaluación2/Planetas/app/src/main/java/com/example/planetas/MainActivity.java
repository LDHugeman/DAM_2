package com.example.planetas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button botonEleccion;
    private Spinner spinnerPlanetas;
    private TextView textViewEleccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerPlanetas = findViewById(R.id.spinner_planetas);
        textViewEleccion = findViewById(R.id.textView_eleccion);
        botonEleccion = findViewById(R.id.boton_eleccion);
        botonEleccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    textViewEleccion.setText("Elección: "+spinnerPlanetas.getSelectedItem()+
                                            "\nEstá en la posición "+spinnerPlanetas.getSelectedItemId());
            }
        });
    }
}
