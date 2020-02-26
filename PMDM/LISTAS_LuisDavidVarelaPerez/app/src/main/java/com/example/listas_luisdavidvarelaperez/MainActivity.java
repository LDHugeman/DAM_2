package com.example.listas_luisdavidvarelaperez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerCursos;
    Spinner spinnerCiclos;
    EditText editTextNombre;
    Button botonGuardar;
    Button botonListar;
    ArrayList<Alumno> alumnos;
    ListView listViewAlumnos;
    private String nombreAlumno;
    AdaptadorPersonalizado adataptadorLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alumnos = new ArrayList<>();
        botonGuardar = findViewById(R.id.boton_Guardar);
        editTextNombre = findViewById(R.id.editText_Nombre);
        spinnerCursos = findViewById(R.id.spinner_Cursos);
        spinnerCiclos = findViewById(R.id.spinner_Ciclos);
        listViewAlumnos = findViewById(R.id.listView_Alumnos);
        botonListar = findViewById(R.id.boton_Listar);
        ArrayAdapter<CharSequence> adaptadorCursos = ArrayAdapter.createFromResource(this,R.array.cursos, android.R.layout.simple_spinner_item);
        spinnerCursos.setAdapter(adaptadorCursos);
        ArrayAdapter<CharSequence> adaptadorCiclos = ArrayAdapter.createFromResource(this, R.array.ciclos, android.R.layout.simple_spinner_item);
        spinnerCiclos.setAdapter(adaptadorCiclos);
        adataptadorLista = new AdaptadorPersonalizado(this,alumnos);
        listViewAlumnos.setAdapter(adataptadorLista);
        registerForContextMenu(listViewAlumnos);
        spinnerCursos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Ciclos")){
                    spinnerCiclos.setVisibility(View.VISIBLE);
                } else {
                    spinnerCiclos.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextNombre.getText().toString();
                if(!nombre.isEmpty()){
                    Alumno alumno = new Alumno(
                        nombre,
                        spinnerCursos.getSelectedItem().toString(),
                        spinnerCiclos.getSelectedItem().toString());
                    alumnos.add(alumno);
                    editTextNombre.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Escribe el nombre del alumno", Toast.LENGTH_LONG).show();
                }
            }
        });

        botonListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alumnos.isEmpty()){
                    Toast.makeText(MainActivity.this, "Lista vacía", Toast.LENGTH_SHORT).show();
                } else {
                    botonGuardar.setEnabled(false);
                    listViewAlumnos.setVisibility(View.VISIBLE);
                }
            }
        });

        listViewAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(spinnerCursos.getSelectedItem().toString().equals("ESO") || spinnerCursos.getSelectedItem().toString().equals("Bach")){
                    Toast.makeText(MainActivity.this, "Alumno: "+parent.getItemAtPosition(position).toString()+"\n"
                            +"Curso: "+spinnerCursos.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Alumno: "+parent.getItemAtPosition(position).toString()+"\n"
                            +"Curso: "+spinnerCursos.getSelectedItem().toString()+ "\n"+ "Ciclo: "+spinnerCiclos.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        nombreAlumno = alumnos.get(info.position).getNombre();
        menu.setHeaderTitle(nombreAlumno);
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        ContextMenu.ContextMenuInfo contextMenuInfo = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) contextMenuInfo;
        nombreAlumno = listViewAlumnos.getAdapter().getItem(info.position).toString();
        int idItem =item.getItemId();
        switch (idItem){
            case R.id.eliminar:
                adataptadorLista.remove(adataptadorLista.getItem(info.position));
                return true;
            case R.id.opcion2:
                Toast.makeText(this, "Esta opción aún no tiene funcionalidad"+item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onContextItemSelected(item);
    }

}
