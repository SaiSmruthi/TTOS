package com.example.texttosignlanguage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activities extends Activity implements OnClickListener{
	
	Button text,voice,news,app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activities);
		initialise();
	}

	private void initialise() {
		// TODO Auto-generated method stub
		
		text=(Button)findViewById(R.id.button1);
		voice=(Button)findViewById(R.id.button2);
		news=(Button)findViewById(R.id.button3);
		app=(Button)findViewById(R.id.button4);
		text.setOnClickListener(this);
		voice.setOnClickListener(this);
		news.setOnClickListener(this);
		app.setOnClickListener(this);
		
		
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{
			case R.id.button1:
				Intent ma=new Intent("com.example.texttosignlanguage.MAINACTIVITY");
				startActivity(ma);
				break;
			case R.id.button2:
				Intent obj=new Intent("com.example.texttosignlanguage.VOICEREG");
				startActivity(obj);
				break;
			case R.id.button3:
				Intent nes=new Intent("com.example.texttosignlanguage.NEWS");
				startActivity(nes);
				break;
			case R.id.button4:
				Intent about=new Intent("com.example.texttosignlanguage.ABOUT");
			    startActivity(about);
				break;
		}
		
	}
	
	

}
