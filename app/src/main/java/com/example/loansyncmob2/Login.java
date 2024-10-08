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

public class Login extends AppCompatActivity {

    TextInputEditText  textInputEditTextEmail, textInputEditTextPassword;
    Button buttonLogin;
    TextView textViewSignUp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextEmail = findViewById(R.id.emailL);
        textInputEditTextPassword = findViewById(R.id.passwordL);
        buttonLogin = findViewById(R.id.btnLogin);
        textViewSignUp = findViewById(R.id.signUpText);
        progressBar = findViewById(R.id.progress);

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstname, lastname, email, password, contact;
                email = String.valueOf(textInputEditTextEmail.getText());
                password = String.valueOf(textInputEditTextPassword.getText());



                //check if there is a value inside the edittext

                if(!email.equals("") && !password.equals("")) {

                    //Start ProgressBar first (Set visibility VISIBLE)
                    progressBar.setVisibility(View.VISIBLE);


                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "email";
                            field[1] = "password";
                            //Creating array for data -- get data from Edittext in SignUp
                            String[] data = new String[2];
                            data[0] = email;
                            data[1] = password;

                            PutData putData = new PutData("http://192.168.254.111/LoginRegister/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    //End ProgressBar (Set visibility to GONE)
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();

                                    String[] responseParts = result.split(",");
                                    if(responseParts[0].equals("Login Success")){
                                        String firstName = responseParts[1];

                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                        intent.putExtra("firstName", firstName);
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