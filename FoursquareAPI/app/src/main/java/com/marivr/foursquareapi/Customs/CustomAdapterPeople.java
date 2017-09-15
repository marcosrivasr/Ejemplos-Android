package com.marivr.foursquareapi.Customs;

import android.content.Context;
import android.media.Image;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.marivr.foursquareapi.Model.Photos.Photo;
import com.marivr.foursquareapi.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.resource;

/**
 * Created by marivr on 03/09/2017.
 */

public class CustomAdapterPeople extends ArrayAdapter {
    public CustomAdapterPeople(@NonNull Context context, ArrayList<Photo> photos) {
        super(context, R.layout.listacustomfriends, photos);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater li = LayoutInflater.from(getContext());
        View view = li.inflate(R.layout.listacustomfriends,parent, false);

        Photo photo = (Photo)getItem(position);
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        CircleImageView ivProfile = (CircleImageView) view.findViewById(R.id.ivProfile);

        tvName.setText(photo.getUser().getFirstName() + " " + photo.getUser().getLastName());
        Picasso.with(getContext()).load(photo.getUser().getPhoto().getPrefix() + "50x50" + photo.getUser().getPhoto().getSufix()).into(ivProfile);

        return view;
    }
}
