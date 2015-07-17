package com.bharv.hhhn;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.ParseAnalytics;
import com.parse.ParseObject;


public class ParseStarterProjectActivity extends Activity {
	int clickedSettingsCount = 0;
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }


	private void sendLocalNotif(){
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= 11){
			Notification notification = new Notification.Builder(getApplicationContext())
					.setContentText("New song from 50 cent!")
					.setSmallIcon(R.drawable.notif)
					.setContentIntent(getPI())
					.setAutoCancel(true)
					.build();

			NotificationManager notificationManager =
					(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			notificationManager.notify(99,notification);
		} else{
			// do something for phones running an SDK before froyo
		}

	}

	private PendingIntent getPI(){
		Intent intent = new Intent(getApplicationContext(), GoToSongActivity.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
		return pi;
	}

	public void exampleNotif(View view){
		ParseObject gameScore = new ParseObject("ClickedExample");
		gameScore.put("score", 1337);
		gameScore.put("playerName", "Sean Plott");
		gameScore.put("cheatMode", false);
		gameScore.saveInBackground();

		Button exmapleButton = (Button)findViewById(R.id.button);
		exmapleButton.setVisibility(View.GONE);
		sendLocalNotif();
	}

	public void settings(View view){
		if(clickedSettingsCount > 9)
			startActivity(new Intent(this, SendPushActivity.class));
		else
			clickedSettingsCount++;
	}
}
