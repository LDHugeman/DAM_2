package com.example.planetas6_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ListView listViewPlanetas;
    private String nombrePlaneta;
    ArrayAdapter<String> adaptador;
    private String [] planetasEstatico;
    private ArrayList<String> planetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewPlanetas = findViewById(R.id.listView_planetas);
        registerForContextMenu(listViewPlanetas);
        planetas = new ArrayList<>();
        planetasEstatico = getResources().getStringArray(R.array.planetas);
        planetas.addAll(Arrays.asList(planetasEstatico));
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, planetas);
        listViewPlanetas.setAdapter(adaptador);
        listViewPlanetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Elección: "+adapterView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        nombrePlaneta = listViewPlanetas.getAdapter().getItem(info.position).toString();
        menu.setHeaderTitle(nombrePlaneta);
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        ContextMenu.ContextMenuInfo contextMenuInfo = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) contextMenuInfo;
        nombrePlaneta = listViewPlanetas.getAdapter().getItem(info.position).toString();
        int idItem = item.getItemId();
        switch (idItem) {
            case R.id.eliminar:
                planetas.remove(info.position);
                adaptador.notifyDataSetChanged();
                return true;
            case R.id.opcion2:
                Toast.makeText(this, nombrePlaneta + " " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opcion3:
                Toast.makeText(this, nombrePlaneta + " " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }
}