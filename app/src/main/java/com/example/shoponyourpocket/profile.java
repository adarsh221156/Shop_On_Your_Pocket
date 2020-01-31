package com.example.shoponyourpocket;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class profile extends Fragment {

    TextView Name, Address, Email, Number, ShopType, Day, Month, Year;
    private FirebaseDatabase mfirebaseDatabse;
    private DatabaseReference databaseReference;
    FirebaseUser user;
    String uid;
    UserID currentUser;

    String user_name, DOB_month, DOB_year, address, numBer, shopType, email;
    int DOB_day;

    public profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_profile, container, false);
        Name = v.findViewById(R.id.nameEdit);
        Address = v.findViewById(R.id.addressEdit);
        Email = v.findViewById(R.id.emailEdit);
        Number = v.findViewById(R.id.textView38);
        Day = v.findViewById(R.id.dayEdit);
        Month = v.findViewById(R.id.monthEdit);
        Year = v.findViewById(R.id.yearEdit);
        ShopType = v.findViewById(R.id.shopTypeEdit);

        mfirebaseDatabse = FirebaseDatabase.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
       // Toast.makeText(profile.this,uid.toLowerCase(),Toast.LENGTH_SHORT).show();
        final DatabaseReference databaseReference = mfirebaseDatabse.getReference();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                /*currentUser = dataSnapshot.getValue(UserID.class);
                Name.setText(currentUser.getName());*/
                /*
                if(dataSnapshot.child(uid).equals("name")){
                    Name.setText(dataSnapshot.child("name").getValue().toString());
                }*/
                user_name = dataSnapshot.child(uid).child("name").getValue(String.class);
                Name.setText(user_name);
                email = dataSnapshot.child(uid).child("email").getValue(String.class);
                Email.setText(email);
                address = dataSnapshot.child(uid).child("address").getValue(String.class);
                Address.setText(address);
                shopType = dataSnapshot.child(uid).child("shoptype").getValue(String.class);
                ShopType.setText(shopType);
                numBer = dataSnapshot.child(uid).child("mobilenumber").getValue(String.class);
                Number.setText(numBer);
                //DOB_day = dataSnapshot.child(uid).child("dob_Day").getValue(Integer.class);
                //Day.setText(DOB_day);
                /*DOB_month = dataSnapshot.child(uid).child("dob_Month").getValue(String.class);
                Month.setText(DOB_month);
                DOB_year = dataSnapshot.child(uid).child("dob_Year").getValue(String.class);
                Year.setText(DOB_year);*/


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Something went wrong...Please check Connections", Toast.LENGTH_SHORT).show();

            }
        });
        /*databaseReference.addValueEventListener(new ValueEventListener() {
         *//*
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*
                user_name = dataSnapshot.child(uid).child("name").getValue(String.class);
                //Toast.makeText(profile.this, "Name:"+user_name, Toast.LENGTH_SHORT).show();
                Name.setText("Adarsh");

                 */
                 /*
                currentUser = dataSnapshot.getValue(UserID.class);
                Name.setText(currentUser.getName());
                  */
           // }

            /*@Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(profile.this, "Something went wrong...Please check Connections", Toast.LENGTH_SHORT).show();
            }


        });*/
        return v;
    }
/*
    @Override
    public void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(profile.this, "Something went wrong...Please check Connections", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
