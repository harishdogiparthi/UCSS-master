package com.anonymous.ucss.student;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anonymous.ucss.R;
import com.anonymous.ucss.config.Config;
import com.anonymous.ucss.request_handler.RequestHandler;

import java.util.HashMap;

public class stu_mail extends AppCompatActivity {

    EditText to,subject,message;
    Button mail_send;
    String from,mto,msubject,mmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_mail);

        Intent intent = getIntent();
        from = intent.getStringExtra(Config.login_id);

        to = (EditText) findViewById(R.id.stu_mail);
        subject = (EditText) findViewById(R.id.stu_mail_subject);
        message = (EditText) findViewById(R.id.stu_mail_message);
        mail_send = (Button) findViewById(R.id.stu_mail_send);


        mail_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendmail();
            }
        });



    }

    private void sendmail(){

        mto = to.getText().toString().trim();
        msubject = subject.getText().toString().trim();
        mmessage = message.getText().toString().trim();


        class sendMail extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(stu_mail.this,"Sending Mail","Wait...",false,false);
                loading.setCancelable(true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(stu_mail.this, s, Toast.LENGTH_SHORT).show();
                if(s.contentEquals("Mail Sent Successfully")){

                    setContentView(R.layout.activity_success_register);
                    TextView textView = (TextView) findViewById(R.id.success_message);
                    textView.setText(R.string.mail_success_msg);

                }


            }

            @Override
            protected String doInBackground(Void... v) {

                HashMap<String,String> params = new HashMap<>();

                params.put(Config.KEY_USER_EMAIL, from);
                params.put(Config.KEY_USER_MAIL_TO,mto);
                params.put(Config.KEY_USER_MAIL_SUBJECT,msubject);
                params.put(Config.KEY_USER_MAIL_MESSAGE,mmessage);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_SEND_STU_MAIL,params);
                return s;
            }
        }
        sendMail sendMail = new sendMail();
        sendMail.execute();
    }

}
