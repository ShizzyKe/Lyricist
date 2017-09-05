package com.example.ian.lyricist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ian.lyricist.model.Lyric;
import com.example.ian.lyricist.model.PrefUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class LyricsCreateActivity extends AppCompatActivity {
    public EditText title;
    public EditText description;
    public Button save;
    public List<Lyric> lyrics;
    public PrefUtils prefUtils;
    public Gson gson;
    String editTitle;
    String editDescription;
    int position;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_create);
        prefUtils = new PrefUtils(this);
        lyrics = new ArrayList<>();
        title = (EditText) findViewById(R.id.title);

        description = (EditText) findViewById(R.id.description);
        save = (Button) findViewById(R.id.save);

        if(getIntent().hasExtra("title1")){
            editTitle = getIntent().getStringExtra("title1");
            editDescription = getIntent().getStringExtra("description1");
            position = getIntent().getIntExtra("position",0);
            title.setText(editTitle, TextView.BufferType.EDITABLE);
            description.setText(editDescription, TextView.BufferType.EDITABLE);
        }


        String lyricsString = prefUtils.getLyrics();
        gson = new Gson();
         if(gson.fromJson(lyricsString, new TypeToken<List<Lyric>>(){}.getType())!= null){
             lyrics = gson.fromJson(lyricsString, new TypeToken<List<Lyric>>(){}.getType());
         }




        save.setOnClickListener(new Button.OnClickListener (){public void onClick(View v)
        {
          Lyric lyric = new Lyric(title.getText().toString().trim(),description.getText().toString().trim());
            if(getIntent().hasExtra("title1")){
                lyrics.set(position, lyric);
            } else {
                lyrics.add(lyric);
            }


            String stringLyrics = gson.toJson(lyrics);
            prefUtils.setLyrics(stringLyrics);
            Toast.makeText(getApplicationContext(),"Project added successfully", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(LyricsCreateActivity.this, MainActivity.class);
            startActivity(myIntent);
        }
        });

    }

}
