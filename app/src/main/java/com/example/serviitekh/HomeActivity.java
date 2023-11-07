package com.example.serviitekh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageButton botonMarketing = findViewById(R.id.producto1);
        ImageButton botonRedes = findViewById(R.id.producto2);
        ImageButton botonWebPredeterminado = findViewById(R.id.producto3);
        ImageButton botonWebPersonalizadas = findViewById(R.id.producto4);

    }
}