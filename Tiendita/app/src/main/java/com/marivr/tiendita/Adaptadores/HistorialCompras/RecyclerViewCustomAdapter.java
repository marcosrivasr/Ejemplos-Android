/*
 * Copyright (C) 2017 Marcos Rivas Rojas
 *
 *
 */
package com.marivr.tiendita.Adaptadores.HistorialCompras;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marivr.tiendita.Datos.Compra;
import com.marivr.tiendita.Datos.ComprasRegistro;
import com.marivr.tiendita.R;
import com.marivr.tiendita.Util.Util;

import java.util.ArrayList;


public class RecyclerViewCustomAdapter extends RecyclerView.Adapter<RecyclerViewCustomAdapter.CustomViewHolder> {

    private ArrayList<ComprasRegistro> compras;
    private RecyclerViewClickListener listener;
    private Context context;


    public RecyclerViewCustomAdapter(ArrayList<ComprasRegistro> compras, Context context, RecyclerViewClickListener listener) {
        this.compras = compras;
        this.listener = listener;
        this.context = context;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNombre, tvCantidad, tvTotal;

        CustomViewHolder(View vista){
            super(vista);

            tvNombre = (TextView) vista.findViewById(R.id.tvNombre);
            tvCantidad = (TextView) vista.findViewById(R.id.tvCantidad);
            tvTotal = (TextView) vista.findViewById(R.id.tvTotal);
        }
    }

    @Override
    public int getItemCount() {
        return compras.size();
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.tvNombre.setText(compras.get(position).getNombre());
        holder.tvCantidad.setText(compras.get(position).getElementos() + " elementos");
        holder.tvTotal.setText(Util.getDecimalFormat(compras.get(position).getTotal()));
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.historial_compras_filacustom, parent, false);

        return new RowViewHolder(vista, listener);
    }

}
