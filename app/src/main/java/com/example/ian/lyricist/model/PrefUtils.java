package com.example.ian.lyricist.model;


import android.content.Context;
import android.content.SharedPreferences;


public class PrefUtils {

        private SharedPreferences sharedPreferencesCompat;

        private SharedPreferences.Editor editor;
        private Context mContext;
        private int PRIVATE_MODE = 0;
        private static final String PREF_NAME = "LyricsPrefs";
        private static final String KEY_LYRICS = "lyrics";
        public PrefUtils(Context mContext) {
            this.mContext = mContext;
            this.sharedPreferencesCompat = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            this.editor = this.sharedPreferencesCompat.edit();
        }

        public void setLyrics(String value)
        {
            editor.putString(KEY_LYRICS, value);
            editor.commit();


        }
        public String getLyrics() {
            return sharedPreferencesCompat.getString(KEY_LYRICS, null);

        }}









