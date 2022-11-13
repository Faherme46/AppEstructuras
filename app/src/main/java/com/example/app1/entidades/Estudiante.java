package com.example.app1.entidades;

import java.util.ArrayList;

public class Estudiante {
    private int id;
    private int faltas;
    private int asistencias;
    private int retrasos;
    private int excusas;
    private String nombre;

    public  Estudiante(){
        this.faltas=0;
        this.asistencias=0;
        this.retrasos=0;
        this.excusas=0;
    }

    public int getId() {
        return id;
    }    public  void setId(int id) {
        this.id = id;
    }

    public int getFaltas() {
        return faltas;
    }    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public int getAsistencias() {
        return asistencias;
    }    public  void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getRetrasos() {
        return retrasos;
    }    public void setRetrasos(int retrasos) {
        this.retrasos = retrasos;
    }

    public int getExcusas() {
        return excusas;
    }    public  void setExcusas(int excusas) {
        this.excusas = excusas;
    }

    public String getNombre() {
        return nombre;
    }    public  void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Estudiante> generar(){

        ArrayList<Estudiante> e= new ArrayList<>();
        String[] lista={"Mateo","Marcos","Lucas","Paula","Camila","Andrea"};
        for (int i = 0; i <lista.length ; i++) {
            Estudiante n=new Estudiante();
            n.setNombre(lista[i]);
            e.add(n);
        }
        return e;
    }



}
