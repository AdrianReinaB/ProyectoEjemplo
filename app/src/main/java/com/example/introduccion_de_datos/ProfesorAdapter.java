package com.example.introduccion_de_datos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.introduccion_de_datos.db.entidades.Profesor;
import com.example.introduccion_de_datos.ui.gallery.GalleryFragment;

import java.util.List;

public class ProfesorAdapter extends RecyclerView.Adapter<ProfesorAdapter.ViewHolder> {

    private List<Profesor> mProfesores;
    private final GalleryFragment.OnListFragmentInteractionListener mListener;

    public ProfesorAdapter(List<Profesor> profesores, GalleryFragment.OnListFragmentInteractionListener listener) {
        mProfesores = profesores;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_profesor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Profesor profesor = mProfesores.get(position);
        holder.idTextView.setText("ID: "+profesor.getId_profesor());
        holder.nombreTextView.setText("Nombre: "+profesor.getNombre());
        holder.apellidoTextView.setText("Apellido:"+profesor.getApellido());
        holder.edadTextView.setText("Edad: "+String.valueOf(profesor.getEdad()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(profesor);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProfesores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TextView idTextView;
        public TextView nombreTextView;
        public TextView apellidoTextView;
        public TextView edadTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            idTextView=view.findViewById(R.id.id);
            nombreTextView = view.findViewById(R.id.Nombre);
            apellidoTextView = view.findViewById(R.id.Apellido);
            edadTextView = view.findViewById(R.id.Edad);
        }
    }
}

