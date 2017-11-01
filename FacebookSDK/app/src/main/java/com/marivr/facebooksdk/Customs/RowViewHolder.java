package com.marivr.facebooksdk.Customs;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * Created by marivr on 31/08/2017.
 */

public class RowViewHolder extends RecycleViewCustomAdapter.CustomViewHolder implements OnClickListener {

    private RecyclerViewClickListener mListener;

    RowViewHolder(View v, RecyclerViewClickListener listener) {
        super(v);
        mListener = listener;
        v.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());
    }
}
