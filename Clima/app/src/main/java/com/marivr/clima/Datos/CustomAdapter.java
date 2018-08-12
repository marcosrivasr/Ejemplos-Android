package com.marivr.clima.Datos;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.marivr.clima.R;

import java.util.ArrayList;

/**
 * Created by marivr on 13/08/2017.
 */

public class CustomAdapter extends ArrayAdapter {

    public CustomAdapter(@NonNull Context context, ArrayList<Ciudad> ciudades) {
        super(context, R.layout.filacustom, ciudades);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View vistaCustom = layoutInflater.inflate(R.layout.filacustom, parent, false);

        Ciudad ciudadItem = (Ciudad)getItem(position);
        TextView tvCiudad = (TextView) vistaCustom.findViewById(R.id.tvCiudad);

        tvCiudad.setText(ciudadItem.getNombre());

        return vistaCustom;
    }
}
