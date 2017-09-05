package com.example.ian.lyricist.model;

/**
 * Created by Ian on 9/4/2017.
 */

public class Lyric {
    String title, description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String body) {
        this.description = body;
    }

    public Lyric(String title, String body) {
        this.title = title;
        this.description = body;

    }
}
