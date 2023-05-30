package com.example.introduccion_de_datos;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.example.introduccion_de_datos.db.InterfazDao.DaoProfesorFB;
import com.example.introduccion_de_datos.db.conexiondb.Conexion_db_Room;
import com.example.introduccion_de_datos.db.conexiondb.Conexion_db_firebase;
import com.example.introduccion_de_datos.db.entidades.Profesor;
import com.example.introduccion_de_datos.ui.VistaDeDatos.VerDatos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.introduccion_de_datos.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CapturaDatos, VerDatos.OnListFragmentInteractionListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    DaoProfesorFB fb=new DaoProfesorFB();

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewSetup();
    }

    public boolean hayConexion(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean conexion;
        if (networkInfo != null && networkInfo.isConnected()) {
            return conexion=true;
            // Si hay conexión a Internet en este momento
        } else {
            return conexion=false;
            // No hay conexión a Internet en este momento
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void creaProf(Profesor profesor) {
        //Toast.makeText(this,profesor.getNombre(), Toast.LENGTH_LONG).show();
            //Conexion_db_Room.getConexion(getApplicationContext()).daoProfesor().crearProfesor(profesor);

        if (hayConexion()){

            fb.crearProfesor(profesor);
            Conexion_db_Room.getConexion(getApplicationContext()).daoProfesor().crearProfesor(profesor);

            }else{

            Toast.makeText(this,"No hay conexion", Toast.LENGTH_LONG).show();
            Conexion_db_Room.getConexion(getApplicationContext()).daoProfesor().crearProfesor(profesor);

            }
    }

    @Override
    public List<Profesor> mostrarDatos() {
        //Room
        List<Profesor> profesores=Conexion_db_Room.getConexion(getApplicationContext()).daoProfesor().verPorfesores();
        //Firestore
        List<Profesor> profesores2=fb.verPorfesores();
        if (profesores2.size()==0 || !hayConexion()){
            return profesores;
        }else{
            return profesores2;
        }
    }

    @Override
    public void borraDato(Profesor profesor) {
        List<Profesor> profesores= Conexion_db_Room.getConexion(getApplicationContext()).daoProfesor().verPorfesores();

        for (Profesor p: profesores){
            if (p.getNombre().equalsIgnoreCase(profesor.getNombre())){
                Conexion_db_Room.getConexion(getApplicationContext()).daoProfesor().borrarProfesor(p);
            }
        }

    }

    @Override
    public void actualizaDato(Profesor profesor) {
        List<Profesor> profesores= Conexion_db_Room.getConexion(getApplicationContext()).daoProfesor().verPorfesores();

        for (Profesor p: profesores){
            if (p.getNombre().equalsIgnoreCase(profesor.getNombre())){
                p.setApellido(profesor.getApellido());
                p.setEdad(profesor.getEdad());
                p.setNombre(profesor.getNombre());
                Conexion_db_Room.getConexion(getApplicationContext()).daoProfesor().modificarProfesor(p);
            }
        }
    }


    private void viewSetup(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        db = Conexion_db_firebase.getConexion();
    }


    @Override
    public void onListFragmentInteraction(Profesor item) {
        Toast.makeText(this,item.getNombre(),Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profesor");

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.editar_profe_recycler, null);
        builder.setView(dialogView);

        EditText eNombre=dialogView.findViewById(R.id.editNom);
        EditText eApellido=dialogView.findViewById(R.id.editApe);
        EditText eEdad=dialogView.findViewById(R.id.editEdad);

        eNombre.setText(item.getNombre());
        eApellido.setText(item.getApellido());
        eEdad.setText(String.valueOf(item.getEdad()));

        if(hayConexion()) {
            builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    item.setNombre(eNombre.getText().toString());
                    item.setApellido(eApellido.getText().toString());
                    item.setEdad(Integer.parseInt(eEdad.getText().toString()));

                    fb.modificarProfesor(item);
                    //Conexion_db_Room.getConexion(getApplicationContext()).daoProfesor().modificarProfesor(item);

                    Conexion_db_Room.getConexion(getApplicationContext()).daoProfesor().verPorfesores();
                    Toast.makeText(MainActivity.this, "Profesor actualizado", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("Borrar", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    fb.buscaProfesor(item);
                }
            });

        }
            builder.setNeutralButton("Cancelar", null);


        AlertDialog dialog = builder.create();
        dialog.show();
     }
}