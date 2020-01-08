package com.example.listapersonalizada_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] arrayAnimales;
    private Integer[] arrayIdFotos;
    private String [] descripciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializacion();
        ListView lv = (ListView) findViewById(R.id.listView_animales);
        AdaptadorPersonalizado adapter = new AdaptadorPersonalizado(this, arrayAnimales, arrayIdFotos, descripciones);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Elección: " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inicializacion() {
        arrayAnimales = new String[]{"Aguila", "Ballena", "Caballo", "Canario", "Delfín", "Gato"};
        arrayIdFotos = new Integer[]{R.drawable.aguila, R.drawable.ballena, R.drawable.caballo, R.drawable.canario, R.drawable.delfin, R.drawable.gato};
        descripciones = new String[]{"Ave rapaz", "Mamífero marino", "Porte elegante", "Canto espectacular", "Mamífero marino", "Adorable"};
    }
}
