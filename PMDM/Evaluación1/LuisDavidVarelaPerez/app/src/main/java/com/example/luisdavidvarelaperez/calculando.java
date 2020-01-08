package com.example.luisdavidvarelaperez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class calculando extends AppCompatActivity {

    private Button botonContinuar;
    private Button botonMostrarResultado;
    private EditText editTextValor;
    private LinearLayout layoutOculto;
    private RadioButton radioButtonArea;
    private RadioButton radioButtonPerimetro;
    private RadioButton radioButtonCuadrado;
    private RadioButton radioButtonCirculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculando);
        botonContinuar = findViewById(R.id.boton_continuar);
        botonMostrarResultado = findViewById(R.id.boton_mostrarResultado);
        editTextValor = findViewById(R.id.editText_valor);
        layoutOculto = findViewById(R.id.layout);
        radioButtonCuadrado = findViewById(R.id.radiobutton_cuadrado);
        radioButtonCirculo = findViewById(R.id.radiobutton_circulo);
        radioButtonArea = findViewById(R.id.radiobutton_area);
        radioButtonPerimetro = findViewById(R.id.radiobutton_perimetro);
        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButtonCuadrado.isChecked()){
                    editTextValor.setHint("Lado");
                } else if(radioButtonCirculo.isChecked()){
                    editTextValor.setHint("Radio");
                }
                botonContinuar.setVisibility(View.GONE);
                layoutOculto.setVisibility(View.VISIBLE);
            }
        });
        botonMostrarResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valor1 = editTextValor.getText().toString();
                if(!valor1.isEmpty()){
                    int valor = Integer.parseInt(valor1);
                    if(radioButtonArea.isChecked() && radioButtonCuadrado.isChecked()){
                        Intent intent = new Intent();
                        intent.putExtra("valorAreaCuadrado", valor);
                        setResult(1, intent);
                    } else if(radioButtonPerimetro.isChecked() && radioButtonCuadrado.isChecked()){
                        Intent intent = new Intent();
                        intent.putExtra("valorPerimetroCuadrado", valor);
                        setResult(2, intent);
                    } else if(radioButtonArea.isChecked() && radioButtonCirculo.isChecked()){
                        Intent intent = new Intent();
                        intent.putExtra("valorAreaCirculo", valor);
                        setResult(3, intent);
                    }else if(radioButtonPerimetro.isChecked() && radioButtonCirculo.isChecked()){
                        Intent intent = new Intent();
                        intent.putExtra("valorPerimetroCirculo", valor);
                        setResult(4, intent);
                    }
                    finish();
                }else{
                    Toast.makeText(calculando.this, getResources().getString(R.string.sinValor), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
