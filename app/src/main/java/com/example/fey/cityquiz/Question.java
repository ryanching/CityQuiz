package com.example.fey.cityquiz;
import java.lang.*;


public class Question{

    public String question;
    public String answer1;
    public String answer2;
    public String answer3;
    public String answer4;
    public String correctAnswer;

    //constructor
    public Question(String q, String a1, String a2, String a3, String a4, String c) {
        this.question = q;
        this.answer1 = a1;
        this.answer2 = a2;
        this.answer3 = a3;
        this.answer4 = a4;
        this.correctAnswer = c;

    }

    public String button1() { return this.answer1; }

    public String button2() {
        return this.answer2;
    }

    public String button3() {
        return this.answer3;
    }

    public String button4() {
        return this.answer4;
    }

    public String return_question()
    {
        return this.question;
    }


    public boolean isAnswer(String answer)
    {
        if (answer.compareTo(this.correctAnswer) == 0 )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
