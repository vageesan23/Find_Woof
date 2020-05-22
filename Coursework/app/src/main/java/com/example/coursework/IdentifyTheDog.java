package com.example.coursework;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class IdentifyTheDog extends AppCompatActivity {
    ImageView itdImageview1,itdImageview2,itdImageview3;//Clickable Imageviewers
    String itdResource1,itdResource2,itdResource3,itdBreed1,itdBreed2,itdBreed3,itdBreed,itdBreedImage;//all the Breeds to compare with resource id
    TextView itdTextview, itdTextview2, itdTextview3;//correct/wrong,time,correctanswer
    ImageView itdImage;//correctimage
    ProgressBar itdpb;//progressbar
    Button itdButton;//submit or next button
    private int progressStatus1 = 0;
    private long millisInFuture = MainActivity.num*1000;
    private long countDownInterval = 1000;
    String itdTimer=MainActivity.timswitch;//get the time from mainactivity
    CountDownTimer yourCountDownTimer;//countdowntimer
    int progressBarMaximumValue = (int)(millisInFuture/countDownInterval);
    String dogName1,dogName2,dogName3;

    // defining the onclick actions for this activity
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identifythedog);

        itdImageview1 =(ImageView)findViewById(R.id.itdimageview1);
        itdImageview2 =(ImageView)findViewById(R.id.itdimageview2);
        itdImageview3 =(ImageView)findViewById(R.id.itdimageview3);
        itdTextview =(TextView)findViewById(R.id.itdtextview);
        itdTextview2 =(TextView)findViewById(R.id.itdtextview2);
        itdTextview3 =(TextView)findViewById(R.id.itdtextview3);
        itdImage =(ImageView)findViewById(R.id.itdimage);
        itdButton =(Button)findViewById(R.id.itdbutton);
        itdImage.setVisibility(View.INVISIBLE);
        itdButton.setText("Next");
        itdpb = (ProgressBar) findViewById(R.id.itdpb);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Identify The Dog");
        itdpb.setMax(progressBarMaximumValue);

        //Timer work if the toggle bar is on
        if(itdTimer.equals("yes")){
            itdpb.setVisibility(View.VISIBLE);
            itdpb.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#05C148")));
            yourCountDownTimer =  new CountDownTimer(millisInFuture, countDownInterval) {

                // Set the timer bar with alerting colors
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                public void onTick(long millisUntilFinished) {
                    long  millisUntilFinished1= millisUntilFinished / 1000+1;
                    progressStatus1 +=1;
                    itdpb.setProgress(progressStatus1);
                    itdTextview3.setText( "Time Remaining: "+millisUntilFinished1+"sec");
                    if(progressStatus1>=progressBarMaximumValue-7&&progressStatus1<progressBarMaximumValue-3)
                        itdpb.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
                    if (progressStatus1>=progressBarMaximumValue-3)
                        itdpb.setProgressTintList(ColorStateList.valueOf(Color.RED));

                }

                // finish the activity with displaying correct answer
                public void onFinish() {
                    progressStatus1 =0;
                    itdpb.setVisibility(View.INVISIBLE);
                    itdTextview3.setText("");
                    itdImageview1.setEnabled(false);
                    itdImageview2.setEnabled(false);
                    itdImageview3.setEnabled(false);
                    itdTextview2.setText("wrong");
                    itdTextview2.setTextColor(Color.RED);
                    itdImage.setVisibility(View.VISIBLE);
                    if (itdBreed1.equals(itdBreed)) {
                        int gtfresource_id2 = getResources().getIdentifier(dogName1, "drawable",
                                "com.example.coursework");
                        itdImage.setImageResource(gtfresource_id2);
                    }else
                    if (itdBreed2.equals(itdBreed)) {
                        int gtfresource_id2 = getResources().getIdentifier(dogName2, "drawable",
                                "com.example.coursework");
                        itdImage.setImageResource(gtfresource_id2);
                    }else {
                        int gtfresource_id2 = getResources().getIdentifier(dogName3, "drawable",
                                "com.example.coursework");
                        itdImage.setImageResource(gtfresource_id2);
                    }
                }
            }.start();
        }else {
            itdpb.setVisibility(View.INVISIBLE);
            itdTextview3.setText("");
        }

        dogName1 = Breeds.names[new Random().nextInt(Breeds.names.length)];
        int itdResource_id1 = getResources().getIdentifier(dogName1, "drawable",
                "com.example.coursework");
        itdImageview1.setImageResource(itdResource_id1);

        //If the image is already access
        String array1[]= dogName1.split("_");
        for (int j=0;j<array1.length;j++){
            itdResource1=array1[0];
        }
        for (int i=0;i<1;i++)
        {
            dogName2 = Breeds.names[new Random().nextInt(Breeds.names.length)];
            String array2[]= dogName2.split("_");
            for (int j=0;j<array2.length;j++){
                itdResource2=array2[0];
            }
            if(itdResource2 == itdResource1){
                i--;
            }
        }

        int itdResource_id2 = getResources().getIdentifier(dogName2, "drawable",
                "com.example.coursework");
        itdImageview2.setImageResource(itdResource_id2);
        for (int i=0;i<1;i++){
            dogName3 = Breeds.names[new Random().nextInt(Breeds.names.length)];
            String array3[]= dogName3.split("_");
            for (int j=0;j<array3.length;j++){
                itdResource3=array3[0];
            }
            // if images are different breeds
            if(itdResource3 == itdResource1 || itdResource3 == itdResource2){
                i--;
            }
        }

        // All image views should be 3 different breeds
        itdBreed1 = itdResource1;
        itdBreed2 = itdResource2;
        itdBreed3 = itdResource3;

        Log.d("Dog1", itdBreed1);
        Log.d("Dog2", itdBreed2);
        Log.d("Dog3", itdBreed3);


        int itdResource_id3 = getResources().getIdentifier(dogName3, "drawable",
                "com.example.coursework");
        itdImageview3.setImageResource(itdResource_id3);

        String names1[] = {itdBreed1, itdBreed2, itdBreed3};
        itdBreed = names1[new Random().nextInt(names1.length)];

        itdTextview.setText(Capitallize.capitalizeWord(itdBreed));
        itdBreedImage = itdBreed;

        // finish the activity with displaying correct or wrong answer
        itdImageview1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                itdImageview1.setEnabled(false);
                itdImageview2.setEnabled(false);
                itdImageview3.setEnabled(false);
                if(itdTimer.equals("yes")){
                    yourCountDownTimer.cancel();
                    itdTextview3.setText("");
                    progressStatus1=0;
                    itdpb.setVisibility(v.INVISIBLE);
                }
                if(itdBreed1.equals(itdBreed)) {
                    itdTextview2.setText("Correct");
                    itdTextview2.setTextColor(Color.parseColor("#05C148"));

                }else {
                    itdImage.setVisibility(View.VISIBLE);
                    itdTextview2.setText("Wrong");
                    itdTextview2.setTextColor(Color.RED);

                    if (itdBreed2.equals(itdBreed)) {
                        int gtfresource_id2 = getResources().getIdentifier(dogName2, "drawable",
                                "com.example.coursework");
                        itdImage.setImageResource(gtfresource_id2);
                    }else {
                        int gtfresource_id2 = getResources().getIdentifier(dogName3, "drawable",
                                "com.example.coursework");
                        itdImage.setImageResource(gtfresource_id2);
                    }


                }
            }
        });

        // finish the activity with displaying correct or wrong answer
        itdImageview2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                itdImageview1.setEnabled(false);
                itdImageview2.setEnabled(false);
                itdImageview3.setEnabled(false);
                if(itdTimer.equals("yes")){
                    yourCountDownTimer.cancel();
                    itdTextview3.setText("");
                    progressStatus1=0;
                    itdpb.setVisibility(v.INVISIBLE);
                }
                if(itdBreed2.equals(itdBreed)) {
                    itdTextview2.setText("Correct");
                    itdTextview2.setTextColor(Color.parseColor("#05C148"));

                }else {
                    itdImage.setVisibility(View.VISIBLE);
                    itdTextview2.setText("Wrong");
                    itdTextview2.setTextColor(Color.RED);
                    if (itdBreed3.equals(itdBreed)) {
                        int gtfresource_id2 = getResources().getIdentifier(dogName3, "drawable",
                                "com.example.coursework");
                        itdImage.setImageResource(gtfresource_id2);
                    }else {
                        int gtfresource_id2 = getResources().getIdentifier(dogName1, "drawable",
                                "com.example.coursework");
                        itdImage.setImageResource(gtfresource_id2);
                    }
                }
            }
        });

        // finish the activity with displaying correct or wrong answer
        itdImageview3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                itdImageview1.setEnabled(false);
                itdImageview2.setEnabled(false);
                itdImageview3.setEnabled(false);
                if(itdTimer.equals("yes")){
                    yourCountDownTimer.cancel();
                    itdTextview3.setText("");
                    progressStatus1=0;
                    itdpb.setVisibility(v.INVISIBLE);
                }
                if(itdBreed3.equals(itdBreed)) {
                    itdTextview2.setText("Correct");
                    itdTextview2.setTextColor(Color.parseColor("#05C148"));

                }else {
                    itdImage.setVisibility(View.VISIBLE);
                    itdTextview2.setText("Wrong");
                    itdTextview2.setTextColor(Color.RED);
                    if (itdBreed2.equals(itdBreed)) {
                        int gtfresource_id2 = getResources().getIdentifier(dogName2, "drawable",
                                "com.example.coursework");
                        itdImage.setImageResource(gtfresource_id2);
                    }else {
                        int gtfresource_id2 = getResources().getIdentifier(dogName1, "drawable",
                                "com.example.coursework");
                        itdImage.setImageResource(gtfresource_id2);
                    }

                }
            }
        });

        // checking if image was not clicked and alert
        itdButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(itdTextview2.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "please click the image",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }else {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }
        });

    }

    //back button in top
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id== android.R.id.home){
            this.finish();

        }
        return super.onOptionsItemSelected(item);
    }
}