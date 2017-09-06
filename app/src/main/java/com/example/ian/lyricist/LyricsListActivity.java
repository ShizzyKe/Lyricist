package com.example.ian.lyricist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
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

public class LyricsListActivity extends AppCompatActivity {


    public PrefUtils prefUtils;
    public Gson gson;
    public RecyclerView recyclerView;
    public Adapter adapter;
    public List<Lyric> lyrics;
    public TextView text2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_list);

        prefUtils = new PrefUtils(this);
        lyrics = new ArrayList<>();
        text2 = (TextView) findViewById(R.id.text2);
        String lyricsString = prefUtils.getLyrics();

        if (lyricsString == null) {
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
}
