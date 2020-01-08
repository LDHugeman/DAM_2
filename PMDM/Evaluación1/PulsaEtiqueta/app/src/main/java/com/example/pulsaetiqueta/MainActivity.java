package com.example.pulsaetiqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewSaludo;
    private ImageView imageViewEmoji;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewSaludo = findViewById(R.id.textView_Saludo);
        imageViewEmoji = findViewById(R.id.imageView_Emoji);
    }

    public void onClickTextViewSaludo(View view) {
        textViewSaludo.setTextSize(40);
        textViewSaludo.setBackgroundColor(getResources().getColor(R.color.colorFondoLetra));
        textViewSaludo.setTextColor(getResources().getColor(R.color.colorLetra));
        textViewSaludo.setAllCaps(true);
        textViewSaludo.setText(getResources().getString(R.string.hola));
        textViewSaludo.setRotation(textViewSaludo.getRotation()+45);

    }

    public void onClickImageEmoji(View view) {
        imageViewEmoji.setRotation(imageViewEmoji.getRotation()+45);
    }
}
