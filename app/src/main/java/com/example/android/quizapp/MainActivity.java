package com.example.android.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //The spinner choices.
    String[] level = new String[]{
            "Choose...",
            "I have a little knowledge",
            "I have a good knowledge",
    };
    int spinnerItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Spinner level_spinner = (Spinner) findViewById(R.id.level_spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, level){

            //Disabling the first choice of the spinner to be set as hint
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }

            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level_spinner.setAdapter(adapter);
        level_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                switch (i){

                    case 1:
                        spinnerItem = 1;
                        break;
                    case 2:
                        spinnerItem = 2;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerItem = 0;
            }
        });
    }
    //Is called when start quiz button is clicked.
    public void startQuiz (View view){
        EditText userName = (EditText) findViewById(R.id.Name);
        String Name = userName.getText().toString();

        //Giving toast if there is something missing whether username or knowledge level.
         if (Name.length() >= 3 & spinnerItem > 0){


             if(spinnerItem == 1){
                Intent quizBeginner = new Intent(this, QuizBeginnerActivity.class);
                quizBeginner.putExtra("bUName", Name);
                startActivity(quizBeginner);
            }
            else{
                Intent quizAdvanced = new Intent(this, QuizAdvancedActivity.class);
                 quizAdvanced.putExtra("aUName", Name);
                 startActivity(quizAdvanced);

            }
        }
        else{
             if(Name.length() == 0 ){
                 Toast.makeText(this, "Please Enter A User Name.", Toast.LENGTH_SHORT).show();
             }
             if(Name.length() < 3 & Name.length() > 0 ){
                 Toast.makeText(this, "User Name Shouldn't Be Less Than 3 Characters.", Toast.LENGTH_SHORT).show();
             }
             if (spinnerItem == 0){
                 Toast.makeText(this, "Please Choose Your Knowledge Level.", Toast.LENGTH_SHORT).show();
             }
         }


    }


}

