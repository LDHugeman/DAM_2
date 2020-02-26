package com.example.planetas6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listViewPlanetas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewPlanetas = findViewById(R.id.listView_planetas);
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.planetas, android.R.layout.simple_list_item_1);
        listViewPlanetas.setAdapter(adaptador);
        listViewPlanetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Elección: "+adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
