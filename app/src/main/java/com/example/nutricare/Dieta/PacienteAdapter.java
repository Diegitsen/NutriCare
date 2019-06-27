package com.example.nutricare.Dieta;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nutricare.Login.Paciente;
import com.example.nutricare.MainActivity;
import com.example.nutricare.R;

import java.util.List;

public class PacienteAdapter extends RecyclerView.Adapter<PacienteAdapter.PacienteHolder>
{
    List<Paciente> listaPacientes;

    public PacienteAdapter(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }


    @NonNull
    @Override
    public PacienteAdapter.PacienteHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paciente,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new PacienteHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PacienteAdapter.PacienteHolder holder, final int i) {

        holder.tvNombre.setText(listaPacientes.get(i).getNombre() + " " + listaPacientes.get(i).getApellido());
        holder.tvPeso.setText("Peso: " + listaPacientes.get(i).getPeso() );
        holder.tvEdad.setText("Edad: " + listaPacientes.get(i).getEdad() );

        holder.ll_item_paciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment frag = new DietaFragment();

                Bundle b = new Bundle();
                b.putInt("ID_USUARIO", listaPacientes.get(i).getIdPaciente());
                frag.setArguments(b);

                ((MainActivity)view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return listaPacientes.size();
    }

    public class PacienteHolder extends RecyclerView.ViewHolder
    {
        TextView tvNombre, tvPeso, tvEdad;
        LinearLayout ll_item_paciente;


        public PacienteHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEdad = itemView.findViewById(R.id.tvEdad);
            tvPeso = itemView.findViewById(R.id.tvPeso);
            ll_item_paciente = itemView.findViewById(R.id.ll_item_paciente);
        }
    }
}
