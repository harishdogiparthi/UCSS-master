package com.anonymous.ucss.registration;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.anonymous.ucss.R;
import com.anonymous.ucss.config.Config;
import com.anonymous.ucss.request_handler.RequestHandler;

import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;



public class Register extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    //Defining views
    private EditText etFname,etMname,etLname,etEmail,etPassword,etConfPassword,etRno,etMno,etHobbies;
    private DatePicker date_picker;
    private RadioGroup radio_group;
    private RadioButton r_gender;
    private int day,month,year;
    private String gender;
    private int selId;
    private Spinner signup_spinner;
    private String who,password,fname,mname,lname,email,rno,mno,dob,hobbies;
    String possibleEmail="";



    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

        InputFilter[] Textfilters1 = new InputFilter[1];
        Textfilters1[0] = new InputFilter(){
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


        //Initializing views
        signup_spinner = (Spinner) findViewById(R.id.signup_spinner);
        etFname = (EditText) findViewById(R.id.etFname);
        etFname.setFilters(Textfilters);
        etMname = (EditText) findViewById(R.id.etMname);
        etMname.setFilters(Textfilters);
        etLname = (EditText) findViewById(R.id.etLname);
        etLname.setFilters(Textfilters);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPassword.setFilters(Textfilters1);
        etConfPassword = (EditText) findViewById(R.id.etConfPass);
        etConfPassword.setFilters(Textfilters1);
        etRno = (EditText) findViewById(R.id.etRno);
        etMno = (EditText) findViewById(R.id.etMobNumber);
        etHobbies = (EditText) findViewById(R.id.etHobbies);
        buttonSubmit = (Button) findViewById(R.id.submit);

        date_picker = (DatePicker) findViewById(R.id.date_picker);



        radio_group = (RadioGroup) findViewById(R.id.radio_group);








        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
                    Account[] accounts = AccountManager.get(Register.this).getAccounts();
                    for (Account account : accounts) {
                        if (emailPattern.matcher(account.name).matches()) {
                            possibleEmail = account.name;
                            etEmail.setText(possibleEmail);
                        }
                    }



                }
            }
        });










        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.who, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        signup_spinner.setAdapter(adapter);

        signup_spinner.setOnItemSelectedListener(this);
        //Setting listeners to button
        buttonSubmit.setOnClickListener(this);
    }


    //Adding an employee
    private void addUser(){


        class AddUser extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                if (s.contentEquals("Student Registration Successful") || s.contentEquals("Faculty Registration Successful")){


                    setContentView(R.layout.activity_success_register);
                    TextView textView = (TextView) findViewById(R.id.success_message);
                    textView.setText(R.string.register_success_msg);

                    Toast.makeText(Register.this, s, Toast.LENGTH_LONG).show();
                }
                else if(s.contentEquals("Active")){
                    Toast.makeText(Register.this, "Email Already Registered And Account is Active", Toast.LENGTH_LONG).show();
                    setContentView(R.layout.activity_success_register);
                    TextView textView = (TextView) findViewById(R.id.success_message);
                    textView.setText(R.string.register_account_active);
                }
                else if(s.contentEquals("Verify")){
                    Toast.makeText(Register.this, "Email Already Registered\nPlease Verify Your Email For Activating Your Account", Toast.LENGTH_LONG).show();
                    setContentView(R.layout.activity_success_register);
                    TextView textView = (TextView) findViewById(R.id.success_message);
                    textView.setText(R.string.register_success_msg);
                }
                else {
                    Toast.makeText(Register.this, s, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_USER_FNAME,fname);
                params.put(Config.KEY_USER_MNAME,mname);
                params.put(Config.KEY_USER_LNAME,lname);
                params.put(Config.KEY_USER_EMAIL,email);
                params.put(Config.KEY_USER_PASSWORD,password);
                params.put(Config.KEY_USER_RNO,rno);
                params.put(Config.KEY_USER_MNO,mno);
                params.put(Config.KEY_USER_DOB,dob);
                params.put(Config.KEY_USER_WHO,who);
                params.put(Config.KEY_USER_GENDER,gender);
                params.put(Config.KEY_USER_HOBBIES,hobbies);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_REGISTER, params);
                return res;
            }
        }

        AddUser addUser = new AddUser();
        addUser.execute();
    }

    @Override
    public void onClick(View v) {

        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();

        final String confPass = etConfPassword.getText().toString().trim();


        fname = etFname.getText().toString().trim();
        mname = etMname.getText().toString().trim();
        lname = etLname.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        rno = etRno.getText().toString().trim();
        mno = etMno.getText().toString().trim();
        day = date_picker.getDayOfMonth();
        month = date_picker.getMonth()+1;
        year = date_picker.getYear();
        hobbies = etHobbies.getText().toString().trim();
        dob = Integer.toString(day)+Integer.toString(month)+Integer.toString(year);
        if(radio_group.getCheckedRadioButtonId()!=-1){
            selId= radio_group.getCheckedRadioButtonId();
            r_gender = (RadioButton) findViewById(selId);
            gender = r_gender.getText().toString().trim();

        }





        if (TextUtils.isEmpty(etFname.getText().toString().trim())){
            etFname.setError(Html.fromHtml("<font color='red'>Enter First name!</font>"));
            etFname.requestFocus();

        }
        else if (TextUtils.isEmpty(etLname.getText().toString().trim())){
            etLname.setError(Html.fromHtml("<font color='red'>Enter Last Name!</font>"));
            etLname.requestFocus();

        }
        else if (TextUtils.isEmpty(etEmail.getText().toString().trim())){
            etEmail.setError(Html.fromHtml("<font color='red'>Enter Email!</font>"));
            etEmail.requestFocus();

        }
        else if (TextUtils.isEmpty(who)){

            Toast.makeText(Register.this, "Select From Spinner", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(etPassword.getText().toString().trim())){
            etPassword.setError(Html.fromHtml("<font color='red'>Enter Password!</font>"));
            etPassword.requestFocus();

        }
        else if (TextUtils.isEmpty(etConfPassword.getText().toString().trim())){
            etConfPassword.setError(Html.fromHtml("<font color='red'>Enter Confirm Password!</font>"));
            etConfPassword.requestFocus();

        }
        else if (TextUtils.isEmpty(etRno.getText().toString().trim()) && who.contains("Masters")){
            etRno.setError(Html.fromHtml("<font color='red'>Enter Roll Number!</font>"));
            etRno.requestFocus();

        }
        else if (rno.length() < 5 && who.contains("Masters")){
            Toast.makeText(Register.this, "University Roll Number Should be 5 digits", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(gender)){
            Toast.makeText(Register.this, "Select Gender", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(etMno.getText().toString().trim())){
            etMno.setError(Html.fromHtml("<font color='red'>Enter Mobile Number</font>"));
            etMno.requestFocus();

        }else if (etHobbies.getText().toString().trim().isEmpty()){

            etHobbies.setError(Html.fromHtml("<font color='red'>Enter Your Hobbies</font>"));
            etHobbies.requestFocus();
        }
        else if(mno.length()!=10){
            Toast.makeText(Register.this, "Mobile Number Should be 10 digits", Toast.LENGTH_SHORT).show();
        }
        else if (!confPass.contentEquals(password)) {

            Toast.makeText(Register.this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();

        }
        else if (!isValidEmail(email)){

            Toast.makeText(Register.this, "Enter a Valid Email", Toast.LENGTH_SHORT).show();

        }
//        else if (!activeInfo.isConnected()){
//            Toast.makeText(Register.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
//        }
        else{
            addUser();
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        who = adapterView.getItemAtPosition(i).toString();
        if(TextUtils.isEmpty(who)){
            signup_spinner.requestFocus();
        }
        else if (who.contentEquals("Faculty")){
            etRno.setText("");
            etRno.setEnabled(false);


        }
        else{
            etRno.setEnabled(true);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
