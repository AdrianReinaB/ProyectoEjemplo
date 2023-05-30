package com.example.introduccion_de_datos.db.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName="Profesores")
public class Profesor {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_profesor")
    private int id_profesor;

    @ColumnInfo(name="nombre")
    private String nombre;

    @ColumnInfo(name="apellido")
    private String apellido;

    @ColumnInfo(name="edad")
    private int edad;


    public Profesor() {
    }

    public Profesor(String nombre) {
        this.nombre = nombre;
    }

    public Profesor(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }


    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profesor profesor = (Profesor) o;
        return edad == profesor.edad && Objects.equals(nombre, profesor.nombre) && Objects.equals(apellido, profesor.apellido);
    }
}
