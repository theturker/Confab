package com.example.wansa.gpmessage;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class chat_activity extends AppCompatActivity {


    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private FirebaseUser fUser;
    private ArrayList<Message> chatLists = new ArrayList<>();
    private CustomAdapter customAdapter;
    private String subject;
    private ListView listView;
    private FloatingActionButton floatingActionButton;
    private EditText inputChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_activity);



        listView = (ListView)findViewById(R.id.chatListView);
        inputChat = (EditText)findViewById(R.id.inputChat);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);

        db = FirebaseDatabase.getInstance();
        fUser = FirebaseAuth.getInstance().getCurrentUser();


        customAdapter = new CustomAdapter(getApplicationContext(),chatLists,fUser);
        listView.setAdapter(customAdapter);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            // referansa ulaşıp ilgili sohbetleri getirebilmemiz için gerekli yapı
            subject = bundle.getString("subject");
            dbRef = db.getReference("ChatSubjects/"+subject+"/mesaj");
            setTitle(subject);
        }


        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chatLists.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Message message = ds.getValue(Message.class);
                    chatLists.add(message);
                    //Log.d("VALUE",ds.getValue(Message.class).getMesajText());
                }
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inputChat.getText().length()>=1){

                    long msTime = System.currentTimeMillis();
                    Date curDateTime = new Date(msTime);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd'/'MM'/'y hh:mm");
                    String dateTime = formatter.format(curDateTime);
                    Message message = new Message(inputChat.getText().toString(),fUser.getEmail(),dateTime);
                    dbRef.push().setValue(message);
                    inputChat.setText("");

                }else{

                    Toast.makeText(getApplicationContext(),"Gönderilecek mesaj uzunluğu en az 2 karakter olmalıdır!",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    }
