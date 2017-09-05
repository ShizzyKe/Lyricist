package com.example.ian.lyricist.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ian.lyricist.LyricsCreateActivity;
import com.example.ian.lyricist.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 9/4/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context mContext;
    public List<Lyric> items = new ArrayList<>();

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
        final Lyric listitem = items.get(position);
        holder.title1.setText(listitem.getTitle());
        holder.description1.setText(listitem.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(mContext, LyricsCreateActivity.class);
                i.putExtra("title1",listitem.getTitle());
                i.putExtra("description1",listitem.getDescription());
                i.putExtra("position",position);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {

        return items.size();
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
