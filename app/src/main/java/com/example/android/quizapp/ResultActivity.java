package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import java.util.jar.Attributes;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView nasa =  (TextView) findViewById(R.id.nasa);
        nasa.setMovementMethod(LinkMovementMethod.getInstance());

        TextView beforeTheFlood =  (TextView) findViewById(R.id.btf);
        beforeTheFlood.setMovementMethod(LinkMovementMethod.getInstance());

        TextView nationalGeographic =  (TextView) findViewById(R.id.ng);
        nationalGeographic.setMovementMethod(LinkMovementMethod.getInstance());

        //determining from where is the data (username and score) coming.
        if(getIntent().getStringExtra("bName") != null){

            String Name = getIntent().getStringExtra("bName");
            int bscore = getIntent().getIntExtra("bScore", 0);
            float percentscore = (bscore * 100) / 11;
            int percent = Math.round(percentscore);
            TextView userName = findViewById(R.id.userName);
            userName.setText(Name);

            TextView score = findViewById(R.id.score);
            score.setText(String.valueOf(percent) + "%");

        }
        else{
            String Name = getIntent().getStringExtra("aName");
            int ascore = getIntent().getIntExtra("aScore", 0);
            float percentscore = (ascore * 100) / 9;
            int percent = Math.round(percentscore);
            TextView userName = findViewById(R.id.userName);
            userName.setText(Name);

            TextView score = findViewById(R.id.score);
            score.setText(String.valueOf(percent) + "%");

        }




    }
}