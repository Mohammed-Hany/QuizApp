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

    TextView aAnswer1;
    TextView aAnswer2;
    TextView aAnswer3;
    TextView aAnswer4;
    TextView aAnswer5;
    TextView aAnswer6;
    TextView seeMore1a;
    TextView seeMore2a;
    TextView seeMore3a;
    TextView seeMore4a;
    TextView seeMore5a;
    TextView seeMore6a;
    Button aNext;
    Button aSubmit;
    EditText aText;
    RadioGroup aRadio1;
    RadioGroup aRadio2;
    RadioGroup aRadio3;
    RadioGroup aRadio4;
    RadioGroup aRadio5;
    RadioGroup aRadio6;
    CheckBox aBox1;
    CheckBox aBox2;
    CheckBox aBox3;
    CheckBox aBox4;

    int advancedScore = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_advanced);
        seeMore1a = findViewById(R.id.aReference1);
        seeMore2a = findViewById(R.id.aReference2);
        seeMore3a = findViewById(R.id.aReference3);
        seeMore4a = findViewById(R.id.aReference4);
        seeMore5a = findViewById(R.id.aReference5);
        seeMore6a = findViewById(R.id.aReference6);
        aAnswer1 = findViewById(R.id.aAnswer1);
        aAnswer2 = findViewById(R.id.aAnswer2);
        aAnswer3 = findViewById(R.id.aAnswer3);
        aAnswer4 = findViewById(R.id.aAnswer4);
        aAnswer5 = findViewById(R.id.aAnswer5);
        aAnswer6 = findViewById(R.id.aAnswer6);
        aRadio1 = findViewById(R.id.q1RadioA);
        aRadio2 = findViewById(R.id.q2RadioA);
        aRadio3 = findViewById(R.id.q3RadioA);
        aRadio4 = findViewById(R.id.q4RadioA);
        aRadio5 = findViewById(R.id.q7RadioA);
        aRadio6 = findViewById(R.id.q8RadioA);
        aBox1 = findViewById(R.id.ck1A);
        aBox2 = findViewById(R.id.ck2A);
        aBox3 = findViewById(R.id.ck3A);
        aBox4 = findViewById(R.id.ck4A);
        aText = findViewById(R.id.editTextA);
        aNext = findViewById(R.id.aNextButton);
        aSubmit = findViewById(R.id.aSubmitButton);




        aHideReferences();

        aSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pop a message up when an answer is missing.
                if (aCheckMissingAnswers()) {
                    Toast.makeText(QuizAdvancedActivity.this, "Please Answer All Questions.", Toast.LENGTH_SHORT).show();
                } else {
                    aShowReferences();
                    aGetAnswers(aRadio1);
                    aGetAnswers(aRadio2);
                    aGetAnswers(aRadio3);
                    aGetAnswers(aRadio4);
                    aGetAnswers(aRadio5);
                    aGetAnswers(aRadio6);
                    aGetAnswers(aBox1);
                    aGetAnswers(aBox2);
                    aGetAnswers(aBox3);
                    aGetAnswers(aBox4);
                    aGetAnswers(aText);


                }

            }
        });

        //Passing the username and score to the result activity and launching.
        aNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = getIntent().getStringExtra("aUName");
                Intent result = new Intent(QuizAdvancedActivity.this, ResultActivity.class);
                result.putExtra("aName", uName);
                result.putExtra("aScore", advancedScore);
                startActivity(result);
            }
        });
    }


    // this method blocks all the choices and determines whether the wrong or the right answers are clicked.
    public void aGetAnswers(View view) {

        if (view instanceof RadioGroup) {

            if (((RadioGroup) view).getCheckedRadioButtonId() != R.id.answer1A || ((RadioGroup) view).getCheckedRadioButtonId() != R.id.answer2A || ((RadioGroup) view).getCheckedRadioButtonId() != R.id.answer3A || ((RadioGroup) view).getCheckedRadioButtonId() != R.id.answer4A || ((RadioGroup) view).getCheckedRadioButtonId() != R.id.answer5A || ((RadioGroup) view).getCheckedRadioButtonId() != R.id.answer6A) {
                RadioButton radioButton = findViewById(((RadioGroup) view).getCheckedRadioButtonId());
                radioButton.setBackgroundColor(0xCCE53935);
            }
            if (((RadioGroup) view).getCheckedRadioButtonId() == R.id.answer1A || ((RadioGroup) view).getCheckedRadioButtonId() == R.id.answer2A || ((RadioGroup) view).getCheckedRadioButtonId() == R.id.answer3A || ((RadioGroup) view).getCheckedRadioButtonId() == R.id.answer4A || ((RadioGroup) view).getCheckedRadioButtonId() == R.id.answer5A || ((RadioGroup) view).getCheckedRadioButtonId() == R.id.answer6A) {
                advancedScore++;
            }

            for (int i = 0; i < ((RadioGroup) view).getChildCount(); i++) {
                ((RadioGroup) view).getChildAt(i).setEnabled(false);

                if (((RadioGroup) view).getChildAt(i).getId() == R.id.answer1A || ((RadioGroup) view).getChildAt(i).getId() == R.id.answer2A || ((RadioGroup) view).getChildAt(i).getId() == R.id.answer3A || ((RadioGroup) view).getChildAt(i).getId() == R.id.answer4A || ((RadioGroup) view).getChildAt(i).getId() == R.id.answer5A || ((RadioGroup) view).getChildAt(i).getId() == R.id.answer6A) {
                    ((RadioGroup) view).getChildAt(i).setBackgroundColor(0xCC43A047);
                }
            }
        }


        if (view instanceof CheckBox) {
            view.setEnabled(false);
            if (view == aBox1 || view == aBox3) {
                view.setBackgroundColor(0xCC43A047);
                if (((CheckBox) view).isChecked()) {
                    advancedScore++;
                }
            } else {
                if (((CheckBox) view).isChecked()) {
                    view.setBackgroundColor(0xCCE53935);
                }
            }
        }
        if (view instanceof EditText) {
            view.setEnabled(false);
            if (((EditText) view).getText().toString().toLowerCase().equals("usa") || ((EditText) view).getText().toString().toLowerCase().equals("united states") || ((EditText) view).getText().toString().toLowerCase().equals("united states of america") || ((EditText) view).getText().toString().toLowerCase().equals("america")) {
                view.setBackgroundColor(0xCC43A047);
                advancedScore++;
            } else {
                view.setBackgroundColor(0xCCE53935);
            }
        }

    }

    // this method checks whether if an answer is missing
    public boolean aCheckMissingAnswers() {

        if (aRadio1.getCheckedRadioButtonId() == -1 || aRadio2.getCheckedRadioButtonId() == -1 || aRadio3.getCheckedRadioButtonId() == -1 || aRadio4.getCheckedRadioButtonId() == -1 || aRadio5.getCheckedRadioButtonId() == -1 || aRadio6.getCheckedRadioButtonId() == -1 || (!aBox1.isChecked() && !aBox2.isChecked() && !aBox3.isChecked() && !aBox4.isChecked()) || aText.getText().toString().length() == 0) {
            return true;
        } else {
            return false;
        }

    }

    //This method makes references hyperlinks and hide them with answers.
    public void aHideReferences() {

        aAnswer1.setVisibility(View.GONE);
        aAnswer2.setVisibility(View.GONE);
        aAnswer3.setVisibility(View.GONE);
        aAnswer4.setVisibility(View.GONE);
        aAnswer5.setVisibility(View.GONE);
        aAnswer6.setVisibility(View.GONE);

        seeMore1a.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore1a.setVisibility(View.GONE);

        seeMore2a.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore2a.setVisibility(View.GONE);

        seeMore3a.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore3a.setVisibility(View.GONE);

        seeMore4a.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore4a.setVisibility(View.GONE);

        seeMore5a.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore5a.setVisibility(View.GONE);

        seeMore6a.setMovementMethod(LinkMovementMethod.getInstance());
        seeMore6a.setVisibility(View.GONE);

        aNext.setVisibility(View.GONE);

    }


    //This method  show answers and references.
    public void aShowReferences() {


        seeMore1a.setVisibility(View.VISIBLE);
        aAnswer1.setVisibility(View.VISIBLE);

        seeMore2a.setVisibility(View.VISIBLE);
        aAnswer2.setVisibility(View.VISIBLE);

        seeMore3a.setVisibility(View.VISIBLE);
        aAnswer3.setVisibility(View.VISIBLE);

        seeMore4a.setVisibility(View.VISIBLE);
        aAnswer4.setVisibility(View.VISIBLE);

        seeMore5a.setVisibility(View.VISIBLE);
        aAnswer5.setVisibility(View.VISIBLE);

        seeMore6a.setVisibility(View.VISIBLE);
        aAnswer6.setVisibility(View.VISIBLE);

        aNext.setVisibility(View.VISIBLE);

    }

}
