package com.example.lesactivites;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent data) {
		Toast.makeText(context, 
				data.getExtras().getCharSequence("param"),
				Toast.LENGTH_LONG).show();
	} 


}
