package com.example.texttosignlanguage;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class NewsBoth extends Activity implements OnClickListener{
	
	Button replay,back;
	ImageView imag;
	String []pattern;
	String arr,get,getsmall;
	String n="ndtv";
	boolean once=true;
	TextView t;
	int i,gi=0;
	TextToSpeech tts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image);
		initialise();
        tts=new TextToSpeech(NewsBoth.this,new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status!=TextToSpeech.ERROR)
                {
                    tts.setLanguage(Locale.US);
                    makegif3();
                }

            }
        });
        //makegif3();
       // replay.performClick();

        //replay.performClick();
        //gi=0;
        //makegif();

	}

	private void initialise() {
		// TODO Auto-generated method stub

        back=(Button)findViewById(R.id.button1);
        replay=(Button)findViewById(R.id.button2);
        imag=(ImageView)findViewById(R.id.imageView);
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
    private void makegif3()
    {
        //tts.setPitch(1.0f);
        String chu="processingplease";
        final int id = getResources().getIdentifier("com.example.texttosignlanguage:drawable/" +chu, null, null);
        imag.setImageResource(id);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400, 400);
        imag.setLayoutParams(layoutParams);
        tts.setSpeechRate(0.2f);
        tts.speak(getsmall,TextToSpeech.QUEUE_FLUSH,null);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{
			case R.id.button1:
					Intent a=new Intent("com.example.texttosignlanguage.ACTIVITIES");
					startActivity(a);
					break;
			case R.id.button2:
                gi=0;
					makegif3();
					break;
		}
		
	}

	private void makegif() {
		// TODO Auto-generated method stub
		
		Thread timer=new Thread(){
			public void run(){
				try{
				//	int j=1,nest=1;
				//	boolean k=true;
					for(i=0;i<pattern.length;i++)
					{
						once=true;
						

							//final int id = getResources().getIdentifier("com.example.texttosignlanguage:drawable/" +pattern[i], null, null);
							//final int one = getResources().getIdentifier("com.example.texttosignlanguage:drawable/" +"ndtv2", null, null);
							//if(id!=0)
							//{
								try
								{
								
		                        runOnUiThread(new Runnable() {
		                            public void run() {
		                            	
		                            	 if(once)
		                                 {
		                                	 tts.setPitch(1.1f);
                                             tts.speak(pattern[i],TextToSpeech.QUEUE_FLUSH,null);

		                                	 once=false;
		                                 }
		                                // sign.setImageResource(id);
		                               //  news.setImageResource(one);
		                  
		                                
		                            }
		                        });
		                        sleep(1000);
								}
								catch (InterruptedException e)
			                    {
									
			                        e.printStackTrace();
			                    }
								finally{
									//k=false;
								}
							}

				}catch(Exception e)
				{
					
				}
			}
			
		};
		timer.start();  
		
		
		
	}


    private void makegif1() {
        // TODO Auto-generated method stub

        try {
            once = true;


            runOnUiThread(new Runnable() {
                public void run() {


                    if (once) {
                        tts.speak(pattern[gi], TextToSpeech.QUEUE_FLUSH, null);
                        once = false;
                    }

                    gi++;
                    //Toast.makeText(getApplicationContext(), "Video completed", Toast.LENGTH_LONG).show();
                    if (gi < pattern.length)
                        makegif();
                }
            });
            sleep(1000);
        }catch (InterruptedException e)
        {

        }finally {

        }

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


}
