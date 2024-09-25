package com.example.loansyncmob2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextFirstname, textInputEditTextLastname, textInputEditTextEmail, textInputEditTextPassword, textInputEditTextContact;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

       textInputEditTextFirstname = findViewById(R.id.firstname);
       textInputEditTextLastname = findViewById(R.id.lastname);
       textInputEditTextEmail = findViewById(R.id.email);
       textInputEditTextPassword = findViewById(R.id.password);
       textInputEditTextContact = findViewById(R.id.contact);
       buttonSignUp = findViewById(R.id.buttonSignUp);
       textViewLogin = findViewById(R.id.loginText);
       progressBar = findViewById(R.id.progress);

       textViewLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), Login.class);
               startActivity(intent);
               finish();
           }
       });

       buttonSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String firstname, lastname, email, password, contact;
               firstname = String.valueOf(textInputEditTextFirstname.getText());
               lastname = String.valueOf(textInputEditTextLastname.getText());
               email = String.valueOf(textInputEditTextEmail.getText());
               password = String.valueOf(textInputEditTextPassword.getText());
               contact = String.valueOf(textInputEditTextContact.getText());

               //check if there is a value inside the edittext

               if(!firstname.equals("") && !lastname.equals("") && !email.equals("") && !password.equals("") && !contact.equals("")) {

                   //Start ProgressBar first (Set visibility VISIBLE)
                   progressBar.setVisibility(View.VISIBLE);


                   Handler handler = new Handler();
                   handler.post(new Runnable() {
                       @Override
                       public void run() {
                           //Starting Write and Read data with URL
                           //Creating array for parameters
                           String[] field = new String[5];
                           field[0] = "firstname";
                           field[1] = "lastname";
                           field[2] = "email";
                           field[3] = "password";
                           field[4] = "contact";
                           //Creating array for data -- get data from Edittext in SignUp
                           String[] data = new String[5];
                           data[0] = firstname;
                           data[1] = lastname;
                           data[2] = email;
                           data[3] = password;
                           data[4] = contact;
                           PutData putData = new PutData("http://192.168.254.111/LoginRegister/signup.php", "POST", field, data);
                           if (putData.startPut()) {
                               if (putData.onComplete()) {
                                   //End ProgressBar (Set visibility to GONE)
                                   progressBar.setVisibility(View.GONE);

                                   String result = putData.getResult();
                                   if(result.equals("Sign Up Success")){
                                       Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent(getApplicationContext(), Login.class);
                                       startActivity(intent);
                                       finish();
                                   }
                                   else {
                                       Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                   }

                               }
                           }
                           //End Write and Read data with URL
                       }
                   });
               }
               else {
                   Toast.makeText(getApplicationContext(),"All fields are required", Toast.LENGTH_SHORT).show();

               }

           }
       });






    }
}