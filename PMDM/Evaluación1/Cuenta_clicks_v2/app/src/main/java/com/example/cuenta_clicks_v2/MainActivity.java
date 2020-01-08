package com.example.cuenta_clicks_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewClicks;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewClicks = findViewById(R.id.textView_clicks);
    }

    public void onClickBotonPulsa(View view) {
        contador++;
    }

    public void onClickBotonFinalizar(View view) {
        textViewClicks.setText("Número de clicks: " + contador);
        contador = 0;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle guardarEstado) {
        super.onSaveInstanceState(guardarEstado);
        guardarEstado.putInt("contador", contador);
        guardarEstado.putString("variable", textViewClicks.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle recEstado) {
        super.onRestoreInstanceState(recEstado);
        contador = recEstado.getInt("contador");
        textViewClicks.setText(recEstado.getString("variable"));
    }
}
