package com.example.shoponyourpocket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    UserSessionManager session;

    private Button letsGo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new UserSessionManager(getApplicationContext());

        if(session.isUserLoggedIn() == true){
            Intent inten7 = new Intent(MainActivity.this, loggedinActivity.class);
            startActivity(inten7);
            finish();
        }

        letsGo = findViewById(R.id.letsgo);
        letsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logsignPage();
            }
        });
    }

    public void logsignPage(){
        Intent intent = new Intent(this, loginSignup.class);
        startActivity(intent);
        finish();
    }
}
