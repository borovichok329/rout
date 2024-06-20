package com.example.ag;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ag.databinding.ActivityLogBinding;
import com.example.ag.databinding.ActivityMainBinding;
import com.example.ag.helpers.LoginResponse;
import com.example.ag.helpers.ServerHelper;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogActivity extends AppCompatActivity {
     private ActivityLogBinding binding;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.preferences = getSharedPreferences ("app2",MODE_PRIVATE);
        binding.login.setOnClickListener(this::login);

    }

    public void  login (View v) {
        String username = binding.logET.getText().toString();
        String pwd = binding.pasET.getText().toString();
        if (username.isEmpty() || pwd.isEmpty()) {
            return;
        }

        ServerHelper.login(username, pwd, new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        String token = loginResponse.getAccess_token();
                        if (token != null) {
                            SharedPreferences.Editor edit = preferences.edit();
                            edit.putString("token", token);
                            edit.apply();
                            Intent intent = new Intent(LogActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {

                            Toast.makeText(LogActivity.this, "Ошибка авторизации1", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(LogActivity.this, "Ошибка авторизации2", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(LogActivity.this, "Ошибка авторизации3", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                t.printStackTrace();
                Toast.makeText(LogActivity.this, "Ошибка сети", Toast.LENGTH_SHORT).show();
            }
        });
            }



    }
