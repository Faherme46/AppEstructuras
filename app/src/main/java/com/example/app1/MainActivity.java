package com.example.app1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    ImageButton btnVer,btnEditar,btnLlenar, btnInforme;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEditar=findViewById(R.id.imgCrear);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this, CrearActivity.class);
                startActivity(intent);

            }
        });

        btnVer =findViewById(R.id.imgVer);
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListarActivity.class);
                //llamo a listar para ver los cursos y editarlos
                intent.putExtra("op",1);
                startActivity(intent);

            }
        });
        btnLlenar=findViewById(R.id.imgLLenar);
        btnLlenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ListarActivity.class);
                //llamo a listar para elegir y registyrar asistencia
                intent.putExtra("op",0);
                startActivity(intent);
            }
        });

        //boton de generar informe
        btnInforme=findViewById(R.id.imgInforme);
        btnInforme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //llama el listado
                Intent intent=new Intent(MainActivity.this, ListarActivity.class);
                //llamo a listar para elegir y registyrar asistencia
                intent.putExtra("op",2);
                startActivity(intent);
            }


        });



    }

//Boton Atras
    @Override
    public void onBackPressed() {
        AlertDialog.Builder mybuild= new AlertDialog.Builder(this);
        mybuild.setMessage("Seguro que desea salir?");
        mybuild.setMessage("¿Seguro que desea salir?");
        mybuild.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        mybuild.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog =mybuild.create();
        dialog.show();
    }


}
