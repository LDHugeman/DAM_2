package com.example.preparacionexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button botonLista;
    private TextView textoLista;
    private Intent irALista;
    private ArrayList <Animal> arrayAnimales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonLista = findViewById(R.id.boton_lista);
        textoLista = findViewById(R.id.texto_lista);
        arrayAnimales = crearListaAnimales();

        botonLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irALista();
            }
        });
    }

    void irALista(){
        irALista = new Intent(MainActivity.this, Lista.class);
        irALista.putExtra("animales", arrayAnimales);
        startActivity(irALista);
    }

    ArrayList<Animal> crearListaAnimales(){
        ArrayList<Animal> animales = new ArrayList<>();
        Animal animal1 = new Animal(
                "Águila",
                "Un pájaro volador",
                R.drawable.aguila,
                R.drawable.color_azul);
        Animal animal2 = new Animal(
                "Ballena",
                "Un animal grande",
                R.drawable.ballena,
                R.drawable.color_azul);
        Animal animal3 = new Animal(
                "Caballo",
                "Una montura",
                R.drawable.caballo,
                R.drawable.color_rosa);
        Animal animal4 = new Animal(
                "Canario",
                "Un pájaro doméstico",
                R.drawable.canario,
                R.drawable.color_verde);
        Animal animal5 = new Animal(
                "Delfín",
                "Un mamífero marino",
                R.drawable.delfin,
                R.drawable.color_negro);
        Animal animal6 = new Animal(
                "Gato",
                "Un animal doméstico",
                R.drawable.gato,
                R.drawable.color_blanco);
        Animal animal7 = new Animal(
                "Perro",
                "Otro animal doméstico",
                R.drawable.perro,
                R.drawable.color_rosa);
        Animal animal8 = new Animal(
                "Vaca",
                "Un pájaro volador",
                R.drawable.vaca,
                R.drawable.color_rosa);
        animales.add(animal1);
        animales.add(animal2);
        animales.add(animal3);
        animales.add(animal4);
        animales.add(animal5);
        animales.add(animal6);
        animales.add(animal7);
        animales.add(animal8);
        return animales;
    }

}
