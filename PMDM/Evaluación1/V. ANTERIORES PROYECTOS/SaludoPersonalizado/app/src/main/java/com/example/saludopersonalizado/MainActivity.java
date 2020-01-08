package com.example.saludopersonalizado;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button botonHola;
    private TextView textViewSaludo;
    private EditText editTextNombre;
    private RadioButton radioButtonSr;
    private CheckBox checkBoxDespedida;
    private LinearLayout linearLayoutVista;
    private RadioButton radioButtonAdios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("ciclo", "Se ejecuta el método onCreate()");
        botonHola = findViewById(R.id.boton_pulsa);
        textViewSaludo = findViewById(R.id.textView_saludo);
        editTextNombre = findViewById(R.id.editText_nombre);
        radioButtonSr = findViewById(R.id.radioButton_Sr);
        checkBoxDespedida = findViewById(R.id.checkbox_Despedida);
        linearLayoutVista = findViewById(R.id.vista);
        radioButtonAdios = findViewById(R.id.radioButton_Adios);
        checkBoxDespedida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxDespedida.isChecked()) {
                    linearLayoutVista.setVisibility(View.VISIBLE);
                } else {
                    linearLayoutVista.setVisibility(View.GONE);
                }
            }
        });
        botonHola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //capturar contenido de la caja de texto
                String strNombre = editTextNombre.getText().toString();
                if (strNombre.isEmpty()) {
                    textViewSaludo.setText(R.string.faltanDatos);
                } else if (radioButtonSr.isChecked()) {
                    textViewSaludo.setText(getResources().getString(R.string.saludo) + ", " + "Sr. " + strNombre);
                } else {
                    textViewSaludo.setText(getResources().getString(R.string.saludo) + ", " + "Sra. " + strNombre);
                }
                if (checkBoxDespedida.isChecked()) {
                    if (radioButtonAdios.isChecked()) {
                        textViewSaludo.append("\n" + "Adiós");
                    } else {
                        textViewSaludo.append("\n" + "Hasta Luego");
                    }
                }
            }
        });
        if(savedInstanceState!=null){
            textViewSaludo.setText(savedInstanceState.getString("variable"));
            Log.i("ciclo", "Se ha recuperado mediante onCreate");
        } else {
            Toast.makeText(this, "No hay nada que recuperar", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle guardarEstado) {
        super.onSaveInstanceState(guardarEstado);
        guardarEstado.putString("variable", textViewSaludo.getText().toString());
        Log.i("ciclo", "Se ejecuta el método onSaveInstanceState");
    }

    /*
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle recEstado) {
        super.onRestoreInstanceState(recEstado);
        textViewSaludo.setText(recEstado.getString("variable"));
        Log.i("ciclo","Se ejecuta el método onRestoreInstanceState");
    }*/


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ciclo", "Se ejecuta el método onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ciclo", "Se ejecuta el método onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ciclo", "Se ejecuta el método onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ciclo", "Se ejecuta el método onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ciclo", "Se ejecuta el método onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ciclo", "Se ejecuta el método onRestart()");
    }
}
