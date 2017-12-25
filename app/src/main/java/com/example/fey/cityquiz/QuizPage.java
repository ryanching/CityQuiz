package com.example.fey.cityquiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.view.animation.TranslateAnimation;  //for button animation
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;
import java.util.concurrent.TimeUnit;
import java.util.List;
import android.os.CountDownTimer;


public class QuizPage extends Activity implements OnClickListener {

    public Quiz CurrentQuiz = new Quiz();
    TextView timerTextView;
    CounterClass timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizpage);

        //newQuestion stores the question information form parse
        ParseObject newQuestion = null;
        //questionList holds the five ParseObject questions
        List<ParseObject> questionList = null;

        //query gets the first 5 objects from parse and store them in the questionlist
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
        query.setLimit(5);

        try {
            //query.find() returns a list of 5 questions
            questionList = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Get information from the parseobjects and make them into Question objects and add the questions to the CurrentQuiz
        for(int i=0; i<5; i++) {
                newQuestion = questionList.get(i);
                String quest = newQuestion.getString("question");
                String a1 = newQuestion.getString("answer1");
                String a2 = newQuestion.getString("answer2");
                String a3 = newQuestion.getString("answer3");
                String a4 = newQuestion.getString("answer4");
                String ca = newQuestion.getString("correctAnswer");
                Question test_question1 = new Question(quest, a1, a2, a3, a4, ca);
                CurrentQuiz.AddQuestion(test_question1);
        }

        //create textview for the timer
        timerTextView = (TextView) findViewById(R.id.timeTextView);
        timerTextView.setText("Time Remaining:\n60");

        //60 second timer
        timer = new CounterClass(60000, 1000);
        timer.start();

        Question TempQuestion = CurrentQuiz.ReturnQuestion();
        generate_question(TempQuestion);

    }

    @Override
    public void onClick(View v){

        //which button user clicked
        Button the_clicked_button = (Button) findViewById(v.getId());
        String answered = the_clicked_button.getText().toString();

        CurrentQuiz.CheckAnswer(answered);

        //no more questions, go to result page
        if(CurrentQuiz.isEmpty())
        {
            //get time remaining for leaderboard tie-breakers
            Intent intent = new Intent(QuizPage.this,ResultPage.class);
            //get number of correct and incorrect answers
            String numCorrect = Integer.toString(CurrentQuiz.getCorrectAnswered());
            String numWrong = Integer.toString(CurrentQuiz.getWrongAnswered());
            //send this information to results page
            intent.putExtra("correct", numCorrect);
            intent.putExtra("wrong", numWrong);
            intent.putExtra("time", timer.millisUntilFinish);
            timer.cancel();
            startActivity(intent);
        }
        //still questions left
        else
        {
            CurrentQuiz.CurrentQuestionIndex++;
            Question TempQuestion = CurrentQuiz.ReturnQuestion();
            generate_question(TempQuestion);
        }

    }

    //generates question in text box, and answers in the buttons.
    public void generate_question(Question question)
    {
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setText(question.button1().toString());
        //adjust button text size based on length of question
        if(button2.getLineCount() > 4 && button2.getLineCount() <= 6){
            button2.setTextSize(15);
        }
        else if(button2.getLineCount() > 6){
            button2.setTextSize(10);
        }

        /*Try to add animation for Answer1*/
        AnimationSet answer1_animation = new AnimationSet(true);
        TranslateAnimation translateAnimationAnswer1 = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,-1f,
                Animation.RELATIVE_TO_PARENT,0f,
                Animation.RELATIVE_TO_PARENT,0f,
                Animation.RELATIVE_TO_PARENT,0f);
        translateAnimationAnswer1.setDuration(500);
        translateAnimationAnswer1.setStartOffset(0);
        answer1_animation.addAnimation(translateAnimationAnswer1);
        button2.startAnimation(answer1_animation);
        /*Animation for Answer 1 finished*/

        button2.setOnClickListener(this);


        Button button3 = (Button   ) findViewById(R.id.button3);
        button3.setText(question.button2().toString());
        if(button3.getLineCount() > 4 && button3.getLineCount() <= 6){
            button3.setTextSize(15);
        }
        else if(button3.getLineCount() > 6){
            button3.setTextSize(10);
        }

        /*Try to add animation for Answer2*/
        AnimationSet answer2_animation = new AnimationSet(true);
        TranslateAnimation translateAnimationAnswer2 = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,-1f,
                Animation.RELATIVE_TO_PARENT,0f,
                Animation.RELATIVE_TO_PARENT,0f,
                Animation.RELATIVE_TO_PARENT,0f);
        translateAnimationAnswer2.setDuration(500);
        translateAnimationAnswer2.setStartOffset(500);
        answer2_animation.addAnimation(translateAnimationAnswer2);
        button3.startAnimation(answer2_animation);
        /*Animation for Answer 2 finished*/

        button3.setOnClickListener(this);



        Button button4 = (Button) findViewById(R.id.button4);
        button4.setText(question.button3().toString());
        if(button4.getLineCount() > 4 && button4.getLineCount() <= 6){
            button4.setTextSize(15);
        }
        else if(button4.getLineCount() > 6){
            button4.setTextSize(10);
        }

        /*Try to add animation for Answer3*/
        AnimationSet answer3_animation = new AnimationSet(true);
        TranslateAnimation translateAnimationAnswer3 = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,-1f,
                Animation.RELATIVE_TO_PARENT,0f,
                Animation.RELATIVE_TO_PARENT,0f,
                Animation.RELATIVE_TO_PARENT,0f);
        translateAnimationAnswer3.setDuration(500);
        translateAnimationAnswer3.setStartOffset(1000);
        answer3_animation.addAnimation(translateAnimationAnswer3);
        button4.startAnimation(answer3_animation);
        /*Animation for Answer 2 finished*/

        button4.setOnClickListener(this);


        Button button5= (Button) findViewById(R.id.button5);
        button5.setText(question.button4().toString());
        if(button5.getLineCount() > 4 && button5.getLineCount() <= 6){
            button5.setTextSize(15);
        }
        else if(button5.getLineCount() > 6){
            button5.setTextSize(10);
        }

        /* Try to add animation for Answer4 */
        AnimationSet answer4_animation = new AnimationSet(true);
        TranslateAnimation translateAnimationAnswer4 = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,-1f,
                Animation.RELATIVE_TO_PARENT,0f,
                Animation.RELATIVE_TO_PARENT,0f,
                Animation.RELATIVE_TO_PARENT,0f);
        translateAnimationAnswer4.setDuration(500);
        translateAnimationAnswer4.setStartOffset(1500);
        answer4_animation.addAnimation(translateAnimationAnswer4);
        button5.startAnimation(answer4_animation);
        /*Animation for Answer 4 finished*/


        button5.setOnClickListener(this);

        TextView current_question = (TextView) findViewById(R.id.textView2);
        current_question.setText(question.return_question().toString());
        if(current_question.getLineCount() > 4 && current_question.getLineCount() <= 6){
            current_question.setTextSize(15);
        }
        else if(current_question.getLineCount() > 6){
            current_question.setTextSize(10);
        }
    }

    /**
     * This method runs when the user presses the back button
     */
    public void onBackPressed(){
        //super.onBackPressed();
        //Cancel the timer to prevent timer bug if the user goes back to home screen
        //timer.cancel();
    }

    //This class is for the 60 second timer in the QuizPage.
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
            String hms = String.format("Time Remaining:\n%02d", TimeUnit.MILLISECONDS.toSeconds(millis));
            timerTextView.setText(hms);
        }

        @Override
        //This runs when the timer hits 0- goes to the results page while marking any unanswered questions false
        public void onFinish() {
            timerTextView.setText("Out of Time");
            Intent intent = new Intent(QuizPage.this,ResultPage.class);
            String numCorrect = Integer.toString(CurrentQuiz.getCorrectAnswered());
            int numWrong = CurrentQuiz.getWrongAnswered();
            numWrong += CurrentQuiz.getNumberRemainingQuestions();
            String numWrongString = Integer.toString(numWrong);
            String millisecondsUntilFinish = Integer.toString((int)timer.millisUntilFinish);
            intent.putExtra("time", millisecondsUntilFinish);
            intent.putExtra("correct", numCorrect);
            intent.putExtra("wrong", numWrongString);
            intent.putExtra("time", millisUntilFinish);
            startActivity(intent);
        }

    }

}
