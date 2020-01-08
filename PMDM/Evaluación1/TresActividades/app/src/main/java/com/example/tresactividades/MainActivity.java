package com.example.tresactividades;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button botonIrActivity2;
    private Button botonIrActivity3;
    private Button botonIrActivityExterna;
    private static final int CODIGO_LLAMADA_A2 = 1;
    private static final int CODIGO_LLAMADA_A3 = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonIrActivity2 = findViewById(R.id.boton_IrActivity2);
        botonIrActivityExterna = findViewById(R.id.boton_IrActivityExterna);
        botonIrActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
                String mensajeA2 = "Mensaje para la Actividad 2";
                intent.putExtra("mensajeA2", mensajeA2);
                startActivityForResult(intent,CODIGO_LLAMADA_A2);
            }
        });
        botonIrActivity3 = findViewById(R.id.boton_IrActivity3);
        botonIrActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TerceraActivity.class);
                String mensajeA3 = "Mensaje para la Actividad 3";
                intent.putExtra("mensajeA3", mensajeA3);
                startActivityForResult(intent,CODIGO_LLAMADA_A3);
            }
        });
        botonIrActivityExterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.example.pulsaetiquetas","com.example.pulsaetiquetas.MainActivity");
                PackageManager packageManager = getPackageManager();
                List actividadesPosibles = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                if(actividadesPosibles.size()>0){
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Ninguna actividad puede realizar esta acción", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODIGO_LLAMADA_A2){
            if(resultCode==RESULT_OK){
                int numeroEnteroRecibido = data.getIntExtra("numeroEntero_de_a2",0);
                Toast.makeText(this, "Ha recibido de la Actividad 2 el número entero: "+numeroEnteroRecibido, Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == CODIGO_LLAMADA_A3){
            if (resultCode == RESULT_OK){
                double numeroDecimalRecibido = data.getDoubleExtra("numeroDecimal_de_a3",0);
                Toast.makeText(this, "Ha recibido de la Actividad 3 el número decimal: " + numeroDecimalRecibido, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
