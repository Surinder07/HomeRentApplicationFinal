package com.example.surinder.homerentapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Chat extends AppCompatActivity {




    private String mDisplayName;
    private ListView mChatListView;
    private EditText mInputText;
    private ImageButton mSendButton;
    private DatabaseReference mDatabaseReference;
    private ChatListAdapter mAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        setDisplayName();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();



        // Link the Views in the layout to the Java code
        mInputText = (EditText) findViewById(R.id.messageInput);
        mSendButton = (ImageButton) findViewById(R.id.sendButton);
        mChatListView = (ListView) findViewById(R.id.chat_list_view);


        // TODO: Send the message when the "enter" button is pressed

        mInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                sendMessage();
                return true;
            }
        });

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                sendMessage();

            }
        });




    }



    private void setDisplayName(){


        SharedPreferences prefs = getSharedPreferences(RegisterActivity.CHAT_PREFS,MODE_PRIVATE);

        mDisplayName = prefs.getString(RegisterActivity.DISPLAY_NAME_KEY,null);

        if(mDisplayName== null) mDisplayName ="Anonymous";


    }


    private void sendMessage() {

        Log.d("FlashChat","I sent something");
        // TODO: Grab the text the user typed in and push the message to Firebase

        String input = mInputText.getText().toString();
        if(!input.equals("")){

            InstantMessage chat = new InstantMessage(input, mDisplayName);

            mDatabaseReference.child("messages").push().setValue(chat);
            mInputText.setText("");
        }


    }


    public void onStart()
    {
        super.onStart();
        mAdapter = new ChatListAdapter(this,mDatabaseReference, mDisplayName);
        mChatListView.setAdapter(mAdapter);



    }

    public void onStop(){

        super.onStop();

        //remove the firebase event Listener on the adapter
        mAdapter.cleanup();


    }

}
