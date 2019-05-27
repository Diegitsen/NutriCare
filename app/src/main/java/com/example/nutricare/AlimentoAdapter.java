package com.example.nutricare;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AlimentoAdapter extends RecyclerView.Adapter<AlimentoAdapter.AlimentoHolder>
{

    List<Alimento> listaAlimento;

    public AlimentoAdapter(List<Alimento> listaAlimento) {
        this.listaAlimento = listaAlimento;
    }


    @NonNull
    @Override
    public AlimentoAdapter.AlimentoHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alimento,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new AlimentoHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AlimentoAdapter.AlimentoHolder holder, int i) {

        holder.tvNombre.setText(listaAlimento.get(i).getNombre().toString());
        holder.tvTipo.setText(listaAlimento.get(i).getTipo()+"");
        holder.tvInfo.setText(listaAlimento.get(i).getInfo().toString());
        if(listaAlimento.get(i).getTipo()==1)
            holder.ivImage.setImageResource(R.drawable.salad);
        else
            holder.ivImage.setImageResource(R.drawable.vegetables);
    }

    @Override
    public int getItemCount() {
        return listaAlimento.size();
    }

    public class AlimentoHolder extends RecyclerView.ViewHolder {

        TextView tvNombre,tvInfo,tvTipo;
        ImageView ivImage;

        public AlimentoHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre= (TextView) itemView.findViewById(R.id.tvNombre);
            tvInfo= (TextView) itemView.findViewById(R.id.tvInfo);
            tvTipo= (TextView) itemView.findViewById(R.id.tvTipo);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
