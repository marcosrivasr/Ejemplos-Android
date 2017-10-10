/*
 * Copyright (C) 2017 Marcos Rivas Rojas
 *
 *
 */
package com.marivr.basededatos.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marivr.basededatos.Datos.Alumno;
import com.marivr.basededatos.R;

import java.util.ArrayList;


public class RecyclerViewCustomAdapter extends
        RecyclerView.Adapter<RecyclerViewCustomAdapter.CustomViewHolder> {

    private ArrayList<Alumno> items;
    private RecyclerViewClickListener listener;
    private Context context;

    public RecyclerViewCustomAdapter(ArrayList<Alumno> items, Context context, RecyclerViewClickListener listener) {
        this.items = items;
        this.listener = listener;
        this.context = context;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMatricula, tvNombre;

        CustomViewHolder(View vista){
            super(vista);

            tvNombre = (TextView) vista.findViewById(R.id.tvNombre);
            tvMatricula = (TextView) vista.findViewById(R.id.tvMatricula);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.tvNombre.setText(items.get(position).getNombre());
        holder.tvMatricula.setText(items.get(position).getId());
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.filacustom, parent, false);

        return new RowViewHolder(vista, listener);
    }

}
