package com.anonymous.ucss.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.anonymous.ucss.R;
import com.anonymous.ucss.config.Config;
import com.anonymous.ucss.view_students;

public class view_details extends AppCompatActivity {

    private String who,table;
    private Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        Intent intent = getIntent();

        who = intent.getStringExtra(Config.WHO);
        table = intent.getStringExtra(Config.table);

        btn1 = (Button) findViewById(R.id.stu_btn1);
        btn2 = (Button) findViewById(R.id.stu_btn2);
        btn3 = (Button) findViewById(R.id.stu_btn3);

        if (table.contentEquals(Config.LABEL1)){
            internal();
        }
        else if (table.contentEquals(Config.LABEL2)){

            attendance();

        }



    }

    public void internal(){
        btn1.setText("Spring");
        btn2.setText("Summer");
        btn3.setText("Fall");

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view_details.this,view_students.class);
                intent.putExtra(Config.table, Config.SPRING_INTNL);
                intent.putExtra(Config.MASTERS,who);
                startActivity(intent);

            }
        });

        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view_details.this,view_students.class);
                intent.putExtra(Config.table, Config.SUMMER_INTNL);
                intent.putExtra(Config.MASTERS,who);
                startActivity(intent);

            }
        });
        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(view_details.this,view_students.class);
                intent.putExtra(Config.table, Config.SUMMER_INTNL);
                intent.putExtra(Config.MASTERS,who);
                startActivity(intent);

            }
        });



    }

    public void attendance(){

        btn1.setText("Spring");
        btn2.setText("Summer");
        btn3.setText("Fall");

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view_details.this,view_students.class);
                intent.putExtra(Config.table, Config.SPRING_ATTEND);
                intent.putExtra(Config.MASTERS,who);
                startActivity(intent);

            }
        });

        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view_details.this,view_students.class);
                intent.putExtra(Config.table, Config.SUMMER_ATTEND);
                intent.putExtra(Config.MASTERS,who);
                startActivity(intent);

            }
        });

        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(view_details.this,view_students.class);
                intent.putExtra(Config.table, Config.SUMMER_ATTEND);
                intent.putExtra(Config.MASTERS,who);
                startActivity(intent);

            }
        });


    }







}
