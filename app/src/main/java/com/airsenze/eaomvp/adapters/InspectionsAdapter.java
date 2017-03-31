package com.airsenze.eaomvp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.airsenze.eaomvp.R;
import com.airsenze.eaomvp.adapters.view_holders.InspectionsViewHolder;
import com.airsenze.eaomvp.models.Inspection;

import java.util.List;

/**
 * Created by Aidan Laing on 2017-03-13.
 *
 */

public class InspectionsAdapter extends RecyclerView.Adapter<InspectionsViewHolder> {

    private Context context;
    private List<Inspection> inspections;

    private LayoutInflater inflater;

    public InspectionsAdapter(Context context, List<Inspection> inspections) {
        this.context = context;
        this.inspections = inspections;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public InspectionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InspectionsViewHolder(inflater.inflate(R.layout.item_inspection, parent, false));
    }

    @Override
    public void onBindViewHolder(InspectionsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return inspections.size();
    }

    public void replaceItems(List<Inspection> inspections) {
        this.inspections = inspections;
        notifyDataSetChanged();
    }

}
