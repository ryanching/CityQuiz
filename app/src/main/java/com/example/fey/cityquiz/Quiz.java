package com.example.fey.cityquiz;

import java.util.ArrayList;

public class Quiz{

    ArrayList<Question> QuestionList = new ArrayList<Question>();   //Question List
    public int CorrectAnswered;        //How many questions are answered correctly for now.
    public int WrongAnswered;
    public int CurrentQuestionIndex;

    //constructor
    public Quiz()
    {
        CorrectAnswered = 0;
        CurrentQuestionIndex = 1;
        WrongAnswered = 0;
    }

    //Adds question to the questionList
    public void AddQuestion(Question q)
    {
        QuestionList.add(q);
    }

    //returns number of correct answers
    public int getCorrectAnswered(){
        return CorrectAnswered;
    }

    //returns number of wrong answers
    public int getWrongAnswered(){
        return WrongAnswered;
    }

    //returns the number of questions in the quiz that have not been answered yet
    public int getNumberRemainingQuestions(){
        return (QuestionList.size() - CurrentQuestionIndex)+1;
    }

    //return true if no questions left
    public boolean isEmpty()
    {
       if(CurrentQuestionIndex < QuestionList.size())
       {
           return false;
       }
       else
       {
           return true;
       }
    }

    //send question to users.
    public Question ReturnQuestion()
    {
        return QuestionList.get(CurrentQuestionIndex-1);
    }

    //Checks for answer correctness
    public void CheckAnswer(String answer)
    {
       if(QuestionList.get(CurrentQuestionIndex-1).isAnswer(answer))
       {
           CorrectAnswered++;
       }
       else
       {
           WrongAnswered++;
       }

    }

}
