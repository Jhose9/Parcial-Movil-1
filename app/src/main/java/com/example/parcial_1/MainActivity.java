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
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private EditText value1,value2,value3,value4;

    private String opcionSeleccionada = "Seleccione";

    private  TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado=findViewById(R.id.Resultado);

        Button btnRandom;
        btnRandom=findViewById(R.id.buttonGenerateRandomPoints);

        Button btnCalcular;
        btnCalcular=findViewById(R.id.buttonCalculate);
        //

        ArrayList<String> vistas = new ArrayList<>();
        vistas.add("Ventana");
        vistas.add("Codificar/Decodificar texto");
        vistas.add("cronómetro");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vistas);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner1 = findViewById(R.id.spinnerview);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String opcionSeleccionada = vistas.get(i);
                if (opcionSeleccionada.equals("Codificar/Decodificar texto")) {

                    Intent Codificar = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(Codificar);
                    Toast.makeText(MainActivity.this, "Seleccionaste codificar", Toast.LENGTH_SHORT).show();

                } else if (opcionSeleccionada.equals("cronómetro")) {
                    Intent Cronometro = new Intent(MainActivity.this,MainActivity3.class);
                    startActivity(Cronometro);
                    Toast.makeText(MainActivity.this, "Seleccionaste cronometro", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Spinner de Operaciones
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("Seleccione");
        opciones.add("Distancia entre puntos");
        opciones.add("Pendiente");
        opciones.add("Punto Medio");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spinnerOperation);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String opcionSeleccionada = opciones.get(i);

                btnCalcular.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                
                
                if (opcionSeleccionada.equals("Pendiente")) {



                        double x1 = Double.parseDouble(value1.getText().toString());
                        double y1 = Double.parseDouble(value2.getText().toString());
                        double x2 = Double.parseDouble(value3.getText().toString());
                        double y2 = Double.parseDouble(value4.getText().toString());
                        double a = y2-y1;
                        double b = x2-x1;
                        resultado.setText(String.valueOf(a+" / "+b));


                    
                } else if (opcionSeleccionada.equals("Distancia entre puntos")) {


                    double x1 = Double.parseDouble(value1.getText().toString());
                    double y1 = Double.parseDouble(value2.getText().toString());
                    double x2 = Double.parseDouble(value3.getText().toString());
                    double y2 = Double.parseDouble(value4.getText().toString());


                    double distancia = calcularDistancia(x1, y1, x2, y2);


                    resultado.setText("La distancia entre los puntos es: " + distancia);


                } else if (opcionSeleccionada.equals("Punto Medio")) {

                    double x1 = Double.parseDouble(value1.getText().toString());
                    double y1 = Double.parseDouble(value2.getText().toString());
                    double x2 = Double.parseDouble(value3.getText().toString());
                    double y2 = Double.parseDouble(value4.getText().toString());

                    double xm = (x1 + x2) / 2;
                    double ym = (y1 + y2) / 2;

                    resultado.setText("yc:"+String.valueOf(xm) +" xc:"+String.valueOf(ym));
                    
                } else if (opcionSeleccionada.equals("Seleccione")) {
                    Toast.makeText(MainActivity.this, "Selecciona una opcion", Toast.LENGTH_SHORT).show();
                }


                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });




        //-------------------------->
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               value1=findViewById(R.id.x1);
                value2=findViewById(R.id.y1);
                value3=findViewById(R.id.x2);
                value4=findViewById(R.id.y2);
                Random random=new Random();
                int v1= random.nextInt(100);
                int v2= random.nextInt(100);
                int v3= random.nextInt(100);
                int v4= random.nextInt(100);



                value1.setText(String.valueOf(v1));
               value2.setText(String.valueOf(v2));
                value3.setText(String.valueOf(v3));
                value4.setText(String.valueOf(v4));

            }
        });
    }


    private double calcularDistancia(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }




}