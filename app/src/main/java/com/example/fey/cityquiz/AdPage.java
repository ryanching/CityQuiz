package com.example.fey.cityquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.os.CountDownTimer;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class AdPage extends Activity implements OnClickListener {

    CounterClass adTimer;   //countdown for ad

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adpage);

        Button button = (Button) findViewById(R.id.adButton);
        button.setOnClickListener(this);
        button.setEnabled(false);
        button.setText("10\n");
        button.setOnClickListener(this);
        adTimer = new CounterClass(5000, 1000);    //after 5 sec, ad can be skiped
        adTimer.start();

        //add link to the ad part
        //For here we only use textview
        TextView adText = (TextView) findViewById(R.id.textAd);

        String html = "For more info about this ad: \n";
        html+="<a href='http://www.pitt.edu/'>Click Here</a>";

        CharSequence charSequence = Html.fromHtml(html);
        adText.setText(charSequence);
        adText.setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(AdPage.this,CountDownPage.class);
        startActivity(intent);
    }

    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public long millisUntilFinish;

        @Override
        // Updates timer every second
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            millisUntilFinish = millisUntilFinished;
            String hms = String.format("Be ready, quiz can be started in:  %02d", TimeUnit.MILLISECONDS.toSeconds(millis)-1);
            Button button = (Button) findViewById(R.id.adButton);
            button.setText(hms);
        }

        @Override
        //This runs when the timer hits 0- goes to the results page while marking any unanswered questions false
        public void onFinish() {
            Button button = (Button) findViewById(R.id.adButton);
            button.setText("Start Quiz");
            button.setEnabled(true);

        }



    }
}
