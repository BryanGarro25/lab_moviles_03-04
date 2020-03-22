package com.example.lab2_moviles.AccesoDatos;

import com.example.lab2_moviles.LogicaNegocio.Profesor;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Profesor> profesorList;
    public Data() {
        profesorList = new ArrayList<>();
        this.prepareProfesorData();
    }
    public void prepareProfesorData() {
        Profesor profesor = new Profesor("123", "Jose", "@jose", 678);
        profesorList.add(profesor);

        profesor = new Profesor("234", "Juan", "@juan", 876);
        profesorList.add(profesor);

        profesor = new Profesor("345", "Mario", "@mario", 789);
        profesorList.add(profesor);

        profesor = new Profesor("456", "Jesus", "@Jesus", 978);
        profesorList.add(profesor);
    }
    public List<Profesor> getProfesorList() {
        return profesorList;
    }
    public void setProfesorList(List<Profesor> profesorList) {
        this.profesorList = profesorList;
    }
}
