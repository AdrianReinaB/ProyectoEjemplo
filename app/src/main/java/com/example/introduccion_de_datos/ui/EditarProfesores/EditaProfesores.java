package com.example.introduccion_de_datos.ui.EditarProfesores;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.introduccion_de_datos.R;
import com.example.introduccion_de_datos.db.InterfazDao.DaoProfesorFB;
import com.example.introduccion_de_datos.db.entidades.Profesor;


public class EditaProfesores extends Fragment {



    private TextView nom, ape, edad;

    private Button edita;

    private DaoProfesorFB daoFb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_profesores, container, false);

        nom=(TextView) view.findViewById(R.id.EditNombre);
        ape=(TextView) view.findViewById(R.id.EditApellido);
        edad=(TextView) view.findViewById(R.id.EditEdad);

        edita=(Button) view.findViewById(R.id.EditarProf);

        edita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daoFb.modificarProfesor(new Profesor(nom.toString(), ape.toString(), Integer.parseInt(edad.toString())), "profe ");

            }
        });

        return view;

    }
}