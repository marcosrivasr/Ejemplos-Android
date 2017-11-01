package com.marivr.facebooksdk.Customs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marivr.facebooksdk.Amigo;

import com.marivr.facebooksdk.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by marivr on 27/08/2017.
 */

public class RecycleViewCustomAdapter extends RecyclerView.Adapter<RecycleViewCustomAdapter.CustomViewHolder>{

    private ArrayList<Amigo> amigos;
    private RecyclerViewClickListener mListener;
    private Context context;


    public static class CustomViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNombre;
        public CircleImageView ivFoto;

        CustomViewHolder(View vistaElemento){
            super(vistaElemento);

            ivFoto = (CircleImageView) vistaElemento.findViewById(R.id.ivFoto);
            tvNombre = (TextView) vistaElemento.findViewById(R.id.tvNombre);
        }
    }

    public RecycleViewCustomAdapter(ArrayList<Amigo> amigos, RecyclerViewClickListener listener, Context context){
        this.amigos = amigos;
        this.mListener = listener;
        this.context = context;
    }
    public void updateData(ArrayList<Amigo> dataset) {
        amigos.clear();
        amigos.addAll(dataset);
        notifyDataSetChanged();
    }

        @Override
    public int getItemCount() {
        return amigos.size();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //TODO: MODIFICAR: Cambiar layout de template
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.filacustom, viewGroup, false);
        //CustomViewHolder customViewHolder = new CustomViewHolder(v);
        //return customViewHolder;
        return new RowViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        /*
        //TODO: MODIFICAR: Asignar valores a campos
        holder.tvNombre.setText(amigos.get(position).getName());
        holder.tvCategoria.setText(amigos.get(position).getCategories().get(0).getName());
        //holder.ivFoto.setImageResource(v.get(position).getFotoId());
*/
        if (holder instanceof RowViewHolder) {
            final RowViewHolder rowHolder = (RowViewHolder) holder;
            //set values of data here
            holder.tvNombre.setText(amigos.get(position).getName());
            Picasso.with(context).load(amigos.get(position).getPicture().getData().getUrl()).into(holder.ivFoto);
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
