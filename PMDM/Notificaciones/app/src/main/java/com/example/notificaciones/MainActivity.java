package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICACION_ALERTA = 0;
    Button botonNotificacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonNotificacion = findViewById(R.id.boton_notificacion);
        botonNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification.Builder notificacion = new Notification.Builder(MainActivity.this);
                notificacion.setSmallIcon(R.drawable.libro);
                notificacion.setTicker("Nuevo libro"); //En las apis actuales no se ve
                notificacion.setContentTitle("-- Hay un nuevo libro disponible --");
                notificacion.setContentText("Nuevo libro disponible en tu biblioteca virtual");
                Bitmap icono = BitmapFactory.decodeResource(getResources(),R.drawable.happy);
                notificacion.setLargeIcon(icono);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);
                notificacion.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificacion.setAutoCancel(true);
                Notification notification = notificacion.build();
                notificationManager.notify(NOTIFICACION_ALERTA,notification);
            }
        });
    }
}
