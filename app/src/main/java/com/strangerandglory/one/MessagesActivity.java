package com.strangerandglory.one;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessagesActivity extends AppCompatActivity {

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<ChatMessage> adapter;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_messages);
        adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        adapter.setNotifyOnChange(true);
        final ListView messageList = (ListView)findViewById(R.id.list);
        messageList.setAdapter(adapter);

        FirebaseApp app = FirebaseApp.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance(app);
        final DatabaseReference chatRootNode = database.getReference("messages");

        final EditText etMessage = (EditText)findViewById(R.id.etMessage);
        final String name = getIntent().getStringExtra("name");
        final Button sendButton = (Button) findViewById(R.id.addBtn);

        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sendButton.setEnabled(s.length() > 0);
            }
        });

        etMessage.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    if (sendButton.isEnabled()) {
                        sendButton.callOnClick();
                    }

                    // Mark key event as handled
                    return true;
                }

                // Mark the event unhandled to let it bubble up
                return false;
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                String message = etMessage.getText().toString();
                ChatMessage chatMessage = new ChatMessage(name, message);
                chatRootNode.push().setValue(chatMessage);
                etMessage.setText("");
                Log.d("Messages", "onClick: ");
            }
        });

        chatRootNode.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Messages", "onChildAdded: ");
                ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                adapter.add(chatMessage);
                messageList.setSelection(messageList.getCount() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
