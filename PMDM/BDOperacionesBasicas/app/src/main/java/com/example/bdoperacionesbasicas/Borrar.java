package com.example.bdoperacionesbasicas;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Borrar extends AppCompatActivity {

    private EditText editTextCodigoEliminar;
    private Button botonOkBorrar;
    private Button botonFinEliminar;
    private Helper helper = new Helper(Borrar.this,"BDUsuarios",null,1);
    private SQLiteDatabase baseDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);
        editTextCodigoEliminar = findViewById(R.id.editText_codigoEliminar);
        botonOkBorrar = findViewById(R.id.boton_okEliminar);
        botonFinEliminar = findViewById(R.id.boton_FinEliminar);

        baseDatos = helper.getWritableDatabase();
        botonOkBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = editTextCodigoEliminar.getText().toString();
                if(!codigo.isEmpty()){
                    int filasEliminadas = baseDatos.delete("usuarios","codigo="+codigo,null);
                    if(filasEliminadas>=1){
                        editTextCodigoEliminar.setText("");
                        Toast.makeText(Borrar.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Borrar.this, "No existe ningún usuario con ese código", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Borrar.this, "Debe escribir el código del usuario que quiere eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonFinEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                baseDatos.close();
            }
        });
    }
}
