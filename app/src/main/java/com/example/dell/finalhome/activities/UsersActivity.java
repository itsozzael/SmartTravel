package com.example.dell.finalhome.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dell.finalhome.R;
import com.example.dell.finalhome.model.User;
import com.example.dell.finalhome.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 12/2/2017.
 */

public class UsersActivity extends AppCompatActivity {

   // private AppCompatActivity activity = UsersActivity.this;
    private TextView textViewName;
    private AppCompatButton appCompatButtonLog;
    //private RecyclerView recyclerViewUsers;
    //private List<User> listUsers;
    //private UsersRecyclerAdapter usersRecyclerAdapter;
    //private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        textViewName = (TextView) findViewById(R.id.text1);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText("Welcome" +nameFromIntent);
        appCompatButtonLog = (AppCompatButton) findViewById(R.id.appCompatButtonLogs);
        appCompatButtonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister = new Intent(getApplicationContext(),Log.class);
                startActivity(intentRegister);
            }
        });

    }



}

