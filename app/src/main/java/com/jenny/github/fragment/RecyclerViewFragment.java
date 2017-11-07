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
import com.jenny.github.API.GetRepoListInterface;
import com.jenny.github.API.GetUserRepoList;
import com.jenny.github.Models.Repo;
import com.jenny.github.R;
import com.jenny.github.Views.RecyclerViewAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jenny.github.Const.GitUserName.user1;


/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewFragment extends Fragment {

    private static final boolean GRID_LAYOUT = false;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        final List<Repo> items = new ArrayList<>();
        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(new RecyclerViewAdapter(items));
        new GetUserRepoList(new GetRepoListInterface() {
            @Override
            public void onFinished(final List<Repo> result) throws IOException {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                items.addAll(result);
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
