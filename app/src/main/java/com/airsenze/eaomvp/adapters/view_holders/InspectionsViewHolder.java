package com.airsenze.eaomvp.adapters.view_holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.airsenze.eaomvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Aidan Laing on 2017-03-13.
 *
 */

public class InspectionsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_inspection_layout) public CardView layout;

    @BindView(R.id.item_inspection_edit_iv) public ImageView editIv;

    public InspectionsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
