package com.example.wansa.gpmessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class sohbetprogram extends AppCompatActivity {


    private Toolbar actionbarr;
    private ListView listView;
    private FirebaseAuth fAuth;
    private ArrayList<String> subjectLists = new ArrayList<>();
    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private ArrayAdapter<String> adapter;


    public void init(){
        actionbarr=(Toolbar)findViewById(R.id.actionBarr);
        setSupportActionBar(actionbarr);
        getSupportActionBar().setTitle("Confab");

    }


    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sohbetprogram);

        init();

        fAuth = FirebaseAuth.getInstance();

        listView = (ListView)findViewById(R.id.listViewSubjects);

        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("ChatSubjects");

        adapter = new ArrayAdapter<String>(sohbetprogram.this, android.R.layout.simple_list_item_1, android.R.id.text1,subjectLists);
        listView.setAdapter(adapter);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                subjectLists.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    subjectLists.add(ds.getKey());
                    Log.d("LOGVALUE",ds.getKey());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),""+databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(sohbetprogram.this, chat_activity.class);
                intent.putExtra("subject",subjectLists.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mainLogout)
        {
            fAuth.signOut();
            finish();
        }
        if (item.getItemId()==R.id.arkasayfa)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}