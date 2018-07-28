package com.example.surinder.homerentapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PostAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_add);



        //to enable the action bar on the top

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
