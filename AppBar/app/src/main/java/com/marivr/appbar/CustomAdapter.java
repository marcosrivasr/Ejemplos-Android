package com.marivr.appbar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marivr on 27/08/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private ArrayList<String> items;
    private Context context;

    public CustomAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(CustomViewHolder itemsViewHolder, int i) {
        itemsViewHolder.vTitle.setText(items.get(i));
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.filacustom, viewGroup, false);

        return new CustomViewHolder(itemView);
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView vTitle;

        public CustomViewHolder(View v) {
            super(v);
            vTitle = (TextView) v.findViewById(R.id.tvNombre);
        }
    }


}
