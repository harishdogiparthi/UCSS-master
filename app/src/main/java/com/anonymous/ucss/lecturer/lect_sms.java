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

import java.util.HashMap;

public class lect_sms extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button sms_send;
    Spinner select;
    EditText message;
    String who,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lect_sms);

        Intent intent = getIntent();
        email = intent.getStringExtra(Config.login_id);



        select = (Spinner) findViewById(R.id.lect_sms_to);
        message = (EditText) findViewById(R.id.lect_sms_message);
        sms_send = (Button) findViewById(R.id.lect_send);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.who, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        select.setAdapter(adapter);
        select.setOnItemSelectedListener(this);



        sms_send.setOnClickListener(new View.OnClickListener() {
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

    }

    private void addEmployee(){

        final String str_message = message.getText().toString().trim();


        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(lect_sms.this,"Loggin in...","Wait...",false,false);
                loading.setCancelable(true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(lect_sms.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();

                params.put(Config.KEY_USER_EMAIL, email);
                params.put(Config.KEY_USER_SMS_NUMBER, who);
                params.put(Config.KEY_USER_SMS_MESSAGE, str_message);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_SEND_LECT_SMS, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }


}
