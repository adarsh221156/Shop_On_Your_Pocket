package com.example.shoponyourpocket;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class editItems extends Fragment {

    EditText id, name, price, profit, description, source, available, sold;
    Button editBtn;

    DatabaseHelper myDb;
    FirebaseUser user;

    public editItems() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_items, container, false);
        myDb = new DatabaseHelper(getActivity());
        id = v.findViewById(R.id.idEdit);
        name = v.findViewById(R.id.NameEdit);
        price = v.findViewById(R.id.PriceEdit);
        profit = v.findViewById(R.id.ProfitEdit);
        description = v.findViewById(R.id.DescriptionEdit);
        source = v.findViewById(R.id.SourceEdit);
        available = v.findViewById(R.id.AvailableEdit);
        sold = v.findViewById(R.id.SoldEdit);
        editBtn = v.findViewById(R.id.editbtn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = myDb.updateData(Integer.parseInt(id.getText().toString()), name.getText().toString(), Double.parseDouble(price.getText().toString()), Double.parseDouble(profit.getText().toString()), description.getText().toString(), source.getText().toString(), Integer.parseInt(available.getText().toString()), Integer.parseInt(sold.getText().toString()));
                if(isUpdated == true){
                    Toast.makeText(getActivity(),"Data Edited Successfully", Toast.LENGTH_SHORT).show();
                    id.setText("");
                    name.setText("");
                    price.setText("");
                    profit.setText("");
                    description.setText("");
                    source.setText("");
                    available.setText("");
                    sold.setText("");
                }
                else
                    Toast.makeText(getActivity(),"Some error Occurred.....Data not Edited", Toast.LENGTH_SHORT).show();



            }
        });

        return v;
    }

}
