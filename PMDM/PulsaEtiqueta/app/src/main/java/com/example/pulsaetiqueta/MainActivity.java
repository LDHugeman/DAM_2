package com.example.pulsaetiqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewSaludo;
    private ImageView imageViewEmoji;
    private int contadorTextView = 0;
    private int contadorImage = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewSaludo = findViewById(R.id.textView_Saludo);
        imageViewEmoji = findViewById(R.id.imageView_Emoji);
    }

    public void onClickTextViewSaludo(View view) {
        contadorTextView++;

        textViewSaludo.setTextSize(40);
        textViewSaludo.setBackgroundColor(getResources().getColor(R.color.colorFondoLetra));
        textViewSaludo.setTextColor(getResources().getColor(R.color.colorLetra));
        textViewSaludo.setAllCaps(true);
        textViewSaludo.setText(getResources().getString(R.string.hola));
        if(contadorTextView == 2){
            textViewSaludo.setRotation(45);
        }else if(contadorTextView==3){
            textViewSaludo.setRotation(90);
        } else if(contadorTextView == 4){
            textViewSaludo.setRotation(135);
        } else if(contadorTextView == 5){
            textViewSaludo.setRotation(180);
        } else if(contadorTextView == 6){
            textViewSaludo.setRotation(225);
        } else if(contadorTextView == 7){
            textViewSaludo.setRotation(270);
        } else if(contadorTextView == 8){
            textViewSaludo.setRotation(315);
        } else if(contadorTextView ==9){
            textViewSaludo.setRotation(360);
            contadorTextView = 0;
        }
    }

    public void onClickImageEmoji(View view) {
        contadorImage++;
        if(contadorImage == 1){
            imageViewEmoji.setRotation(45);
        }else if(contadorImage==2){
            imageViewEmoji.setRotation(90);
        } else if(contadorImage == 3){
            imageViewEmoji.setRotation(135);
        } else if(contadorImage == 4){
            imageViewEmoji.setRotation(180);
        } else if(contadorImage == 5){
            imageViewEmoji.setRotation(225);
        } else if(contadorImage == 6){
            imageViewEmoji.setRotation(270);
        } else if(contadorImage == 7){
            imageViewEmoji.setRotation(315);
        } else if(contadorImage ==8){
            imageViewEmoji.setRotation(360);
            contadorImage = 0;
        }
    }
}
