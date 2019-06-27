package com.example.nutricare.Utils;

import com.example.nutricare.Dieta.DietaFragment;
import com.example.nutricare.Dieta.InfoFragment;
import com.example.nutricare.Dieta.LstaPacientesFragment;
import com.example.nutricare.Dieta2.Dieta2Fragment;
import com.example.nutricare.Estadistica.EstadisticasFragment;

public interface iFragments extends DietaFragment.OnFragmentInteractionListener,
        EstadisticasFragment.OnFragmentInteractionListener,
        Dieta2Fragment.OnFragmentInteractionListener,
        InfoFragment.OnFragmentInteractionListener,
        LstaPacientesFragment.OnFragmentInteractionListener
{


}
