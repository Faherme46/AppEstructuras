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


    ArrayList<Estudiante> listaEstudiantes;
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
        holder.viewAsistencias.setText(String.valueOf(listaEstudiantes.get(position).getAsistencias()));}
        if (listaEstudiantes.size()==0){
            holder.viewNombre.setText("No hay estudiantes para mostrar");
        };
    }

    @Override
    public int getItemCount() {
        return listaEstudiantes.size();


    }

    public class EstudianteViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewAsistencias, viewFaltas;
        RadioGroup group;
        RadioButton falta,retraso,excusa;

        public EstudianteViewHolder(@NonNull View itemView) {
            super(itemView);

            viewFaltas =itemView.findViewById(R.id.viewFaltas);
            viewAsistencias =itemView.findViewById(R.id.viewAsistencias);
            viewNombre =itemView.findViewById(R.id.viewNombre);
            group=itemView.findViewById(R.id.group);
            falta=itemView.findViewById(R.id.radioFalta);
            retraso=itemView.findViewById(R.id.radioRetraso);
            excusa=itemView.findViewById(R.id.radioExcusa);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //para ver cada estudiante

                }
            });
        }
    }
}
