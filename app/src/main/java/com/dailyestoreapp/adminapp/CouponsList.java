package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CouponsList extends AppCompatActivity {
Button save ;
EditText coupon_name,desc,offer_percent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons_list);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               CouponsList.CustomDialogClass1 cdd2=new CouponsList.CustomDialogClass1(CouponsList.this);
                cdd2.show();
            }
        });

    }
    public class CustomDialogClass1 extends Dialog implements
            android.view.View.OnClickListener {

        public Context c;
        public Dialog d;
        public Button yes, no;
        String txt;
        public TextView textdisplayed;

        public CustomDialogClass1(Context a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;

        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.approve_dialog);
            yes = (Button) findViewById(R.id.btn_yes);

            textdisplayed=(TextView)findViewById(R.id.txt_dia);
            textdisplayed.setText("Coupon added successfully");
            yes.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_yes:
                    dismiss();
                    break;

                default:
                    break;
            }

        }
    }
}
