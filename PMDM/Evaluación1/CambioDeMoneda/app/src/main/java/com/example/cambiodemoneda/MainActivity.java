package com.example.cambiodemoneda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioButtonPtsEuros;
    private Button botonCambiar;
    private EditText editTextValor;
    private DecimalFormat formato = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioButtonPtsEuros = findViewById(R.id.radioButton_PtsEuros);
        botonCambiar = findViewById(R.id.button_Cambiar);
        editTextValor = findViewById(R.id.editText_Valor);
        botonCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SegundaActivity.class);
                String numero = editTextValor.getText().toString();
                if (!numero.isEmpty()) {
                    double PESETAS = 166.386;
                    double valor = Double.parseDouble(numero);
                    if (radioButtonPtsEuros.isChecked()) {
                        valor = valor / PESETAS;
                        String valorEuros = numero + " " + getResources().getString(R.string.ptsAEuros) + " " + formato.format(valor) + " " + getResources().getString(R.string.euros);
                        intent.putExtra("cambio",valorEuros);
                    } else {
                        valor = valor * PESETAS;
                        String valorPesetas = numero + " " + getResources().getString(R.string.eurosAPts) + " " + formato.format(valor) + " " + getResources().getString(R.string.pesetas);
                        intent.putExtra("cambio", valorPesetas);
                    }
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Debes teclear un valor", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
