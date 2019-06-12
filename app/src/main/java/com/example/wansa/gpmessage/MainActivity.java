package com.example.wansa.gpmessage;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private Toolbar actionbar;
    private ViewPager vpMain;
    private TabLayout tabsMain;
    private TabsAdapter tabsAdapter;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private Button sohbet;


    public void init(){
        actionbar=(Toolbar)findViewById(R.id.actionBar);
        setSupportActionBar(actionbar);
        getSupportActionBar().setTitle("Confab");
        auth=FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();

        sohbet=(Button)findViewById(R.id.sohbetid);



        vpMain=(ViewPager)findViewById(R.id.vpMain);
        tabsAdapter=new TabsAdapter(getSupportFragmentManager());
        vpMain.setAdapter(tabsAdapter);

        tabsMain=(TabLayout)findViewById(R.id.tabsMain);
        tabsMain.setupWithViewPager(vpMain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    protected void onStart() {


        sohbet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sohbett=new Intent(MainActivity.this,sohbetprogram.class);
                startActivity(sohbett);
                finish();

            }
        });
        if (currentUser==null){
            Intent welcomeIntent=new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(welcomeIntent);
            finish();
        }
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);

         getMenuInflater().inflate(R.menu.menu_main, menu);

         return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);

         if (item.getItemId()==R.id.mainLogout){

             auth.signOut();
             Intent loginIntent=new Intent(MainActivity.this,LoginActivity.class);
             startActivity(loginIntent);
             finish();

         }

         return true;
    }
}
