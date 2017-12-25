package com.example.fey.cityquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.concurrent.TimeUnit;


public class ResultPage extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultpage);

        //get num right/wrong and time remaining in quiz from the quizpage
        Intent intent = getIntent();
        String numCorrect = (String) intent.getSerializableExtra("correct");
        String numWrong = (String) intent.getSerializableExtra("wrong");
        long millisUntilFinishLong = (long) intent.getSerializableExtra("time");
        String millisUntilFinish = Long.toString(millisUntilFinishLong);

        int weeklyScore, monthlyScore, allTimeScore;

        //Make sure user is logged in before trying to update their scores
        if(ParseUser.getCurrentUser() != null) {
            //If they haven't taken the quiz yet, set all their scores to zero
            if(ParseUser.getCurrentUser().get("DailyScore") == null){
                setUserScoresToZero();
            }
            //get weekly score from parse
            weeklyScore = Integer.parseInt((String)ParseUser.getCurrentUser().get("WeeklyScore"));

            //Add today's score to weekly score and store it to Parse and save it to parse
            weeklyScore += Integer.parseInt(numCorrect);
            ParseUser.getCurrentUser().put("WeeklyScore", Integer.toString(weeklyScore));

            //If they haven't taken the quiz yet, set all their scores to zero
            if(ParseUser.getCurrentUser().get("MonthlyScore") == null){
                setUserScoresToZero();
            }
            //get monthly score from parse
            monthlyScore = Integer.parseInt((String) ParseUser.getCurrentUser().get("MonthlyScore"));
           //add todays score to monthly and save to parse
            monthlyScore += Integer.parseInt(numCorrect);
            ParseUser.getCurrentUser().put("MonthlyScore", Integer.toString(monthlyScore));
            //update all time score
            allTimeScore = Integer.parseInt((String) ParseUser.getCurrentUser().get("AllTimeScore"));
            allTimeScore += Integer.parseInt(numCorrect);
            ParseUser.getCurrentUser().put("AllTimeScore", Integer.toString(allTimeScore));
            //Update daily score
            ParseUser.getCurrentUser().put("DailyScore", numCorrect);
            //update time completed
            ParseUser.getCurrentUser().put("Time", millisUntilFinish);
            ParseUser.getCurrentUser().saveInBackground();
        }

        //Update results text view
        TextView resultsView = (TextView) findViewById(R.id.textView3);
        resultsView.setText("You got "+ numCorrect + " correct, and " + numWrong + " wrong.");

        TextView timeView = (TextView) findViewById(R.id.timeRemaining);
        String hms = String.format("You finished with %02d seconds remaining", TimeUnit.MILLISECONDS.toSeconds(Long.parseLong((millisUntilFinish))));
        timeView.setText(hms);

        //go home button
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);

        Button button12 = (Button) findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //go to leader board page
                Intent myIntent = new Intent(ResultPage.this, LeaderPage.class);
                startActivity(myIntent);
            }
        });

    }

    /**
     * Sets all user scores in Parse to string "0"
     */
    public void setUserScoresToZero() {
        System.out.println(ParseUser.getCurrentUser().getEmail());
        ParseUser.getCurrentUser().put("DailyScore", "0");
        ParseUser.getCurrentUser().put("WeeklyScore","0");
        ParseUser.getCurrentUser().put("MonthlyScore","0");
        ParseUser.getCurrentUser().put("AllTimeScore","0");
        try {
            ParseUser.getCurrentUser().save();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v){
        Intent intent = new Intent(ResultPage.this,HomePage.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }

}
