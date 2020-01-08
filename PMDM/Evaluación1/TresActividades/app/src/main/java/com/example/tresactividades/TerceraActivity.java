package com.example.tresactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TerceraActivity extends AppCompatActivity {

    private Button botonIrActivity1DesdeA3;
    private TextView textViewMensajeA1enA3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera);
        botonIrActivity1DesdeA3 = findViewById(R.id.boton_IrActivity1DesdeA3);
        botonIrActivity1DesdeA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double numeroDecimal = 10.15;
                Intent intent = new Intent();
                intent.putExtra("numeroDecimal_de_a3",numeroDecimal);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        textViewMensajeA1enA3 = findViewById(R.id.textView_MensajeA1enA3);
        Intent intent = getIntent();
        String mensajeRecuperado = intent.getStringExtra("mensajeA3");
        textViewMensajeA1enA3.setText(mensajeRecuperado);
    }
}
