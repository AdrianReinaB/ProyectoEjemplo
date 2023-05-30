package com.example.introduccion_de_datos;

import com.example.introduccion_de_datos.db.entidades.Profesor;

import java.util.List;

public interface CapturaDatos {
    public void creaProf(Profesor profesor);
    public List<Profesor> mostrarDatos();
    public void borraDato(Profesor profesor);
    public void actualizaDato(Profesor profesor);
}
