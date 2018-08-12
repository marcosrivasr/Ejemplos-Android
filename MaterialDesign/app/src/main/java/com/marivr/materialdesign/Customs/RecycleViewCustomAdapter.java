package com.marivr.materialdesign.Customs;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marivr.materialdesign.Interfaces.OnItemClickListener;
import com.marivr.materialdesign.Modelo.Perfil;
import com.marivr.materialdesign.R;

import java.util.ArrayList;

/**
 * Created by marivr on 27/08/2017.
 */

public class RecycleViewCustomAdapter extends RecyclerView.Adapter<RecycleViewCustomAdapter.PerfilViewHolder>{

    private ArrayList<Perfil> perfiles;

    public static class PerfilViewHolder extends RecyclerView.ViewHolder{

        private CardView card;
        private ImageView ivFoto;
        private TextView tvNombre;

        PerfilViewHolder(View vistaElemento){
            super(vistaElemento);

            card = (CardView) vistaElemento.findViewById(R.id.cardView);
            ivFoto = (ImageView) vistaElemento.findViewById(R.id.ivFoto);
            tvNombre = (TextView) vistaElemento.findViewById(R.id.tvNombre);
        }

    }

    public RecycleViewCustomAdapter(ArrayList<Perfil> perfiles){
        this.perfiles = perfiles;

    }

    @Override
    public int getItemCount() {
        return perfiles.size();
    }
    @Override
    public PerfilViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customcard, viewGroup, false);
        PerfilViewHolder perfilViewHolder = new PerfilViewHolder(v);
        return perfilViewHolder;
    }

    @Override
    public void onBindViewHolder(PerfilViewHolder holder, int position) {
        holder.tvNombre.setText(perfiles.get(position).getNombre());
        holder.ivFoto.setImageResource(perfiles.get(position).getFotoId());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
