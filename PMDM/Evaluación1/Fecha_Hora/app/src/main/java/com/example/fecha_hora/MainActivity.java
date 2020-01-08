package com.example.fecha_hora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button botonVerDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        botonVerDatos = findViewById(R.id.boton_verDatos);
        botonVerDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dia = datePicker.getDayOfMonth();
                int mes = datePicker.getMonth()+1;
                int ano = datePicker.getYear();
                Toast.makeText(MainActivity.this,
                        "Fecha: "+dia+"/"+mes+"/"+ano+"\n"+"Hora: "+ timePicker.getCurrentHour()+":"+timePicker.getCurrentMinute(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
