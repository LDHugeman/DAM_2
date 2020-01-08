package com.example.componentes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMensaje;
    private ToggleButton toggleButtonOnOff;
    private CheckBox checkBoxWindows;
    private CheckBox checkBoxLinux;
    private RadioGroup radioGroupSiNo;
    private RadioButton radioButtonSi;
    private RadioButton radioButtonNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMensaje = findViewById(R.id.textView_mensaje);
        toggleButtonOnOff = findViewById(R.id.toogleButton_onOff);
        checkBoxWindows = findViewById(R.id.checkbox_Windows);
        checkBoxLinux = findViewById(R.id.checkbox_Linux);
        radioGroupSiNo = findViewById(R.id.radioGroup_Si_No);
        radioButtonSi = findViewById(R.id.radio_Si);
        radioButtonNo = findViewById(R.id.radio_No);

        radioGroupSiNo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio_Si){
                    textViewMensaje.setText("RadioButton Si");
                } else {
                    textViewMensaje.setText("RadioButton No");
                }
            }
        });

        checkBoxWindows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBoxWindows.isChecked()){
                    textViewMensaje.setText("Windows está marcado");
                } else {
                    textViewMensaje.setText("Windows está desmarcado");
                }
            }
        });

        checkBoxLinux.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    textViewMensaje.setText("Linux está marcado");
                }else{
                    textViewMensaje.setText("Linux está desmarcado");
                }
            }
        });
    }



    public void onClickToogle(View view) {
        if(toggleButtonOnOff.isChecked()){
            textViewMensaje.setText("ToggleButton está ON");
        } else {
            textViewMensaje.setText("ToggleButton está OFF");
        }
    }
}
