package com.marivr.chat;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marivr.chat.Mensaje;
import com.marivr.chat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marivr on 17/08/2017.
 */

public class CustomAdapter extends ArrayAdapter<Mensaje> {

    private TextView countryName;
    private List<Mensaje> countries = new ArrayList<Mensaje>();
    private LinearLayout wrapper;

    @Override
    public void add(Mensaje object) {
        countries.add(object);
        super.add(object);
    }

    public CustomAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public int getCount() {
        return this.countries.size();
    }

    public Mensaje getItem(int index) {
        return this.countries.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_fila_custom, parent, false);
        }

        wrapper = (LinearLayout) row.findViewById(R.id.wrapper);

        Mensaje coment = getItem(position);

        countryName = (TextView) row.findViewById(R.id.comment);

        countryName.setText(coment.getTexto());

        //countryName.setBackgroundResource(coment.left ? R.drawable.bubble_yellow : R.drawable.bubble_green);
        if(coment.getTexto().length()>18){
            countryName.setMaxWidth(120);
        }
        wrapper.setGravity(coment.isIzquierda() ? Gravity.LEFT : Gravity.RIGHT);

        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}
