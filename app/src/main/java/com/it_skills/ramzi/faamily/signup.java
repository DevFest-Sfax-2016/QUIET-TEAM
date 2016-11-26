package com.it_skills.ramzi.faamily;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity implements View.OnClickListener{

    private Button button;
    private EditText editTextuser, editTextPassword;
    private TextView TextViewSigin;
    private android.app.ProgressDialog ProgressDialog;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button = (Button) findViewById(R.id.ButtonRegister);
        editTextuser = (EditText) findViewById(R.id.Username);
        editTextPassword = (EditText) findViewById(R.id.PasswordRegister);
        TextViewSigin = (TextView) findViewById(R.id.textV);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        button.setOnClickListener(this);
        TextViewSigin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.ButtonRegister:
                RegisterUser();
                break;
            case R.id.textV:
                Intent I =new Intent(this,login.class);
                startActivity(I);
                break;
        }

    }

    private void RegisterUser() {
        String user = editTextuser.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(this, "Please Enter your Email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Enter your Password ", Toast.LENGTH_LONG).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(user,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    Toast.makeText(signup.this, "isSuccessful", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(signup.this, "is not Successful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}