package com.example.shoponyourpocket;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class dltItems extends Fragment {

    EditText id;
    Button dltbtn;

    DatabaseHelper myDb;

    public dltItems() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dlt_items, container, false);
        id = v.findViewById(R.id.dltId);
        dltbtn = v.findViewById(R.id.dltbtn);

        myDb = new DatabaseHelper(getActivity());

        dltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int deletedRow = myDb.deleteData(Integer.parseInt(id.getText().toString()));
                if(deletedRow > 0){
                    Toast.makeText(getActivity(),"Data deleted Successfully", Toast.LENGTH_SHORT).show();
                    id.setText("");
                }
                else
                    Toast.makeText(getActivity(),"Data not deleted.....", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }

}
