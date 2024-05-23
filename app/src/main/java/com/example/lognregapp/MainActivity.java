package com.example.lognregapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnRegistration;
    EditText login;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnRegistration = findViewById(R.id.btnRegistration);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);

        btnLogin.setOnClickListener(v -> {
            if (!login.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://localhost:7046/api/Registration/")
                        // on below line we are calling add Converter
                        // factory as GSON converter factory.
                        .addConverterFactory(GsonConverterFactory.create())
                        // at last we are building our retrofit builder.
                        .build();
                // below line is to create an instance for our retrofit api call class and initializing it.
                RetrofitAPICall retrofitAPI = retrofit.create(RetrofitAPICall.class);
                // on below line creating and initializing call variable for get data method.
                Call<Void> call = retrofitAPI.Verification(new User(login.getText().toString(), password.getText().toString(), "123"));

                // on below line adding an enqueue to parse the data from api.
                call.enqueue(new Callback<Void>() {

                    // on below line calling on response method.
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        // inside on response method setting text from our api response.
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "You entered!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong data!", Toast.LENGTH_LONG).show();
                        }
                    }

                    // on below line calling on failure method.
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // displaying a toast message when as error is received.
                        Toast.makeText(MainActivity.this, "Fail to get the data.." + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                Intent intent = new Intent(MainActivity.this, MainApp.class);
                startActivity(intent);
            }
        });

        btnRegistration.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Registration.class);
            startActivity(intent);
        });

    }
}