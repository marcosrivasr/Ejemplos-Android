/*
 * Copyright (C) 2017 Marcos Rivas Rojas
 *
 *
 */
package com.marivr.pendientes.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.marivr.pendientes.Main;
import com.marivr.pendientes.Model.Pendiente;
import com.marivr.pendientes.R;

import java.util.ArrayList;


public class RecyclerViewCustomAdapter extends
        RecyclerView.Adapter<RecyclerViewCustomAdapter.CustomViewHolder> {

    private ArrayList<Pendiente> items;

    private RecyclerViewClickListener listener;

    private Context context;


    public RecyclerViewCustomAdapter(ArrayList<Pendiente> items, Context context, RecyclerViewClickListener listener) {
        this.items = items;
        this.listener = listener;
        this.context = context;
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTexto, tvFecha, tvPrioridad;
        private Button bEliminar;
        private RadioButton rbCompletar;

        CustomViewHolder(View vista){
            super(vista);

            tvTexto = (TextView) vista.findViewById(R.id.tvText);
            tvFecha = (TextView) vista.findViewById(R.id.tvFecha);
            tvPrioridad = (TextView) vista.findViewById(R.id.tvPrioridad);
            bEliminar = (Button) vista.findViewById(R.id.bEliminar);
            rbCompletar = (RadioButton) vista.findViewById(R.id.rbCompletar);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        holder.tvTexto.setText(items.get(position).getTexto());
        holder.tvFecha.setText("Recordarme el " + items.get(position).getFecha().toString());

        switch (items.get(position).getPrioridad()){
            case 0:
                holder.tvPrioridad.setBackgroundColor(ContextCompat.getColor(context, R.color.prioridadAlta));
                break;
            case 1:
                holder.tvPrioridad.setBackgroundColor(ContextCompat.getColor(context, R.color.prioridadMedia));
                break;
            case 2:
                holder.tvPrioridad.setBackgroundColor(ContextCompat.getColor(context, R.color.prioridadBaja));
                break;
        }

        holder.bEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //eliminarItem(position);
                Main activity = (Main) context;
                activity.eliminarItem(position);
            }
        });

        holder.rbCompletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.get(position).setCompleta(true);
                Main activity = (Main) context;
                activity.completarItem(position);
            }
        });
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.filacustom, parent, false);
        return new RowViewHolder(vista, listener);
    }

}
