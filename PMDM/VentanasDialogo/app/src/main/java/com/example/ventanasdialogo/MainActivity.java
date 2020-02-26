package com.example.ventanasdialogo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botonVentana1;
    Button botonVentana2;
    Button botonVentana3;
    Button botonVentana4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonVentana1 = findViewById(R.id.button_ventana1);
        botonVentana2 = findViewById(R.id.button_ventana2);
        botonVentana3 = findViewById(R.id.button_ventana3);
        botonVentana4 = findViewById(R.id.button_ventana4);
        botonVentana1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(MainActivity.this);
                ventana.setTitle("Atención");
                ventana.setMessage("Esto es un mensaje. Pulsa el botón...");
                ventana.setIcon(android.R.drawable.ic_dialog_alert);
                ventana.show();
            }
        });

        botonVentana2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(MainActivity.this);
                ventana.setTitle("Atención");
                ventana.setMessage("Esto es un mensaje. Pulsa el botón OK para volver a la pantalla principal");
                ventana.setIcon(android.R.drawable.ic_dialog_alert);
                ventana.setCancelable(false);
                ventana.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                ventana.show();
            }
        });

        botonVentana3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(MainActivity.this);
                ventana.setTitle("Importante");
                ventana.setMessage("¿Acepta la ejecución de esta aplicación en modo de prueba?");
                ventana.setIcon(android.R.drawable.ic_dialog_alert);
                ventana.setCancelable(false);
                ventana.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "Bienvenid@", Toast.LENGTH_SHORT).show();
                    }
                });
                ventana.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                ventana.show();
            }
        });

        botonVentana4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(MainActivity.this);
                ventana.setTitle("Importante");
                ventana.setMessage("¿Acepta la ejecución de esta aplicación en modo de prueba?");
                ventana.setIcon(android.R.drawable.ic_dialog_alert);
                ventana.setCancelable(false);
                ventana.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "Bienvenid@", Toast.LENGTH_SHORT).show();
                    }
                });
                ventana.setNeutralButton("Más tarde", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "Más tarde entonces", Toast.LENGTH_SHORT).show();
                    }
                });
                ventana.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                ventana.show();
            }
        });
    }
}
