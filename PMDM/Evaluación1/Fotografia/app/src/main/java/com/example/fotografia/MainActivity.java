package com.example.fotografia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button botonCamara;
    private ImageView imageView;
    private static final int CODIGO_CAPTURA_IMAGEN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imagen_camara);
        botonCamara = findViewById(R.id.boton_camara);
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            botonCamara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(intent, CODIGO_CAPTURA_IMAGEN);
                }
            });
        } else {
            Toast.makeText(this, "Cámara no disponible", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODIGO_CAPTURA_IMAGEN){
            if(resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                Bitmap imagenBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imagenBitmap);
            }
        }
    }
}
