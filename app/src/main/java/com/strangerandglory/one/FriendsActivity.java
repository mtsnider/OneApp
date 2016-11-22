package com.strangerandglory.one;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FriendsActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ListView mListView;
    private ProgressDialog pDialog;
    private Handler updateBarHandler;
    private String newUser;
    private ListView list;
    private ArrayAdapter arrayAdapater;
    private TextView userName; //single list item
    private String addNewUser;
    private Button add_user;
    private ArrayList<String> list_of_users = new ArrayList<>();
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot().child("members");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        sharedPreferences = getSharedPreferences("main", MODE_PRIVATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        add_user = (Button) findViewById(R.id.add_user);
        list = (ListView) findViewById(R.id.friendslist);
        userName = (TextView) findViewById(R.id.userName);

        arrayAdapater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_of_users);




        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();

                    Member user = new Member(addNewUser);
                    root.child(sharedPreferences.getString("uid", "no udi"))
                            .child("friends")
                            .setValue(user);

                Log.d("mAuth", sharedPreferences.getString("uid","no udi"));

            }
        });

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });










    }

    private void addUser() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                final EditText email = new EditText(this);

                dialog.setView(email);
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addNewUser = email.getText().toString();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();
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
