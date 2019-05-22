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

public class MainActivity extends AppCompatActivity implements iFragments {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        DietaFragment dietaFragment = new DietaFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container, dietaFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        Fragment frag = null;
        boolean fragmentSeleccionado = false;

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dieta:
                    frag = new DietaFragment();
                    fragmentSeleccionado = true;
                    break;


                case R.id.navigation_estadistica:
                    frag = new EstadisticasFragment();
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
