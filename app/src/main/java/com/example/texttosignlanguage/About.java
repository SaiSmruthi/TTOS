package com.example.texttosignlanguage;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class About extends Activity{
	
	Button back;
	TextView title,text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app);
		initalise();
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent ma=new Intent("com.example.texttosignlanguage.ACTIVITIES");
			startActivity(ma);
				
			}
		});
		
	}

	private void initalise() {
		// TODO Auto-generated method stub
		back=(Button)findViewById(R.id.button1);
		title=(TextView)findViewById(R.id.textView1);
		text=(TextView)findViewById(R.id.textView2);
		Random num=new Random();
		title.setTextColor(Color.rgb(num.nextInt(265),num.nextInt(265),num.nextInt(265)));
		text.setTextColor(Color.rgb(num.nextInt(265),num.nextInt(265),num.nextInt(265)));
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	

}
