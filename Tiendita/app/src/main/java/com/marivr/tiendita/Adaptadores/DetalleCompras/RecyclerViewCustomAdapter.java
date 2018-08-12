/*
 * Copyright (C) 2017 Marcos Rivas Rojas
 *
 *
 */
package com.marivr.tiendita.Adaptadores.DetalleCompras;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marivr.tiendita.Datos.Compra;
import com.marivr.tiendita.Datos.CompraRegistro;
import com.marivr.tiendita.R;
import com.marivr.tiendita.Util.Util;

import java.util.ArrayList;


public class RecyclerViewCustomAdapter extends
        RecyclerView.Adapter<RecyclerViewCustomAdapter.CustomViewHolder> {

    private ArrayList<CompraRegistro> compras;
    private RecyclerViewClickListener listener;
    private Context context;

    public RecyclerViewCustomAdapter(ArrayList<CompraRegistro> compras, Context context, RecyclerViewClickListener listener) {
        this.compras = compras;
        this.listener = listener;
        this.context = context;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNombre, tvPrecio, tvCantidad, tvTotal;

        CustomViewHolder(View vista){
            super(vista);

            tvNombre = (TextView) vista.findViewById(R.id.tvNombre);
            tvPrecio = (TextView) vista.findViewById(R.id.tvPrecio);
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
        holder.tvPrecio.setText(Util.getDecimalFormat(compras.get(position).getPrecio()));
        holder.tvCantidad.setText(String.valueOf(compras.get(position).getCantidad()) + " de ");
        holder.tvTotal.setText(Util.getDecimalFormat(compras.get(position).getPrecio() * compras.get(position).getCantidad()));
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.compras_filacustom, parent, false);

        return new RowViewHolder(vista, listener);
    }

}
