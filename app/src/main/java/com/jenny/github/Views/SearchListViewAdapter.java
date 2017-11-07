package com.jenny.github.Views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jenny.github.R;

import java.util.List;

/**
 * Created by Jenny on 11/6/17.
 */
public class SearchListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> contents;
    static final int TYPE_CELL = 1;

    public SearchListViewAdapter(List<String> contents) {
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
                .inflate(R.layout.search_fragment, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final View view = holder.itemView;
        TextView nameTextView = view.findViewById(R.id.search_name);
        final String item = contents.get(position);
        nameTextView.setText(item);
    }

}
