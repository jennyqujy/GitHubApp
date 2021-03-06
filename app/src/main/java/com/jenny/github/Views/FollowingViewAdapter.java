package com.jenny.github.Views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jenny.github.Models.User;
import com.jenny.github.R;

import java.util.List;

/**
 * Created by Jenny on 10/23/17.
 */

public class FollowingViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<User> users;

    static final int TYPE_HEADER = 0;

    public FollowingViewAdapter(List<User> user) {
        this.users = user;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            default:
                return TYPE_HEADER;
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.following_list_item_card_small, parent, false);
        return new RecyclerView.ViewHolder(view){};
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final View view = holder.itemView;
        TextView nameTextView = view.findViewById(R.id.following_name);
        TextView bioTextView = view.findViewById(R.id.following_bio);
        if (!users.isEmpty()) {
            final User user = users.get(0);
            nameTextView.setText(user.getName());
            bioTextView.setText(user.getBio());
        }
    }
}
