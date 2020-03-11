package com.example.bdexterna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNombre;
    private Button botonConsultar;
    private TextView textViewEmail;
    private EditText editTextId;
    private EditText editTextNombreInsertar;
    private EditText editTextEmail;
    private Button botonInsertar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNombre = findViewById(R.id.editText_nombre);
        textViewEmail = findViewById(R.id.textView_email);
        botonConsultar = findViewById(R.id.boton_consultar);
        editTextId = findViewById(R.id.editText_id);
        editTextNombreInsertar = findViewById(R.id.editText_nombreInsertar);
        editTextEmail = findViewById(R.id.editText_email);
        botonInsertar = findViewById(R.id.boton_insertar);
        botonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiAssetHelper miAssetHelper = new MiAssetHelper(MainActivity.this,"ejemplo.db",null, 1);
                SQLiteDatabase baseDatos = miAssetHelper.getWritableDatabase();
                String nombre = editTextNombre.getText().toString();
                if(!nombre.isEmpty()){
                    String [] datosARecuperar = {"email"};
                    Cursor cursor = baseDatos.query("tabla_emails",datosARecuperar,"nombre='"+nombre+"'",null,null,null,null,null);
                    if(cursor.moveToFirst()){
                        do{
                            String email = cursor.getString(0);
                            textViewEmail.setText(email);
                        }while (cursor.moveToNext());
                    } else {
                        Toast.makeText(MainActivity.this, "Usuario inexistente", Toast.LENGTH_SHORT).show();
                    }
                    editTextNombre.setText("");
                    cursor.close();
                    baseDatos.close();
                }
            }
        });

        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiAssetHelper miAssetHelper = new MiAssetHelper(MainActivity.this,"ejemplo.db",null, 1);
                SQLiteDatabase baseDatos = miAssetHelper.getWritableDatabase();
                String id = editTextId.getText().toString();
                if(!id.isEmpty()){
                    String nombre = editTextNombreInsertar.getText().toString();
                    if(!nombre.isEmpty()){
                        String email = editTextEmail.getText().toString();
                        if(!email.isEmpty()){
                            ContentValues nuevoRegistro = new ContentValues();
                            int idUsuario = Integer.parseInt(id);
                            nuevoRegistro.put("id",idUsuario);
                            nuevoRegistro.put("nombre", nombre);
                            nuevoRegistro.put("email",email);
                            editTextId.setText("");
                            editTextNombreInsertar.setText("");
                            editTextEmail.setText("");
                            long registros = baseDatos.insert("tabla_emails",null, nuevoRegistro);
                            if(registros>0){
                                Toast.makeText(MainActivity.this, "INSERCIÓN CORRECTA", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "ERROR DE INSERCIÓN", Toast.LENGTH_SHORT).show();
                            }
                            baseDatos.close();
                        }else {
                            Toast.makeText(MainActivity.this, "Falta el email", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Falta el nombre", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Falta el id", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
