package com.marivr.flickr.Customs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.marivr.flickr.Datos.Categoria;
import com.marivr.flickr.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by marivr on 20/08/2017.
 */



public class CustomListViewAdapter extends ArrayAdapter {

    static class ViewHolder{
        TextView tvCategoriaNombre;
        ImageView ivCategoriaImagen;
    }
    public CustomListViewAdapter(@NonNull Context context,@NonNull ArrayList<?> ciudades) {
        super(context, R.layout.activity_template_categoria, ciudades);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder vh;

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View vistaCustom = layoutInflater.inflate(R.layout.activity_template_categoria, parent, false);

        vh = new ViewHolder();
        Categoria categoriaItem = (Categoria)getItem(position);
        vh.tvCategoriaNombre = (TextView) vistaCustom.findViewById(R.id.tvCategoriaNombre);
        vh.ivCategoriaImagen = (ImageView) vistaCustom.findViewById(R.id.ivCategoriaImagen);

        vh.tvCategoriaNombre.setText(categoriaItem.getTexto());
        setImagen(vh.ivCategoriaImagen, categoriaItem.getIcono());

        return vistaCustom;
    }

    private void setImagen(ImageView iv, String name) {
        try {
            InputStream stream = getContext().getAssets().open(name);
            Drawable d = Drawable.createFromStream(stream, null);
            iv.setImageDrawable(d);

        } catch (IOException ioe) { //Maneja error }


        }
    }
}
