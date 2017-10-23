package com.jenny.github.Acitivities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.jenny.github.API.GetPhotoInterface;
import com.jenny.github.API.GetUserInterface;
import com.jenny.github.API.GetUserPhoto;
import com.jenny.github.API.GetUserTask;
import com.jenny.github.Models.User;
import com.jenny.github.R;
import com.jenny.github.fragment.ProfileOverviewFragment;
import com.jenny.github.fragment.RecyclerViewFragment;

import java.io.IOException;

import butterknife.ButterKnife;

import static com.jenny.github.Const.GitUserName.user1;
import static com.jenny.github.Const.HeaderImage.galaxyImageURL;
import static com.jenny.github.Utils.ProfilePhotoUtils.getCroppedBitmap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Todo: Refactor this viewPager part to a new activity and link with the main activity.
         */
        final MaterialViewPager mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        mViewPager.setImageUrl(galaxyImageURL, 0);
        ButterKnife.bind(this);

        //set tool bar text color and support actions.
        final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        toolbar.setTitleTextColor(Color.WHITE);


        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 5) {
                    case 0:
                        return ProfileOverviewFragment.newInstance();
                    case 1:
                        return RecyclerViewFragment.newInstance();
                }
                return new Fragment();
            }

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 5) {
                    case 0:
                        return "Overview";
                    case 1:
                        return "Repositories";
                    case 2:
                        return "Stars";
                    case 3:
                        return "Followers";
                    case 4:
                        return "Following";
                }
                return "";
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setShouldExpand(true);
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.getPagerTitleStrip().setTextColor(Color.WHITE);

        /**
         * TODO: Refactor this floating button fab into some Activity
         */
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              startAcitivity(new Intent(MainActivity.this, LoginActivity.class));
//    }

        /**
         * Opening a new thread to set user tab including profile picture, name and email.
         */
        new GetUserTask(new GetUserInterface() {
            @Override
            public void onFinished(User user) throws IOException {

            new GetUserPhoto(new GetPhotoInterface() {
                @Override
                public void onFinished(Bitmap result) throws IOException {
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                ImageView headerLogoView = (ImageView) findViewById(R.id.logoImageView);
                imageView.setImageBitmap(getCroppedBitmap(result));
                headerLogoView.setImageBitmap(getCroppedBitmap(result));
                }
            }).execute(user.getAvatorUrl());

            TextView userName = (TextView) findViewById(R.id.textViewName);
            userName.setText(user.getName());
            TextView userBio = (TextView) findViewById(R.id.textViewBio);
            userBio.setText(user.getBio());

            }
        }).execute(user1);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_overview) {
            //set up page for profile overview:

        } else if (id == R.id.nav_stars) {

        } else if (id==R.id.nav_repository){

        } else if (id == R.id.nav_followers) {

        } else if (id == R.id.nav_following) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}
