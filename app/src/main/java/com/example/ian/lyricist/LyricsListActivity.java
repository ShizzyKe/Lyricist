package com.example.ian.lyricist;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.ian.lyricist.model.Adapter;
import com.example.ian.lyricist.model.Lyric;
import com.example.ian.lyricist.model.PrefUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 9/4/2017.
 */

public class LyricsListActivity extends  AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {


    public PrefUtils prefUtils;
    public Gson gson;
    public RecyclerView recyclerView;
    public Adapter adapter;
    public List<Lyric> lyrics;
    public TextView text2;
    public MenuItem searchMenuItem;
    public SearchView searchView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_list);

        prefUtils = new PrefUtils(this);
        lyrics = new ArrayList<>();
        text2 = (TextView) findViewById(R.id.text2);
        String lyricsString = prefUtils.getLyrics();
        Log.e("Shizzy",String.valueOf(lyricsString));
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
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
