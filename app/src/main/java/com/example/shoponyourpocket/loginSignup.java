package com.example.shoponyourpocket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginSignup extends AppCompatActivity {

    private EditText memail;
    private EditText mpassword;
    private Button login;
    private Button signup;
    public CheckBox rememe;

    UserSessionManager session;

    private TextView tvLoad;
    TextView tvReset;

    AwesomeValidation awesomeValidation;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_signup);

        session = new UserSessionManager(getApplicationContext());

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        memail = findViewById(R.id.email_id);
        mpassword = findViewById(R.id.id_pass);
        rememe = findViewById(R.id.remchkbtn);           //Complete Checkbox button
        login = findViewById(R.id.logbtn);

        awesomeValidation.addValidation(loginSignup.this, R.id.email_id, android.util.Patterns.EMAIL_ADDRESS, R.string.emailerr);

        firebaseAuth = FirebaseAuth.getInstance();

        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isUserLoggedIn(), Toast.LENGTH_SHORT).show();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Email = memail.getText().toString().trim();
                String Password = mpassword.getText().toString().trim();

                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(loginSignup.this, "Please Enter Email", Toast.LENGTH_SHORT);
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    Toast.makeText(loginSignup.this, "Please Enter Password", Toast.LENGTH_SHORT);
                    return;
                }
                if(mpassword.getText().toString().length()<8){
                    mpassword.setError("Make it large....");
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(loginSignup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    session.createUserLoginSession("User Session ", Email);
                                    loggedinpage();
                                    finish();
                                } else {
                                    Toast.makeText(loginSignup.this, "Login failed or User not Available....",Toast.LENGTH_SHORT);
                                }

                                // ...
                            }
                        });


            }
        });

        signup = findViewById(R.id.signbtn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signuppage();
                finish();
            }
        });

        

    }

    public void loggedinpage(){
        Intent intent2 = new Intent(this, loggedinActivity.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);

    }

    public void signuppage(){

        Intent intent1 = new Intent(this,forsignup.class);
        startActivity(intent1);
    }
}
