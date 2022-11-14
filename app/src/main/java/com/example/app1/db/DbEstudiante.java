package com.example.app1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.app1.entidades.Curso;
import com.example.app1.entidades.Estudiante;

import java.util.ArrayList;

public class DbEstudiante extends DbHelperEstudiante {
    Context context;
    String name;
    int version;

    public DbEstudiante(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, version);
        this.context=context;
        this.name=name;
        this.version=version;
    }

    public ArrayList<Estudiante> mostrarEstudiantes(){
        DbHelperEstudiante dbHelper = new DbHelperEstudiante(context, name, version);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Estudiante> listaEstudiantes=new ArrayList<>();
        Estudiante Estudiante=null;
        Cursor cursor=null;

        cursor=db.rawQuery("SELECT * FROM "+Tabla_Estudiantes,null);

        if(cursor.moveToFirst()) {
            do {
                Estudiante = new Estudiante();
                Estudiante.setId(cursor.getInt(0));
                Estudiante.setNombre(cursor.getString(1));
                Estudiante.setAsistencias(cursor.getInt(2));
                Estudiante.setFaltas(cursor.getInt(3));
                Estudiante.setRetrasos(cursor.getInt(4));
                Estudiante.setExcusas(cursor.getInt(5));

                listaEstudiantes.add(Estudiante);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaEstudiantes;


    }

    public Estudiante verEstudiantes(int id){
        DbHelperEstudiante dbHelper = new DbHelperEstudiante(context,name,version);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Estudiante estudiante=null;
        Cursor cursor=null;

        cursor=db.rawQuery("SELECT * FROM "+Tabla_Estudiantes +" WHERE id="+id+" LIMIT 1",null);

        if (cursor.moveToFirst()){
            estudiante = new Estudiante();
            estudiante.setId(cursor.getInt(0));
            estudiante.setNombre(cursor.getString(1));
            estudiante.setAsistencias(cursor.getInt(2));
            estudiante.setFaltas(cursor.getInt(3));
            estudiante.setRetrasos(cursor.getInt(4));
            estudiante.setExcusas(cursor.getInt(5));
        }
        cursor.close();
        return estudiante;


    }


    public boolean editarEstudiante(Estudiante e){

        boolean correcto=false;

        DbHelperEstudiante dbHelper = new DbHelperEstudiante(context, name, version);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE "+Tabla_Estudiantes+" SET nombre ='"+e.getNombre()+"', Asistencias= '"+e.getAsistencias()+"', fallas = '"+e.getFaltas()+"' , retrasos='"+e.getRetrasos()+"',excusas= '"+e.getExcusas()+"'  WHERE id= '"+e.getId()+"' ");
            correcto=true;
        }
        catch (Exception x){
            x.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;

    }

    public long insertarEstudiantes(Estudiante e){
        long id=0;
        try {
            DbHelperEstudiante dbHelper = new DbHelperEstudiante(context, name, version);

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("nombre", e.getNombre());
            values.put("Asistencias",e.getAsistencias());
            values.put("fallas", e.getFaltas());
            values.put("retrasos", e.getRetrasos());
            values.put("excusas", e.getExcusas());


            id = db.insert(Tabla_Estudiantes, null, values);
        }catch (Exception x){
            x.toString();
            System.out.println(x.getMessage());
        }

        return id;

    }

}
