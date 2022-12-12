package com.example.proyectosensores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton rbbru, rbvoz, rbinfra, rbmarca;

    int op = 0;

    Button btnselec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        rbbru = findViewById(R.id.rbbrujula);
        rbvoz = findViewById(R.id.rbvoz);
        rbinfra = findViewById(R.id.rbinfrarojo);
        rbmarca = findViewById(R.id.rbmarcapasos);
        btnselec = findViewById(R.id.btnseleccionar);

        rbbru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 1;
            }
        });

        rbvoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 2;
            }
        });
        rbinfra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 3;
            }
        });
        rbmarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op = 4;
            }
        });

        btnselec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (op) {

                    case 1:
                        Intent intent = new Intent(getApplicationContext(), Brujula.class);
                        startActivity(intent);
                        break;

                    case 2:
                        Intent intent2 = new Intent(getApplicationContext(), Voz.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getApplicationContext(), Infrarojo.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(getApplicationContext(), Pasos.class);
                        startActivity(intent4);
                        break;
                }
            }
        });
    }
}