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

    TextView bAnswer1;
    TextView bAnswer2;
    TextView bAnswer3;
    TextView bAnswer4;
    TextView bAnswer5;
    TextView bAnswer6;
    TextView bAnswer7;
    TextView seeMore1b;
    TextView seeMore2b;
    TextView seeMore3b;
    TextView seeMore4b;
    TextView seeMore5b;
    TextView seeMore6b;
    TextView seeMore7b;
    Button bNext;
    Button bSubmit;
    EditText bText;
    RadioGroup bRadio1;
    RadioGroup bRadio2;
    RadioGroup bRadio3;
    RadioGroup bRadio4;
    RadioGroup bRadio5;
    RadioGroup bRadio6;
    RadioGroup bRadio7;
    CheckBox bBox1;
    CheckBox bBox2;
    CheckBox bBox3;
    CheckBox bBox4;

    int beginnerScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_beginner);
        bAnswer1 = findViewById(R.id.beginnerAnswer1);
        bAnswer2 = findViewById(R.id.beginnerAnswer2);
        bAnswer3 = findViewById(R.id.beginnerAnswer3);
        bAnswer4 = findViewById(R.id.beginnerAnswer4);
        bAnswer5 = findViewById(R.id.beginnerAnswer5);
        bAnswer6 = findViewById(R.id.beginnerAnswer6);
        bAnswer7 = findViewById(R.id.beginnerAnswer7);
        seeMore1b = findViewById(R.id.beginnerReference1);
        seeMore2b = findViewById(R.id.beginnerReference2);
        seeMore3b = findViewById(R.id.beginnerReference3);
        seeMore4b = findViewById(R.id.beginnerReference4);
        seeMore5b = findViewById(R.id.beginnerReference5);
        seeMore6b = findViewById(R.id.beginnerReference6);
        seeMore7b = findViewById(R.id.beginnerReference7);
        bNext = findViewById(R.id.bNextButton);
        bSubmit = findViewById(R.id.bSubmitButton);
        bText = findViewById(R.id.editText);
        bRadio1 = findViewById(R.id.q1Radio);
        bRadio2 = findViewById(R.id.q2Radio);
        bRadio3 = findViewById(R.id.q3Radio);
        bRadio4 = findViewById(R.id.q4Radio);
        bRadio5 = findViewById(R.id.q7Radio);
        bRadio6 = findViewById(R.id.q8Radio);
        bRadio7 = findViewById(R.id.q9Radio);
        bBox1 = findViewById(R.id.ck1);
        bBox2 = findViewById(R.id.ck2);
        bBox3 = findViewById(R.id.ck3);
        bBox4 = findViewById(R.id.ck4);

        bHideReferences();

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pop a message up when an answer is missing.
                if (bCheckMissingAnswers()){
                    Toast.makeText(QuizBeginnerActivity.this, "Please Answer All Questions.", Toast.LENGTH_SHORT).show();
                }
                else{
                    bShowReferences();
                    bGetAnswers(bRadio1);
                    bGetAnswers(bRadio2);
                    bGetAnswers(bRadio3);
                    bGetAnswers(bRadio4);
                    bGetAnswers(bRadio5);
                    bGetAnswers(bRadio6);
                    bGetAnswers(bRadio7);
                    bGetAnswers(bBox1);
                    bGetAnswers(bBox2);
                    bGetAnswers(bBox3);
                    bGetAnswers(bBox4);
                    bGetAnswers(bText);


                }

            }
        });

        //Passing the username and score to the result activity and launching.
        bNext.setOnClickListener(new View.OnClickListener() {
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
    public void bGetAnswers(View view) {

        if (view instanceof RadioGroup) {
            if (((RadioGroup) view).getCheckedRadioButtonId() != R.id.answer1 || ((RadioGroup) view).getCheckedRadioButtonId() != R.id.answer2 || ((RadioGroup) view).getCheckedRadioButtonId() != R.id.answer3 || ((RadioGroup) view).getCheckedRadioButtonId() != R.id.answer4 || ((RadioGroup) view).getCheckedRadioButtonId() != R.id.answer5 || ((RadioGroup) view).getCheckedRadioButtonId() != R.id.answer6 || ((RadioGroup) view).getCheckedRadioButtonId() != R.id.answer7) {
                RadioButton radioButton = (RadioButton) findViewById(((RadioGroup) view).getCheckedRadioButtonId());
                radioButton.setBackgroundColor(0xCCE53935);
            }
            if (((RadioGroup) view).getCheckedRadioButtonId() == R.id.answer1 || ((RadioGroup) view).getCheckedRadioButtonId() == R.id.answer2 || ((RadioGroup) view).getCheckedRadioButtonId() == R.id.answer3 || ((RadioGroup) view).getCheckedRadioButtonId() == R.id.answer4 || ((RadioGroup) view).getCheckedRadioButtonId() == R.id.answer5 || ((RadioGroup) view).getCheckedRadioButtonId() == R.id.answer6 || ((RadioGroup) view).getCheckedRadioButtonId() == R.id.answer7) {
                beginnerScore++;
            }

            for (int i = 0; i < ((RadioGroup) view).getChildCount(); i++) {
                ((RadioGroup) view).getChildAt(i).setEnabled(false);

                if (((RadioGroup) view).getChildAt(i).getId() == R.id.answer1 || ((RadioGroup) view).getChildAt(i).getId() == R.id.answer2 || ((RadioGroup) view).getChildAt(i).getId() == R.id.answer3 || ((RadioGroup) view).getChildAt(i).getId() == R.id.answer4 || ((RadioGroup) view).getChildAt(i).getId() == R.id.answer5 || ((RadioGroup) view).getChildAt(i).getId() == R.id.answer6 || ((RadioGroup) view).getChildAt(i).getId() == R.id.answer7) {
                    ((RadioGroup) view).getChildAt(i).setBackgroundColor(0xCC43A047);
                }
            }
        }


        if (view instanceof CheckBox) {
            view.setEnabled(false);
            if (view == bBox1|| view == bBox2 || view == bBox4) {
                view.setBackgroundColor(0xCC43A047);
                if(((CheckBox) view).isChecked()){
                    beginnerScore ++;
                }
            }
            else {
                if (((CheckBox) view).isChecked()) {
                    view.setBackgroundColor(0xCCE53935);
                }
            }
        }
        if (view instanceof EditText){
            view.setEnabled(false);
            if (((EditText) view).getText().toString().toLowerCase().equals("china")) {
                view.setBackgroundColor(0xCC43A047);
                beginnerScore ++;
            }
            else {
                view.setBackgroundColor(0xCCE53935);
            }
        }

    }
    // this method checks whether if an answer is missing
    public boolean bCheckMissingAnswers(){

        if(bRadio1.getCheckedRadioButtonId() == -1 || bRadio2.getCheckedRadioButtonId() == -1 || bRadio3.getCheckedRadioButtonId() == -1 || bRadio4.getCheckedRadioButtonId() == -1 || bRadio5.getCheckedRadioButtonId() == -1 || bRadio6.getCheckedRadioButtonId() == -1 || bRadio7.getCheckedRadioButtonId() == -1 || (!bBox1.isChecked() && !bBox2.isChecked() && !bBox3.isChecked() && !bBox4.isChecked()) || bText.getText().toString().length() == 0){
            return true;
        }
        else{
            return false;
        }

    }



    //This method makes references hyperlinks and hide them with answers.
    public void bHideReferences(){
        bAnswer1.setVisibility(View.GONE);
        bAnswer2.setVisibility(View.GONE);
        bAnswer3.setVisibility(View.GONE);
        bAnswer4.setVisibility(View.GONE);
        bAnswer5.setVisibility(View.GONE);
        bAnswer6.setVisibility(View.GONE);
        bAnswer7.setVisibility(View.GONE);

        seeMore1b.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore1b.setVisibility(View.GONE);

        seeMore2b.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore2b.setVisibility(View.GONE);

        seeMore3b.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore3b.setVisibility(View.GONE);

        seeMore4b.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore4b.setVisibility(View.GONE);

        seeMore5b.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore5b.setVisibility(View.GONE);

        seeMore6b.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore6b.setVisibility(View.GONE);

        seeMore7b.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore7b.setVisibility(View.GONE);

        bNext.setVisibility(View.GONE);

    }

//This method  shows answers and references.
    public void bShowReferences(){
        seeMore1b.setVisibility(View.VISIBLE);
        bAnswer1.setVisibility(View.VISIBLE);

        seeMore2b.setVisibility(View.VISIBLE);
        bAnswer2.setVisibility(View.VISIBLE);

        seeMore3b.setVisibility(View.VISIBLE);
        bAnswer3.setVisibility(View.VISIBLE);

        seeMore4b.setVisibility(View.VISIBLE);
        bAnswer4.setVisibility(View.VISIBLE);

        seeMore5b.setVisibility(View.VISIBLE);
        bAnswer5.setVisibility(View.VISIBLE);

        seeMore6b.setVisibility(View.VISIBLE);
        bAnswer6.setVisibility(View.VISIBLE);

        seeMore7b.setVisibility(View.VISIBLE);
        bAnswer7.setVisibility(View.VISIBLE);

        bNext.setVisibility(View.VISIBLE);

    }
}
