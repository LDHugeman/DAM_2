package com.example.listapersonalizada_v4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] arrayAnimales;
    private TypedArray arrayIdFotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializacion();
        ListView lv = (ListView)findViewById(R.id.lv);
        AdaptadorPersonalizado adapter = new AdaptadorPersonalizado(this,arrayAnimales,arrayIdFotos);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Elección: "+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void inicializacion(){
        arrayAnimales = getResources().getStringArray(R.array.textoAnimales);
        arrayIdFotos = getResources().obtainTypedArray(R.array.imagenes);
    }
}
