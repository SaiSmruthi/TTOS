package com.example.texttosignlanguage;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.R.*;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	
	Button convert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		initalise();
}
		
		
	private void initalise() {
		// TODO Auto-generated method stub
		convert=(Button)findViewById(R.id.bconvert);
		convert.setOnClickListener(this);
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
				EditText text=(EditText) findViewById(R.id.gettext);
				String arr=text.getText().toString();
				Bundle b=new Bundle();
				b.putString("NAME",arr);
				Intent image=new Intent(this,Image.class);
				image.putExtras(b);
				startActivity(image);
			
			
		
		
	}
	
}
