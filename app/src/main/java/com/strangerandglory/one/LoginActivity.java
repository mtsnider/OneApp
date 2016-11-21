package com.strangerandglory.one;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String TAG = "Login";
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button sign_in = (Button) findViewById(R.id.sign_in_button);
        final Button register = (Button) findViewById(R.id.btn_register);
        Button forgot_password = (Button) findViewById(R.id.btn_reset_password);


        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mAuth.getCurrentUser() != null) {
                    Log.d("Login", "onAuthStateChanged:signed_in:");
                } else {
                    // User is signed out
                    Log.d("Login", "onAuthStateChanged:signed_out");
                }


            }
        };

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               signIn(email.getText().toString(), password.getText().toString());

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(email.getText().toString(), password.getText().toString());
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.sendPasswordResetEmail(email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("LOG", "We have sent you instructions to reset your password!");
                                } else {
                                    Log.d("LOG", "Failed to send reset email!");
                                }

                            }
                        });
            }
        });


    }



    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        sharedPreferences = getSharedPreferences("main", MODE_PRIVATE);
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        Log.d(TAG, mAuth.getCurrentUser().getEmail());
                        Log.d(TAG, mAuth.getCurrentUser().getUid());
                        sharedPreferences = getSharedPreferences("main", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name",mAuth.getCurrentUser().getDisplayName() );
                        editor.putString("email",mAuth.getCurrentUser().getEmail() );
                        editor.putString("uid",mAuth.getCurrentUser().getUid() );
                        editor.commit();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);


                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());

                        }

                    }
                });
    }

    public void register(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                    }
                });
    }







    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
