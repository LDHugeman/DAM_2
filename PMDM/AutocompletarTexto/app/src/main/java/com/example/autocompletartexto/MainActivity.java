package com.example.autocompletartexto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String [] nombres;
    private AutoCompleteTextView autoCompleteTextViewNombres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombres = getResources().getStringArray(R.array.nombres);
        autoCompleteTextViewNombres = findViewById(R.id.autoCompleteTexView_nombres);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nombres);
        autoCompleteTextViewNombres.setAdapter(adaptador);
        autoCompleteTextViewNombres.setThreshold(1); //Desde que caracter empieza la búsqueda
        autoCompleteTextViewNombres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Toast.makeText(MainActivity.this, "Has eligido el nombre "+ adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
