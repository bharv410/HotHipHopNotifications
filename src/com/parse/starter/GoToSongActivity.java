package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.parse.ParseObject;

/**
 * Created by benjamin.harvey on 7/17/15.
 */
public class GoToSongActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ParseObject gameScore = new ParseObject("ReallyWentToSong");
        gameScore.put("score", 1337);
        gameScore.put("playerName", "Sean Plott");
        gameScore.put("cheatMode", false);
        gameScore.saveInBackground();

        String url = "http://bit.ly/1e584dD";

        if (!url.startsWith("http://") && !url.startsWith("https://")) url = "http://" + url;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
        finish();
    }
}

