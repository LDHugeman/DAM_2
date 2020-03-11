package com.example.bdoperacionesbasicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button botonInsertar;
    private Button botonBorrar;
    private Button botonModificar;
    private Button botonMuestraUno;
    private Button botonMuestraVarios;
    private ListView listaUsuarios;
    private ArrayList<Usuario> usuarios;
    private AdaptadorPersonalizado adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonInsertar = findViewById(R.id.boton_insertar);
        botonBorrar = findViewById(R.id.boton_borrar);
        botonModificar = findViewById(R.id.boton_modificar);
        botonMuestraUno = findViewById(R.id.boton_muestraUno);
        botonMuestraVarios = findViewById(R.id.boton_muestraVarios);
        listaUsuarios = findViewById(R.id.lista_usuarios);
        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Insertar.class);
                startActivity(intent);
            }
        });

        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Borrar.class);
                startActivity(intent);
            }
        });

        botonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Modificar.class);
                startActivity(intent);
            }
        });

        botonMuestraUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Consultar.class);
                startActivity(intent);
            }
        });

        botonMuestraVarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    usuarios = new ArrayList<>();
                    String [] datosARecuperar= {"codigo","nombre"};
                    Helper helper = new Helper(MainActivity.this,"BDUsuarios",null,1);
                    SQLiteDatabase baseDatos = helper.getWritableDatabase();
                    Cursor cursor = baseDatos.query("usuarios",datosARecuperar,null,null,null,null,null,null);
                    if(cursor.moveToFirst()){
                        do{
                            int codigoConsulta = cursor.getInt(0);
                            String nombre = cursor.getString(1);
                            Usuario usuario = new Usuario(codigoConsulta,nombre);
                            usuarios.add(usuario);
                            Toast.makeText(MainActivity.this, "Código: "+codigoConsulta+" Nombre: "+nombre, Toast.LENGTH_SHORT).show();
                        }while (cursor.moveToNext());
                    } else {
                        Toast.makeText(MainActivity.this, "Usuario inexistente", Toast.LENGTH_SHORT).show();
                    }
                    adaptador = new AdaptadorPersonalizado(MainActivity.this, usuarios);
                    listaUsuarios.setAdapter(adaptador);
                    cursor.close();
                    baseDatos.close();
            }
        });
    }
}
