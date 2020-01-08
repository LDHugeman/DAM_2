package com.example.cuenta_clicks_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button botonPulsa;
    private TextView textViewClicks;
    private int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewClicks = findViewById(R.id.textView_clicks);
        botonPulsa = findViewById(R.id.boton_pulsa);
        botonPulsa.setOnClickListener(escuchador);
    }

    private View.OnClickListener escuchador = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            contador++;
            textViewClicks.setText("Número de clicks: "+contador);
        }
    };
}
