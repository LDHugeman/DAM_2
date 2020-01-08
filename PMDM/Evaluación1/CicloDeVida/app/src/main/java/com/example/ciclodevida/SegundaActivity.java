package com.example.ciclodevida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity {

    private TextView textViewMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        Toast.makeText(this, "ACTIVITY 2: Se ejecuta el método onCreate()", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "ACTIVITY 2: Se ejecuta el método onCreate()");
        textViewMensaje = findViewById(R.id.textView_Mensaje);

        //recuperar dato enviado
        Intent intent = getIntent(); // recupero el intent de la invocación de esta activity
        String mensajeRecuperado = intent.getStringExtra("mensaje");
        //recuperar desde el bundle
        String mensajeRecuperado2 = intent.getExtras().getString("mensaje_2");
        textViewMensaje.setText(mensajeRecuperado + "\n" + mensajeRecuperado2);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "ACTIVITY 2: Se ejecuta el método onStart()", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "ACTIVITY 2: Se ejecuta el método onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "ACTIVITY 2: Se ejecuta el método onResume()", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "ACTIVITY 2: Se ejecuta el método onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "ACTIVITY 2: Se ejecuta el método onPause()", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "ACTIVITY 2: Se ejecuta el método onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "ACTIVITY 2: Se ejecuta el método onStop()", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "ACTIVITY 2: Se ejecuta el método onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "ACTIVITY 2: Se ejecuta el método onDestroy()", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "ACTIVITY 2: Se ejecuta el método onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "ACTIVITY 2: Se ejecuta el método onRestart()", Toast.LENGTH_SHORT).show();
        Log.i("ciclo", "ACTIVITY 2: Se ejecuta el método onRestart()");
    }
}
