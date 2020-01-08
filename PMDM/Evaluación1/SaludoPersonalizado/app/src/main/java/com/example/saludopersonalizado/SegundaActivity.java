package com.example.saludopersonalizado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {


    private TextView textViewSaludo;
    private TextView textViewEdad;
    private Button botonVolver;
    private CheckBox checkBoxDespedida;
    private RadioButton radioButtonAdios;
    private LinearLayout linearLayoutVista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textViewSaludo = findViewById(R.id.textView_saludo);
        textViewEdad = findViewById(R.id.textView_edad);
        linearLayoutVista = findViewById(R.id.vista);
        botonVolver = findViewById(R.id.boton_volver);
        checkBoxDespedida = findViewById(R.id.checkbox_Despedida);
        radioButtonAdios = findViewById(R.id.radioButton_Adios);
        checkBoxDespedida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxDespedida.isChecked()) {
                    linearLayoutVista.setVisibility(View.VISIBLE);
                } else {
                    linearLayoutVista.setVisibility(View.GONE);
                }
            }
        });
        Intent intent = getIntent();
        if (intent.hasExtra("saludo")) {
            textViewSaludo.setText(intent.getExtras().getString("saludo"));
        }
        if (intent.hasExtra("edad")) {
            byte edad = intent.getExtras().getByte("edad");
            if (edad >= 18) {
                textViewEdad.setText("Eres mayor de edad: " + edad);
            } else {
                textViewEdad.setText("Eres menor de edad: " + edad);
            }
        } else {
            textViewEdad.setText(intent.getExtras().getString("sinEdad"));
        }
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (checkBoxDespedida.isChecked()) {
                    if (radioButtonAdios.isChecked()) {
                        String adios = getResources().getString(R.string.adios);
                        intent.putExtra("despedida", adios);
                        setResult(RESULT_OK, intent);
                    } else {
                        String hastaluego = getResources().getString(R.string.hastaluego);
                        intent.putExtra("despedida", hastaluego);
                        setResult(RESULT_OK, intent);
                    }
                } else {
                    intent.putExtra("sinDespedida", "No has seleccionado una despedida");
                    setResult(RESULT_CANCELED, intent);
                }
                finish();
            }
        });

    }
}
