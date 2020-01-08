package com.example.actividades_calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button botonIrCalculadora;
    private Button botonIrAjustes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonIrCalculadora = findViewById(R.id.boton_IrCalculadora);
        botonIrAjustes = findViewById(R.id.boton_IrAjustes);
        botonIrCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.android.calculator2","com.android.calculator2.Calculator");
                startActivity(intent);
            }
        });
        botonIrAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.android.settings","com.android.settings.Settings");
                startActivity(intent);
            }
        });
    }
}
