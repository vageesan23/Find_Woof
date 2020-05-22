package com.example.coursework;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SearchDogBreeds extends AppCompatActivity {
    ViewPager viewPager;
    ViewPageAdapter viewPageAdapter; // make slide show effect in this activity
    int j=0;
    public static ArrayList list=new ArrayList<Integer>(); //store the breeds in the array
    String dogName=""; //adding images of selected breed
    EditText searchText; //search for a breed
    Button search,cancel; // search and stop action buttons
    Timer timer; //count timer

    // defining the onclick actions for this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dog_breeds);
        searchText=findViewById(R.id.searchTextView);
        search=findViewById(R.id.searchButton);
        cancel=findViewById(R.id.cancel);
        viewPager = findViewById(R.id.viewPager);
        searchText.setText(dogName);
        setTitle("Search Dog Breeds");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();

                // if search name is already exist in the breeds array then adding searched breed into new array
                for (int i=0;i<Breeds.names.length;i++){

                    String array1[]= Breeds.names[i].split("_");
                    for (int j=0;j<array1.length;j++){
                        dogName=array1[0];
                    }
                    if (searchText.getText().toString().toLowerCase().equals(dogName)) {
                        Log.d("dogs", Breeds.names[i]);
                        int resource_id = getResources().getIdentifier(Breeds.names[i], "drawable",
                                "com.example.coursework");
                        list.add(resource_id);
                        j=0;

                    }
                }

                // Makes image slideshow of searched breed
                viewPageAdapter = new ViewPageAdapter(SearchDogBreeds.this);
                viewPager.setAdapter(viewPageAdapter);
                // each 5 seconds images getting changed
                timer = new Timer();
                timer.scheduleAtFixedRate(new MyTimetTask(),5000,5000 );

            }
        });


        // clear the activity once the cancel button is clicked
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                timer.cancel();
                finish();
                startActivity(intent);
            }
        });

    }

    public class MyTimetTask  extends TimerTask{

        @Override
        public void run() {
            SearchDogBreeds.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    j++;
                    for (int i=0;i<list.size();i++){


                        if (i==j){
                            viewPager.setCurrentItem(i);
                            System.out.println("i"+i);
                            System.out.println("j"+j);
                        }

                        if (j==list.size()-1){
                            j=0;
                        }
                    }
                }
            });
        }
    }

}
