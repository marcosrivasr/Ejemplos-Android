/*
 * Copyright (C) 2017 Marcos Rivas Rojas
 *
 *
 */
package com.marivr.basededatos.Adaptadores;

import android.view.View;


public class RowViewHolder
        extends RecyclerViewCustomAdapter.CustomViewHolder
        implements View.OnClickListener {

    private RecyclerViewClickListener listener;

    public RowViewHolder(View itemView, RecyclerViewClickListener listener) {
        super(itemView);
        this.listener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition());
    }
}
