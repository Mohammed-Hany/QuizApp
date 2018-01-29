package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizAdvancedActivity extends AppCompatActivity {

    int advancedScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_advanced);
        aHideReferences();

        Button submit = findViewById(R.id.aSubmitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pop a message up when an answer is missing.
                if (aCheckMissingAnswers()){
                    Toast.makeText(QuizAdvancedActivity.this, "Please Answer All Questions.", Toast.LENGTH_SHORT).show();
                }
                else{
                    aShowReferences();
                    aGetAnswers(1, R.id.q1RadioA);
                    aGetAnswers(1, R.id.q2RadioA);
                    aGetAnswers(1, R.id.q3RadioA);
                    aGetAnswers(1, R.id.q4RadioA);
                    aGetAnswers(1, R.id.q7RadioA);
                    aGetAnswers(1, R.id.q8RadioA);
                    aGetAnswers(2, R.id.ck1A);
                    aGetAnswers(2, R.id.ck2A);
                    aGetAnswers(2, R.id.ck3A);
                    aGetAnswers(2, R.id.ck4A);
                    aGetAnswers(3, R.id.editTextA);


                }

            }
        });

        //Passing the username and score to the result activity and launching.
        Button next = findViewById(R.id.aNextButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = getIntent().getStringExtra("aUName");
                Intent result = new Intent(QuizAdvancedActivity.this , ResultActivity.class);
                result.putExtra("aName", uName);
                result.putExtra("aScore", advancedScore);
                startActivity(result);
            }
        });
    }


    // this method blocks all the choices and determines whether the wrong or the right answers are clicked.
    public void aGetAnswers(int type, int id) {

        if(type == 1){
            RadioGroup group = findViewById(id);
            if (group.getCheckedRadioButtonId() != R.id.answer1A || group.getCheckedRadioButtonId() != R.id.answer2A || group.getCheckedRadioButtonId() != R.id.answer3A || group.getCheckedRadioButtonId() != R.id.answer4A || group.getCheckedRadioButtonId() != R.id.answer5A || group.getCheckedRadioButtonId() != R.id.answer6A ) {
                RadioButton radioButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());
                radioButton.setBackgroundColor(0xCCE53935);
            }
            if (group.getCheckedRadioButtonId() == R.id.answer1A || group.getCheckedRadioButtonId() == R.id.answer2A || group.getCheckedRadioButtonId() == R.id.answer3A || group.getCheckedRadioButtonId() == R.id.answer4A || group.getCheckedRadioButtonId() == R.id.answer5A || group.getCheckedRadioButtonId() == R.id.answer6A ) {
                advancedScore ++;
            }

            for (int i = 0; i < group.getChildCount(); i++) {
                group.getChildAt(i).setEnabled(false);

                if (group.getChildAt(i).getId() == R.id.answer1A || group.getChildAt(i).getId() == R.id.answer2A || group.getChildAt(i).getId() == R.id.answer3A || group.getChildAt(i).getId() == R.id.answer4A || group.getChildAt(i).getId() == R.id.answer5A || group.getChildAt(i).getId() == R.id.answer6A ) {
                    group.getChildAt(i).setBackgroundColor(0xCC43A047);
                }
            }
        }


        if (type == 2) {
            CheckBox checkBox = findViewById(id);
            checkBox.setEnabled(false);
            if (id == R.id.ck1A || id == R.id.ck3A) {
                checkBox.setBackgroundColor(0xCC43A047);
                if(checkBox.isChecked()){
                    advancedScore ++;
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
            if (editText.getText().toString().toLowerCase().equals("usa") || editText.getText().toString().toLowerCase().equals("united states") || editText.getText().toString().toLowerCase().equals("united states of america") || editText.getText().toString().toLowerCase().equals("america") ) {
                editText.setBackgroundColor(0xCC43A047);
                advancedScore ++;
            }
            else {
                editText.setBackgroundColor(0xCCE53935);
            }
        }

    }

    // this method checks whether if an answer is missing
    public boolean aCheckMissingAnswers(){
        RadioGroup radio1 = findViewById(R.id.q1RadioA);
        RadioGroup radio2 = findViewById(R.id.q2RadioA);
        RadioGroup radio3 = findViewById(R.id.q3RadioA);
        RadioGroup radio4 = findViewById(R.id.q4RadioA);
        RadioGroup radio5 = findViewById(R.id.q7RadioA);
        RadioGroup radio6 = findViewById(R.id.q8RadioA);
        CheckBox box1 = findViewById(R.id.ck1A);
        CheckBox box2 = findViewById(R.id.ck2A);
        CheckBox box3 = findViewById(R.id.ck3A);
        CheckBox box4 = findViewById(R.id.ck4A);
        EditText text = findViewById(R.id.editTextA);
        if(radio1.getCheckedRadioButtonId() == -1 || radio2.getCheckedRadioButtonId() == -1 || radio3.getCheckedRadioButtonId() == -1 || radio4.getCheckedRadioButtonId() == -1 || radio5.getCheckedRadioButtonId() == -1 || radio6.getCheckedRadioButtonId() == -1 || (!box1.isChecked() && !box2.isChecked() && !box3.isChecked() && !box4.isChecked()) || text.getText().toString().length() == 0){
            return true;
        }
        else{
            return false;
        }

    }

    //This method makes references hyperlinks and hide them with answers.
    public void aHideReferences(){
        TextView aAnswer1 = (TextView) findViewById(R.id.aAnswer1);
        aAnswer1.setVisibility(View.GONE);

        TextView aAnswer2 = (TextView) findViewById(R.id.aAnswer2);
        aAnswer2.setVisibility(View.GONE);

        TextView aAnswer3 = (TextView) findViewById(R.id.aAnswer3);
        aAnswer3.setVisibility(View.GONE);

        TextView aAnswer4 = (TextView) findViewById(R.id.aAnswer4);
        aAnswer4.setVisibility(View.GONE);

        TextView aAnswer5 = (TextView) findViewById(R.id.aAnswer5);
        aAnswer5.setVisibility(View.GONE);

        TextView aAnswer6 = (TextView) findViewById(R.id.aAnswer6);
        aAnswer6.setVisibility(View.GONE);

        TextView seeMore1 =  (TextView) findViewById(R.id.aReference1);
        seeMore1.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore1.setVisibility(View.GONE);

        TextView seeMore2 =  (TextView) findViewById(R.id.aReference2);
        seeMore2.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore2.setVisibility(View.GONE);

        TextView seeMore3 =  (TextView) findViewById(R.id.aReference3);
        seeMore3.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore3.setVisibility(View.GONE);

        TextView seeMore4 =  (TextView) findViewById(R.id.aReference4);
        seeMore4.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore4.setVisibility(View.GONE);

        TextView seeMore5 =  (TextView) findViewById(R.id.aReference5);
        seeMore5.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore5.setVisibility(View.GONE);

        TextView seeMore6 =  (TextView) findViewById(R.id.aReference6);
        seeMore6.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore6.setVisibility(View.GONE);

        Button next = findViewById(R.id.aNextButton);
        next.setVisibility(View.GONE);

    }

    //This method  show answers and references.
    public void aShowReferences(){
        TextView seeMore1 =  (TextView) findViewById(R.id.aReference1);
        seeMore1.setVisibility(View.VISIBLE);
        TextView aAnswer1 = (TextView) findViewById(R.id.aAnswer1);
        aAnswer1.setVisibility(View.VISIBLE);

        TextView seeMore2 =  (TextView) findViewById(R.id.aReference2);
        seeMore2.setVisibility(View.VISIBLE);
        TextView aAnswer2 = (TextView) findViewById(R.id.aAnswer2);
        aAnswer2.setVisibility(View.VISIBLE);

        TextView seeMore3 =  (TextView) findViewById(R.id.aReference3);
        seeMore3.setVisibility(View.VISIBLE);
        TextView aAnswer3 = (TextView) findViewById(R.id.aAnswer3);
        aAnswer3.setVisibility(View.VISIBLE);

        TextView seeMore4 =  (TextView) findViewById(R.id.aReference4);
        seeMore4.setVisibility(View.VISIBLE);
        TextView aAnswer4 = (TextView) findViewById(R.id.aAnswer4);
        aAnswer4.setVisibility(View.VISIBLE);

        TextView seeMore5 =  (TextView) findViewById(R.id.aReference5);
        seeMore5.setVisibility(View.VISIBLE);
        TextView aAnswer5 = (TextView) findViewById(R.id.aAnswer5);
        aAnswer5.setVisibility(View.VISIBLE);

        TextView seeMore6 =  (TextView) findViewById(R.id.aReference6);
        seeMore6.setVisibility(View.VISIBLE);
        TextView aAnswer6 = (TextView) findViewById(R.id.aAnswer6);
        aAnswer6.setVisibility(View.VISIBLE);

        Button next = findViewById(R.id.aNextButton);
        next.setVisibility(View.VISIBLE);

    }

}
