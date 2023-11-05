package com.example.serviitekh;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviitekh.Conexion.Modelo;
import com.example.serviitekh.Conexion.conex;
import com.example.serviitekh.Obj.Usuarios;

public class MainActivity extends AppCompatActivity {
    static Modelo m = new Modelo();
    static Cursor cur;
    static Usuarios u = new Usuarios();
    static String usuario;
    static String passw;
    static String[] UsuariosBBDD;
    static String[] userIntroducido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textError = findViewById(R.id.error);
        EditText user = findViewById(R.id.user);
        EditText pass = findViewById(R.id.pass);
        Button botonLogin = findViewById(R.id.login);
        Button botonRegistro = findViewById(R.id.register);

        conex conn = new conex(this, "REGISTROS", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();

        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cur = db.query("USUARIOS", null, null, null, null, null, null);
                String[] columnasBBDD = {cur.getColumnName(0), cur.getColumnName(1)};

                if (cur.getCount() > 0) {

                    cur = db.query("USUARIOS", columnasBBDD, null, null, null, null, null);
                    cur.moveToFirst();
                    String datosUsuarioUser = cur.getString(0);
                    String datosUsuarioPass = cur.getString(1);
                    UsuariosBBDD = new String[]{datosUsuarioUser + " " + datosUsuarioPass};
                    userIntroducido = new String[]{user.getText().toString() + " " + pass.getText().toString()};

                    for (int i = 0; i < UsuariosBBDD.length; i++) {

                        if (UsuariosBBDD[i].equals(userIntroducido[i])) {
                            Toast.makeText(MainActivity.this, "Usuario correcto", Toast.LENGTH_SHORT).show();


                        }else{
                            textError.setText("Usuario incorrecto o tienes que registrate");
                        }
                    }
                }

            }
        });

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resInsert = 0;
                usuario = user.getText().toString();
                passw = pass.getText().toString();

                u = new Usuarios(usuario,passw);

                resInsert = m.insertarUssuario(getApplicationContext(), u);
                if (resInsert == 1) {
                    Toast.makeText(MainActivity.this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No enviado a la base de datos, error 404", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

}