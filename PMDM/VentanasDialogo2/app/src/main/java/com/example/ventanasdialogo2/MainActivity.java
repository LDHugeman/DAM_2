package com.example.ventanasdialogo2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> opcionesSeleccionadas;
    Button botonVentana1;
    Button botonVentana2;
    Button botonVentana3;
    Button botonVentana4;
    Button botonVentanaLista;
    Button botonVentanaRadioButton;
    Button botonVentanaCheckbox;
    String[] colores;
    private static final int DIALOGO_MENSAJE_1 = 1;
    private static final int DIALOGO_MENSAJE_2 = 2;
    private static final int DIALOGO_MENSAJE_3 = 3;
    private static final int DIALOGO_MENSAJE_4 = 4;
    private static final int DIALOGO_LISTA = 5;
    private static final int DIALOGO_RADIOBUTTON = 6;
    private static final int DIALOGO_CHECKBOX = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonVentana1 = findViewById(R.id.button_ventana1);
        botonVentana2 = findViewById(R.id.button_ventana2);
        botonVentana3 = findViewById(R.id.button_ventana3);
        botonVentana4 = findViewById(R.id.button_ventana4);
        botonVentanaRadioButton = findViewById(R.id.button_ventana_con_lista_radiobutton);
        botonVentanaCheckbox = findViewById(R.id.button_ventana_con_lista_checkbox);
        botonVentanaLista = findViewById(R.id.button_ventana_con_lista);
        colores = getResources().getStringArray(R.array.colores);
        botonVentana1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOGO_MENSAJE_1);
            }
        });
        botonVentana2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOGO_MENSAJE_2);
            }
        });

        botonVentana3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOGO_MENSAJE_3);
            }
        });

        botonVentana4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOGO_MENSAJE_4);
            }
        });

        botonVentanaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOGO_LISTA);
            }
        });

        botonVentanaRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOGO_RADIOBUTTON);
            }
        });
        botonVentanaCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOGO_CHECKBOX);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(MainActivity.this);
        if (id == DIALOGO_MENSAJE_1) {
            ventana.setTitle("Atención");
            ventana.setMessage("Esto es un mensaje. Pulsa el botón...");
            ventana.setIcon(android.R.drawable.ic_dialog_alert);
        } else if (id == DIALOGO_MENSAJE_2) {

            ventana.setTitle("Atención");
            ventana.setMessage("Esto es un mensaje. Pulsa el botón OK para volver a la pantalla principal");
            ventana.setIcon(android.R.drawable.ic_dialog_alert);
            ventana.setCancelable(false);
            ventana.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        } else if (id == DIALOGO_MENSAJE_3) {
            ventana.setTitle("Importante");
            ventana.setMessage("¿Acepta la ejecución de esta aplicación en modo de prueba?");
            ventana.setIcon(android.R.drawable.ic_dialog_alert);
            ventana.setCancelable(false);
            ventana.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Toast.makeText(MainActivity.this, "Bienvenid@", Toast.LENGTH_SHORT).show();
                }
            });
            ventana.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        } else if (id == DIALOGO_MENSAJE_4) {
            ventana.setTitle("Importante");
            ventana.setMessage("¿Acepta la ejecución de esta aplicación en modo de prueba?");
            ventana.setIcon(android.R.drawable.ic_dialog_alert);
            ventana.setCancelable(false);
            ventana.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Toast.makeText(MainActivity.this, "Bienvenid@", Toast.LENGTH_SHORT).show();
                }
            });
            ventana.setNeutralButton("Más tarde", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Toast.makeText(MainActivity.this, "Más tarde entonces", Toast.LENGTH_SHORT).show();
                }
            });
            ventana.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        } else if (id == DIALOGO_LISTA) {
            ventana.setTitle("Elige color");
            ventana.setItems(colores, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Opción elegida: " + colores[which], Toast.LENGTH_SHORT).show();
                }
            });
        } else if (id == DIALOGO_RADIOBUTTON) {
            ventana.setTitle("Elige color");
            ventana.setSingleChoiceItems(colores, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Opción elegida: " + colores[which], Toast.LENGTH_SHORT).show();
                }
            });
        } else if (id == DIALOGO_CHECKBOX) {
            opcionesSeleccionadas = new ArrayList<>();
            ventana.setTitle("Elige color");
            ventana.setMultiChoiceItems(colores, null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    if (isChecked) {
                        opcionesSeleccionadas.add(colores[which]);
                    } else if (opcionesSeleccionadas.contains(colores[which])) {
                        opcionesSeleccionadas.remove(colores[which]);
                    }
                }
            });
            //array estatico de booleans
            ventana.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String eleccion = "Opciones elegidas: ";

                    for(int i=0;i<opcionesSeleccionadas.size();i++){
                        eleccion += opcionesSeleccionadas.get(i)+" ";
                    }
                    Toast.makeText(MainActivity.this, eleccion, Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                    removeDialog(DIALOGO_CHECKBOX);
                }
            });
        }
        return ventana.create();
    }
}
