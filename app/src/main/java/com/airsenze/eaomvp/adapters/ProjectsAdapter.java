package com.airsenze.eaomvp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airsenze.eaomvp.R;
import com.airsenze.eaomvp.adapters.view_holders.ProjectsViewHolder;
import com.airsenze.eaomvp.models.Project;

import java.util.List;

/**
 * Created by Aidan Laing on 2017-03-27.
 *
 */

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsViewHolder> {

    private Context context;
    private List<Project> projects;
    private ProjectClickListener projectClickListener;
    private LayoutInflater inflater;

    public ProjectsAdapter(Context context, List<Project> projects, ProjectClickListener projectClickListener) {
        this.context = context;
        this.projects = projects;
        this.projectClickListener = projectClickListener;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public ProjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProjectsViewHolder(inflater.inflate(R.layout.item_project, parent, false));
    }

    @Override
    public void onBindViewHolder(ProjectsViewHolder holder, int position) {

        final Project project = projects.get(position);

        if(project == null) {
            return;
        }

        //CLICK
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                projectClickListener.projectClicked(project);
            }
        });

        //NAME
        String name = project.getName();
        if (name != null) {
            holder.nameTv.setText(name);
        }
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public interface ProjectClickListener {
        void projectClicked(Project project);
    }

    public void replaceItems(List<Project> projects) {
        this.projects = projects;
        notifyDataSetChanged();
    }
}
