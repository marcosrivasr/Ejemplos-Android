/*
 * Copyright (C) 2017 Marcos Rivas Rojas
 *
 *
 */
package com.marivr.tiendita.Adaptadores.Productos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marivr.tiendita.Datos.Producto;
import com.marivr.tiendita.R;
import com.marivr.tiendita.Util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class RecyclerViewCustomAdapter extends
        RecyclerView.Adapter<RecyclerViewCustomAdapter.CustomViewHolder> {

    // TODO: (1) DECLARAR ESTRUCTURA DE DATOS
    private ArrayList<Producto> productos;

    // TODO: 11.- Creamos un miembro derivado de la interfaz creada
    private RecyclerViewClickListener listener;

    private Context context;


    // TODO: (2) CONSTRUCTOR
    // TODO: 12.- SE AÑADE AL CONSTRUCTOR EL LISTENER COMO PARÁMETRO
    public RecyclerViewCustomAdapter(ArrayList<Producto> fotos, Context context, RecyclerViewClickListener listener) {
        this.productos = fotos;
        this.listener = listener;
        this.context = context;
    }


    // TODO: (3) DEFINIMOS EL PATRÓN VIEWHOLDER CREANDO UNA CLASE ESTÁTICA PARA DEFINIR ELEMENTOS UI
    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNombre, tvPrecio;
        private CircleImageView ivFoto;

        CustomViewHolder(View vista){
            super(vista);

            tvNombre = (TextView) vista.findViewById(R.id.tvNombre);
            tvPrecio = (TextView) vista.findViewById(R.id.tvPrecio);
            ivFoto = (CircleImageView) vista.findViewById(R.id.ivFoto);
        }
    }

    // TODO: (4) SE IMPLEMENTAN MÉTODOS REQUERIDOS
    @Override
    public int getItemCount() {
        return productos.size();
    }

    // TODO: (5) EL MÉTODO SIRVE PARA COLOCAR VALORES
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        //holder.ivFoto.setImageResource(productos.get(position).getImagen());
        Picasso.with(context)
                .load(productos.get(position).getFoto())
                .resize(80, 80)
                .centerCrop()
                .into(holder.ivFoto);
        holder.tvNombre.setText(productos.get(position).getNombre());
        holder.tvPrecio.setText(Util.getDecimalFormat(productos.get(position).getPrecio()));
    }


    // TODO: (6) SE USA EL INFLATER PARA LINKEAR EL XML Y ASIGNARLE LOS ELEMENTOS DEL VIEWHOLDER
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.filacustom, parent, false);

        //CustomViewHolder customViewHolder = new CustomViewHolder(vista);
        //return customViewHolder;

        // TODO: 13.- Agregamos un objeto ComprasRowViewHolder y eliminamos las dos líneas anteriores
        return new RowViewHolder(vista, listener);
    }

}
