package com.example.coursework;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView itbImageview;
    TextView textView;
    Button identifyTheBreed,identifyTheDog,searchDogBreeds; // main screen buttons
    ImageView imageView;
    Animation frombottom,fromtop; // preview animation
    public static int num;

    Switch timSwitch;
    public static String timswitch="no";

    // defining the onclick actions for this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        identifyTheBreed =(Button)findViewById(R.id.identifythebreed);
        searchDogBreeds =(Button)findViewById(R.id.searchdogbreeds);
        identifyTheDog =(Button)findViewById(R.id.identifythedog);
        imageView=(ImageView)findViewById(R.id.imageView);
        frombottom= AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop= AnimationUtils.loadAnimation(this,R.anim.fromtop);
        timSwitch = (Switch) findViewById(R.id.switch2);
        identifyTheBreed.setAnimation(frombottom);
        searchDogBreeds.setAnimation(frombottom);
        identifyTheDog.setAnimation(frombottom);
        timSwitch.setAnimation(frombottom);
        imageView.setAnimation(fromtop);
        timSwitch.setChecked(false);

        timSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked ) {
                if (isChecked==true){
                    timswitch="yes";
                    ShowDialog();
                }else {

                    timswitch="no";
                }
            }
        });


        // initializing the identifyTheBreed activity inside main activity
       identifyTheBreed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent StartIntent=new Intent(getApplicationContext(), IdentifyTheBreed.class);
                startActivity(StartIntent);
            }
        });

        // initializing the identifyTheDog activity inside main activity
        identifyTheDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               Intent StartIntent=new Intent(getApplicationContext(), IdentifyTheDog.class);
                startActivity(StartIntent);
            }
        });

        // initializing the searchDogBreeds activity inside main activity
        searchDogBreeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent StartIntent=new Intent(getApplicationContext(), SearchDogBreeds.class);
                startActivity(StartIntent);
            }
        });

    }

    // Set the timer
    public void ShowDialog()
    {

        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        final View Viewlayout = inflater.inflate(R.layout.customalert,
                (ViewGroup) findViewById(R.id.layout_dialog));

        final TextView item1 = (TextView)Viewlayout.findViewById(R.id.txtItem1); // txtItem1

        popDialog.setTitle("Set timer");
        popDialog.setView(Viewlayout);

        //  seekBar1
        final SeekBar seek1 = (SeekBar) Viewlayout.findViewById(R.id.seekBar1);
        seek1.setProgress(10);
        seek1.setMax(50);

        item1.setText("10 sec");

        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                //Do something here with new value
                item1.setText( progress+"sec");
            }

            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }
        });

        // Button OK
        popDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        num=seek1.getProgress();
                        if(num<10){
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "please select up to 10 sec",
                                    Toast.LENGTH_SHORT);

                            toast.show();
                            timSwitch.setChecked(false);
                        }
                        else {
                            dialog.dismiss();
                        }

                    }

                });


        popDialog.create();
        popDialog.show();

    }

}
