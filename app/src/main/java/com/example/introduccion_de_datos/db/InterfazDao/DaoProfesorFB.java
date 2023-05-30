package com.example.introduccion_de_datos.db.InterfazDao;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.introduccion_de_datos.db.conexiondb.Conexion_db_firebase;
import com.example.introduccion_de_datos.db.entidades.Profesor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoProfesorFB implements InterfaceDaoProfesor{


    FirebaseFirestore db= Conexion_db_firebase.getConexion();
    List<Profesor> datoProf = new ArrayList<>();



    @Override
    public void crearProfesor(Profesor pro) {
        Map<String, Object> prof = new HashMap<>();

        prof.put("ID", pro.getId_profesor());
        prof.put("Nombre", pro.getNombre());
        prof.put("Apellido", pro.getApellido());
        prof.put("Edad", pro.getEdad());

        db.collection("Profesores")
                .add(prof);

    }

    @Override
    public void borrarProfesor(Profesor pro) {

    }

    public void borrarProfesor(String documento){
        DocumentReference docRef = db.collection("Profesores").document(documento);
        docRef.delete();
        Log.d("dato","Dato borrado");
    }


    public void buscaProfesor(Profesor pro) {

        CollectionReference coleccion = db.collection("Profesores");

        Query consulta = coleccion.whereEqualTo("Nombre", pro.getNombre())
                .whereEqualTo("Apellido", pro.getApellido())
                .whereEqualTo("Edad", pro.getEdad())
                .whereEqualTo("ID", pro.getId_profesor());

        consulta.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                String idDocu="";
                if (task.isSuccessful()) {
                    Log.d("docu", "Dato encontrado");
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("dato", document.getId() + " => " + document.getData());
                        idDocu=document.getId();
                    }
                    borrarProfesor(idDocu);
                } else {
                    Log.d("docu", "Dato no encontrado");
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }

    @Override
    public void modificarProfesor(Profesor pro) {

    }

    public void modificarProfesor(Profesor pro, String docu) {
        Map<String, Object> data = new HashMap<>();
        data.put("Nombre", pro.getNombre());
        data.put("Apellido", pro.getApellido());
        data.put("Edad", pro.getEdad());

        db.collection("Profesores").document(docu)
                .set(data, SetOptions.merge());
        /*DocumentReference washingtonRef = db.collection("Profesores").document(docu);
        washingtonRef
                .update("Nombre", pro.getNombre())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });*/
    }

    @Override
    public Profesor verProfesorId(int id) {
        return null;
    }

    @Override
    public List<Profesor> verPorfesores() {

        db.collection("Profesores").orderBy("Nombre", Query.Direction.DESCENDING).limit(10)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                datoProf.add(new Profesor(document.getString("Nombre"), document.getString("Apellido"),  document.getLong("Edad").intValue()));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                    }

                });

        return datoProf;

    }

    @Override
    public void borrarProfesores() {

    }
}
/*
.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
    ss                }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
 */