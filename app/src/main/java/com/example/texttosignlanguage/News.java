package com.example.texttosignlanguage;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class News extends Activity implements OnClickListener {
	
	
	EditText text;
	Button normal,sign,both,spells;
	String arr;
	String pattern[];
	int i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news);
		initialise();
	}

	private void initialise() {
		// TODO Auto-generated method stub
		
		text=(EditText)findViewById(R.id.editText1);
		normal=(Button)findViewById(R.id.button1);
		sign=(Button)findViewById(R.id.button2);
		both=(Button)findViewById(R.id.button3);
        spells=(Button)findViewById(R.id.button4);
		normal.setOnClickListener(this);
		sign.setOnClickListener(this);
		both.setOnClickListener(this);
        spells.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{
			case R.id.button1:
				Bundle a=new Bundle();
				arr=text.getText().toString();
				a.putString("NAME",arr);
				Intent i=new Intent(this,NewsNormal.class);
				i.putExtras(a);
				startActivity(i);
				break;
			case R.id.button2:
				Bundle b=new Bundle();
				arr=text.getText().toString();
				b.putString("NAME",arr);
				Intent image=new Intent(this,NewsSign.class);
				image.putExtras(b);
				startActivity(image);
				break;
			case R.id.button3:
				Bundle c=new Bundle();
				arr=text.getText().toString();
				c.putString("NAME",arr);
				Intent p=new Intent(this,NewsBoth.class);
				p.putExtras(c);
				startActivity(p);
				break;
            case R.id.button4:
                Bundle ccc=new Bundle();
                arr=text.getText().toString();
                ccc.putString("NAME",arr);
                Intent ppp=new Intent(this,Finger.class);
                ppp.putExtras(ccc);
                startActivity(ppp);
                break;
		}
		
	}

	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		
		
		super.onPause();
		finish();
	}

	
	

}
