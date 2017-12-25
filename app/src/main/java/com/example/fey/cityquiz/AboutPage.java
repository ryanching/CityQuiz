package com.example.fey.cityquiz;

import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.app.Activity;

public class AboutPage extends Activity implements OnClickListener {

    //Page ONLY has text info on it, covered by activity_about.xml
    //this page, therefore, only needs to be able to convert a button press
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button button = (Button) findViewById(R.id.button9);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(AboutPage.this,HomePage.class);
        startActivity(intent);
    }

}
