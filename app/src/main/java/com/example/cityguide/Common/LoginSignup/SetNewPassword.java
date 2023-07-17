package com.example.cityguide.Common.LoginSignup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.cityguide.Databases.CheckInternet;
import com.example.cityguide.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SetNewPassword extends AppCompatActivity {

    private TextInputLayout newPassword, confirmPassword;
    Button Next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_set_new_password);
        Next_btn = findViewById(R.id.set_new_password_btn);
        newPassword=findViewById(R.id.set_new_password);
        confirmPassword=findViewById(R.id.confirm_password);
        Next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validate phone Number
                if (!validatePassword() | !validateConfirmPassword()) {
                    return;
                }

                //Get data from fields
                String _newPassword = newPassword.getEditText().getText().toString().trim();
                String _phoneNumber = getIntent().getStringExtra("phoneNo");

                //update data İn friebase and in sessions
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.child(_phoneNumber).child("password").setValue(_newPassword);

                startActivity(new Intent(getApplicationContext(), ForgetPasswordSuccessMessage.class));
                finish();
            }
        });
    }


    private boolean validateConfirmPassword() {
        if (newPassword != newPassword) {
            return false;
        } else {
            return true;
        }

    }

    private boolean validatePassword() {

        String val =newPassword.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            newPassword.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            newPassword.setError("Password should contain 4 characters!");
            return false;
        } else {
            newPassword.setError(null);
            newPassword.setErrorEnabled(false);
            return true;
        }
    }//end method


    //show internet coonnection dialog
    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SetNewPassword.this);
        builder.setMessage("please connect the internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));
                    }
                });
    }


}