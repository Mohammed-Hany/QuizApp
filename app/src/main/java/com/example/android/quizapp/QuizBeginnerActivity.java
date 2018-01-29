package com.example.android.quizapp;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizBeginnerActivity extends AppCompatActivity {

    int beginnerScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_beginner);
        bHideReferences();

        Button submit = findViewById(R.id.bSubmitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pop a message up when an answer is missing.
                if (bCheckMissingAnswers()){
                    Toast.makeText(QuizBeginnerActivity.this, "Please Answer All Questions.", Toast.LENGTH_SHORT).show();
                }
                else{
                    bShowReferences();
                    bGetAnswers(1, R.id.q1Radio);
                    bGetAnswers(1, R.id.q2Radio);
                    bGetAnswers(1, R.id.q3Radio);
                    bGetAnswers(1, R.id.q4Radio);
                    bGetAnswers(1, R.id.q7Radio);
                    bGetAnswers(1, R.id.q8Radio);
                    bGetAnswers(1, R.id.q9Radio);
                    bGetAnswers(2, R.id.ck1);
                    bGetAnswers(2, R.id.ck2);
                    bGetAnswers(2, R.id.ck3);
                    bGetAnswers(2, R.id.ck4);
                    bGetAnswers(3, R.id.editText);


                }

            }
        });

        //Passing the username and score to the result activity and launching.
        Button next = findViewById(R.id.bNextButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = getIntent().getStringExtra("bUName");
                Intent result = new Intent(QuizBeginnerActivity.this , ResultActivity.class);
                result.putExtra("bName", uName);
                result.putExtra("bScore", beginnerScore);
                startActivity(result);
            }
        });
    }

    // this method blocks all the choices and determines whether the wrong or the right answers are clicked.
    public void bGetAnswers(int type, int id) {

        if(type == 1){
            RadioGroup group = findViewById(id);
            if (group.getCheckedRadioButtonId() != R.id.answer1 || group.getCheckedRadioButtonId() != R.id.answer2 || group.getCheckedRadioButtonId() != R.id.answer3 || group.getCheckedRadioButtonId() != R.id.answer4 || group.getCheckedRadioButtonId() != R.id.answer5 || group.getCheckedRadioButtonId() != R.id.answer6 || group.getCheckedRadioButtonId() != R.id.answer7) {
                RadioButton radioButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());
                radioButton.setBackgroundColor(0xCCE53935);
            }
            if (group.getCheckedRadioButtonId() == R.id.answer1 || group.getCheckedRadioButtonId() == R.id.answer2 || group.getCheckedRadioButtonId() == R.id.answer3 || group.getCheckedRadioButtonId() == R.id.answer4 || group.getCheckedRadioButtonId() == R.id.answer5 || group.getCheckedRadioButtonId() == R.id.answer6 || group.getCheckedRadioButtonId() == R.id.answer7) {
                beginnerScore ++;
            }

                for (int i = 0; i < group.getChildCount(); i++) {
                group.getChildAt(i).setEnabled(false);

                if (group.getChildAt(i).getId() == R.id.answer1 || group.getChildAt(i).getId() == R.id.answer2 || group.getChildAt(i).getId() == R.id.answer3 || group.getChildAt(i).getId() == R.id.answer4 || group.getChildAt(i).getId() == R.id.answer5 || group.getChildAt(i).getId() == R.id.answer6 || group.getChildAt(i).getId() == R.id.answer7) {
                    group.getChildAt(i).setBackgroundColor(0xCC43A047);
                }
            }
        }


        if (type == 2) {
            CheckBox checkBox = findViewById(id);
            checkBox.setEnabled(false);
            if (id == R.id.ck1 || id == R.id.ck2 || id == R.id.ck4) {
                checkBox.setBackgroundColor(0xCC43A047);
                if(checkBox.isChecked()){
                    beginnerScore ++;
                }
            }
            else {
                if (checkBox.isChecked()) {
                    checkBox.setBackgroundColor(0xCCE53935);
                }
            }
        }
        if (type == 3){
            EditText editText = findViewById(id);
            editText.setEnabled(false);
            if (editText.getText().toString().toLowerCase().equals("china")) {
                editText.setBackgroundColor(0xCC43A047);
                beginnerScore ++;
            }
            else {
                editText.setBackgroundColor(0xCCE53935);
            }
        }

    }
    // this method checks whether if an answer is missing
    public boolean bCheckMissingAnswers(){
        RadioGroup radio1 = findViewById(R.id.q1Radio);
        RadioGroup radio2 = findViewById(R.id.q2Radio);
        RadioGroup radio3 = findViewById(R.id.q3Radio);
        RadioGroup radio4 = findViewById(R.id.q4Radio);
        RadioGroup radio5 = findViewById(R.id.q7Radio);
        RadioGroup radio6 = findViewById(R.id.q8Radio);
        RadioGroup radio7 = findViewById(R.id.q9Radio);
        CheckBox box1 = findViewById(R.id.ck1);
        CheckBox box2 = findViewById(R.id.ck2);
        CheckBox box3 = findViewById(R.id.ck3);
        CheckBox box4 = findViewById(R.id.ck4);
        EditText text = findViewById(R.id.editText);
        if(radio1.getCheckedRadioButtonId() == -1 || radio2.getCheckedRadioButtonId() == -1 || radio3.getCheckedRadioButtonId() == -1 || radio4.getCheckedRadioButtonId() == -1 || radio5.getCheckedRadioButtonId() == -1 || radio6.getCheckedRadioButtonId() == -1 || radio7.getCheckedRadioButtonId() == -1 || (!box1.isChecked() && !box2.isChecked() && !box3.isChecked() && !box4.isChecked()) || text.getText().toString().length() == 0){
            return true;
        }
        else{
            return false;
        }

    }


