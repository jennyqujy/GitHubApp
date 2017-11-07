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
import com.jenny.github.API.GetSearched;
import com.jenny.github.API.GetSearchedInterface;
import com.jenny.github.R;
import com.jenny.github.Views.SearchListViewAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jenny on 11/6/17.
 */

public class SearchFragment extends Fragment {

    private static final boolean GRID_LAYOUT = false;

    @BindView(R.id.searchView)
    RecyclerView mRecyclerView;

    public static com.jenny.github.fragment.RecyclerViewFragment newInstance() {
        return new com.jenny.github.fragment.RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
    }

    public void doSearch(String query, String user) {
        final List<String> items = new ArrayList<>();
        mRecyclerView.setAdapter(new SearchListViewAdapter(items));
        new GetSearched(new GetSearchedInterface() {
            @Override
            public void onFinished(final List<String> result) throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        items.clear();
                        items.addAll(result);
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                        getView().invalidate();
                        mRecyclerView.removeAllViews();
                        mRecyclerView.invalidate();
                    }
                });
            }
        }).execute(query, user);
    }

}

