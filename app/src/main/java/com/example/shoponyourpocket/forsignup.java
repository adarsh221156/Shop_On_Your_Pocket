package com.example.shoponyourpocket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class forsignup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private FirebaseDatabase mdatabase;

    private FirebaseAuth firebaseAuth;

    EditText name, email, mobilenumber,address, password, DOB_day, DOB_month, DOB_year;
    Button signupp;
    Spinner shoptype;

    String selected;

    AwesomeValidation awesomeValidation;

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent intent8 = new Intent(forsignup.this, loginSignup.class);
        startActivity(intent8);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forsignup);

        mdatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        //final DatabaseReference myRef = mdatabase.getReference("UserID");
        final DatabaseReference myRef = mdatabase.getReference("UserID");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        name = findViewById(R.id.nameedit);
        email = findViewById(R.id.emailedit);
        mobilenumber = findViewById(R.id.mobilenumberedit);
        address = findViewById(R.id.addressedit);
        password = findViewById(R.id.passwordedit);
        DOB_day = findViewById(R.id.dayedit);
        DOB_month = findViewById(R.id.monthedit);
        DOB_year = findViewById(R.id.yearedit);

        signupp = findViewById(R.id.signupbtn);

        shoptype = findViewById(R.id.shoptypespinner);
        ArrayAdapter<CharSequence> shoptypeadapter = ArrayAdapter.createFromResource(this, R.array.shoptypes, android.R.layout.simple_spinner_item);
        //dropdown layout style
        shoptypeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shoptype.setAdapter(shoptypeadapter);

        awesomeValidation.addValidation(forsignup.this, R.id.nameedit, "[a-zA-Z\\s]+", R.string.nameerr);
        awesomeValidation.addValidation(forsignup.this, R.id.emailedit, android.util.Patterns.EMAIL_ADDRESS, R.string.emailerr);
        awesomeValidation.addValidation(forsignup.this, R.id.mobilenumberedit, RegexTemplate.TELEPHONE, R.string.phoneerr);

        signupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+";

                String email_valid = email.getText().toString();
                Matcher matcher = Pattern.compile(validemail).matcher(email_valid);
                if(!matcher.matches()){
                    email.setError("Enter valid email id");
                }*/
                if(password.getText().toString().equals("")){
                    password.setError("Enter password....");
                    return;
                }
                if(password.getText().toString().length()<8){
                    password.setError("Make it large....");
                    return;
                }
                if(mobilenumber.getText().toString().length()!=10){
                    mobilenumber.setError("Please enter 10 digits of your mobile number");
                    return;
                }

                if(!awesomeValidation.validate()){
                    Toast.makeText(forsignup.this, "Please enter all correct information", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(DOB_day.getText().toString().equals("") || DOB_month.getText().toString().equals("") || DOB_year.getText().toString().equals("")){
                    DOB_year.setError("Invalid....");
                    return;
                }

                /*
                shoptype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        selected = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                */

                final String Name = name.getText().toString();
                final String Email = email.getText().toString();
                final String number = mobilenumber.getText().toString();
                final String addresss = address.getText().toString();
                final String Password = password.getText().toString();
                final String ShopType = shoptype.getSelectedItem().toString();        //shoptype.toString();

                //---------------------------------------------------------------------------------
                //Toast.makeText(forsignup.this, "Selected "+ ShopType, Toast.LENGTH_SHORT).show();

                final int Day = Integer.parseInt(DOB_day.getText().toString().trim());
                final int Month = Integer.parseInt(DOB_month.getText().toString().trim());
                final int Year = Integer.parseInt(DOB_year.getText().toString().trim());

                /*
                final UserID user1 = new UserID();
                user1.setName(Name);
                user1.setAddress(addresss);
                user1.setDOB_Day(Day);
                user1.setDOB_Month(Month);
                user1.setDOB_Year(Year);
                user1.setEmail(Email);
                user1.setPassword(Password);
                user1.setShoptype(ShopType);
                user1.setMobilenumber(number);
                */

                /*myRef.child(Email).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        UserID user1 = dataSnapshot.getValue(UserID.class);
                        name = user1.name;

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/
                //String childname = user1.getMobilenumber();

                firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(forsignup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    UserID user1 = new UserID(Name, Email, Password, number, ShopType, addresss, Day, Month, Year);
                                    //myRef.child(number).setValue(user1);

                                    FirebaseDatabase.getInstance().getReference("UserID").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(forsignup.this,"Registration Complete",Toast.LENGTH_SHORT);
                                            Intent intent3 = new Intent(forsignup.this, loginSignup.class);
                                            startActivity(intent3);
                                            finish();
                                        }
                                    });


                                } else {
                                    Toast.makeText(forsignup.this, "Authentication failed.....Check your connection...",Toast.LENGTH_SHORT);
                                }

                                // ...
                            }
                        });


                //myRef.push().setValue(user1);



            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selected = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
