package com.bharv.hhhn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONObject;

/**
 * Created by benjamin.harvey on 7/17/15.
 */
public class CustomParseReceiver extends ParsePushBroadcastReceiver {
    private static final String ARTIST_TITLE = "artist";
    private static final String SONG_TITLE = "song";
    private static final String URL = "url";

    @Override
    protected void onPushOpen(Context context, Intent intent) {

        try {
            Bundle receivedMessage = intent.getExtras();
            JSONObject receivedJSON;
            Log.d("benmark", "KEY_PUSH_DATA = " + receivedMessage.getString(ParsePushBroadcastReceiver.KEY_PUSH_DATA));
            receivedJSON = new JSONObject(receivedMessage.getString(ParsePushBroadcastReceiver.KEY_PUSH_DATA));
            if (receivedJSON.length() > 0) {

                String artist = receivedJSON.getString(ARTIST_TITLE);
                String song = receivedJSON.getString(SONG_TITLE);
                String url = receivedJSON.getString(URL);


                Intent i = new Intent(context, GoToSongActivity.class);
                i.putExtras(intent.getExtras());
                i.putExtra("artist", artist);
                i.putExtra("song", song);
                i.putExtra("url", url);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}