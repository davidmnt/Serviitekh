package com.example.serviitekh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviitekh.Conexion.Modelo;
import com.example.serviitekh.Conexion.conex;
import com.example.serviitekh.Obj.Usuarios;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    static Modelo m = new Modelo();
    static Cursor cur;
    static Usuarios u = new Usuarios();
    static String usuario;
    static String passw;
    static String userIntroducido;
    static boolean encontrado;
    static String[] datosBBDD = new String[0];
    static int dimensionBBDD = 0;


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
                encontrado = false;


                //Cogemos la query para sacar todas las columnas que queremos para la siguiente query;
                cur = db.query("USUARIOS", null, null, null, null, null, null);
                String[] columnasBBDD = {cur.getColumnName(0), cur.getColumnName(1)};

                //Le damos dimension de la bbdd
                dimensionBBDD = cur.getCount();

                //Asignamos diemnsion al array
                datosBBDD = new String[dimensionBBDD];

                Log.e("Columnas", Arrays.toString(columnasBBDD));
                if (dimensionBBDD > 0) {

                    //Cogemos la query para sacar todos los datos de la tabla como un select * from USUARIOS;
                    cur = db.query("USUARIOS", columnasBBDD, null, null, null, null, null);

                    //Bucle para meter la bbdd en un array list
                    for (int i = 0; i < dimensionBBDD; i++) {
                        cur.moveToNext();
                        datosBBDD[i] = cur.getString(0) + cur.getString(1);

                    }

                    //Sacamos al log el resultado del array
                    Log.e("User arrayList", Arrays.toString(datosBBDD));

                    userIntroducido = user.getText().toString() + pass.getText().toString();

                    String[] arrIntr = new String[1];
                    arrIntr[0] = userIntroducido;

                    Log.e("Result array de introducion", Arrays.toString(arrIntr));

                    //Bucle para comprobar si esta el usuario en la base de datos o no
                    for (int i = 0; i < datosBBDD.length; i++) {
                        if (datosBBDD[i].equals(arrIntr[0])) {
                            Toast.makeText(getApplicationContext(), "Usuario correcto", Toast.LENGTH_SHORT).show();
                            encontrado = true;
                            textError.setText("");
                            mandarHome();
                            break;
                        }
                    }
                }

                //En caso de que no se ecnuentre el usuario sacar un error
                if (encontrado == false) {
                    textError.setText("Usuario incorrecto o tienes que registrate");
                }
            }
        });

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resInsert = 0;
                usuario = user.getText().toString();
                passw = pass.getText().toString();

                u = new Usuarios(usuario, passw);

                resInsert = m.insertarUssuario(getApplicationContext(), u);
                if (resInsert == 1) {
                    Toast.makeText(MainActivity.this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No enviado a la base de datos, error 404", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    private void mandarHome(){
        Intent i = new Intent(this,HomeActivity.class);
        startActivity(i);
    }

}