package com.example.fey.cityquiz;


import android.view.animation.ScaleAnimation;
import android.view.animation.Animation;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.content.Intent;
import android.app.Activity;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;


public class CountDownPage extends Activity {

    CounterClass adTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        TextView countdownView = (TextView) findViewById(R.id.textCountdown);

        adTimer = new CounterClass(5500, 1000);    //after 5 sec, ad can be skiped
        adTimer.start();
    }

    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public long millisUntilFinish;

        @Override
        // Updates timer every second
        public void onTick(long millisUntilFinished) {
            TextView countdownView = (TextView) findViewById(R.id.textCountdown);


            long time = millisUntilFinished/1000-1;
            countdownView.setText(""+time);

            if ( millisUntilFinished/1000-1!=0)
            {
                //initiation
                Animation scaleAnimation = new ScaleAnimation(1.0f, 0.1f,1.0f,0.1f,
                        Animation.RELATIVE_TO_SELF,0.5f,
                        Animation.RELATIVE_TO_SELF,0.5f);
                //duration
                scaleAnimation.setDuration(1000);
                countdownView.startAnimation(scaleAnimation);
            }

        }

        @Override

        public void onFinish() {
            TextView countdownView = (TextView) findViewById(R.id.textCountdown);
            countdownView.setText("GO");
            countdownView.setTextSize(150);
            Intent intent = new Intent(CountDownPage.this,QuizPage.class);
            startActivity(intent);

        }

    }

    /*Disable the back button*/
    @Override
    public void onBackPressed() {
    }

}
