package com.example.introduccion_de_datos.db.conexiondb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.introduccion_de_datos.db.InterfazDao.InterfaceDaoProfesor;
import com.example.introduccion_de_datos.db.entidades.Profesor;

@Database(entities = {Profesor.class}, version = 1)
public abstract class Conexion_db_Room extends RoomDatabase {

    public static Conexion_db_Room INSTANCE;

    public abstract InterfaceDaoProfesor daoProfesor();

    public static Conexion_db_Room getConexion(Context context){

        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), Conexion_db_Room.class, "centro").allowMainThreadQueries().build();
        }

        return INSTANCE;
    }

    public void destroyInstance(){
        INSTANCE=null;
    }
}
