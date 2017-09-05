package com.example.ian.lyricist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ian.lyricist.R;
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

public class LyricsListActivity extends AppCompatActivity {


    public PrefUtils prefUtils;
    public Gson gson;
    public RecyclerView recyclerView;
    public Adapter adapter;
    public List<Lyric> lyrics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_activity);

        prefUtils = new PrefUtils(this);
        lyrics = new ArrayList<>();
        String lyricsString = prefUtils.getLyrics();
        Log.e("lyrics", lyricsString);
        gson = new Gson();
        lyrics = gson.fromJson(lyricsString, new TypeToken<List<Lyric>>(){}.getType());
        adapter = new Adapter(this);
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setItems(lyrics);
    }
}
