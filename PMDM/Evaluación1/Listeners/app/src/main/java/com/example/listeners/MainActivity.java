package com.example.listeners;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1, btn2, btn3, btn4;
    private TextView respuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        respuesta = findViewById(R.id.respuesta);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        Aux escuchador1 = new Aux();
        btn1.setOnClickListener(escuchador1);
        btn2.setOnClickListener(escuchador2);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respuesta.setText("Botón 3 pulsado \n Gestión un solo paso");
            }
        });
        btn4.setOnClickListener(this);
    }

    private class Aux implements View.OnClickListener {
        @Override
        public void onClick(View v){
            respuesta.setText("Botón 1 pulsado\n Gestión con clase interna");
        }
    }

    private View.OnClickListener escuchador2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            respuesta.setText("Botón 2 pulsado \n Gestión con clase anónima");
        }
    };

    @Override
    public void onClick(View v) {
        respuesta.setText("Botón 4 pulsado \n Gestión desde la propia clase");
    }

}
