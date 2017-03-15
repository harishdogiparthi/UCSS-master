package com.anonymous.ucss.lecturer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.anonymous.ucss.R;
import com.anonymous.ucss.config.Config;
import com.anonymous.ucss.request_handler.RequestHandler;
import com.anonymous.ucss.student.Student;

import java.util.HashMap;

public class lect_send extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

   EditText subject,message;
    Button send;
    Spinner mail_to;
    String who,email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lect_send);

        Intent intent = getIntent();
        email = intent.getStringExtra(Config.login_id);



        mail_to = (Spinner) findViewById(R.id.mail_to);
        subject = (EditText) findViewById(R.id.mail_subject);
        message = (EditText) findViewById(R.id.mail_message);
        send = (Button) findViewById(R.id.mail_send);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.who, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        mail_to.setAdapter(adapter);
        mail_to.setOnItemSelectedListener(this);


       send.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               addEmployee();

           }
       });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        who = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

//        Toast.makeText(lect_send.this,"Select To Whom to Send From The Spinner",Toast.LENGTH_LONG).show();
    }

    private void addEmployee(){

        final String str_subject = subject.getText().toString().trim();
        final String str_message = message.getText().toString().trim();


        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(lect_send.this,"Sending...","Wait...",false,false);
                loading.setCancelable(true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(lect_send.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();

                params.put(Config.KEY_USER_EMAIL,email);
                params.put(Config.KEY_USER_MAIL_TO, who);
                params.put(Config.KEY_USER_MAIL_SUBJECT,str_subject);
                params.put(Config.KEY_USER_MAIL_MESSAGE,str_message);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_SEND_MAIL, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }


}
