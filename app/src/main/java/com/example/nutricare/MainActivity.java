package com.example.nutricare;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.nutricare.Dieta.DietaFragment;
import com.example.nutricare.Dieta2.Dieta2Fragment;
import com.example.nutricare.Estadistica.EstadisticasFragment;
import com.example.nutricare.Utils.iFragments;

public class MainActivity extends AppCompatActivity implements iFragments {

    int idUsuario, idTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle b = getIntent().getExtras();
        idUsuario =  b.getInt("ID_USUARIO",-1);

        Bundle b2 = getIntent().getExtras();
        idTipo =  b2.getInt("ID_TIPO",-1);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        DietaFragment dietaFragment = new DietaFragment();
        Dieta2Fragment dieta2Fragment = new Dieta2Fragment();

        Bundle bundle = new Bundle();
        bundle.putInt("ID_USUARIO", idUsuario);
        dietaFragment.setArguments(bundle);

        Bundle bundle2 = new Bundle();
        bundle2.putInt("ID_TIPO", idTipo);
        dietaFragment.setArguments(bundle2);

        Toast.makeText(this, "Bienvenido " + idTipo, Toast.LENGTH_SHORT).show();

        if(idTipo==1)
            getSupportFragmentManager().beginTransaction().add(R.id.container, dietaFragment).commit();
        else if(idTipo==2)
            getSupportFragmentManager().beginTransaction().add(R.id.container, dieta2Fragment).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        Fragment frag = null;
        boolean fragmentSeleccionado = false;

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dieta:
                    if(idTipo==1)
                        frag = new DietaFragment();
                    else if(idTipo==2)
                        frag = new Dieta2Fragment();

                    Bundle bundle = new Bundle();
                    bundle.putInt("ID_USUARIO", idUsuario);
                    frag.setArguments(bundle);

                    fragmentSeleccionado = true;
                    break;


                case R.id.navigation_estadistica:
                    frag = new EstadisticasFragment();
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("ID_USUARIO", idUsuario);
                    frag.setArguments(bundle2);
                    fragmentSeleccionado = true;
                    break;
            }

            if(fragmentSeleccionado==true)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
            }
            return true;
        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    public void pushFabButton(View v)
    {
        Toast.makeText(this, "Hey", Toast.LENGTH_SHORT).show();
    }
}
