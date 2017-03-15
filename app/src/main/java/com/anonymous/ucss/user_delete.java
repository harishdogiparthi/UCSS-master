package com.anonymous.ucss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anonymous.ucss.config.Config;
import com.anonymous.ucss.request_handler.RequestHandler;

import java.util.HashMap;

public class user_delete extends AppCompatActivity {

    TextView textView;
    Button delete;
    String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_delete);

        Intent intent = getIntent();
        account = intent.getStringExtra(Config.login_id);

        textView = (TextView) findViewById(R.id.del_account);
        delete = (Button) findViewById(R.id.sbm_del_account);

        textView.setText(account);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delUser();
            }
        });





    }



    private void delUser(){




        class DelUser extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(user_delete.this,"Deleting...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.contentEquals("Account Deleted Successfully")){


                    textView.setText(R.string.delete_account_success);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        }
                    }, 3000);





                    ;



                }
                else {
                    Toast.makeText(user_delete.this, s, Toast.LENGTH_LONG).show();


                }
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();

                params.put(Config.KEY_USER_EMAIL,account);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_DEL_USER, params);
                return res;
            }
        }

        DelUser delUser = new DelUser();
        delUser.execute();
    }


}
