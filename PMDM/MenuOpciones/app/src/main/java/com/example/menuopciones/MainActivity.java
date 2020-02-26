package com.example.menuopciones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case  R.id.item1:
                Toast.makeText(this, "Pulsada "+ item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Pulsada "+ item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Pulsada " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return true;
        }
    }
}
