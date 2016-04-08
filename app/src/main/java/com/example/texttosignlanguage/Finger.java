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
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.widget.MediaController;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.view.View.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import static java.lang.Thread.sleep;

public class Finger extends Activity implements OnClickListener,SurfaceHolder.Callback{

    Button back, replay;
    //ImageView image;
    VideoView mVideoView;
    int i;
    int gi = 0;
    String[] pattern;
    String arr, get, getsmall;
    String news = "ndtv";
    TextView t;
    TextToSpeech tts;

    boolean once = true;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        initialise();
        //makegif();
        //gi=0;
        //showVideo();

        tts = new TextToSpeech(Finger.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                }

            }
        });
        try {
            makegif();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void makegif() throws InterruptedException {

        final String dataResourceDirectory = "raw";
        // String[] dataResoruceFilename = pattern;
        final VideoView vd = (VideoView) findViewById(R.id.videoView);
        final MediaController mc = new MediaController(this);
        final int id = getResources().getIdentifier(pattern[gi], "raw", getPackageName());
        // TODO Auto-generated method stub

        //  Thread timer = new Thread() {
        //    public void run() {
        //      try {



        once = true;


        //final int id = getResources().getIdentifier("com.example.texttosignlanguage:drawable/" + "ndtv", null, null);
        if (id != 0) {
            try {

                runOnUiThread(new Runnable() {
                    public void run() {


                        Uri uri = Uri.parse("android.resource://" + getApplication().getPackageName() + "/" + dataResourceDirectory + "/" + pattern[gi]);


                        vd.setMediaController(mc);
                        vd.setVideoURI(uri);
                        vd.start();
                        if (once) {
                            tts.setSpeechRate(0.2f);
                            tts.speak(pattern[gi], TextToSpeech.QUEUE_FLUSH, null);
                            once = false;
                        }
                        vd.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                gi++;
                                //Toast.makeText(getApplicationContext(), "Video completed", Toast.LENGTH_LONG).show();
                                if (gi < pattern.length)
                                    //showVideo();
                                    try {
                                        makegif();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                            }
                        });



                        //image.setImageResource(id);
                        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400, 400);
                        //image.setLayoutParams(layoutParams);

                    }
                });
                // sleep(0);
            } catch (Exception e) {

                e.printStackTrace();
            } finally {

            }
        } else {


            sleep(1000);
            gi++;
            if (gi < pattern.length)
                makegif();
            //showVideo();
        }





//                } catch (Exception e) {

        //    }
        //  }

        // };
        //timer.start();

    }




    private void initialise() {
        //TODO Auto-generated method stub
        back = (Button) findViewById(R.id.button1);
        replay = (Button) findViewById(R.id.button2);
        //image = (ImageView) findViewById(R.id.imageView);

        Bundle b = getIntent().getExtras();
        arr = (String) b.get("NAME");
        final TextView t = (TextView) findViewById(R.id.message);
        t.setText(arr.toUpperCase());
        get = (String) t.getText();
        getsmall = get.toLowerCase();
        pattern = getsmall.split("");
        back.setOnClickListener(this);
        replay.setOnClickListener(this);

    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub

        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
        finish();
    }





    private void showVideo() {
        String dataResourceDirectory = "raw";
        // String[] dataResoruceFilename = pattern;
        VideoView vd = (VideoView) findViewById(R.id.videoView);
        final int id = getResources().getIdentifier(pattern[gi], "raw", getPackageName());
        if (id != 0) {
            Uri uri = Uri.parse("android.resource://" + getApplication().getPackageName() + "/" + dataResourceDirectory + "/" + pattern[gi++]);

            MediaController mc = new MediaController(this);
            vd.setMediaController(mc);
            vd.setVideoURI(uri);
            vd.start();
            vd.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    //Toast.makeText(getApplicationContext(), "Video completed", Toast.LENGTH_LONG).show();
                    if (gi < pattern.length)
                        showVideo();

                }
            });
        } else {
            //Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
            gi++;
            if (gi < pattern.length)
                showVideo();
        }
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.button1:
                Intent ma = new Intent("com.example.texttosignlanguage.ACTIVITIES");
                startActivity(ma);
                break;
            case R.id.button2:
                // makegif();
                gi=0;
                //showVideo();
                try {
                    makegif();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;


        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}