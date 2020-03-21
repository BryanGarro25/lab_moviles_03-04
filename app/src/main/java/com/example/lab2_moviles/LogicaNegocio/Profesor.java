package com.example.lab2_moviles.LogicaNegocio;

import java.io.Serializable;
public class Profesor implements Serializable{
    private String cedula;
    private String nombre;
    private String email;
    private int telefono;

    public Profesor(String cedula, String nombre, String email, int telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profesor)) return false;

        Profesor profesor = (Profesor) o;

        if (getTelefono() != profesor.getTelefono()) return false;
        if (getCedula() != null ? !getCedula().equals(profesor.getCedula()) : profesor.getCedula() != null)
            return false;
        if (getNombre() != null ? !getNombre().equals(profesor.getNombre()) : profesor.getNombre() != null)
            return false;
        return getEmail() != null ? getEmail().equals(profesor.getEmail()) : profesor.getEmail() == null;
    }

    @Override
    public int hashCode() {
        int result = getCedula() != null ? getCedula().hashCode() : 0;
        result = 31 * result + (getNombre() != null ? getNombre().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + getTelefono();
        return result;
    }
}
