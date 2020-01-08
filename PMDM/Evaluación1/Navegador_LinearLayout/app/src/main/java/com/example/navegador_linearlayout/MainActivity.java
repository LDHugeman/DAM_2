package com.example.navegador_linearlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_mensaje = (TextView) findViewById(R.id.tv_mensaje);
    }

    public void onClickBtn(View view) {
        if (view.getId() == R.id.btn_si) {
            tv_mensaje.setText("Pulsado btn si");
        } else if (view.getId() == R.id.btn_no) {
            tv_mensaje.setText("Pulsado btn no");
        } else if (view.getId() == R.id.btn_aveces) {
            tv_mensaje.setText("Pulsado btn a A veces");
        }
    }

    /*
    public void onClick_si(View v) {
        tv_mensaje.setText("Pulsado btn si");
    }

    public void onClick_no(View v) {
        tv_mensaje.setText("Pulsado btn no");
    }

    public void onClick_aVeces(View v) {
        tv_mensaje.setText("Pulsado btn a A veces");
    }*/
}
