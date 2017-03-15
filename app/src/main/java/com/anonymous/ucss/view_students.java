package com.anonymous.ucss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.anonymous.ucss.config.Config;
import com.anonymous.ucss.request_handler.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class view_students extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;
    private String JSON_STRING;
    public String who,table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        Intent intent = getIntent();
        table = intent.getStringExtra(Config.table);
        who = intent.getStringExtra(Config.MASTERS);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(view_students.this);

        getJSON();
    }


    private void showStudents(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String rno = jo.getString(Config.TAG_RNO);
                String fname = jo.getString(Config.TAG_NAME);




                HashMap<String,String> employees = new HashMap<>();
                employees.put(Config.TAG_RNO,rno);
                employees.put(Config.TAG_NAME,fname);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                view_students.this, list, R.layout.list_marks_i,
                new String[]{Config.TAG_RNO,Config.TAG_NAME},
                new int[]{R.id.id,R.id.email});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(view_students.this,"Fetching Data","Wait...",false,false);
                loading.setCancelable(true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showStudents();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();

                params.put(Config.KEY_USER_WHO,who);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_LIST_ALL,params);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(view_students.this, view_individual.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String rno = map.get(Config.TAG_RNO).toString();
        intent.putExtra(Config.table,table);
        intent.putExtra(Config.TAG_RNO,rno);
        startActivity(intent);
    }
}
