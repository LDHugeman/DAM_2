package com.example.tresactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SegundaActivity extends AppCompatActivity {

    private Button botonIrActivity1;
    private TextView textViewMensajeA1enA2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        botonIrActivity1 = findViewById(R.id.boton_IrActivity1Desde2);
        textViewMensajeA1enA2 = findViewById(R.id.textView_MensajeA1enA2);
        botonIrActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numeroEntero = 55;
                Intent intent = new Intent();
                intent.putExtra("numeroEntero_de_a2", numeroEntero);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        Intent intent = getIntent();
        String mensajeRecuperado = intent.getStringExtra("mensajeA2");
        textViewMensajeA1enA2.setText(mensajeRecuperado);
    }
}
