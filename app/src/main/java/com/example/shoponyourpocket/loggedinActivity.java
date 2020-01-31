package com.example.shoponyourpocket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loggedinActivity extends AppCompatActivity {

    UserSessionManager session;

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;

    FirebaseUser user;
    String uid;

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer, new home_for_all_items());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home ");
      //  menuItem.setChecked(true);
        mDrawerlayout.closeDrawers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);

        session = new UserSessionManager(getApplicationContext());

        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(loggedinActivity.this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mainContainer, new home_for_all_items());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("All Items: ");

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.home:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainContainer, new home_for_all_items());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Home ");
                        menuItem.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;
                    case R.id.profile:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainContainer, new profile());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Your Profile ");
                        menuItem.setChecked(true);
                        //mDrawerlayout.closeDrawers();
                        break;
                    case R.id.totalProfit:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainContainer, new totalProfit());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Total Profit.... ");
                        menuItem.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;
                    case R.id.searchItem:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainContainer, new SearchItem());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Search here....  ");
                        menuItem.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;
                    case R.id.addItem:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainContainer, new addItems());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Add New Items....");
                        menuItem.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;
                    case R.id.editItem:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainContainer, new editItems());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Edit Some Item.... ");
                        menuItem.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;
                    case R.id.dltItem:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainContainer, new dltItems());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Delete Items.... ");
                        menuItem.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;
                    case R.id.totalSale:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainContainer, new totalSale());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Total Sale.... ");
                        menuItem.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;
                    case R.id.logout:
                        Intent intent5 = new Intent(loggedinActivity.this, loginSignup.class);
                        startActivity(intent5);
                        session.logoutUser();
                        finish();
                        menuItem.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;


                }


                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //first for show

        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }
/*
        int id = item.getItemId();

        if(id == R.id.logout){
            Intent intent5 = new Intent(loggedinActivity.this, loginSignup.class);
            startActivity(intent5);
            //finish();
            return false;
        }

*/
        return super.onOptionsItemSelected(item);
    }
}
