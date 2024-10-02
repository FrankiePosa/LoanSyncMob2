package com.example.loansyncmob2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Find the TextView where we want to display the user's first name
        TextView userTextView = view.findViewById(R.id.user);

        // Get the first name from the Intent passed from Login activity
        String firstName = getActivity().getIntent().getStringExtra("firstName");

        // Set the first name to the TextView
        if (firstName != null && !firstName.isEmpty()) {
            userTextView.setText(firstName);  // Display the first name
        } else {
            userTextView.setText("User");  // Default text if no first name is available
        }


        ImageView imageView = view.findViewById(R.id.backgroundImageView);

        // Apply blur effect using Glide
        Glide.with(this)
                .load(R.drawable.bggrad)  // Replace with your actual image resource
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25)))
                .into(imageView);

        CardView cardView = view.findViewById(R.id.cardviewleft);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to another fragment, but remain on 'Home' in bottom navigation
                Fragment newFragment = new LoanSelectFragment(); // Replace with the fragment you want to navigate to

                // Perform the fragment transaction
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, newFragment); // Replace 'frame_layout' with your FrameLayout's ID
                transaction.addToBackStack(null); // Add this transaction to the back stack
                transaction.commit();
            }


        });

        return view;


    }






}