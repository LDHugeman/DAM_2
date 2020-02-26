package com.example.toastpersonalizadas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botonDefectiva;
    Button botonOtraPosicion;
    Button botonPersonalizada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonDefectiva = findViewById(R.id.boton_defectiva);
        botonOtraPosicion = findViewById(R.id.boton_en_otra_posicion);
        botonPersonalizada = findViewById(R.id.boton_personalizada);

        botonDefectiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Toast Defectiva", Toast.LENGTH_SHORT).show();
            }
        });

        botonOtraPosicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast2 = Toast.makeText(MainActivity.this,"Toast en otra posicion", Toast.LENGTH_LONG);
                toast2.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT,30,0);
                //toast2.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT,0,0);
                toast2.show();
            }
        });
        botonPersonalizada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layoutToast = findViewById(R.id.linearLayout_toast);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.mitoast, layoutToast);
                TextView textViewToast = view.findViewById(R.id.textView_mensaje);
                textViewToast.setText("Toast personalizada");
                Toast toast3 = new Toast(MainActivity.this);
                toast3.setDuration(Toast.LENGTH_LONG);
                toast3.setView(view);
                toast3.show();
            }
        });
    }
}
