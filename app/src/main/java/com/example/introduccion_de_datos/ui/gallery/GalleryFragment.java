package com.example.introduccion_de_datos.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.introduccion_de_datos.CapturaDatos;
import com.example.introduccion_de_datos.ProfesorAdapter;
import com.example.introduccion_de_datos.R;
import com.example.introduccion_de_datos.databinding.FragmentGalleryBinding;
import com.example.introduccion_de_datos.db.entidades.Profesor;

import java.util.List;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    private OnListFragmentInteractionListener mListener;

    public TextView muestra;

    private String texto;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = root.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Profesor> profesores = ((CapturaDatos) getActivity()).mostrarDatos();
        ProfesorAdapter adapter = new ProfesorAdapter(profesores, mListener);
        recyclerView.setAdapter(adapter);


        //muestra=root.findViewById(R.id.Nombre);

        //mostrar();

        return root;
    }


    /*private void mostrar(){
        texto="";

        List<Profesor> profesores = ((CapturaDatos) getActivity()).mostrarDatos();
        for(Profesor p: profesores){
            //Log.d("prueba", p.getNombre());
            texto+="ID: "+p.getId_profesor()+"\nNombre: "+p.getNombre()+"\nApellido: "+p.getApellido()+"\nEdad: "+p.getEdad()+"\n-------------------\n";
        }

        muestra.setText(texto);
    }*/

/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            cp = (CapturaDatos) context;
            //List<Profesor> profesores= cp.mostrarDatos();



            /*String texto="";

            for(Persona p: personas){
                Log.d("prueba", p.getNombre());
                texto+=p.getNombre()+"\n";
            }

            //muestra.setText(texto);

            Log.d("prueba", texto);


        } catch (ClassCastException e) {

            int x=1;

        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " Se debe implementar OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Profesor item);


    }

}