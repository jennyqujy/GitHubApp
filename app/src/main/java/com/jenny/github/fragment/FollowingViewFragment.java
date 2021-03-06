package com.jenny.github.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.jenny.github.API.GetUserInterface;
import com.jenny.github.API.GetUserTask;
import com.jenny.github.Models.User;
import com.jenny.github.R;
import com.jenny.github.Views.ProfileOverviewAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jenny.github.Const.GitUserName.user1;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FollowingViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowingViewFragment extends Fragment {
    /**
     * A simple {@link Fragment} subclass.
     * Activities that contain this fragment must implement the
     * to handle interaction events.
     * Use the {@link com.jenny.github.fragment.ProfileOverviewFragment#newInstance} factory method to
     * create an instance of this fragment.
     */
    private static final boolean GRID_LAYOUT = false;

    @BindView(R.id.following_view)
    RecyclerView mRecyclerView;

    public static com.jenny.github.fragment.ProfileOverviewFragment newInstance() {
        com.jenny.github.fragment.ProfileOverviewFragment fragment = new com.jenny.github.fragment.ProfileOverviewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_following_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        final List<User> users = new ArrayList<>();

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(new ProfileOverviewAdapter(users));
        new GetUserTask(new GetUserInterface() {
            @Override
            public void onFinished(final User user2) throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        users.add(user2);
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                        getView().invalidate();
                        mRecyclerView.removeAllViews();
                        mRecyclerView.invalidate();
                    }
                });
            }
        }).execute(user1);
    }
}

