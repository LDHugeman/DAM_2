package com.example.luisdavidvarelaperez;

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
import android.widget.TextView;
import android.widget.Toast;

public class masInformacion extends AppCompatActivity {

    private TextView textViewUrl;
    private static final int LLAMADA_TELEFONO = 0;
    private Button botonAtras;
    private Button botonFinApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_informacion);
        textViewUrl = findViewById(R.id.textView_url);
        botonAtras = findViewById(R.id.boton_atras);
        botonFinApp = findViewById(R.id.boton_fin);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(masInformacion.this,MainActivity.class);
                startActivity(intent);
            }
        });

        botonFinApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }


    public void onClickUrl(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ingemecanica.com/tutoriales/areas.html"));
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        } else {
            Toast.makeText(this, "No se puede acceder al navegador", Toast.LENGTH_SHORT).show();
        }
    }


    public void onClickNumero(View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34) 986110011"));
                startActivity(intent);
            } else {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, LLAMADA_TELEFONO);
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34) 986110011"));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LLAMADA_TELEFONO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+34) 986110011"));
                startActivity(intent);
            } else {
                Toast.makeText(this, "No se puede llamar sin el permiso", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
