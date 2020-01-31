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
public class addItems extends Fragment {

    DatabaseHelper myDb;
    EditText name, price, descrip, profit, source, available;
    Button addbtn;

    public addItems() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_add_items, container, false);

        myDb = new DatabaseHelper(getActivity());

        name = v.findViewById(R.id.itemName);
        price = v.findViewById(R.id.salePrice);
        descrip = v.findViewById(R.id.item_description);
        profit = v.findViewById(R.id.profit);
        source = v.findViewById(R.id.item_source);
        available = v.findViewById(R.id.availability);
        addbtn = v.findViewById(R.id.addbtn);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isInserted = myDb.insertData(name.getText().toString(), Double.parseDouble(price.getText().toString()), Double.parseDouble(profit.getText().toString()), descrip.getText().toString(), source.getText().toString(), Integer.parseInt(available.getText().toString()));
                if(isInserted == true){
                    Toast.makeText(getActivity(),"Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    price.setText("");
                    descrip.setText("");
                    profit.setText("");
                    source.setText("");
                    available.setText("");
                }
                else
                    Toast.makeText(getActivity(),"Data not Inserted.....", Toast.LENGTH_SHORT).show();

            }
        });



        return v;
    }

}
