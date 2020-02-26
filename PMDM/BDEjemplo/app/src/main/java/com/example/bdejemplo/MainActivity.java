package com.example.bdejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button botonCrearBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonCrearBD = findViewById(R.id.boton_crearBD);
        botonCrearBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crear();
            }
        });

    }

    private void crear() {
        Helper helper = new Helper(this, "BDUsuarios", null, 1);
        SQLiteDatabase baseDatos = helper.getWritableDatabase();
        //baseDatos.execSQL("INSERT INTO usuarios (codigo, nombre) values (1, 'Usuario 1')");
        //baseDatos.execSQL("INSERT INTO usuarios (codigo, nombre) values (2, 'Usuario 2')");
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("nombre", "Usuario 5");
        nuevoRegistro.put("codigo", "5");
        baseDatos.insert("usuarios", null, nuevoRegistro);
        nuevoRegistro.put("nombre", "Usuario 6");
        nuevoRegistro.put("codigo", "6");

        long cuantos = baseDatos.insert("usuarios", null, nuevoRegistro);
        if (cuantos > 0) {
            Toast.makeText(this, "INSERCIÓN CORRECTA", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "ERROR DE INSERCIÓN", Toast.LENGTH_SHORT).show();
        }
        String[] datosARecuperar = {"codigo", "nombre"};
        Cursor cursor = baseDatos.query("usuarios", datosARecuperar, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int codigo = cursor.getInt(0);
                String nombre = cursor.getString(1);
                Toast.makeText(this, "Código: " + codigo + " Nombre: " + nombre, Toast.LENGTH_LONG).show();
            } while (cursor.moveToNext());
        } else Toast.makeText(this, "Usuario inexistente", Toast.LENGTH_LONG).show();
        cursor.close();
        baseDatos.close();
    }
}
