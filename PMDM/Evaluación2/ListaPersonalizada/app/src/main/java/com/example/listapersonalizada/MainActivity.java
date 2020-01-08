package com.example.listapersonalizada;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView)findViewById(R.id.lv);
        String [] arrayPlanetas=getResources().getStringArray(R.array.planetas);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.layout_fila,R.id.tvplaneta,arrayPlanetas);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Elección: "+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
