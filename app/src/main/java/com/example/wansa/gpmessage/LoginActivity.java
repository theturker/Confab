package com.example.wansa.gpmessage;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    
    private Toolbar actionbarLogin;
    private EditText txtEmail, txtPassword;
    private Button btnLogin, btnRegisterLogin;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    TextView baslik;
    public void init(){

        actionbarLogin=(Toolbar)findViewById(R.id.actionbarLogin);

        setSupportActionBar(actionbarLogin);
        getSupportActionBar().setTitle("Giriş Yap");

        auth=FirebaseAuth.getInstance();

        currentUser=auth.getCurrentUser();

        txtEmail=(EditText)findViewById(R.id.txtemailLogin);
        txtPassword=(EditText)findViewById(R.id.txtpasswordLogin);
        btnLogin=(Button)findViewById(R.id.userLogin); //hatalı olabilecek kısım.
        baslik=(TextView)findViewById(R.id.txtWelcome);
        btnRegisterLogin=(Button)findViewById(R.id.btnRegisterLogin);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();

            }
        });
         btnRegisterLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 goToregisterActivity();
             }
         });
    }

    private void goToregisterActivity() {
        Intent registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(registerIntent);
        finish();
    }

    private void loginUser() {

        String email=txtEmail.getText().toString();
        String password=txtPassword.getText().toString();

        if (TextUtils.isEmpty(email)){

            Toast.makeText(this,"Lütfen Email Adresinizi Giriniz",Toast.LENGTH_LONG).show();

        }else if (TextUtils.isEmpty(password)){

            Toast.makeText(this,"Lütfen Şifrenizi Giriniz",Toast.LENGTH_LONG).show();

        }else {
            btnLogin.setEnabled(false); //giriş yaparken butona 1 kere tıklamak için

            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        Toast.makeText(LoginActivity.this,"Giriş Başarılı",Toast.LENGTH_LONG).show();
                        Intent mainIntent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }else{

                        Toast.makeText(LoginActivity.this,"Giriş Başarısız",Toast.LENGTH_LONG).show();
                        btnLogin.setEnabled(true);//sayfa açıldıktan sonra veya hatadan sonra butonu tekrar aktif yapmak için kullanılır

                    }

                }
            });

        }

    }
}
