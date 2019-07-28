package com.example.lesactivites;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PlanifiedActivities extends Activity implements OnClickListener {

	
	
	
	Receiver receiver;
	private AlarmManager alarms;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.planified_activities);
		receiver = new Receiver();
		((Button) findViewById(R.id.button1)).setOnClickListener(this);
		((Button) findViewById(R.id.button2)).setOnClickListener(this);
		((Button) findViewById(R.id.button3)).setOnClickListener(this);
		registerReceiver(receiver, new IntentFilter("ACTION_ATTENTION")); 
 		alarms = ((AlarmManager) getSystemService(ALARM_SERVICE));
		
		
	}
	
	@Override
	public void finish() {
		unregisterReceiver(receiver);
		super.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.planified_activities, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		PendingIntent oper;
		switch (v.getId()) {
		case R.id.button1:
			intent = new Intent("ACTION_ATTENTION");
			intent.putExtra("param", "atention!");
			sendBroadcast(intent);
			break;
		case R.id.button2:
			intent = new Intent("ACTION_ATTENTION");
			intent.putExtra("param", " Pending atention!");
			PendingIntent operation = PendingIntent.getBroadcast(this, 0, intent, 0);
			alarms.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+3000, operation);
			break;
		case R.id.button3:
			intent = new Intent("ACTION_ALARM_FILTER");
			intent.putExtra("param", " Pending atention filter!");
			oper = PendingIntent.getBroadcast(this, 0, intent, 0);
			alarms.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+7000, oper );
			break;
		default:
			break;
		}
		
	}

}
