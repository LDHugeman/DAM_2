package com.example.saludopersonalizado;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CODIGO_LLAMADA = 1;
    private Button botonHola;
    private EditText editTextNombre;
    private EditText editTextEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNombre = findViewById(R.id.editText_nombre);
        editTextEdad = findViewById(R.id.editText_edad);
        botonHola = findViewById(R.id.boton_hola);
        botonHola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
                Bundle bundle = new Bundle();
                String saludo1 = editTextNombre.getText().toString();
                if(!saludo1.isEmpty()){
                    String saludo = "Hola, "+ saludo1;
                    bundle.putString("saludo",saludo);
                } else {
                    bundle.putString("saludo", "Escriba su nombre");
                }
                String edad1 = editTextEdad.getText().toString();
                if(!edad1.isEmpty()){
                    byte edad = Byte.parseByte(edad1);
                    bundle.putByte("edad",edad);
                } else {
                    bundle.putString("sinEdad", "Escriba su edad");
                }
                intent.putExtras(bundle);
                startActivityForResult(intent, CODIGO_LLAMADA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODIGO_LLAMADA){
            if(resultCode == RESULT_OK){
                String despedida = data.getStringExtra("despedida");
                Toast.makeText(this, despedida, Toast.LENGTH_SHORT).show();
            }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, data.getStringExtra("sinDespedida"), Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*String saludo;
    byte edad;
    String sinEdad;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle guardarEstado) {
        super.onSaveInstanceState(guardarEstado);
        guardarEstado.putString("saludo", saludo);
        guardarEstado.putByte("edad", edad);
        guardarEstado.putString("sinEdad",sinEdad);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle recuperarEstado) {
        super.onRestoreInstanceState(recuperarEstado);
        saludo = recuperarEstado.getString("saludo");
        edad = recuperarEstado.getByte("edad");
        sinEdad = recuperarEstado.getString("sinEdad");
    }*/
}
