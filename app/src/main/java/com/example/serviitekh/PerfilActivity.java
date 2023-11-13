package com.example.serviitekh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        TextView textNombre  = findViewById(R.id.textNombre);

        String nombre = getIntent().getStringExtra("User");
        textNombre.setText(nombre.toUpperCase());

    }
}