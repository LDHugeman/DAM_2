package com.example.ejemplosintentsimplicitos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button botonContactos;
    private Button botonMostrarTeclado;
    private Button botonMarcarNumero;
    private Button botonLlamarTelefono;
    private Button botonAbrirNavegador;
    private Button botonVerMapa;
    private static final int LLAMADA_TELEFONO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonContactos = findViewById(R.id.boton_contactos);
        botonMostrarTeclado = findViewById(R.id.boton_mostrarTeclado);
        botonMarcarNumero = findViewById(R.id.boton_marcarNumero);
        botonLlamarTelefono = findViewById(R.id.boton_llamarTelefono);
        botonAbrirNavegador = findViewById(R.id.boton_abrirNavegador);
        botonVerMapa = findViewById(R.id.boton_verMapa);
        botonContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "IRRESOLUBLE", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonMostrarTeclado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
            }
        });

        botonMarcarNumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+34) 986112233"));
                startActivity(intent);
            }
        });

        botonLlamarTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34) 986112233"));
                        startActivity(intent);
                    } else {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, LLAMADA_TELEFONO);
                    }
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34) 986112233"));
                    startActivity(intent);
                }
            }
        });

        botonAbrirNavegador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.edu.xunta.gal/portal"));
                startActivity(intent);
            }
        });

        botonVerMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:42.25,-8.68"));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LLAMADA_TELEFONO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34) 986112233"));
                startActivity(intent);
            } else {
                Toast.makeText(this, "No se puede llamar sin el permiso", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
