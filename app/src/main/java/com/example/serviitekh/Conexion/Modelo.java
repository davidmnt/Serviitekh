package com.example.serviitekh.Conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.serviitekh.Obj.Usuarios;

public class Modelo {

    SQLiteDatabase getConn(Context context) {
        conex conn = new conex(context, "REGISTROS", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        return db;
    }



    public int insertarUssuario(Context context, Usuarios u){
        int res = 0;
        String sql = null;

        sql = "INSERT INTO USUARIOS (Usuario,Contraseña) VALUES ('" + u.getUsuario() + "','" + u.getPass() + "')";
        SQLiteDatabase db = this.getConn(context);


        try {
            db.execSQL(sql);
            res = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
