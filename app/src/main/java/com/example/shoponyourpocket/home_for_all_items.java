package com.example.shoponyourpocket;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class home_for_all_items extends Fragment {

    DatabaseHelper myDb;
    FirebaseUser user;
    String uid;

    public home_for_all_items() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_for_all_items, container, false);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        myDb = new DatabaseHelper(getActivity());

        Cursor res = myDb.getAllData();
        if(res.getCount() == 0){
            showMessege("Error", "No Data Found");
            return v;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append(" Id : "+ res.getString(0)+"\n");
            buffer.append(" Item Name : "+ res.getString(1)+"\n");
            buffer.append(" Sale Price : "+ res.getString(2)+"\n");
            buffer.append(" Profit per Item : "+ res.getString(3)+"\n");
            buffer.append(" Description : "+ res.getString(4)+"\n");
            buffer.append(" Item Source : "+ res.getString(5)+"\n");
            buffer.append(" Availability : "+ res.getString(6)+"\n");
            buffer.append(" Number of Items sold : "+ res.getString(7)+"\n\n");

        }
        showMessege("Data", buffer.toString());

        return v;
    }

    public void showMessege(String title, String messege){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(messege);
        builder.show();
    }

}


