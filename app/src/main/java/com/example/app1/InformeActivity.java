package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app1.db.DbCurso;
import com.example.app1.db.DbEstudiante;
import com.example.app1.entidades.Curso;
import com.example.app1.entidades.Estudiante;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class InformeActivity extends AppCompatActivity {

    EditText txtMateria, txtInfo;
    TextView  txtClases, txtGrado, txtNumero;
    FloatingActionButton fabCopy,fabShare;


    int id=1;
    Curso c;
    int asistencia=0;
    int retraso =0;
    int excusa=0;
    int faltas=0;
    String mensaje;

    ArrayList<Estudiante> arrayEstudiantes;
    DecimalFormat df = new DecimalFormat(".2f");

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);

        txtMateria=findViewById(R.id.txtMateri);
        txtInfo=findViewById(R.id.txtInf);

        txtClases=findViewById(R.id.txtClases);
        txtGrado=findViewById(R.id.txtGrado);
        txtNumero=findViewById(R.id.txtEstudiantes);

        fabCopy=findViewById(R.id.fabCopy);
        fabShare=findViewById(R.id.fabShare);


        if (savedInstanceState==null){
            Bundle extras= getIntent().getExtras();

            if (extras==null) {
                id = Integer.parseInt(null);
            }else {
                id=extras.getInt("id");
            }
        }else{
            id=(int) savedInstanceState.getSerializable("id");
        }

        DbCurso dbCurso= new DbCurso(InformeActivity.this,"Cursos",0);
        c =dbCurso.verCurso(id);

        //asignacion de valores
        if (c !=null){
            txtMateria.setText(c.getMateria());
            txtClases.setText(String.valueOf(c.getClasesRegistradas()));
            txtGrado.setText(c.getGrado());
            txtNumero.setText(String.valueOf(c.getNumEstudiantes()));
            txtMateria.setInputType(InputType.TYPE_NULL);
            txtClases.setInputType(InputType.TYPE_NULL);
            txtGrado.setInputType(InputType.TYPE_NULL);
            txtNumero.setInputType(InputType.TYPE_NULL);

        }

        DbEstudiante dbEstudiante = new DbEstudiante(InformeActivity.this, c.getAcceso(), 0);
        arrayEstudiantes = dbEstudiante.mostrarEstudiantes();

        for (Estudiante e: arrayEstudiantes) {
            asistencia +=(int) e.getAsistencias();
            faltas += e.getFaltas();
            retraso += e.getRetrasos();
            excusa += e.getExcusas();
        }
        int asistenciasTotales=(int)c.getClasesRegistradas()*c.getNumEstudiantes();



        float porcentajeAsistencia=(float) asistencia/asistenciasTotales*100;
        float porcentajeRetraso=(float) retraso/asistencia*100;
        float porcentajeExcusa=(float) excusa/faltas*100;



        mensaje= String.format( "En el curso de %s con los estudiantes de %s he dictado %d clases. En estas obtuve una asistencia del %.2f%%. Sin embargo, %.2f%% de ellas fueron con retraso. Del %.2f%% de faltas el %.2f%% fueron excusadas",c.getMateria(),c.getGrado(),c.getClasesRegistradas(),porcentajeAsistencia,porcentajeRetraso,(1-porcentajeAsistencia/100),porcentajeExcusa);

        txtInfo.setText(mensaje);


        //Para copiar al portapapeles
        fabCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager myClipboard = myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData myClip;
                myClip = ClipData.newPlainText("text", txtInfo.getText().toString());
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(InformeActivity.this, "Copiado al Portapapeles", Toast.LENGTH_SHORT).show();
            }
        });

        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT,mensaje);
                startActivity(Intent.createChooser(share,"Compartir"));
            }
        });


    }

}