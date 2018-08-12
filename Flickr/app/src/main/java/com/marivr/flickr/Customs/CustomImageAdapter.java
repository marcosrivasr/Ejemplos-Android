package com.marivr.flickr.Customs;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.marivr.flickr.Datos.Foto;

import java.util.ArrayList;

import static android.view.View.NO_ID;

/**
 * Created by marivr on 20/08/2017.
 */

public class CustomImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Foto> fotos;

    public CustomImageAdapter(Context c, ArrayList<Foto> fotos) {
        mContext = c;
        this.fotos = fotos;
    }

    public int getCount() {
        return fotos.size();
    }

    public Object getItem(int position) {
        return NO_ID;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageDrawable(fotos.get(position).getImagen());
        return imageView;
    }
}
