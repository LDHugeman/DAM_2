package com.example.bdoperacionesbasicas;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Consultar extends AppCompatActivity {

    private EditText editTextCodigoConsultar;
    private Button botonConsultar;
    private Button botonFinConsultar;
    private Helper helper = new Helper(Consultar.this,"BDUsuarios",null,1);
    private SQLiteDatabase baseDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        editTextCodigoConsultar = findViewById(R.id.editText_codigoConsultar);
        botonConsultar = findViewById(R.id.boton_consultar);
        botonFinConsultar = findViewById(R.id.boton_FinConsultar);

        baseDatos = helper.getWritableDatabase();
        botonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = editTextCodigoConsultar.getText().toString();
                if(!codigo.isEmpty()){
                    String [] datosARecuperar= {"codigo","nombre"};

                    Cursor cursor = baseDatos.query("usuarios",datosARecuperar,"codigo="+codigo,null,null,null,null,null);
                    if(cursor.moveToFirst()){
                        do{
                            int codigoConsulta = cursor.getInt(0);
                            String nombre = cursor.getString(1);
                            Toast.makeText(Consultar.this, "Código: "+codigoConsulta+" Nombre: "+nombre, Toast.LENGTH_SHORT).show();
                        }while (cursor.moveToNext());
                    } else {
                        Toast.makeText(Consultar.this, "Usuario inexistente", Toast.LENGTH_SHORT).show();
                    }
                    editTextCodigoConsultar.setText("");
                    cursor.close();
                } else {
                    Toast.makeText(Consultar.this, "Falta el código", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonFinConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                baseDatos.close();
            }
        });
    }
}
