package com.example.coursework;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdentifyTheBreed extends AppCompatActivity {
    ImageView itbImageview; //to display the image
    TextView itbTextview, itbTextview2, itbTextview3; //correct or wrong, breedname, timer
    Spinner itbSpinner;//list of the breed name
    String resource = "";//get the name of the breed name from array
    Button itbButton;//submit or next button
    String A = "";//for breed code and the breed name
    String itbTimer = MainActivity.timswitch;//get the time from mainactivity
    CountDownTimer yourCountDownTimer;//timer
    ProgressBar itbpb; //time bar for the activity
    private int progressStatus1 = 0;
    //The number of milliseconds in the future from the
    //call to start() until the count down is done
    private long millisInFuture = MainActivity.num * 1000; //20 seconds (make it dividable by 1000)
    //The interval along the way to receive onTick() callbacks
    private long countDownInterval = 1000;
    final int progressBarMaximumValue = (int) (millisInFuture / countDownInterval);

    //Spinner method
    public void gtcSpinner() {

        // Creating a list inside spinner method to add the breeds inside the spinner
        itbSpinner = (Spinner) findViewById(R.id.itbspinner);
        List<String> list = new ArrayList<>();
        String dogName="";

        for (int i = 0; i < Breeds.names.length; i++) {
            String array1[]= Breeds.names[i].split("_"); // ignores the characters from "_" by splitting
            for (int j=0;j<array1.length;j++){
                dogName=array1[0];
            }
            int dogcount=0;
            for (int j=0;j<list.size();j++){
                if (list.get(j).toLowerCase().equals(dogName)){
                    dogcount++;
                }

            }
            if (dogcount==0){
                list.add(Capitallize.capitalizeWord(dogName));
            }


        }

        // getting the spinner in adapter view
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itbSpinner.setAdapter(dataAdapter);
    }

    // defining the onclick actions for this activity
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identifythebreed);
        itbImageview = (ImageView) findViewById(R.id.itbimageview);
        itbTextview = (TextView) findViewById(R.id.itbtextview);
        itbTextview2 = (TextView) findViewById(R.id.itbtextview2);
        itbTextview3 = (TextView) findViewById(R.id.itbtextview3);
        itbButton = (Button) findViewById(R.id.itbbutton);
        itbTextview.setText("");
        gtcSpinner();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        itbpb = (ProgressBar) findViewById(R.id.pb);

        itbpb.setMax(progressBarMaximumValue);

        setTitle("Identify The Breed");
        //Timer work if the toggle bar is on
        if (itbTimer.equals("yes")) {

            itbpb.setVisibility(View.VISIBLE);
            itbpb.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#05C148")));
            yourCountDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {

                // Set the timer bar with alerting colors
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                public void onTick(long millisUntilFinished) {
                    long millisUntilFinished1 = millisUntilFinished / 1000 + 1;
                    itbTextview2.setText("Time Remaining: " + millisUntilFinished1 + "sec");
                    progressStatus1 += 1;
                    itbpb.setProgress(progressStatus1);
                    if (progressStatus1 >= progressBarMaximumValue - 7 && progressStatus1 < progressBarMaximumValue - 3)
                        itbpb.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
                    if (progressStatus1 >= progressBarMaximumValue - 3)
                        itbpb.setProgressTintList(ColorStateList.valueOf(Color.RED));
                }

                // finish the activity with suitable correct or wrong answer
                public void onFinish() {
                    itbTextview.setText("");
                    itbTextview2.setText("");
                    progressStatus1 = 0;
                    itbpb.setVisibility(View.INVISIBLE);

                    if (itbSpinner.getSelectedItem().toString().toLowerCase().equals(A)) {
                        itbTextview.setText("Correct");
                        itbTextview.setTextColor(Color.GREEN);
                    } else {
                        itbTextview.setText("Wrong");
                        itbTextview.setTextColor(Color.RED);
                        itbTextview3.setText(Capitallize.capitalizeWord(A));
                        itbTextview3.setTextColor(Color.BLUE);
                    }
                    itbButton.setText("Next");

                }
            }.start();
        } else {

            itbTextview2.setText("");
            itbpb.setVisibility(View.INVISIBLE);
        }


        //get the Breed from array
        resource = Breeds.names[new Random().nextInt(Breeds.names.length)];
        Log.d("dog name",resource);
        //  The image id from resource
        int resource_id = getResources().getIdentifier(resource, "drawable",
                "com.example.coursework");
        //Add the image
        itbImageview.setImageResource(resource_id);
        //Breed name for the Id

        String array1[]=resource.split("_");
        for (int j=0;j<array1.length;j++){
            A=array1[0];
        }
        itbButton
                .setText("submit");
        itbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itbButton.getText().toString().equals("submit")) {
                    if (itbSpinner.getSelectedItem().toString().toLowerCase().equals(A)) {
                        itbTextview.setText("Correct");
                        itbTextview.setTextColor(Color.parseColor("#05C148"));


                    } else {
                        itbTextview.setText("Wrong");
                        itbTextview.setTextColor(Color.RED);
                        itbTextview3.setText(Capitallize.capitalizeWord(A));
                        itbTextview3.setTextColor(Color.BLUE);
                    }
                    itbButton.setText("Next");
                    if (itbTimer.equals("yes")) {
                        yourCountDownTimer.cancel();
                    }
                    itbTextview2.setText("");
                    itbpb.setVisibility(View.INVISIBLE);
                } else {
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
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();

        }
        return super.onOptionsItemSelected(item);
    }
}