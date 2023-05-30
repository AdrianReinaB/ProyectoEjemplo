package com.example.introduccion_de_datos.ui.AgregarDatos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.introduccion_de_datos.CapturaDatos;
import com.example.introduccion_de_datos.R;
import com.example.introduccion_de_datos.databinding.FragmentHomeBinding;
import com.example.introduccion_de_datos.db.entidades.Profesor;

public class AgregaProfesor extends Fragment {

    private FragmentHomeBinding binding;

    private TextView nombre;

    private TextView apellido;

    private TextView edad;

    private Button bAlta;

    private Button bBorrado;

    private CapturaDatos listener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View view=inflater.inflate(R.layout.fragment_home, container, false);

        nombre=root.findViewById(R.id.nom);
        apellido=root.findViewById(R.id.ape);
        edad=root.findViewById(R.id.edad);

        nombre.setText("");
        apellido.setText("");
        edad.setText("");

        bAlta=root.findViewById(R.id.btBuscar);
        bBorrado=root.findViewById(R.id.btBorraP);



        bBorrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrar(view);
            }
        });

        bAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre.getText().toString().isEmpty() || apellido.getText().toString().isEmpty() || edad.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Falta algun campo por rellenar",Toast.LENGTH_SHORT).show();
                }else {
                    Profesor profesor=new Profesor(
                            nombre.getText().toString(),
                            apellido.getText().toString(),
                            Integer.parseInt(edad.getText().toString()));

                    listener.creaProf(profesor);
                }

            }
        });

        return root;
    }

    public void borrar(View view){
        nombre.setText("");
        apellido.setText("");
        edad.setText("");
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (CapturaDatos) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " Se debe implementar " +
                    "OnClickBotonDia");
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}