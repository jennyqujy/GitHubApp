package com.jenny.github.Acitivities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.SearchView;

import com.jenny.github.R;
import com.jenny.github.fragment.SearchFragment;

public class SearchActivity extends AppCompatActivity {

    TabLayout tabLayout;
    FrameLayout simpleFrameLayout;

    SearchFragment fragment = null;
    String searchWhat = "user";
    String currentQuery = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // get the reference of FrameLayout and TabLayout
        simpleFrameLayout = (FrameLayout) findViewById(R.id.simpleFrameLayout);
        tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout);
        // Create a new Tab named "First"
        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("Users"); // set the Text for the first Tab
        // first tab
        tabLayout.addTab(firstTab); // add  the tab at in the TabLayout

        // Create a new Tab named "Second"
        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Repos"); // set the Text for the second Tab
        tabLayout.addTab(secondTab); // add  the tab  in the TabLayout

        fragment = new SearchFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.simpleFrameLayout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        // perform setOnTabSelectedListener event on TabLayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            // get the current selected tab's position and replace the fragment accordingly
                switch (tab.getPosition()) {
                    case 0:
                        searchWhat = "user";
                        if (currentQuery != null)
                            fragment.doSearch(currentQuery, searchWhat);
                        break;
                    case 1:
                        searchWhat = "repo";
                        if (currentQuery != null)
                            fragment.doSearch(currentQuery, searchWhat);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        SearchView searchView = (SearchView) findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                assert (fragment!=null);
//                assert (query!=null);
                currentQuery = query;
                fragment.doSearch(query, searchWhat);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
