/*
 * Copyright (C) 2017 Marcos Rivas Rojas
 *
 *
 */
package com.marivr.dbazure.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marivr.dbazure.R;
import com.marivr.dbazure.TodoItem;

import java.util.ArrayList;


public class RecyclerViewCustomAdapter extends
        RecyclerView.Adapter<RecyclerViewCustomAdapter.CustomViewHolder> {

    private ArrayList<TodoItem> items;

    private RecyclerViewClickListener listener;

    private Context context;


    public RecyclerViewCustomAdapter(ArrayList<TodoItem> items, Context context, RecyclerViewClickListener listener) {
        this.items = items;
        this.listener = listener;
        this.context = context;
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNombre;

        CustomViewHolder(View vista){
            super(vista);

            tvNombre = (TextView) vista.findViewById(R.id.tvNombre);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.tvNombre.setText(items.get(position).getText());
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.filacustom, parent, false);
        return new RowViewHolder(vista, listener);
    }

}
