package com.strangerandglory.one;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ListView mListView;
    private ProgressDialog pDialog;
    private Handler updateBarHandler;
    ArrayList<String> friendsList;
    Cursor cursor;
    int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton addFriend = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(FriendsActivity.this);
                dialog.setContentView(R.layout.add_friend_dialog);
                //dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                EditText email = (EditText) dialog.findViewById(R.id.add_email);
                email.setText("Android custom dialog example!");


                Button add = (Button) dialog.findViewById(R.id.add_email_btn);
                // if button is clicked, close the custom dialog
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                });
            }
        });






    }

    private void writeNewUser(String userId, String name, String email) {
        Member user = new Member(name, email);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(userId).setValue(user);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.messages:
                Intent test = new Intent(FriendsActivity.this, FriendsActivity.class);
                startActivityForResult(test,0);
                return true;

            case R.id.posts:

                return true;

            case R.id.action_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
