package com.example.app1;

import static com.example.app1.entidades.helper.isAlpha;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app1.db.DbCurso;
import com.example.app1.db.DbEstudiante;
import com.example.app1.db.DbHelperEstudiante;
import com.example.app1.entidades.Curso;
import com.example.app1.entidades.Estudiante;

import java.util.ArrayList;

public class CrearActivity extends AppCompatActivity{

    public String  n;
    boolean b=false;
    Estudiante e=new Estudiante();
    ArrayList<Estudiante> arr= e.generar();


    Button guardar,cargar;
    Button volver;


    EditText txtClases;
    EditText txtGrado;
    EditText txtMateria, txtSalon;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        txtMateria =(EditText) findViewById(R.id.eMateria);
        txtClases =(EditText)findViewById(R.id.numClases);
        txtGrado =(EditText)findViewById(R.id.grado);
        txtSalon =(EditText)findViewById(R.id.salon);


        //Boton Volver
        volver=findViewById(R.id.btnVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(CrearActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        //Boton Guardar
        guardar=(Button) findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validar()) {
                    if(b){
                        llenarDb("Cursos", 0);
                    }else{
                        Toast.makeText(CrearActivity.this, "No se han cargado estudiantes", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CrearActivity.this, "Error en los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Boton Cargar
        cargar=(Button) findViewById(R.id.btnCargar);
        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n="e_"+txtMateria.getText().toString()+"_"+txtGrado.getText().toString()+txtSalon.getText().toString()+".db";

                b=crearDb(n,0);

            }
        });

    }


    public boolean validar(){
        boolean ret=true;
        try{
        //validacion de datos vacios
        if (txtMateria.getText().toString().isEmpty()){
            txtMateria.setError("Este campo no debe estar vacio");
            ret=false;
        }
        if (txtClases.getText().toString().isEmpty()){
            txtClases.setError("Este campo no debe estar vacio");
            ret=false;
        }
        if (txtGrado.getText().toString().isEmpty()){
            txtGrado.setError("Este campo no debe estar vacio");
            ret=false;
        }
        if(Integer.parseInt(txtGrado.getText().toString())<0||Integer.parseInt(txtGrado.getText().toString())>11){
            txtGrado.setError("Debe ser un numero entre 0 y 11");
            ret=false;
        }
        if (txtSalon.getText().toString().isEmpty()){
            txtSalon.setError("Este campo no puede quedar vacio");
            ret=false;
        }
        if (txtSalon.getText().toString().length()!=1 || !isAlpha(txtSalon.getText().toString())){
            txtSalon.setError("Debe ser una unica letra");
            ret=false;
        }

        }catch (Exception e){
            txtGrado.setError("Debe ser un numero");
            txtClases.setError("Debe ser un numero");
            return false;
        }


        return ret;
    }

    public void llenarDb(@Nullable String name, @Nullable int v) {
            DbCurso db = new DbCurso(CrearActivity.this,name,v);
            long id=db.insertarCurso(txtMateria.getText().toString(),(txtGrado.getText().toString()+"-"+ txtSalon.getText().toString()),Integer.parseInt(txtClases.getText().toString()), this.n, arr.size());
            if (id>0){
                Toast.makeText(this, "Registro Guardado", Toast.LENGTH_SHORT).show();
                limpiar();

            }else{
                Toast.makeText(this,"Error al Guardar",Toast.LENGTH_SHORT).show();
            }
    }

    public boolean crearDb(@Nullable String name, @Nullable int v) {

        if(validar()){
            DbHelperEstudiante dbHelperEstudiante = new DbHelperEstudiante(CrearActivity.this,name,v);
            SQLiteDatabase db = dbHelperEstudiante.getWritableDatabase();
            DbEstudiante d= new DbEstudiante(CrearActivity.this,name,v);



            if (db!=null){
                Toast.makeText(CrearActivity.this, "Estudiantes Cargados", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < arr.size(); i++) {
                    long l= d.insertarEstudiantes(arr.get(i));
                    if (l<0){
                        Toast.makeText(this, "Error al agregar", Toast.LENGTH_SHORT).show();
                    }
                }

                return true;
            }else{
                Toast.makeText(CrearActivity.this, "Error al crear la base de datos", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return false;
    }

    private void limpiar(){
        txtClases.setText("");
        txtMateria.setText("");
        txtGrado.setText("");
        txtSalon.setText("");
    }
}