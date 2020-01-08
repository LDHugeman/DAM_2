package com.example.cambiodemoneda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {

    private TextView textViewResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        textViewResultado = findViewById(R.id.textView_Resultado);
        Intent intent = getIntent();
        String resultado = intent.getStringExtra("cambio");
        textViewResultado.setText(resultado);
    }
}
