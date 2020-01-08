package com.example.alarma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private Button botonActivarAlarma;
    private TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonActivarAlarma = findViewById(R.id.boton_activarAlarma);
        timePicker = findViewById(R.id.timePicker);
        botonActivarAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE,"Mensaje de la alarma")
                        .putExtra(AlarmClock.EXTRA_HOUR,timePicker.getCurrentHour())
                        .putExtra(AlarmClock.EXTRA_MINUTES,timePicker.getCurrentMinute());
                startActivity(intent);
            }
        });
    }
}
