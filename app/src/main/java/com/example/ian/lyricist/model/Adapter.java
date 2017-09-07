package com.example.ian.lyricist.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ian.lyricist.LyricsCreateActivity;
import com.example.ian.lyricist.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.example.ian.lyricist.R.attr.title;
import static com.example.ian.lyricist.R.id.description;

/**
 * Created by Ian on 9/4/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context mContext;
    public List<Lyric> items = new ArrayList<>();
    public PrefUtils prefUtils;
    public Gson gson;
    public RecyclerView recyclerView;
    public Adapter adapter;
    public List<Lyric> lyrics;
    public TextView text2;
    public MenuItem searchMenuItem;
    public SearchView searchView;


    public Adapter(Context mContext)
    {
            this.mContext = mContext;
             this.items = items;
    }
    public void setItems(List<Lyric> items)
    {

        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        prefUtils = new PrefUtils(mContext);
        lyrics = new ArrayList<>();
        final String lyricsString = prefUtils.getLyrics();
        final Lyric listitem = items.get(position);
        holder.title1.setText(listitem.getTitle());
        holder.description1.setText(listitem.getDescription());
        final String activityName = mContext.getClass().getSimpleName();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                switch (activityName)
                {
                    case "LyricsListActivity":
                        Intent i = new Intent(mContext, LyricsCreateActivity.class);
                        i.putExtra("title1",listitem.getTitle());
                        i.putExtra("description1",listitem.getDescription());
                        i.putExtra("position",position);
                        mContext.startActivity(i);
                        break;
                    case "DeleteActivity":
                        items.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(mContext,"Project deleted", Toast.LENGTH_SHORT).show();
                        gson = new Gson();
                        lyrics = gson.fromJson(lyricsString, new TypeToken<List<Lyric>>() {
                        }.getType());
                        lyrics.remove(position);
                        String stringLyrics = gson.toJson(lyrics);
                        prefUtils.setLyrics(stringLyrics);

                }

            }
        });
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public void setFilter (ArrayList<Lyric> newlist)
    {
        items = new ArrayList<>();
        items.addAll(newlist);
        notifyDataSetChanged();

    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {   public TextView title1;
        public TextView description1;
        public List<Lyric> items = new ArrayList<>();
        Context mContext;



        public ViewHolder(View itemView) {
            super(itemView);

            this.items= items;
            this.mContext= mContext;

            title1 = (TextView) itemView.findViewById(R.id.title1);
            description1 = (TextView) itemView.findViewById(R.id.description1);
        }

    }


}
