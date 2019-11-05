package com.example.cambiodemoneda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioButtonPtsEuros;
    private Button buttonCambiar;
    private EditText editTextValor;
    private TextView textViewResultado;
    private DecimalFormat formato = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioButtonPtsEuros = findViewById(R.id.radioButton_PtsEuros);
        buttonCambiar = findViewById(R.id.button_Cambiar);
        editTextValor = findViewById(R.id.editText_Valor);
        textViewResultado = findViewById(R.id.textView_Resultado);
        buttonCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = editTextValor.getText().toString();
                if (numero.isEmpty()) {
                    textViewResultado.setText("Debes teclear un valor numérico");
                } else {
                    mostrarCambioDeMoneda(numero);
                }
            }
        });
    }

    void mostrarCambioDeMoneda(String numero) {
        Double PESETAS = 166.386;
        double valor = Double.parseDouble(numero);
        if (radioButtonPtsEuros.isChecked()) {
            valor = valor / PESETAS;
            textViewResultado.setText(numero+" " +getResources().getString(R.string.ptsAEuros) + " "+formato.format(valor)+ " "+getResources().getString(R.string.euros));
        } else {
            valor = valor * PESETAS;
            textViewResultado.setText(numero+" " + getResources().getString(R.string.eurosAPts) + " "+formato.format(valor) + " " + getResources().getString(R.string.pesetas));
        }
    }
}
