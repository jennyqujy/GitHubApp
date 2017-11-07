package com.jenny.github.Views;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jenny.github.Acitivities.RepoWebViewActivity;
import com.jenny.github.Models.Repo;
import com.jenny.github.R;

import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Repo> contents;
    static final int TYPE_CELL = 1;

    public RecyclerViewAdapter(List<Repo> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_card_small, parent, false);
        return new RecyclerView.ViewHolder(view){};
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final View view = holder.itemView;
        TextView nameTextView = view.findViewById(R.id.repo_name);
        TextView despcriptionTextView = view.findViewById(R.id.repo_description);
        final Repo repo = contents.get(position);
        nameTextView.setText(repo.getName());
        despcriptionTextView.setText(repo.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepoWebViewActivity.setRepo(repo);
                v.getContext().startActivity(new Intent(v.getContext(), RepoWebViewActivity.class));

            }
        });

    }
}
