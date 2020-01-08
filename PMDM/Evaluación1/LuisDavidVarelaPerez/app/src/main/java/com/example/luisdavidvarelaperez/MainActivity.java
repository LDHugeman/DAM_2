package com.example.luisdavidvarelaperez;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button botonEmpezar;
    private TextView textViewBienvenido;
    private Button botonTerminar;
    private static final int CODIGO_LLAMADA_CALCULANDO = 1;
    private final static double NUMERO_PI = 3.14;
    private DecimalFormat formato = new DecimalFormat("0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonEmpezar = findViewById(R.id.boton_empezar);
        textViewBienvenido = findViewById(R.id.textView_bienvenido);
        textViewBienvenido.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MainActivity.this, masInformacion.class);
                startActivity(intent);
                return false;
            }
        });
        botonEmpezar.setOnClickListener(escuchador);
        botonTerminar = findViewById(R.id.boton_terminar);
    }
    private View.OnClickListener escuchador = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, calculando.class);
            startActivityForResult(intent, CODIGO_LLAMADA_CALCULANDO);
        }
    };

    public void onClickTerminar(View view) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODIGO_LLAMADA_CALCULANDO){
            if(resultCode == 1){
                int valorAreaCuadrado = data.getIntExtra("valorAreaCuadrado",0);
                valorAreaCuadrado = valorAreaCuadrado * valorAreaCuadrado;
                textViewBienvenido.setText("Cuadrado - Area"+"\n"+valorAreaCuadrado);
            }else if(resultCode ==2){
                int valorPerimetroCuadrado = data.getIntExtra("valorPerimetroCuadrado",0);
                valorPerimetroCuadrado = 4* valorPerimetroCuadrado;
                textViewBienvenido.setText("Cadrado - Perimetro"+"\n"+valorPerimetroCuadrado);
            }else if(resultCode == 3){
                int valorAreaCirculo = data.getIntExtra("valorAreaCirculo",0);
                double valorACirculo = NUMERO_PI * valorAreaCirculo * valorAreaCirculo;
                textViewBienvenido.setText("Circulo - Área"+"\n"+formato.format(valorACirculo));
            } else if(resultCode == 4){
                int valorPerimetroCirculo = data.getIntExtra("valorPerimetroCirculo",0);
                double valorPCirculo = 2 * NUMERO_PI * valorPerimetroCirculo;
                textViewBienvenido.setText("Circulo - Perímetro"+"\n"+formato.format(valorPCirculo));
            }
        }
    }
}
