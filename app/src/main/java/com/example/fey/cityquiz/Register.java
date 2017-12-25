package com.example.fey.cityquiz;

import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.parse.*;
import android.provider.Settings.Secure;
import java.util.List;

public class Register extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /*
        This line gets the unique id associated with each android device. Can only be reset by wiping the phone
         */
        final String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d("ANDROID ID", android_id);

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("androidID", android_id);    //If these id's match, an account has already been registered on that account
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> scoreList, ParseException e) {
                if (e == null) {
                    if(scoreList.size() > 0){
                        Log.d("id", "android_id matches one already in the table");
                        //Displays dialog to inform the user the passwords were unequal and they must try again
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                        builder.setMessage(R.string.androidIdAlertMessage).setTitle(R.string.androidIdAlertTitle);
                        builder.setCancelable(false);   //Makes it so a user can't click outside of the alert to close the dialog. They must click the button
                        builder.setPositiveButton("Back to Home",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //These two lines automatically take the user back to the home page after a successful registration
                                        Intent goHome = new Intent(Register.this,HomePage.class);
                                        startActivity(goHome);
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }else{
                        Log.d("No Match", "Android ID does not match any in table");
                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }

    public void onClick(View v){
        Intent intent = new Intent(Register.this,HomePage.class);
        startActivity(intent);
    }

    /**
     * Checks to see if any fields are empty and/or if two password fields are equal
     */
    public void checkFields(View v){

        EditText name_field = (EditText)findViewById(R.id.nameField);
        EditText addr_field = (EditText)findViewById(R.id.addressField);
        EditText dob_field = (EditText)findViewById(R.id.dateOfBirthField);
        EditText email_field = (EditText)findViewById(R.id.emailField);
        EditText pass1 = (EditText)findViewById(R.id.passwordField);
        EditText pass2 = (EditText)findViewById(R.id.passwordConfirmField);


        String name = name_field.getText().toString().trim();
        String address = addr_field.getText().toString().trim();
        String dob = dob_field.getText().toString().trim();
        String email = email_field.getText().toString().trim();
        String password = pass1.getText().toString().trim();
        String passwordRetry = pass2.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            name_field.setError("You must enter a username");
        }

        if(TextUtils.isEmpty(address)){
            name_field.setError("You must enter an address");
        }

        if(TextUtils.isEmpty(dob)){
            name_field.setError("You must enter a date of birth");
        }

        if(TextUtils.isEmpty(email)){
            email_field.setError("You must enter an email");
        }

        if (TextUtils.isEmpty(password)){
            pass1.setError("You must enter a password");
        }

        if(TextUtils.isEmpty(passwordRetry)){
            pass2.setError("You must enter a password");
        }

        if(password.equals(passwordRetry)){

            String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
            Log.d("ANDROID ID", android_id);

            ParseUser user = new ParseUser();
            user.setUsername(name);
            user.setPassword(password);
            user.setEmail(email);
            user.put("address", address);
            user.put("dateOfBirth", dob);
            user.put("androidID", android_id);

            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        //success
                    } else {
                        // Sign up didn't succeed. Look at the ParseException
                        // to figure out what went wrong
                        Log.d("Error", e.toString());
                    }
                }
            });

            //These two lines automatically take the user back to the home page after a successful registration
            Intent goHome = new Intent(Register.this,HomePage.class);
            startActivity(goHome);

        } else {
            //Displays dialog to inform the user the passwords were unequal and they must try again
            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
            builder.setMessage(R.string.unequalPasswords).setTitle(R.string.passwordDialogTitle);
            builder.setCancelable(true);
            builder.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }
    }
}
