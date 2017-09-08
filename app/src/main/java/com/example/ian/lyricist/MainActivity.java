package com.example.ian.lyricist;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ian.lyricist.model.Adapter;
import com.example.ian.lyricist.model.Lyric;
import com.example.ian.lyricist.model.PrefUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener
{
    public PrefUtils prefUtils;
    public Gson gson;
    public RecyclerView recyclerView;
    public Adapter adapter;
    public List<Lyric> lyrics;
    public TextView text2;
    public MenuItem searchMenuItem;
    public SearchView searchView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefUtils = new PrefUtils(this);
        lyrics = new ArrayList<>();
        text2 = (TextView) findViewById(R.id.text2);
        String lyricsString = prefUtils.getLyrics();

        if (lyricsString == null || lyrics.size() == 0) {
            text2.setVisibility(View.VISIBLE);
        } else {
            gson = new Gson();
            lyrics = gson.fromJson(lyricsString, new TypeToken<List<Lyric>>() {
            }.getType());
            adapter = new Adapter(this);
            recyclerView = (RecyclerView) findViewById(R.id.recycler);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            adapter.setItems(lyrics);

        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, LyricsCreateActivity.class);
                startActivity(myIntent);
            }
        });

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
   public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_create) {
            Intent myIntent = new Intent(MainActivity.this, LyricsCreateActivity.class);
            startActivity(myIntent);
        }

        else if (id == R.id.nav_edit) {
            Intent myIntent = new Intent(MainActivity.this, LyricsListActivity.class);
            startActivity(myIntent);
        }
        else if (id == R.id.nav_search) {
            Intent myIntent = new Intent(MainActivity.this, LyricsListActivity.class);
            startActivity(myIntent);

        }
        else if (id == R.id.nav_delete) {
            Intent myIntent = new Intent(MainActivity.this, DeleteActivity.class);
            startActivity(myIntent);

        }


        else if (id == R.id.nav_about) {
            Intent myIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter = new Adapter(this);
        newText = newText.toLowerCase();
        List<Lyric> newlist = new ArrayList<>();
        for (Lyric lyric : lyrics)
        {
            String name = lyric.getTitle().toLowerCase();
            if (name.contains(newText))
                newlist.add(lyric);
        }
        adapter.setFilter((ArrayList<Lyric>) newlist);
           return true;


        }
}
