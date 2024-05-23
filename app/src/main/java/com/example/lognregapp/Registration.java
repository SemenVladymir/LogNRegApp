package com.example.lognregapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registration extends AppCompatActivity {

    Button btnRegistration;
    EditText login;
    EditText password;
    EditText email;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnRegistration = findViewById(R.id.button);
        login = findViewById(R.id.Login);
        password = findViewById(R.id.Password);
        email = findViewById(R.id.Email);

        btnRegistration.setOnClickListener(v -> {
            if (!login.getText().toString().isEmpty() && !password.getText().toString().isEmpty()
                    && !email.getText().toString().isEmpty()) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://localhost:7046/api/Registration/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RetrofitAPICall retrofitAPI = retrofit.create(RetrofitAPICall.class);
                Call<Void> call = retrofitAPI.Autorisation(
                        new User(
                                login.getText().toString(),
                                password.getText().toString(),
                                email.getText().toString()));

                call.enqueue(new Callback<Void>() {

                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(Registration.this, "Registration is successful!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Registration.this, "Wrong data!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Registration.this, "Fail to get the data.." + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                Intent intent = new Intent(Registration.this, MainApp.class);
                startActivity(intent);
            }
        });

    }
}