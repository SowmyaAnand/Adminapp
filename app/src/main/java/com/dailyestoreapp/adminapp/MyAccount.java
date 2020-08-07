package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyAccount extends AppCompatActivity {
Button logout;
    TextView edit_account ;
    EditText name_account,mob_account,email_account,place_account,address_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        logout=findViewById(R.id.logout);
        name_account = (EditText) findViewById(R.id.Name_account);
        mob_account = (EditText) findViewById(R.id.Mobilenumber_account);
        email_account = (EditText)findViewById(R.id.emailid_account);

        place_account = (EditText)findViewById(R.id.Place_account);

        edit_account = (TextView)findViewById(R.id.edit_acount);
        edit_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name_account.setEnabled(true);name_account.setFocusable(true);
                mob_account.setEnabled(true);mob_account.setFocusable(true);
                email_account.setEnabled(true);email_account.setFocusable(true);

                place_account.setEnabled(true);place_account.setFocusable(true);


            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MyAccount.this,Login.class);
                startActivity(next);
            }
        });
    }
}
