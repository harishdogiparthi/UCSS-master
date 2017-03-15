package com.anonymous.ucss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anonymous.ucss.config.Config;
import com.anonymous.ucss.request_handler.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class edit_user extends AppCompatActivity {


    EditText edit_fname,edit_mname,edit_lname,edit_rno,edit_mno;
    DatePicker edit_datepicker;
    Button edit_submit;
    private String JSON_STRING = null;
     String email = null;
    String fname = null;
    String mname = null;
    String lname = null;
    String rno = null;
    String mno = null;
    String dob = null;
    String user;

    int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);


        Intent intent = getIntent();
        email = intent.getStringExtra(Config.login_id);
        user = intent.getStringExtra(Config.WHO);


        InputFilter[] Textfilters = new InputFilter[1];
        Textfilters[0] = new InputFilter(){
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (end > start) {

                    char[] acceptedChars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',' '};

                    for (int index = start; index < end; index++) {
                        if (!new String(acceptedChars).contains(String.valueOf(source.charAt(index)))) {
                            return "";
                        }
                    }
                }
                return null;
            }

        };


        edit_fname = (EditText) findViewById(R.id.edit_etFname);
        edit_fname.setFilters(Textfilters);
        edit_mname = (EditText) findViewById(R.id.edit_etMname);
        edit_mname.setFilters(Textfilters);
        edit_lname = (EditText) findViewById(R.id.edit_etLname);
        edit_lname.setFilters(Textfilters);

        edit_mno = (EditText) findViewById(R.id.edit_etMobNumber);
        edit_rno = (EditText) findViewById(R.id.edit_etRno);

        edit_submit = (Button) findViewById(R.id.edit_submit);
        edit_datepicker = (DatePicker) findViewById(R.id.edit_date_picker);


        if (user.contentEquals("Faculty")){
            edit_rno.setEnabled(false);
        }






        getJSON();


        edit_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fname = edit_fname.getText().toString().trim();
                mname = edit_mname.getText().toString().trim();
                lname = edit_lname.getText().toString().trim();
                rno = edit_rno.getText().toString().trim();
                mno = edit_mno.getText().toString().trim();

                day = edit_datepicker.getDayOfMonth();
                month = edit_datepicker.getMonth()+1;
                year = edit_datepicker.getYear();

                dob = Integer.toString(day)+Integer.toString(month)+Integer.toString(year);



                updateUser();
            }
        });




    }


    private void showDetails(){
        JSONObject jsonObject = null;
        String mfname,mmname,mlname,mrno,mmno,mdob;
        mfname = null;
        mmname = null;
        mlname = null;
        mrno = null;
        mmno = null;
        mdob = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                 mfname = jo.getString(Config.TAG_FNAME);
                 mmname = jo.getString(Config.TAG_MNAME);
                 mlname = jo.getString(Config.TAG_LNAME);
                 mrno = jo.getString(Config.TAG_RNO);
                 mmno = jo.getString(Config.TAG_MOB_NO);
                 mdob = jo.getString(Config.TAG_DOB);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        if(!TextUtils.isEmpty(mfname)){

            edit_fname.setText(mfname);
        }
        if(!TextUtils.isEmpty(mmname)){

            edit_mname.setText(mmname);
        }
        if(!TextUtils.isEmpty(mlname)){

            edit_lname.setText(mlname);
        }
        if(!TextUtils.isEmpty(mrno)){


            edit_rno.setText(mrno);
        }
        if (!TextUtils.isEmpty(mmno)){

            edit_mno.setText(mmno);
        }
        if (!TextUtils.isEmpty(mdob)) {

            if(mdob.length() == 8) {
                day = Integer.parseInt(mdob.substring(0, 2));
                month = Integer.parseInt(mdob.substring(2, 4));
                year = Integer.parseInt(mdob.substring(4, 8));


                edit_datepicker.updateDate(year, month - 1, day);
            }

        }





    }


















    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(edit_user.this,"Fetching Data","Wait...",false,false);
                loading.setCancelable(true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showDetails();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();

                params.put(Config.KEY_USER_EMAIL,email);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_EDIT_USER,params);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }



    private void updateUser(){
        class UpdateUser extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(edit_user.this,"Updating Data","Wait...",false,false);
                loading.setCancelable(true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(edit_user.this, s, Toast.LENGTH_SHORT).show();
                if (s.contentEquals("Updated Successfully")){
                    setContentView(R.layout.activity_success_register);
                    TextView textView = (TextView)findViewById(R.id.success_message);
                    textView.setText(s);
                }
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();


                params.put(Config.KEY_USER_EMAIL,email);
                params.put(Config.KEY_USER_FNAME,fname);
                params.put(Config.KEY_USER_MNAME,mname);
                params.put(Config.KEY_USER_LNAME,lname);
                params.put(Config.KEY_USER_RNO,rno);
                params.put(Config.KEY_USER_MNO,mno);
                params.put(Config.KEY_USER_DOB,dob);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_UPDATE_USER,params);
                return s;
            }
        }
        UpdateUser updateUser = new UpdateUser();
        updateUser.execute();
    }











}
