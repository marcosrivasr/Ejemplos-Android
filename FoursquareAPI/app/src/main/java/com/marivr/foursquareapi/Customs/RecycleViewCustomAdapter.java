package com.marivr.foursquareapi.Customs;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.marivr.foursquareapi.Controllers.SearchResults;
import com.marivr.foursquareapi.Model.Photos.PhotosResponse;
import com.marivr.foursquareapi.Model.Venues.Stats;
import com.marivr.foursquareapi.Model.Venues.StatsResponse;
import com.marivr.foursquareapi.Model.Venues.Venue;
import com.marivr.foursquareapi.Model.Venues.VenuesResponse;
import com.marivr.foursquareapi.Networking.HttpRequest;
import com.marivr.foursquareapi.Networking.RequestBuilder;
import com.marivr.foursquareapi.R;
import com.marivr.foursquareapi.Util.Preferences;
import com.squareup.picasso.Picasso;

import java.net.ConnectException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;

/**
 * Created by marivr on 27/08/2017.
 */

public class RecycleViewCustomAdapter extends RecyclerView.Adapter<RecycleViewCustomAdapter.CustomViewHolder>{

    private ArrayList<Venue> venues;
    private RecyclerViewClickListener mListener;
    private Context context;
    private String token;

    public static class CustomViewHolder extends RecyclerView.ViewHolder{

        private TextView tvChecks;
        private ImageView ivFoto;
        private TextView tvNombre;
        private TextView tvCategoria;
        public CircleImageView imProfile01, imProfile02,imProfile03;

        CustomViewHolder(View vistaElemento){
            super(vistaElemento);

            tvChecks = (TextView) vistaElemento.findViewById(R.id.tvChecks);
            ivFoto = (ImageView) vistaElemento.findViewById(R.id.ivFoto);
            tvNombre = (TextView) vistaElemento.findViewById(R.id.tvNombre);
            tvCategoria = (TextView) vistaElemento.findViewById(R.id.tvCategoria);
        }
    }

    public RecycleViewCustomAdapter(ArrayList<Venue> venues, RecyclerViewClickListener listener, Context context, String token){
        this.venues = venues;
        this.mListener = listener;
        this.context = context;
        this.token = token;
    }
    public void updateData(ArrayList<Venue> dataset) {
        venues.clear();
        venues.addAll(dataset);
        notifyDataSetChanged();
    }

        @Override
    public int getItemCount() {
        return venues.size();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //TODO: MODIFICAR: Cambiar layout de template
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listacustom, viewGroup, false);
        CustomViewHolder customViewHolder = new CustomViewHolder(v);
        //return customViewHolder;
        return new RowViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        /*
        //TODO: MODIFICAR: Asignar valores a campos
        holder.tvNombre.setText(venues.get(position).getName());
        holder.tvCategoria.setText(venues.get(position).getCategories().get(0).getName());
        //holder.ivFoto.setImageResource(v.get(position).getFotoId());
*/
        if (holder instanceof RowViewHolder) {
            final RowViewHolder rowHolder = (RowViewHolder) holder;
            //set values of data here
            holder.tvNombre.setText(venues.get(position).getName());
            holder.tvCategoria.setText(venues.get(position).getCategories().get(0).getName());
            holder.tvChecks.setText(venues.get(position).getStats().getCheckinsCount() + " checkins");
            String imageUrl = venues.get(position).getCategories().get(0).getIcon().getPrefix() + "bg_64" +
                    venues.get(position).getCategories().get(0).getIcon().getSuffix();
            Picasso.with(context).load(imageUrl).into(holder.ivFoto);
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
