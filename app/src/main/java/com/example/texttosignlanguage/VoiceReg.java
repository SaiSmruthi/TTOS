package com.example.texttosignlanguage;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class VoiceReg extends Activity implements OnClickListener {
	
	Button capture,go,back,conv;
	ListView lv;
	TextView text;
	String arr;
	final static int check=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voicereg);
		initialise();
	}

	private void initialise() {
		// TODO Auto-generated method stub
		
		capture=(Button)findViewById(R.id.button1);
		go=(Button)findViewById(R.id.button2);
		back=(Button)findViewById(R.id.button3);
        conv=(Button)findViewById(R.id.button);
		text=(TextView)findViewById(R.id.textView1);
		lv=(ListView)findViewById(R.id.listView1);
		capture.setOnClickListener(this);
		go.setOnClickListener(this);
        conv.setOnClickListener(this);
		back.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{
			case R.id.button1:
				Intent obj=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				obj.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM );
				obj.putExtra(RecognizerIntent.EXTRA_PROMPT,"COME ON SPEAK !!");
				startActivityForResult(obj,check);
				break;
			case R.id.button2:
				
				String val=(String) text.getText();
				Bundle b=new Bundle();
				b.putString("NAME",val);
				Intent send=new Intent(VoiceReg.this,Image.class);
				send.putExtras(b);
				startActivity(send);
				break;
            case R.id.button:

                String vall=(String) text.getText();
                Bundle bb=new Bundle();
                bb.putString("NAME",vall);
                Intent ssend=new Intent(VoiceReg.this,NewsNormal.class);
                ssend.putExtras(bb);
                startActivity(ssend);
                break;
				
			case R.id.button3:
				Intent ma=new Intent("com.example.texttosignlanguage.ACTIVITIES");
				startActivity(ma);
				break;

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==check && resultCode==RESULT_OK)
		{
			ArrayList<String> res= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, res));
			
			lv.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
					// TODO Auto-generated method stub
					
					arr= lv.getItemAtPosition(arg2).toString().trim();
					text.setText(arr);
					
				}
				
			});
		}
	}

	
	

}
