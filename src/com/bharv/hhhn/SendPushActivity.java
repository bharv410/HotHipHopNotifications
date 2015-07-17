package com.bharv.hhhn;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.SendCallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by benjamin.harvey on 7/17/15.
 */
public class SendPushActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        trackSendPushActivity();

    }
    public void exampleNotif(View view){
        trackSendPush();
        sendPush();
    }

    public void trackSendPushActivity(){
        ParseObject gameScore = new ParseObject("trackSendPushActivity");
        gameScore.put("score", 1337);
        gameScore.put("playerName", "Sean Plott");
        gameScore.put("cheatMode", false);
        gameScore.saveInBackground();
    }

    public void trackSendPush(){
        ParseObject gameScore = new ParseObject("trackSendPush");
        gameScore.put("score", 1337);
        gameScore.put("playerName", "Sean Plott");
        gameScore.put("cheatMode", false);
        gameScore.saveInBackground();
    }

    private void sendPush(){
        try {
            JSONObject data = new JSONObject("{\n" +
                    "        \"alert\": \"The Giants won against the Mets 2-3.\",\"artist\": \"U2\",\"song\": \"Dance With Wolves Again\",\"url\": \"bit.ly/1e584dD\"\n" +
                    "\n" +
                    "    }");
            ParsePush push = new ParsePush();
            push.setData(data);
            push.sendInBackground(new SendCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.v("benmark", "pushed");
                    } else {
                        Log.v("benmark", "error pusshing");
                    }
                }
            });
        }catch (JSONException e){ e.printStackTrace(); };
    }
}

