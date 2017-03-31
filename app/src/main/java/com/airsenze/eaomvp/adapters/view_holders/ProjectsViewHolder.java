package com.airsenze.eaomvp.adapters.view_holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.airsenze.eaomvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Aidan Laing on 2017-03-27.
 *
 */

public class ProjectsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_project_card_view)
    public CardView cardView;

    @BindView(R.id.item_project_name_tv)
    public TextView nameTv;

    public ProjectsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
