package com.example.ciclodevida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TerceraActivity extends AppCompatActivity {

    Button botonRetorno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera);

        botonRetorno = findViewById(R.id.boton_Retorno);
        botonRetorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "ESTO ES UN MENSAJE DE VUELTA A ACTIVITY 1";
                Intent intent = new Intent();
                intent.putExtra("mensaje_de_a3",s);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
