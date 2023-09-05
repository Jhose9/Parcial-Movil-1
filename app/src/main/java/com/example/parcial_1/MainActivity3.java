package com.example.parcial_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {


    private TextView contadorTextView;
    private Button iniciarButton, detenerButton, continuarButton, resetearButton;
    private boolean cronometroActivo;
    private int segundos, minutos, horas;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        contadorTextView = findViewById(R.id.contadorTextView);
        iniciarButton = findViewById(R.id.iniciarButton);
        detenerButton = findViewById(R.id.detenerButton);
        continuarButton = findViewById(R.id.continuarButton);
        resetearButton = findViewById(R.id.resetearButton);

        ArrayList<String> vistas = new ArrayList<>();
        vistas.add("Ventana");
        vistas.add("Inicio");
        vistas.add("Codificar/Decodificar texto");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vistas);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner1 = findViewById(R.id.spinnerview3);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String opcionSeleccionada = vistas.get(i);
                if (opcionSeleccionada.equals("Inicio")) {

                    Intent Inicio = new Intent(MainActivity3.this,MainActivity.class);
                    startActivity(Inicio);
                    Toast.makeText(MainActivity3.this, "Seleccionaste Inicio", Toast.LENGTH_SHORT).show();

                } else if (opcionSeleccionada.equals("Codificar/Decodificar texto")) {
                    Intent Cronometro = new Intent(MainActivity3.this,MainActivity2.class);
                    startActivity(Cronometro);
                    Toast.makeText(MainActivity3.this, "Seleccionaste cronometro", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        handler = new Handler();
        cronometroActivo = false;
        iniciarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarCronometro();
            }
        });

        detenerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detenerCronometro();
            }
        });

        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuarCronometro();
            }
        });

        resetearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetearCronometro();
            }
        });
    }

    private void iniciarCronometro() {
        cronometroActivo = true;
        handler.postDelayed(actualizarTiempo, 1000);
    }

    private void detenerCronometro() {
        cronometroActivo = false;
        handler.removeCallbacks(actualizarTiempo);
    }

    private void continuarCronometro() {
        cronometroActivo = true;
        handler.postDelayed(actualizarTiempo, 1000);
    }

    private void resetearCronometro() {
        cronometroActivo = false;
        segundos = 0;
        minutos = 0;
        horas = 0;
        actualizarContador();
    }

    private Runnable actualizarTiempo = new Runnable() {
        public void run() {
            if (cronometroActivo) {
                segundos++;
                if (segundos == 60) {
                    segundos = 0;
                    minutos++;
                    if (minutos == 60) {
                        minutos = 0;
                        horas++;
                    }
                }
                actualizarContador();
                handler.postDelayed(this, 1000);
            }
        }
    };

    private void actualizarContador() {
        String tiempo = String.format("%02d:%02d:%02d", horas, minutos, segundos);
        contadorTextView.setText(tiempo);
    }
}