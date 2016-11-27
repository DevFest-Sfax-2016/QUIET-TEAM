package com.it_skills.ramzi.faamily;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity implements View.OnClickListener{
    private EditText Username, Password;
    private Button LoginButton,buttonAnynomecse;
    private TextView signupLogin;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    FirebaseAuth.AuthStateListener mAuthlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(login.this, HomeActivity.class));
            finish();
        }
        Username = (EditText) findViewById(R.id.UsernameId);
        Password = (EditText) findViewById(R.id.PasswordId);
        signupLogin = (TextView) findViewById(R.id.TextViewaccount);
        LoginButton = (Button) findViewById(R.id.LoginButtonId);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        buttonAnynomecse=(Button)findViewById(R.id.loginAnonymous);
        mAuthlistener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user==null){
                //important
                }
                else {
                    Log.d("SignIn",user.getUid());

                }

            }
        };
        signupLogin.setOnClickListener(this);
        LoginButton.setOnClickListener(this);
        buttonAnynomecse.setOnClickListener(this);

    }
    @Override
    public void onStart(){
        super.onStart();
        auth.addAuthStateListener(mAuthlistener);
    }

    @Override
    public void onStop() {
        super.onStop();
        auth.removeAuthStateListener(mAuthlistener);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.LoginButtonId:
                Userlogin();
                break;
            case R.id.TextViewaccount:
                finish();
                startActivity(new Intent(this, signup.class));
            case R.id.loginAnonymous:
                loginAnonymous();
                break;
        }
    }

    private void loginAnonymous() {
        auth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"error login !! ",Toast.LENGTH_SHORT).show();
                    }
            }
        });


    }

    private void Userlogin() {
        String user = Username.getText().toString().trim();
        String password = Password.getText().toString().trim();
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        //authenticate user
        auth.signInWithEmailAndPassword(user, password)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (Password.getText().toString().length() < 6) {
                                Password.setError(getString(R.string.minimum_password));
                            } else
                            {
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        } else {
//                            Toast.makeText(login.this, "is not Successful", Toast.LENGTH_LONG).show();
//                            finish();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

}