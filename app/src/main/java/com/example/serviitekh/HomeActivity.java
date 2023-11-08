package com.example.serviitekh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageButton botonMarketing = findViewById(R.id.producto1);
        ImageButton botonRedes = findViewById(R.id.producto2);
        ImageButton botonWebPredeterminado = findViewById(R.id.producto3);
        ImageButton botonWebPersonalizadas = findViewById(R.id.producto4);
        CardView card = findViewById(R.id.SobreNosotros);
        TextView textBienvenida = findViewById(R.id.texto_arriba);

        String nombre = getIntent().getStringExtra("USUARIO");
        textBienvenida.setText("Bienvenido a Serviitekh " + nombre.toUpperCase());
        Log.e("Usuario",nombre);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MandarSobreNosotros();
            }
        });

        botonMarketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             MandarMarketing();
            }
        });

        botonRedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MandarRS();
            }
        });
        botonWebPredeterminado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MandarWebPredeterminadas();
            }
        });
        botonWebPersonalizadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MandarWebPersonalizada();
            }
        });

    }

    private void MandarSobreNosotros(){
        Intent i = new Intent(this, SobreNosotrosActivity.class);
        startActivity(i);
    }
    private void MandarMarketing(){
        Intent i = new Intent(this, MarketingActivity.class);
        startActivity(i);
    }

    private void MandarRS(){
        Intent i = new Intent(this, RedesSocialesActivity.class);
        startActivity(i);
    }
    private void MandarWebPredeterminadas(){
        Intent i = new Intent(this, WebPredeterminadaActivity.class);
        startActivity(i);
    }
    private void MandarWebPersonalizada(){
        Intent i = new Intent(this, WebPersonalizadasActivity.class);
        startActivity(i);
    }




}