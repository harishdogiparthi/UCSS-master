package com.anonymous.ucss.lecturer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anonymous.ucss.R;
import com.anonymous.ucss.config.Config;
import com.anonymous.ucss.view_students;

public class Onlyone extends AppCompatActivity {

    Button btn1,btn2,btn4,btn5,btn7,btn8;
    TextView header1,header2,header3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlyone);

        Intent intent = getIntent();

        String username = intent.getStringExtra(Config.login_id);
        String list = intent.getStringExtra(Config.list);
        header1 = (TextView) findViewById(R.id.header1);
        header2 = (TextView) findViewById(R.id.header2);
        header3 = (TextView) findViewById(R.id.header3);


        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);


        if (list.contentEquals(Config.LABEL1)) {

            internal();
        }
        else if (list.contentEquals(Config.LABEL2)){
            attendance();
        }


    }

    public void internal(){

        header1.setText("Spring");
        header2.setText("Summer");
        header3.setText("Fall");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Onlyone.this,view_students.class);
                intent.putExtra(Config.table, Config.SPRING_INTNL);
                intent.putExtra(Config.MASTERS,"Masters 1st Year");
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Onlyone.this,view_students.class);
                intent.putExtra(Config.table, Config.SPRING_INTNL);
                intent.putExtra(Config.MASTERS,"Masters 2nd Year");
                startActivity(intent);

            }
        });



        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Onlyone.this,view_students.class);
                intent.putExtra(Config.table, Config.SUMMER_INTNL);
                intent.putExtra(Config.MASTERS,"Masters 1st Year");
                startActivity(intent);

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Onlyone.this,view_students.class);
                intent.putExtra(Config.table, Config.SUMMER_INTNL);
                intent.putExtra(Config.MASTERS,"Masters 2nd Year");
                startActivity(intent);

            }
        });




        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Onlyone.this,view_students.class);
                intent.putExtra(Config.table, Config.SUMMER_INTNL);
                intent.putExtra(Config.MASTERS,"Masters 1st Year");
                startActivity(intent);

            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Onlyone.this,view_students.class);
                intent.putExtra(Config.table, Config.SUMMER_INTNL);
                intent.putExtra(Config.MASTERS,"Masters 2nd Year");
                startActivity(intent);

            }
        });






    }


    public void attendance(){

        header1.setText("Spring");
        header2.setText("Summer");
        header3.setText("Fall");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Onlyone.this,view_students.class);
                intent.putExtra(Config.table, Config.SPRING_ATTEND);
                intent.putExtra(Config.MASTERS,"Masters 1st Year");
                startActivity(intent);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Onlyone.this,view_students.class);
                intent.putExtra(Config.table, Config.SPRING_ATTEND);
                intent.putExtra(Config.MASTERS,"Masters 2nd Year");
                startActivity(intent);


            }
        });



        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Onlyone.this,view_students.class);
                intent.putExtra(Config.table, Config.SUMMER_ATTEND);
                intent.putExtra(Config.MASTERS,"Masters 1st Year");
                startActivity(intent);

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Onlyone.this,view_students.class);
                intent.putExtra(Config.table, Config.SUMMER_ATTEND);
                intent.putExtra(Config.MASTERS,"Masters 2nd Year");
                startActivity(intent);

            }
        });


        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Onlyone.this,view_students.class);
                intent.putExtra(Config.table, Config.SUMMER_ATTEND);
                intent.putExtra(Config.MASTERS,"Masters 1st Year");
                startActivity(intent);

            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Onlyone.this,view_students.class);
                intent.putExtra(Config.table, Config.SUMMER_ATTEND);
                intent.putExtra(Config.MASTERS,"Masters 2nd Year");
                startActivity(intent);

            }
        });






    }


}
