package com.example.lab2_moviles.AccesoDatos;

import com.example.lab2_moviles.LogicaNegocio.Profesor;
import com.example.lab2_moviles.LogicaNegocio.Curso;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Profesor> profesorList;
    private List<Curso> cursoList;
    public Data() {
        profesorList = new ArrayList<>();
        this.prepareProfesorData();
        cursoList = new ArrayList<>();
        this.prepareCursoData();
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
    public void prepareCursoData() {
       /* Curso curso = new Curso("ST", "Soporte", 3, 4,0);
        cursoList.add(curso);
        curso = new Curso("FD", "Fundamentos", 3, 4,0);
        cursoList.add(curso);
        curso = new Curso("PG1", "Programacion I", 3, 4,0);
        cursoList.add(curso);
        curso = new Curso("PG2", "Programacion II", 3, 4,0);
        cursoList.add(curso);
        curso = new Curso("PG3", "Programacion III", 3, 4);
        cursoList.add(curso);
        curso = new Curso("PG4", "Programacion IV", 3, 4);
        cursoList.add(curso);
        curso = new Curso("EDA", "Estructuras Datos", 3, 4);
        cursoList.add(curso);
        curso = new Curso("EDI", "Estructuras Discretas", 3, 4);
        cursoList.add(curso);*/
    }
    public List<Profesor> getProfesorList() {
        return profesorList;
    }
    public void setProfesorList(List<Profesor> profesorList) {
        this.profesorList = profesorList;
    }
    public List<Curso> getCursoList() {
        return cursoList;
    }
    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }
}
