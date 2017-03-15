package com.anonymous.ucss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.anonymous.ucss.config.Config;
import com.anonymous.ucss.request_handler.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class view_individual extends AppCompatActivity {

    String roll_no,table;
    private String JSON_STRING;
    TextView sub1,sub2,sub3,sub4,sub5;
    String marks1,marks2,marks3,marks4,marks5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_individual);


        Intent intent = getIntent();
        roll_no = intent.getStringExtra(Config.TAG_RNO);
        table = intent.getStringExtra(Config.table);

        sub1 = (TextView) findViewById(R.id.marks1);
        sub2 = (TextView) findViewById(R.id.marks2);
        sub3 = (TextView) findViewById(R.id.marks3);
        sub4 = (TextView) findViewById(R.id.marks4);
        sub5 = (TextView) findViewById(R.id.marks5);




        getJSON();



    }



    private void showMarks(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                 marks1 = jo.getString(Config.TAG_SUB1);
                 marks2 = jo.getString(Config.TAG_SUB2);
                 marks3 = jo.getString(Config.TAG_SUB3);
                 marks4 = jo.getString(Config.TAG_SUB4);
                 marks5 = jo.getString(Config.TAG_SUB5);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        sub1.setText(marks1);
        sub2.setText(marks2);
        sub3.setText(marks3);
        sub4.setText(marks4);
        sub5.setText(marks5);


    }




    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(view_individual.this,"Fetching Data","Wait...",false,false);
                loading.setCancelable(true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showMarks();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();

                params.put(Config.KEY_USER_TABLE,table);
                params.put(Config.KEY_USER_RNO,roll_no);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_VIEW_INDIVIDUAL,params);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }




}
