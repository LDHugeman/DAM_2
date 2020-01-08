package com.example.cuenta_clicks_v4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewClicks;
    private Button botonPulsa;
    private Button botonFinalizar;
    int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewClicks = findViewById(R.id.textView_clicks);
        botonPulsa = findViewById(R.id.boton_pulsa);
        botonFinalizar = findViewById(R.id.boton_finalizar);

        botonPulsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador++;
            }
        });

        botonFinalizar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        textViewClicks.setText("Número de clicks: " + contador);
        contador = 0;
    }
}
