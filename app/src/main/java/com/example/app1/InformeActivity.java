package com.example.app1;

import static com.example.app1.entidades.helper.isAlpha;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app1.db.DbCurso;
import com.example.app1.entidades.Curso;

public class InformeActivity extends AppCompatActivity {

    EditText txtNombre,txtMateria;
    TextView  txtClases, txtGrado, txtNumero;

    Button btnInforme;
    int id=1;
    Curso curso;
    RadioGroup radioGroup;
    RadioButton radioMacho,radioHembra;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);

        txtMateria=findViewById(R.id.txtMateri);
        txtNombre=findViewById(R.id.txtNombre);
        txtClases=findViewById(R.id.txtClases);
        txtGrado=findViewById(R.id.txtGrado);
        txtNumero=findViewById(R.id.txtEstudiantes);
        btnInforme=findViewById(R.id.btnInforme);

        radioGroup=findViewById(R.id.radioGenero);
        radioHembra=findViewById(R.id.radioHembra);
        radioMacho=findViewById(R.id.radioMacho);



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
        curso=dbCurso.verCurso(id);

        //asignacion de valores
        if (curso!=null){
            txtMateria.setText(curso.getMateria());
            txtClases.setText(String.valueOf(curso.getClasesRegistradas()));
            txtGrado.setText(curso.getGrado());
            txtNumero.setText(String.valueOf(curso.getNumEstudiantes()));
            txtMateria.setInputType(InputType.TYPE_NULL);
            txtClases.setInputType(InputType.TYPE_NULL);
            txtGrado.setInputType(InputType.TYPE_NULL);
            txtNumero.setInputType(InputType.TYPE_NULL);

        }

        btnInforme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar()){

                }
            }
        });



    }

    public boolean validar(){
        boolean ret=true;
        try{
            //validacion de datos vacios
            if (txtNombre.getText().toString().isEmpty()){
                txtNombre.setError("Este campo no debe estar vacio");
                ret=false;
            }
            if(radioGroup.getCheckedRadioButtonId()==-1){
                Toast.makeText(this, "Debe elegir una opcion", Toast.LENGTH_SHORT).show();
                ret=false;
            }

        }catch (Exception e){

            return false;
        }


        return ret;
    }
}