package com.example.menucontextualvarios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textViewEtiqueta;
    ImageView imageViewImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewEtiqueta = findViewById(R.id.textView_etiqueta);
        imageViewImagen = findViewById(R.id.imageView_imagen);
        registerForContextMenu(textViewEtiqueta);
        registerForContextMenu(imageViewImagen);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        if (view.getId() == textViewEtiqueta.getId()){
            inflater.inflate(R.menu.menu_contextual_etiqueta, menu);
        } else if(view.getId() == imageViewImagen.getId()){
            inflater.inflate(R.menu.menu_contextual_imagen, menu);
        }

        super.onCreateContextMenu(menu, view, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.opcion1:
                Toast.makeText(this, "Etiqueta: "+ item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opcion2:
                Toast.makeText(this, "Etiqueta: "+ item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opcion3:
                Toast.makeText(this, "Etiqueta: "+ item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opcionA:
                Toast.makeText(this, "Imagen: "+ item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opcionB:
                Toast.makeText(this, "Imagen: "+item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
