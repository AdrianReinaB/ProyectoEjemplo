package com.example.introduccion_de_datos.db.conexiondb;


import com.google.firebase.firestore.FirebaseFirestore;

public class Conexion_db_firebase {


    static FirebaseFirestore db;

    public static FirebaseFirestore getConexion() {
        db=FirebaseFirestore.getInstance();
        return db;
    }

}
