package com.example.introduccion_de_datos.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.introduccion_de_datos.ui.Adapter.PagerAdapter2;
import com.example.introduccion_de_datos.databinding.FragmentSlideshowBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    private TabLayout tabs;
    private ViewPager2 viewPager2;
    private PagerAdapter2 pagerAdapter2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewPager2 = binding.viewPager2;
        pagerAdapter2 = new PagerAdapter2(this.getActivity());
        viewPager2.setAdapter(pagerAdapter2);

        tabs = binding.tabs;

        new TabLayoutMediator(tabs,viewPager2,(tab, position)->{
            if (position==0)tab.setText("Editar profesores");
            if (position==1)tab.setText("Ver profesores");
        }).attach();



        return root;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}