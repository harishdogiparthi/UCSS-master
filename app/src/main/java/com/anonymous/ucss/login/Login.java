package com.anonymous.ucss.login;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anonymous.ucss.R;
import com.anonymous.ucss.config.Config;
import com.anonymous.ucss.lecturer.Lecturer;
import com.anonymous.ucss.registration.Register;
import com.anonymous.ucss.request_handler.RequestHandler;
import com.anonymous.ucss.student.Student;

import java.util.HashMap;
import java.util.regex.Pattern;


public class Login extends AppCompatActivity {



    EditText etLusername,etLPassword;
    Button etLogin,signup;
    String possibleEmail="";
    String username,password;
    public String result = "Login Successful";
    public String login_id = "Login_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLusername = (EditText) findViewById(R.id.etUsername);
        etLPassword = (EditText) findViewById(R.id.etPass);
        etLogin = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);

        final InputFilter[] Textfilters = new InputFilter[1];
        Textfilters[0] = new InputFilter(){
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (end > start) {

                    char[] acceptedChars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','1','2','3','4','5','6','7','8','9','0','!','@','#','$','%','&','*','(',')','_','-'};

                    for (int index = start; index < end; index++) {
                        if (!new String(acceptedChars).contains(String.valueOf(source.charAt(index)))) {
                            return "";
                        }
                    }
                }
                return null;
            }

        };

        etLPassword.setFilters(Textfilters);

        etLusername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
                    Account[] accounts = AccountManager.get(Login.this).getAccounts();
                    for (Account account : accounts) {
                        if (emailPattern.matcher(account.name).matches()) {
                            possibleEmail = account.name;
                            etLusername.setText(possibleEmail);
                        }
                    }



                }
            }
        });






        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,Register.class);
                startActivity(i);
                finish();

            }
        });

        etLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();


                username = etLusername.getText().toString().trim();
                password = etLPassword.getText().toString().trim();

                if (!isValidEmail(username)){
                    etLusername.setError(Html.fromHtml("<font color='red'>Enter Valid Email!</font>"));
                    etLusername.requestFocus();

                }
                else if (TextUtils.isEmpty(password)){

                    etLPassword.setError(Html.fromHtml("<font color='red'>Enter Password!</font>"));

                }
//                else if (!activeInfo.isConnected()){
//            Toast.makeText(Register.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
//        }
                else {
                    login();
                }



            }
        });

    }




    private void login(){

        final String username = etLusername.getText().toString().trim();
        final String password = etLPassword.getText().toString().trim();


        class checkLogin extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Login.this,"Loggin in...","Wait...",false,false);
                loading.setCancelable(true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                s.toString();
                switch (s){
                    case "Masters 1st Year":
                        Toast.makeText(Login.this, s+" Login Successful", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(Login.this,Student.class);
                    intent1.putExtra(Config.login_id,username);
                        intent1.putExtra(Config.WHO,"Masters 1st Year");
                    startActivity(intent1);
                        break;
                    case "Masters 2nd Year":
                        Toast.makeText(Login.this, s+" Login Successful", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(Login.this,Student.class);
                        intent2.putExtra(Config.login_id,username);
                        intent2.putExtra(Config.WHO,"Masters 2nd Year");
                        startActivity(intent2);
                        break;
                    case "Faculty":
                        Toast.makeText(Login.this, s+" Login Successful", Toast.LENGTH_LONG).show();
                        Intent intent4 = new Intent(Login.this,Lecturer.class);
                        intent4.putExtra(Config.login_id,username);
                        startActivity(intent4);
                        break;
                    case "Verify Email To Login":
                        Toast.makeText(Login.this, s, Toast.LENGTH_LONG).show();
                        break;
                    case "Login Failed":
                        Toast.makeText(Login.this, "Incorrect Username or Password \n OR \nEmail Not Registered", Toast.LENGTH_SHORT).show();

                        break;




                }
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();

                params.put(Config.KEY_USER_EMAIL,username);
                params.put(Config.KEY_USER_PASSWORD,password);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_LOGIN, params);
                return res;
            }
        }

        checkLogin checkLogin = new checkLogin();
        checkLogin.execute();
    }


    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}
