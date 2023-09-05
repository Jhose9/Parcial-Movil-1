package com.example.parcial_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {



    private EditText textInput;
    private Button codificarButton, decodificarButton;
    private TextView resultadoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textInput = findViewById(R.id.textInput);
        codificarButton = findViewById(R.id.codificarButton);
        decodificarButton = findViewById(R.id.decodificarButton);
        resultadoTextView = findViewById(R.id.resultadoTextView);

        ArrayList<String> vistas = new ArrayList<>();
        vistas.add("Ventana");
        vistas.add("Inicio");
        vistas.add("cronómetro");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vistas);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner1 = findViewById(R.id.spinnerview2);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String opcionSeleccionada = vistas.get(i);
                if (opcionSeleccionada.equals("Inicio")) {

                    Intent Inicio = new Intent(MainActivity2.this,MainActivity.class);
                    startActivity(Inicio);
                    Toast.makeText(MainActivity2.this, "Seleccionaste Inicio", Toast.LENGTH_SHORT).show();

                } else if (opcionSeleccionada.equals("cronómetro")) {
                    Intent Cronometro = new Intent(MainActivity2.this,MainActivity3.class);
                    startActivity(Cronometro);
                    Toast.makeText(MainActivity2.this, "Seleccionaste cronometro", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






        codificarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String textoOriginal = textInput.getText().toString();
                String textoCodificado = codificarTexto(textoOriginal);
                resultadoTextView.setText(textoCodificado);
            }
        });

        decodificarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoCodificado = textInput.getText().toString();
                String textoDecodificado = decodificarTexto(textoCodificado);
                resultadoTextView.setText(textoDecodificado);
            }
        });
    }
    private String codificarTexto(String texto) {
        // Realizar las conversiones especificadas
        texto = texto.replaceAll("a", "@");
        texto = texto.replaceAll("e", "3");
        texto = texto.replaceAll("i", "1");
        texto = texto.replaceAll("o", "8");
        texto = texto.replaceAll("u", "5");
        texto = texto.replaceAll("m", "&");
        texto = texto.replaceAll("n", "(");
        texto = texto.replaceAll("p", ")");
        texto = texto.replaceAll("r", "#");

        return texto;
    }

    private String decodificarTexto(String texto) {
        // Revertir las conversiones
        texto = texto.replaceAll("@", "a");
        texto = texto.replaceAll("3", "e");
        texto = texto.replaceAll("1", "i");
        texto = texto.replaceAll("8", "o");
        texto = texto.replaceAll("5", "u");
        texto = texto.replaceAll("&", "m");
        texto = texto.replaceAll("\\(", "n");
        texto = texto.replaceAll("\\)", "p");
        texto = texto.replaceAll("#", "r");

        return texto;
    }

}