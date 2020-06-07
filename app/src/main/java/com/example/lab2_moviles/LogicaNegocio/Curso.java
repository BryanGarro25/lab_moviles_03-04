package com.example.lab2_moviles.LogicaNegocio;

import java.io.Serializable;

public class Curso implements Serializable{
    private int id;
    private String codigo;
    private String nombre;
    private int creditos;
    private int horas;

    public Curso(String codigo, String nombre, int creditos, int horas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horas = horas;
    }

    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos=0;
        this.horas=0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return nombre;
        /*
        return "Curso{" +
                "nombre='" + nombre + ''' +
                ", codigo='" + codigo + ''' +
                ", creditos=" + creditos +
                ", horas=" + horas +
                '}';
                */
    }

}