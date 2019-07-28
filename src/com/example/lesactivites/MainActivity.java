package com.example.lesactivites;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText = (EditText) findViewById(R.id.editText1);
		((Button)findViewById(R.id.btnQoui)).setOnClickListener(this);
		((Button)findViewById(R.id.btnSelfSubActivite)).setOnClickListener(this);
		((Button)findViewById(R.id.btnGotoGoogle)).setOnClickListener(this);
		((Button)findViewById(R.id.btnCallUrgence)).setOnClickListener(this);
		((Button)findViewById(R.id.btnAppSetting)).setOnClickListener(this);
		((Button)findViewById(R.id.btnWifiSetting)).setOnClickListener(this);
		((Button)findViewById(R.id.btnPlanifActivity)).setOnClickListener(this);
		
		if(this.getIntent().hasExtra("self-sub-activity")){
			Toast.makeText(this, "self-sub-activity", Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void finish() {
		setResult(RESULT_OK, new Intent().putExtra("Edited", editText.getText()));
		super.finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 if(requestCode==99 && resultCode== RESULT_OK){
			 editText.setText(data.getExtras().getCharSequence("Edited"));
		 }
		super.onActivityResult(requestCode, resultCode, data);
	}
	@Override
	public void onClick(View v) {
		Intent intent ;
		switch (v.getId()) {
		case R.id.btnQoui:
			Toast.makeText(this, editText.getText(), Toast.LENGTH_SHORT).show();
			break;
		case R.id.btnSelfSubActivite :
			  intent = new Intent(this, getClass());
			  intent.putExtra("self-sub-activity", "yet");
			  startActivityForResult(intent, 99);
			  break;
		case R.id.btnGotoGoogle:
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
			startActivity(intent); break;
		case R.id.btnAppSetting:
			intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS );
			startActivity(intent); break;
		case R.id.btnWifiSetting:
			intent = new Intent(Settings.ACTION_WIFI_SETTINGS );
			startActivity(intent); break;
		case R.id.btnCallUrgence:
			intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:112"));
			startActivity(intent); break;
		case R.id.btnPlanifActivity:
			intent = new Intent(this, PlanifiedActivities.class);
			startActivity(intent);
		default:
			break;
		}
		
	}

}
