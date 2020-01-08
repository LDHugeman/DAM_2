package com.example.cuenta_clicks_v1;

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

    public void onClickBotonPulsa(View view){
        contador++;
        textViewClicks.setText("Has pulsado "+contador+ " veces");
    }
}