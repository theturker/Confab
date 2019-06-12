package com.example.wansa.gpmessage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {

    private Toolbar actionbarRegister;
    private EditText txtUsername, txtEmail, txtPassword;
    private Button btnRegister,btnLoginRegister;
    private FirebaseAuth auth;


    public void init() {
        actionbarRegister = (Toolbar) findViewById(R.id.actionbarRegister);
        setSupportActionBar(actionbarRegister);
        getSupportActionBar().setTitle("Hesap Oluşturun");


        auth=FirebaseAuth.getInstance();

        txtUsername=(EditText) findViewById(R.id.txtUsernameRegister);
        txtEmail=(EditText) findViewById(R.id.txtEmailRegister);
        txtPassword=(EditText)findViewById(R.id.txtPasswordRegister);
        btnRegister=(Button)findViewById(R.id.btnWelcomeRegister);
        btnLoginRegister=(Button)findViewById(R.id.loginbuton);


    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    createNewAccount();

                }



            });
            btnLoginRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToLoginActivity();

                }
            });
    }

    private void goToLoginActivity() {

        Intent loginIntent=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(loginIntent);
        finish();

    }

    private void createNewAccount() {

        String username = txtUsername.getText().toString();
        String email=txtEmail.getText().toString();
        String password=txtPassword.getText().toString();

        if (TextUtils.isEmpty(email)){

            Toast.makeText(this,"email boş olmaz",Toast.LENGTH_LONG).show();

        }else if (TextUtils.isEmpty(password)){

            Toast.makeText(this,"şifre boş olmaz",Toast.LENGTH_LONG).show();

        }else {

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override

                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        Toast.makeText(RegisterActivity.this,"Hesap oluştu",Toast.LENGTH_LONG).show();
                        Intent loginIntent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(loginIntent);
                        finish();

                    }else {

                        Toast.makeText(RegisterActivity.this,"hata oldu",Toast.LENGTH_LONG).show();
                    }

                }
            });

        }

    }
}

