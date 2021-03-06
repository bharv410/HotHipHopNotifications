package com.bharv.hhhn;

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
        gameScore.put("song name", getIntent().getStringExtra("song"));
        gameScore.put("playerName", "Sean Plott");
        gameScore.put("cheatMode", false);
        gameScore.saveInBackground();

        String url = getIntent().getStringExtra("url");

        if (!url.startsWith("http://") && !url.startsWith("https://")) url = "http://" + url;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
        finish();
    }
}

