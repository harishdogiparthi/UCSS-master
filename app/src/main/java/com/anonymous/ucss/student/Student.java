package com.anonymous.ucss.student;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.anonymous.ucss.R;
import com.anonymous.ucss.config.Config;
import com.anonymous.ucss.edit_user;
import com.anonymous.ucss.login.Login;
import com.anonymous.ucss.user_delete;

public class Student extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String username,who;
    String user="Student";
    TextView email;
    int tofinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student);

        Intent intent = getIntent();
        username = intent.getStringExtra(Config.login_id);
        who = intent.getStringExtra(Config.WHO);








        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            public void onDrawerSlide(View drawerView,float v) {
                super.onDrawerSlide(drawerView,0);
                // Do whatever you want here
                email = (TextView) findViewById(R.id.log_stu_email);
                email.setText(username);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // set title
            alertDialogBuilder.setTitle("Logout");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Click Yes to Logout !")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close
                            // current activity
                            Student.this.finish();
                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();


//            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_stu_int) {


            Intent intent = new Intent(Student.this,view_details.class);
            intent.putExtra(Config.login_id,username);
            intent.putExtra(Config.WHO, who);
            intent.putExtra(Config.table, Config.LABEL1);
            startActivity(intent);


        } else if (id == R.id.nav_stu_attendance) {
            Intent intent = new Intent(Student.this,view_details.class);
            intent.putExtra(Config.login_id,username);
            intent.putExtra(Config.WHO, who);
            intent.putExtra(Config.table, Config.LABEL2);
            startActivity(intent);

        } else if (id == R.id.nav_stu_edit) {

            Intent intent = new Intent(Student.this,edit_user.class);
            intent.putExtra(Config.login_id,username);
            intent.putExtra(Config.WHO,user);
            startActivity(intent);

        }else if (id == R.id.nav_stu_files) {

            Intent intent = new Intent(Student.this,download_files.class);
            intent.putExtra(Config.WHO,who);
            startActivity(intent);

        }  else if (id == R.id.nav_stu_delete) {
            Intent intent = new Intent(Student.this,user_delete.class);
            intent.putExtra(Config.login_id,username);
            startActivity(intent);

        } else if (id == R.id.nav_mail) {
            Intent intent = new Intent(Student.this, stu_mail.class);
            intent.putExtra(Config.login_id,username);
            startActivity(intent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