//This method makes references hyperlinks and hide them with answers.
    public void bHideReferences(){
        TextView bAnswer1 = (TextView) findViewById(R.id.beginnerAnswer1);
        bAnswer1.setVisibility(View.GONE);

        TextView bAnswer2 = (TextView) findViewById(R.id.beginnerAnswer2);
        bAnswer2.setVisibility(View.GONE);

        TextView bAnswer3 = (TextView) findViewById(R.id.beginnerAnswer3);
        bAnswer3.setVisibility(View.GONE);

        TextView bAnswer4 = (TextView) findViewById(R.id.beginnerAnswer4);
        bAnswer4.setVisibility(View.GONE);

        TextView bAnswer5 = (TextView) findViewById(R.id.beginnerAnswer5);
        bAnswer5.setVisibility(View.GONE);

        TextView bAnswer6 = (TextView) findViewById(R.id.beginnerAnswer6);
        bAnswer6.setVisibility(View.GONE);

        TextView bAnswer7 = (TextView) findViewById(R.id.beginnerAnswer7);
        bAnswer7.setVisibility(View.GONE);

        TextView seeMore1 =  (TextView) findViewById(R.id.beginnerReference1);
        seeMore1.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore1.setVisibility(View.GONE);

        TextView seeMore2 =  (TextView) findViewById(R.id.beginnerReference2);
        seeMore2.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore2.setVisibility(View.GONE);

        TextView seeMore3 =  (TextView) findViewById(R.id.beginnerReference3);
        seeMore3.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore3.setVisibility(View.GONE);

        TextView seeMore4 =  (TextView) findViewById(R.id.beginnerReference4);
        seeMore4.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore4.setVisibility(View.GONE);

        TextView seeMore5 =  (TextView) findViewById(R.id.beginnerReference5);
        seeMore5.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore5.setVisibility(View.GONE);

        TextView seeMore6 =  (TextView) findViewById(R.id.beginnerReference6);
        seeMore6.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore6.setVisibility(View.GONE);

        TextView seeMore7 =  (TextView) findViewById(R.id.beginnerReference7);
        seeMore7.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore7.setVisibility(View.GONE);

        Button next = findViewById(R.id.bNextButton);
        next.setVisibility(View.GONE);

    }

//This method  shows answers and references.
    public void bShowReferences(){
        TextView seeMore1 =  (TextView) findViewById(R.id.beginnerReference1);
        seeMore1.setVisibility(View.VISIBLE);
        TextView bAnswer1 = (TextView) findViewById(R.id.beginnerAnswer1);
        bAnswer1.setVisibility(View.VISIBLE);

        TextView seeMore2 =  (TextView) findViewById(R.id.beginnerReference2);
        seeMore2.setVisibility(View.VISIBLE);
        TextView bAnswer2 = (TextView) findViewById(R.id.beginnerAnswer2);
        bAnswer2.setVisibility(View.VISIBLE);

        TextView seeMore3 =  (TextView) findViewById(R.id.beginnerReference3);
        seeMore3.setVisibility(View.VISIBLE);
        TextView bAnswer3 = (TextView) findViewById(R.id.beginnerAnswer3);
        bAnswer3.setVisibility(View.VISIBLE);

        TextView seeMore4 =  (TextView) findViewById(R.id.beginnerReference4);
        seeMore4.setVisibility(View.VISIBLE);
        TextView bAnswer4 = (TextView) findViewById(R.id.beginnerAnswer4);
        bAnswer4.setVisibility(View.VISIBLE);

        TextView seeMore5 =  (TextView) findViewById(R.id.beginnerReference5);
        seeMore5.setVisibility(View.VISIBLE);
        TextView bAnswer5 = (TextView) findViewById(R.id.beginnerAnswer5);
        bAnswer5.setVisibility(View.VISIBLE);

        TextView seeMore6 =  (TextView) findViewById(R.id.beginnerReference6);
        seeMore6.setVisibility(View.VISIBLE);
        TextView bAnswer6 = (TextView) findViewById(R.id.beginnerAnswer6);
        bAnswer6.setVisibility(View.VISIBLE);

        TextView seeMore7 =  (TextView) findViewById(R.id.beginnerReference7);
        seeMore7.setVisibility(View.VISIBLE);
        TextView bAnswer7 = (TextView) findViewById(R.id.beginnerAnswer7);
        bAnswer7.setVisibility(View.VISIBLE);

        Button next = findViewById(R.id.bNextButton);
        next.setVisibility(View.VISIBLE);

    }
}
