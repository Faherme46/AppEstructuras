package com.example.app1.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1.R;
import com.example.app1.entidades.Estudiante;

import java.util.ArrayList;

public class ListaEstudiantesAdapter extends RecyclerView.Adapter<ListaEstudiantesAdapter.EstudianteViewHolder> {


    public ArrayList<Estudiante> listaEstudiantes;
    private ArrayList<Estudiante> estudiantes;


    int value;

    public ListaEstudiantesAdapter(ArrayList<Estudiante> listaEstudiantes, int value){
        this.listaEstudiantes =listaEstudiantes;

        this.value=value;
    }
    @NonNull
    @Override
    public EstudianteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (value==0){
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_estudiante_llenar,null,false);

            return new EstudianteViewHolder(view);
        }else{

            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_estudiante,null,false);

            return new EstudianteViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull EstudianteViewHolder holder, int position) {
        holder.viewNombre.setText(listaEstudiantes.get(position).getNombre());


        if (value!=0){
            holder.viewFaltas.setText((String.valueOf(listaEstudiantes.get(position).getFaltas())));
            holder.viewAsistencias.setText(String.valueOf(listaEstudiantes.get(position).getAsistencias()));
        }else{
            holder.viewId.setText(String.valueOf(listaEstudiantes.get(position).getId()));
        }
        if (listaEstudiantes.size()==0){
            holder.viewNombre.setText("No hay estudiantes para mostrar");
        };

        setEstudiantes(holder.returnEstudiantes());

    }

    @Override
    public int getItemCount() {
        return listaEstudiantes.size();


    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }


    public class EstudianteViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewAsistencias, viewFaltas, viewId;
        RadioGroup group;
        RadioButton rFalta,rExcusa,retraso;

        public EstudianteViewHolder(@NonNull View itemView) {
            super(itemView);

            viewFaltas =itemView.findViewById(R.id.viewFaltas);
            viewId=itemView.findViewById(R.id.textviewId);
            viewAsistencias =itemView.findViewById(R.id.viewAsistencias);
            viewNombre =itemView.findViewById(R.id.viewNombre);
            retraso=itemView.findViewById(R.id.radioRetraso);
            rExcusa=itemView.findViewById(R.id.radioExcusa);
            rFalta=itemView.findViewById(R.id.radioFalta);


            if (value==0) {
                final int x=rFalta.getId();
                final int y=rFalta.getId();
                final int z=rFalta.getId();
                group = itemView.findViewById(R.id.group);

                group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {


                        int id = Integer.parseInt((String) viewId.getText());
                        Estudiante e = listaEstudiantes.get(id - 1);
                        if (i == x) {
                            e.setFaltas(e.getFaltas() + 1);
                            e.setChanged(1);
                        } else if (i == y) {
                            e.setRetrasos(e.getRetrasos() + 1);
                            e.setAsistencias(e.getAsistencias());
                        } else if (i == z){
                            e.setChanged(1);
                        e.setExcusas(e.getExcusas() + 1);
                        }
                        listaEstudiantes.set(id - 1, e);
                        }

                    }
                );
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //para ver cada estudiant

                }
            });
        }
        public ArrayList<Estudiante> returnEstudiantes(){
            return listaEstudiantes;
        }

    }
}
