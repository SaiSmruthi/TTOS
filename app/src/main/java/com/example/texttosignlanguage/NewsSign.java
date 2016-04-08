package com.example.texttosignlanguage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewsSign extends Activity implements OnClickListener{

	Button back,replay;
	ImageView image;
	String []pattern;
	String arr,get,getsmall;
	TextView t;
	TextToSpeech tts;
	int i;
	boolean once=true;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image);
		initialise();
		makegif();
		tts=new TextToSpeech(NewsSign.this,new TextToSpeech.OnInitListener() {
			
			@Override
			public void onInit(int status) {
				// TODO Auto-generated method stub
				if(status!=TextToSpeech.ERROR)
				{
					tts.setLanguage(Locale.US);
				}
				
			}
		});
    }
	
	
    
	private void makegif() {
		// TODO Auto-generated method stub
		
		Thread timer=new Thread(){
			public void run(){
				try{
					int j=1;
					boolean k=true;
					for(i=0;i<pattern.length;i++)
					{
						once=true;
						
						while(k)
						{
							final int id = getResources().getIdentifier("com.example.texttosignlanguage:drawable/" +pattern[i], null, null);
							if(id!=0)
							{
								try
								{
								
		                        runOnUiThread(new Runnable() {
		                            public void run() {
		                            	
		                            	 if(once)
		                                 {
		                                	 tts.speak(pattern[i],TextToSpeech.QUEUE_FLUSH,null);
		                                	 once=false;
		                                 }
		                                 image.setImageResource(id);
		                                 LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400, 400);
		                                 image.setLayoutParams(layoutParams);
		                                
		                            }
		                        });
		                        sleep(1000);
								}
								catch (InterruptedException e)
			                    {
									
			                        e.printStackTrace();
			                    }
								finally{
									k=false;
								}
							}
							else
							{
								while(k)
								{
									StringBuilder pat=new StringBuilder(50);
									pat.append(pattern[i]);
									pat.append(j);
									final int ids = getResources().getIdentifier("com.example.texttosignlanguage:drawable/" +pat, null, null);
									if(ids!=0)
									{
										try
										{
									
				                        runOnUiThread(new Runnable() {
				                            public void run() {
				                            	if(once)
				                                 {
                                                     tts.setSpeechRate(0.2f);
				                                	 tts.speak(pattern[i],TextToSpeech.QUEUE_FLUSH,null);
				                                	 once=false;
				                                 }
				                                 image.setImageResource(ids);
				                                 LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400, 400);
				                                 image.setLayoutParams(layoutParams);
				                                 
				                            }
				                        });
				                        sleep(1000);
										}
										catch (InterruptedException e)
					                    {
					                        e.printStackTrace();
					                    }
									
									}
									else
									{
										k=false;
									
									}
									j++;
									
								}
							}
						}
						j=1;
						k=true;
					}
					
				}catch(Exception e)
				{
					
				}
			}
			
		};
		timer.start(); 
		
	}



	private void initialise() {
		// TODO Auto-generated method stub
		back=(Button)findViewById(R.id.button1);
		replay=(Button)findViewById(R.id.button2);
		image=(ImageView)findViewById(R.id.imageView);
		Bundle b=getIntent().getExtras();
    	arr=(String)b.get("NAME");
    	final TextView t=(TextView)findViewById(R.id.message);
    	t.setText(arr.toUpperCase());
    	get=(String)t.getText();
    	getsmall=get.toLowerCase();
    	pattern=getsmall.split(" ");
    	back.setOnClickListener(this);
    	replay.setOnClickListener(this);
		
	}



	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		
		if(tts!=null)
		{
			tts.stop();
			tts.shutdown();
		}
		super.onPause();
		finish();
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{
			case R.id.button1:
				Intent ma=new Intent("com.example.texttosignlanguage.ACTIVITIES");
				startActivity(ma);
				break;
			case R.id.button2:
				makegif();
				break;
		
		}
		
	}
}
