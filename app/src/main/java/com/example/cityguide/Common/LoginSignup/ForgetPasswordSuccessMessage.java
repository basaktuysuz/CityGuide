package com.example.cityguide.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.cityguide.R;

public class ForgetPasswordSuccessMessage extends AppCompatActivity {
Button SuccessBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forget_password_success_message);

   SuccessBtn=findViewById(R.id.password_success_go_to_login_bnt);
   SuccessBtn.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent intent =new Intent(getApplicationContext(),Login.class);
           startActivity(intent);
       }
   });
    }
}