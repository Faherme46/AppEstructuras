package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app1.adaptadores.ListaEstudiantesAdapter;
import com.example.app1.db.DbCurso;
import com.example.app1.db.DbEstudiante;
import com.example.app1.entidades.Curso;
import com.example.app1.entidades.Estudiante;

import java.util.ArrayList;

public class EstudiantesActivity extends AppCompatActivity {

    RecyclerView listaEstudiantes;
    ArrayList<Estudiante> arrayEstudiantes;
    ArrayList<Curso> db;
    String acceso;
    DbCurso dbCurso;
    DbEstudiante dbEstudiante;
    TextView viewNumer,titulo;

    Button btnGuadarAsistenci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes);

        viewNumer=findViewById(R.id.viewNumero);
        titulo=findViewById(R.id.textView9);

        listaEstudiantes = findViewById(R.id.listaEstudiantes);
        listaEstudiantes.setLayoutManager(new LinearLayoutManager(this));
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            if (extras == null) {
                acceso = "";
            } else {
                acceso = extras.getString("acceso");
            }
        } else {
            acceso = (String) savedInstanceState.getSerializable("acceso");
        }

        dbEstudiante = new DbEstudiante(EstudiantesActivity.this, acceso, 0);


        arrayEstudiantes = dbEstudiante.mostrarEstudiantes();
        Bundle extras = getIntent().getExtras();

        ListaEstudiantesAdapter adapter = new ListaEstudiantesAdapter(arrayEstudiantes, extras.getInt("op"));



        listaEstudiantes.setAdapter(adapter);

        btnGuadarAsistenci = findViewById(R.id.btnGuardarAsistencia);
        if (extras.getInt("op") == 1) {
            btnGuadarAsistenci.setVisibility(View.INVISIBLE);
            viewNumer.setVisibility(View.INVISIBLE);
            titulo.setVisibility(View.INVISIBLE);

        } else {
            dbCurso=new DbCurso(EstudiantesActivity.this,"Cursos.db",0);
            Curso curso=new Curso();
            for (Curso c: dbCurso.mostrarCursos()) {
                if(c.getId()==extras.getInt("id")){
r
                    viewNumer.setText(String.valueOf(c.getClasesRegistradas()+1));

                    curso=c;
                }
            }

            db=dbCurso.mostrarCursos();


            Curso finalCurso = curso;
            btnGuadarAsistenci.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    guardar(adapter.getEstudiantes(), finalCurso);
                }
            });

        }

    }

    public void guardar(ArrayList<Estudiante> lista, Curso c){

        boolean x=false;
        for (Estudiante e:lista) {
            if (e.getChanged()==0){
                e.setAsistencias(e.getAsistencias()+1);
            }
            x=dbEstudiante.editarEstudiante(e);

        }
        if (x) {
            c.setClasesRegistradas(c.getClasesRegistradas()+1);
            dbCurso.editarCurso(c);
            Toast.makeText(this, "Asistencia guardada", Toast.LENGTH_SHORT).show();
            Intent i= new Intent(EstudiantesActivity.this,MainActivity.class);
            startActivity(i);
        }


    }
}