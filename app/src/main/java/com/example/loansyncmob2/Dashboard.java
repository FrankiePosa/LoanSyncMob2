package com.example.loansyncmob2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.loansyncmob2.R;


import android.os.Bundle;

import com.example.loansyncmob2.databinding.ActivityDashboardBinding;


public class Dashboard extends AppCompatActivity {

    ActivityDashboardBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView3.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.navhome) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.navloan) {
                replaceFragment(new LoanFragment());
            } else if (item.getItemId() == R.id.navpayment) {
                replaceFragment(new PaymentFragment());
            } else if (item.getItemId() == R.id.navanalytics) {
                replaceFragment(new AnalyticsFragment());
            } else if (item.getItemId() == R.id.navprofile) {
                replaceFragment(new ProfileFragment());
            }


            return true;
        });


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}