package com.example.lab2_moviles.LogicaNegocio;
import java.io.Serializable;

public class Usuario implements Serializable{
    private String correo;
    private String contraseña;
    public Usuario() {
        this.correo = "none";
        this.contraseña = "none";

    }
    public Usuario(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;

    }
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "correo='" + correo + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}
