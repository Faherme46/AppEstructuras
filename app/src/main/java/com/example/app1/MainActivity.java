package com.example.app1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnVer,btnEditar,btnLlenar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEditar=findViewById(R.id.btnCrear);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this, CrearActivity.class);
                startActivity(intent);

            }
        });

        btnVer =findViewById(R.id.btnVer);
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListarActivity.class);
                //llamo a listar para ver los cursos y editarlos
                intent.putExtra("op",1);
                startActivity(intent);

            }
        });
        btnLlenar=findViewById(R.id.btnLlenar);
        btnLlenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ListarActivity.class);
                //llamo a listar para elegir y registyrar asistencia
                intent.putExtra("op",0);
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
