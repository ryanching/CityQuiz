package com.example.fey.cityquiz;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.widget.EditText;

public class SubmitPage extends Activity {

    Button submitButton;
    Button backButton;
    EditText mEdit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        submitButton = (Button)findViewById(R.id.button10);
        backButton = (Button)findViewById(R.id.button11);

        //When Submit button is pushed, will send an email with whatever is in the EditText box at the time
        submitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                mEdit = (EditText)findViewById(R.id.editText);
                //Code to send an email with the user submission to our designated gmail account
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"pittcityquiz@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "new question submission");
                //subject of email is whatever is in the box at time of submit button pushing
                i.putExtra(Intent.EXTRA_TEXT, mEdit.getText().toString());
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                }
            }
        });

        //Goes back to home page when clicked
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SubmitPage.this,HomePage.class);
                startActivity(intent);
            }
        });
    }
}
