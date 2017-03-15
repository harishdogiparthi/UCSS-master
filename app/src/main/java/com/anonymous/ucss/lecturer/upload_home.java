package com.anonymous.ucss.lecturer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anonymous.ucss.R;
import com.anonymous.ucss.config.Config;

public class upload_home extends AppCompatActivity {

    Button button1,button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_home);

        button1 = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(upload_home.this, upload.class);
                intent.putExtra(Config.MASTERS, "Masters 1st Year");
                startActivity(intent);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(upload_home.this, upload.class);
                intent.putExtra(Config.MASTERS, "Masters 2nd Year");
                startActivity(intent);


            }
        });









    }
}
