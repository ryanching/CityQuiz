package com.example.fey.cityquiz;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class LeaderPage extends Activity {
    //Home button, and sort by buttons
    Button button12, dailyButton, weeklyButton, monthlyButton, allTimeButton;
    //Large textbox to display leader boards
    TextView leaderboards;
    //query to get users
    ParseQuery<ParseUser> query;
    //list to store users
    List<ParseUser> userList = null;
    //String array to store user names
    String playerNames[] = new String[10];
    //Players position on the leaderboards
    int playerPosition = 0;
    TextView header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderpage);
        //textview at top of page
        header = (TextView) findViewById(R.id.textView6);
        header.setTextSize(30);
        header.setText("Daily \nLeaderboards");
        //query gets users from Parse
        query = ParseUser.getQuery();
        //set leaderboards equal to the large text view
        leaderboards = (TextView) findViewById(R.id.LeaderboardTextView);
        leaderboards.setTextSize(20);
        //default to daily leader boards
        SortOrder order = SortOrder.DailyScore;
        sortUsers(order);
        //home button
        button12 = (Button) findViewById(R.id.buttonLeaderonleaderboard);
        //set leaderboard to daily leaders
        dailyButton = (Button) findViewById(R.id.daily);
        //set leaderboard to weekly leaders
        weeklyButton = (Button) findViewById(R.id.weekly);
        //set leaderboard to monthly leaders
        monthlyButton = (Button) findViewById(R.id.monthly);
        //set leaderboard to allTime leaders
        allTimeButton = (Button) findViewById(R.id.allTime);
        //Send user to home screen
        button12.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(LeaderPage.this,HomePage.class);
                startActivity(intent);
            }
        });
        //daily button sorts users by their daily score
        dailyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                header.setText("Daily \nLeaderboards");
                SortOrder order = SortOrder.DailyScore;
                sortUsers(order);
            }
        });
        //weekly button sorts users by their weekly score
        weeklyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                header.setText("Weekly \nLeaderboards");
                SortOrder order = SortOrder.WeeklyScore;
                sortUsers(order);
            }
        });
        //monthly button sorts users by their monthly score
        monthlyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                header.setText("Monthly \nLeaderboards");
                SortOrder order = SortOrder.MonthlyScore;
                sortUsers(order);
            }
        });
        //all time button sorts users by their all time score
        allTimeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                header.setText("All Time \nLeaderboards");
                SortOrder order = SortOrder.AllTimeScore;
                sortUsers(order);
            }
        });

    }

    /**
     * This method sorts the user query by daily, weekly, monthly, or all time score and updates the leaderboards with the player names. It also gets the user's position on the leaderboards.
     * @param order this parameter can be set to DailyScore, MonthlyScore, WeeklyScore, or allTimeScore, and sorts the usernames accordingly.
     */
    public void sortUsers(SortOrder order){
        //Order usernames from highest score to lowest
        query.orderByDescending(String.valueOf(order));
        //If there is a tie in the score, sort the usernames by time.
        query.addDescendingOrder("Time");
        try {
            //query.find returns a list of ParseUser objects
            userList = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Iterate through the list of users
        for(int i=0; i<userList.size(); i++){

            //get the top 10 player names to be displayed on the leader board
            if(i<10) {
                playerNames[i] = userList.get(i).getUsername();
            }

            //get the current user's position in the leaderboards
            if(userList.get(i)==ParseUser.getCurrentUser()){
                playerPosition = i+1;
            }
        }
        //Replace null usernames with white space
        for(int i=0; i<10; i++){
            if(playerNames[i] == null){
                playerNames[i] = " ";
            }
        }
        //Set the leaderboards textview to print out the top 10 users and the current users position in the leaderboards
        leaderboards.setText("1st: " + playerNames[0] + "\n2nd: " + playerNames[1] + "\n3rd: "+playerNames[2]+"\n4th: "+playerNames[3]+"\n5th: "+playerNames[4]+"\n6th: "+playerNames[5]+"\n7th: "+playerNames[6]+
                "\n8th: "+playerNames[7]+"\n9th: "+playerNames[8]+"\n10th: "+playerNames[9]+ "\nYour position: "+playerPosition);
    }

    /**
     * SortOrder stores a string value of DailyScore, WeeklyScore, Monthlyscore, and allTimeScore depending on what the user wants the leaderboards to be sorted by.
     */
    private enum SortOrder
    {
        DailyScore, WeeklyScore, MonthlyScore, AllTimeScore
    }
}
