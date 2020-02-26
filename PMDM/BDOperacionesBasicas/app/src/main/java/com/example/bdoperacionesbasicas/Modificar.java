package com.example.bdoperacionesbasicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modificar extends AppCompatActivity {

    private EditText editTextCodigoModificar;
    private EditText editTextNombreModificar;
    private Button botonModificarUsuario;
    private Button botonFinModificar;
    private Helper helper = new Helper(Modificar.this,"BDUsuarios",null,1);
    private SQLiteDatabase baseDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        editTextCodigoModificar = findViewById(R.id.editText_codigoModificar);
        editTextNombreModificar = findViewById(R.id.editText_nombreModificar);
        botonModificarUsuario = findViewById(R.id.boton_modificarUsuario);
        botonFinModificar = findViewById(R.id.boton_FinModificar);

        baseDatos = helper.getWritableDatabase();
        botonModificarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = editTextCodigoModificar.getText().toString();
                if(!codigo.isEmpty()){
                    String nombre = editTextNombreModificar.getText().toString();
                    if(!nombre.isEmpty()){

                        ContentValues modificarRegistro = new ContentValues();
                        modificarRegistro.put("nombre",nombre);
                        baseDatos.update("usuarios",modificarRegistro,"codigo="+codigo,null);
                        editTextCodigoModificar.setText("");
                        editTextNombreModificar.setText("");
                        Toast.makeText(Modificar.this, "Usuario modificado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Modificar.this, "Falta el nombre", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Modificar.this, "Falta el código", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonFinModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                baseDatos.close();
            }
        });
    }
}
