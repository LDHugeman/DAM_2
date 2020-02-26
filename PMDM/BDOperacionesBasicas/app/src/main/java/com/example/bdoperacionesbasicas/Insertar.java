package com.example.bdoperacionesbasicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insertar extends AppCompatActivity {

    private EditText editTextCodigoInsertar;
    private EditText editTextNombre;
    private Button botonOkInsertar;
    private Button botonFinInsertar;
    private Helper helper = new Helper(Insertar.this,"BDUsuarios",null,1);
    private SQLiteDatabase baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        editTextCodigoInsertar = findViewById(R.id.editText_codigoInsertar);
        editTextNombre = findViewById(R.id.editText_nombre);
        botonOkInsertar = findViewById(R.id.boton_okInsertar);
        botonFinInsertar = findViewById(R.id.boton_FinInsertar);
        baseDatos = helper.getWritableDatabase();
        botonOkInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = editTextCodigoInsertar.getText().toString();
                if(!codigo.isEmpty()){
                    String nombre = editTextNombre.getText().toString();
                    if(!nombre.isEmpty()){
                        ContentValues nuevoRegistro = new ContentValues();
                        nuevoRegistro.put("codigo",codigo);
                        nuevoRegistro.put("nombre",nombre);
                        editTextNombre.setText("");
                        editTextCodigoInsertar.setText("");
                        long registros = baseDatos.insert("usuarios", null, nuevoRegistro);
                        if(registros>0){
                            Toast.makeText(Insertar.this, "INSERCIÓN CORRECTA", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Insertar.this, "ERROR DE INSERCIÓN", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Insertar.this, "Falta el nombre", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Insertar.this, "Falta el código", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonFinInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                baseDatos.close();
            }
        });
    }
}
