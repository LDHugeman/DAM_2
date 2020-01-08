package com.example.ciclodevida;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button botonFinalizar;
    private Button botonIrActivity2;
    private Button botonIrActivity3;
    private Button botonIrActivityExterna;
    private static final int CODIGO_LLAMADA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Se ejecuta el método onCreate()", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Se ejecuta el método onCreate()");
        botonFinalizar = findViewById(R.id.boton_Finalizar);
        botonIrActivity2 = findViewById(R.id.boton_IrActivity2);
        botonIrActivity3 = findViewById(R.id.boton_IrActivity3);
        botonIrActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TerceraActivity.class);
                //llamar con respuesta
                startActivityForResult(intent, CODIGO_LLAMADA);
            }
        });
        botonIrActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SegundaActivity.class);
                //adjunto datos de envío
                String mensaje = "MENSAJE PARA LA ACTIVIDAD 2";
                intent.putExtra("mensaje", mensaje);
                // más datos mediante un objeto bundle
                Bundle bundle = new Bundle();
                bundle.putString("mensaje_2", ".....ESTO ES OTRO MENSAJE....");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        botonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Se ejecuta el método onStart()", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Se ejecuta el método onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Se ejecuta el método onResume()", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Se ejecuta el método onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Se ejecuta el método onPause()", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Se ejecuta el método onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Se ejecuta el método onStop()", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Se ejecuta el método onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Se ejecuta el método onDestroy()", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Se ejecuta el método onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Se ejecuta el método onRestart()", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "Se ejecuta el método onRestart()");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODIGO_LLAMADA){
            if(resultCode==RESULT_OK){
                String strRecibido = data.getStringExtra("mensaje_de_a3");
                Toast.makeText(this, "Se ha recibido el mensaje: "+strRecibido, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
