package com.example.ian.lyricist.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ian.lyricist.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 9/4/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context mContext;

    public List<Lyric> items = new ArrayList<>();
    public Adapter(Context mContext){
            this.mContext = mContext;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        Lyric listitem = items.get(position);
        holder.title1.setText(listitem.getTitle());
        holder.description1.setText(listitem.getDescription());
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {   public TextView title1;
        public TextView description1;



        public ViewHolder(View itemView) {
            super(itemView);

            title1 = (TextView) itemView.findViewById(R.id.title1);
            description1 = (TextView) itemView.findViewById(R.id.description1);
        }
    }



}
