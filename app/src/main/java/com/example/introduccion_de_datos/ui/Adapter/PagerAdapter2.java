package com.example.introduccion_de_datos.ui.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.introduccion_de_datos.ui.EditarProfesores.EditaProfesores;
import com.example.introduccion_de_datos.ui.VerProfesores;

public class PagerAdapter2 extends FragmentStateAdapter {

    public PagerAdapter2(FragmentActivity fm) {
        super(fm);
    }


    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:{
                return new EditaProfesores();
            }
            case 1:{
                return new VerProfesores();
            }

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
