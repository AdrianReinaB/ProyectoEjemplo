package com.example.introduccion_de_datos.db.InterfazDao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.introduccion_de_datos.db.entidades.Profesor;

import java.util.List;

@Dao
public interface InterfaceDaoProfesor {

    @Insert
    public void crearProfesor(Profesor pro);

    @Delete
    public void borrarProfesor(Profesor pro);

    @Update
    public void modificarProfesor(Profesor pro);

    @Query("SELECT * FROM profesores WHERE id_profesor LIKE :id")
    public Profesor verProfesorId(int id);

    @Query("SELECT * FROM profesores")
    public List<Profesor> verPorfesores();

    @Query("DELETE from profesores")
    public void borrarProfesores();

}
