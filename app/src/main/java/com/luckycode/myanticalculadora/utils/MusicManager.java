package com.luckycode.myanticalculadora.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import com.luckycode.myanticalculadora.R;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by marcelocuevas on 9/19/17.
 */

public class MusicManager {
    private static final String TAG = "MusicManager";

    private static final int MUSIC_PREVIOUS = -1;
    private static final int MUSIC_MENU = 0;
    private static final int MUSIC_WON = 1;
    private static final int MUSIC_GAMEOVER=2;
    private static final int MUSIC_SWIPE=3;
    private static final int MUSIC_ERROR=4;

    private static HashMap players = new HashMap<Integer,MediaPlayer>();
    private static int currentMusic = -1;
    private static int previousMusic = -1;


    public static void start(Context context, int music, boolean force,boolean looped) {
        if (!force && currentMusic > -1) {
            return;
        }
        if (music == MUSIC_PREVIOUS) {
            music = previousMusic;
        }
        if (currentMusic != -1) {
            previousMusic = currentMusic;
        }
        currentMusic = music;
        MediaPlayer mp = (MediaPlayer) players.get(music);
        if (mp != null) {
            if (!mp.isPlaying()) {
                mp.start();
            }
        } else {
            if (music == MUSIC_MENU) {
                mp = MediaPlayer.create(context, R.raw.main_sound);
            } else if (music == MUSIC_WON) {
                mp = MediaPlayer.create(context, R.raw.win);
            } else if (music == MUSIC_GAMEOVER) {
                mp = MediaPlayer.create(context, R.raw.game_over);
            } else if(music==MUSIC_SWIPE) {
                mp = MediaPlayer.create(context, R.raw.swipe);
            }else if(music==MUSIC_ERROR){
                mp=MediaPlayer.create(context,R.raw.error);
            }else{
                Log.e(TAG, "unsupported music number - " + music);
                return;
            }
            players.put(music, mp);
            mp.setLooping(looped);
            mp.start();
        }
    }

    public static void pause() {
        Collection<MediaPlayer> mps = players.values();
        for (MediaPlayer p : mps) {
            if (p.isPlaying()) {
                p.pause();
            }
        }
        // previousMusic should always be something valid
        if (currentMusic != -1) {
            previousMusic = currentMusic;
            Log.d(TAG, "Previous music was [" + previousMusic + "]");
        }
        currentMusic = -1;
        Log.d(TAG, "Current music is now [" + currentMusic + "]");
    }

    public static void pause(int music){
        Collection<MediaPlayer> mps = players.values();
        for (MediaPlayer p : mps) {
            if (players.get(music).equals(p)){
                p.pause();
            }
        }
    }
}