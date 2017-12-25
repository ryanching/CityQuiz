package test;

import android.content.Intent;
import android.test.InstrumentationTestCase;
import com.example.fey.cityquiz.Quiz;
import com.example.fey.cityquiz.Question;
import com.example.fey.cityquiz.QuizPage;

/**
 * Created by Ryan on 10/12/15.
 */
public class QuizTest extends InstrumentationTestCase {

    Question question1;
    Quiz quiz1;
    String question = "This is the question";
    String answer1 = "answer1";
    String answer2 = "answer2";
    String answer3 = "answer3";
    String answer4 = "answer4";
    String correctAnswer = "answer1";

    @Override
    protected void setUp() throws Exception{
        super.setUp();



        //create new question
        question1 = new Question(question, answer1, answer2, answer3, answer4, correctAnswer);
    }


    //Test the return question function in the question class
    public void testReturnQuestion(){
        assertEquals("This is the question", question1.return_question());
    }

    // test the button1 function in the question class
    public void testButton1(){
        assertEquals("answer1", question1.button1());
    }

    // test the button2 function in the question class
    public void testButton2(){
        assertEquals("answer2", question1.button2());
    }

    // test the button3 function in the question class
    public void testButton3(){
        assertEquals("answer3", question1.button3());
    }

    // test the button4 function in the question class
    public void testButton4(){
        assertEquals("answer4", question1.button4());
    }

    //test the isAnswer function in the question class
    public void testIsAnswer(){
        assertTrue(question1.isAnswer("answer1"));
        assertFalse(question1.isAnswer("answer2"));
    }

    //test the isEmptry function in the quiz class when the quiz is empty
    public void testIsEmpty(){
        quiz1 = new Quiz();
        assertTrue(quiz1.isEmpty());
    }

    //test the isEmpty function in the quiz class when the quiz is not empty
    public void testIsNotEmpty(){
        quiz1 = new Quiz();
        quiz1.AddQuestion(question1);
        quiz1.AddQuestion(question1);
        assertFalse(quiz1.isEmpty());
    }

    //test the returnQuestion function in the quiz class
    public void testQuizReturnQuestion(){
        quiz1 = new Quiz();
        quiz1.AddQuestion(question1);
        assertEquals(question1, quiz1.ReturnQuestion());
    }

    //test that the correctAnswered variable is iterated correctly in the quiz class
    public void testCorrectAnswered(){
        quiz1 = new Quiz();
        quiz1.AddQuestion(question1);
        assertEquals(0, quiz1.CorrectAnswered);
        //CheckAnswer() should iterate correctAnswer variable by one
        quiz1.CheckAnswer(question1.correctAnswer);
        assertEquals(1, quiz1.CorrectAnswered);
    }

    //test that the wrongAnswered variable is iterated correctly in the quiz class
    public void testWrongAnswered(){
        quiz1 = new Quiz();
        quiz1.AddQuestion(question1);
        assertEquals(0, quiz1.WrongAnswered);
        //CheckAnswer() should iterate wrongAnswered variable by one
        quiz1.CheckAnswer("wrong answer");
        assertEquals(1, quiz1.WrongAnswered);
    }

    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }

}
