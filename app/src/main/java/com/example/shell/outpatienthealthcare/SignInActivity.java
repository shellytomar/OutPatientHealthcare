package com.example.shell.outpatienthealthcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.shell.outpatienthealthcare.model.User;
import com.example.shell.outpatienthealthcare.rest.RestAPIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Sign In");
    }

    public void onClickSignIn(View view){
        if (view.getId() == R.id.signInButton) {
            Intent i = new Intent(SignInActivity.this, DashboardActivity.class);
            startActivity(i);
        }
    }



    private void fetchUser(){
        Call<User> call = RestAPIClient.get().signin(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("Register User", "Successfully Logged in user");
                user  = response.body();
                Log.i("user",user.getName());
                if(user.getUserId()>0) {
                    Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
                    intent.putExtra(SignUpActivity.USER, user);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("Error ","Cannot login user");
            }
        });
    }
}
