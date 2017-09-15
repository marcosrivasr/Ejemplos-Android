package com.marivr.basededatos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.marivr.basededatos.Datos.Alumno;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by marivr on 09/09/2017.
 */

public class CustomAdapter extends ArrayAdapter {

    private ArrayList<Alumno> alumnos;


    public CustomAdapter(@NonNull Context context, ArrayList<Alumno> alumnos) {
        super(context, R.layout.fila, alumnos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View vista = layoutInflater.inflate(R.layout.fila, parent, false);

        Alumno item = (Alumno) getItem(position);
        TextView tvNombre = (TextView)vista.findViewById(R.id.tvNombre);
        TextView tvMatricula = (TextView)vista.findViewById(R.id.tvMatricula);
        CircleImageView ivFoto = (CircleImageView) vista.findViewById(R.id.ivFoto);

        tvNombre.setText(item.getNombre());
        tvMatricula.setText(item.getId());
        Picasso.with(getContext())
                .load(item.getAvatar())
                .resize(80, 80)
                .centerCrop()
                .into(ivFoto);
        return vista;
    }
}
