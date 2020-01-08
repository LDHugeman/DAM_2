package com.example.provinciasylocalidades_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String [] provincias;
    private AutoCompleteTextView autoCompleteTextViewProvincias;
    private Spinner spinnerLocalidades;
    private ArrayAdapter<CharSequence> adaptador;
    private String provincia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        provincias = getResources().getStringArray(R.array.provincias);
        autoCompleteTextViewProvincias = findViewById(R.id.autoCompleteTextView_provincias);
        ArrayAdapter adaptadorAc = new ArrayAdapter(this,android.R.layout.simple_list_item_1, provincias);
        autoCompleteTextViewProvincias.setAdapter(adaptadorAc);
        spinnerLocalidades = findViewById(R.id.spinner_localidades);
        autoCompleteTextViewProvincias.setThreshold(1);
        autoCompleteTextViewProvincias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                provincia = adapterView.getItemAtPosition(position).toString();
                if (provincia.equals("A Coruña")){
                    adaptador = ArrayAdapter.createFromResource(MainActivity.this, R.array.localidadesACoruña, android.R.layout.simple_spinner_item);
                } else if (provincia.equals("Lugo")){
                    adaptador = ArrayAdapter.createFromResource(MainActivity.this, R.array.localidadesLugo, android.R.layout.simple_spinner_item);
                } else if (provincia.equals("Ourense")){
                    adaptador = ArrayAdapter.createFromResource(MainActivity.this, R.array.localidadesOurense, android.R.layout.simple_spinner_item);
                } else if (provincia.equals("Pontevedra")){
                    adaptador = ArrayAdapter.createFromResource(MainActivity.this, R.array.localidadesPontevedra, android.R.layout.simple_spinner_item);
                } else {
                    adaptador = ArrayAdapter.createFromResource(MainActivity.this, R.array.sinLocalidades, android.R.layout.simple_spinner_item);
                }
                spinnerLocalidades.setAdapter(adaptador);
            }
        });

        spinnerLocalidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                if(adapterView.getSelectedItem().toString().equals("No hay localidades")){
                    Toast.makeText(MainActivity.this, "Aún no se han definido localidades", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Provincia: "+provincia+"\n"+"Localidad: "+spinnerLocalidades.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
