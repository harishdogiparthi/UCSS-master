package com.anonymous.ucss.student;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anonymous.ucss.R;
import com.anonymous.ucss.config.Config;
import com.anonymous.ucss.request_handler.RequestHandler;

import java.util.HashMap;

public class stu_sms extends AppCompatActivity {


    EditText sms_message,sms_number,sms_uid,sms_upass;
    Button sms_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_sms);


        final InputFilter[] Textfilters = new InputFilter[1];
        Textfilters[0] = new InputFilter(){
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (end > start) {

                    char[] acceptedChars = new char[]{'0','1','2','3','4','5','6','7','8','9'};

                    for (int index = start; index < end; index++) {
                        if (!new String(acceptedChars).contains(String.valueOf(source.charAt(index)))) {
                            return "";
                        }
                    }
                }
                return null;
            }

        };


        sms_uid = (EditText) findViewById(R.id.sms_uid);
        sms_uid.setFilters(Textfilters);
        sms_upass = (EditText) findViewById(R.id.sms_upass);
        sms_number = (EditText) findViewById(R.id.sms_number);
        sms_number.setFilters(Textfilters);
        sms_message = (EditText) findViewById(R.id.sms_message);
        sms_send = (Button) findViewById(R.id.sms_send);

        sms_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(sms_uid.getText().toString().trim())){
                    sms_uid.setError(Html.fromHtml("<font color='red'>Enter Way2sms Registered Mobile Number!</font>"));
                    sms_uid.requestFocus();

                }
                else if (TextUtils.isEmpty(sms_upass.getText().toString().trim())){
                    sms_upass.setError(Html.fromHtml("<font color='red'>Enter Way2sms password!</font>"));
                    sms_upass.requestFocus();
                }
                else if (TextUtils.isEmpty(sms_number.getText().toString().trim())){
                    sms_number.setError(Html.fromHtml("<font color='red'>Enter Recepient</font>"));
                }
                else if (TextUtils.isEmpty(sms_message.getText().toString().trim())){
                    sms_message.setError(Html.fromHtml("<font color='red'>Enter Message</font>"));
                }
                else if (sms_uid.length()<10 || sms_uid.length() >10){
                    Toast.makeText(stu_sms.this, "Mobile Should be 10 digits", Toast.LENGTH_SHORT).show();
                }
                else if (sms_number.length()<10 || sms_number.length() >10){
                    Toast.makeText(stu_sms.this, "Mobile Should be 10 digits", Toast.LENGTH_SHORT).show();
                }
                else if (sms_message.length()>200){
                    Toast.makeText(stu_sms.this, "Message Cannot Exceed 200 Characters", Toast.LENGTH_SHORT).show();
                }
                else{
                    addEmployee();
                }


            }
        });
    }


    private void addEmployee(){


        final String uid = sms_uid.getText().toString().trim();
        final String upass = sms_upass.getText().toString().trim();
        final String number = sms_number.getText().toString().trim();
        final String message = sms_message.getText().toString().trim();



        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(stu_sms.this,"Sending...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.contentEquals("invalid login")){
                    Toast.makeText(stu_sms.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(stu_sms.this, "SMS Sent Successfully", Toast.LENGTH_LONG).show();

                    setContentView(R.layout.activity_success_register);
                    TextView text = (TextView) findViewById(R.id.success_message);
                    text.setText("SMS Sent Successfully");

                }
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_USER_SMS_UID,uid);
                params.put(Config.KEY_USER_SMS_UPASS,upass);
                params.put(Config.KEY_USER_SMS_NUMBER,number);
                params.put(Config.KEY_USER_SMS_MESSAGE,message);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_SEND_STU_SMS, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }
}
