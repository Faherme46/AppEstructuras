package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app1.adaptadores.ListaEstudiantesAdapter;
import com.example.app1.db.DbEstudiante;
import com.example.app1.entidades.Estudiante;

import java.util.ArrayList;

public class EstudiantesActivity extends AppCompatActivity {

    RecyclerView listaEstudiantes;
    ArrayList<Estudiante> arrayEstudiantes;
    String acceso;

    Button btnGuadarAsistenci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes);



        listaEstudiantes=findViewById(R.id.listaEstudiantes);
        listaEstudiantes.setLayoutManager(new LinearLayoutManager(this));
        if (savedInstanceState==null){
            Bundle extras= getIntent().getExtras();

            if (extras==null) {
                acceso = "";
            }else {
                acceso=extras.getString("acceso");
            }
        }else{
            acceso=(String) savedInstanceState.getSerializable("acceso");
        }

        DbEstudiante dbEstudiante=new DbEstudiante(EstudiantesActivity.this,acceso,0);

        arrayEstudiantes=dbEstudiante.mostrarEstudiantes();
        Bundle extras= getIntent().getExtras();


        ListaEstudiantesAdapter adapter=new ListaEstudiantesAdapter(arrayEstudiantes,extras.getInt("op"));


        listaEstudiantes.setAdapter(adapter);

        btnGuadarAsistenci=findViewById(R.id.btnGuardarAsistencia);
        if(extras.getInt("op")==1){
            btnGuadarAsistenci.setVisibility(View.INVISIBLE);
        }


    }
}