package com.marivr.gridviewejemplo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import static android.view.View.NO_ID;

/**
 * Created by marivr on 22/08/2017.
 */

public class GridViewAdapter extends BaseAdapter {

    private ArrayList<Integer> imagenes;

    private Context context;

    private ImageView imageView;

    public GridViewAdapter(Context c, ArrayList<Integer> imagenes) {

        this.context = c;
        this.imagenes = imagenes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            imageView = new ImageView(context);
            // Configura las dimensiones de la vista, sin importar el tamaño de la imagen
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            // Configura la forma en que se va a representar la imagen
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }else{
            imageView = (ImageView)convertView;
        }
        imageView.setImageResource(imagenes.get(position));
        return imageView;
    }

    @Override
    public long getItemId(int position) {
        return NO_ID;
    }

    @Override
    public Object getItem(int position) {
        return imagenes.get(position);
    }

    @Override
    public int getCount() {
        return imagenes.size();
    }
}
