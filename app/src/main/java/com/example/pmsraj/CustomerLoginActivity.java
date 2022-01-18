package com.example.pmsraj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import at.favre.lib.crypto.bcrypt.BCrypt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerLoginActivity extends AppCompatActivity {
    private static final String TAG ="SearchFragment";
    private static final String BASE_URL = "http://192.168.8.155:8080";
 private  EditText txtusername, txtpassword;
 private TextView tcLink;
 private Button btnloginn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        txtusername = findViewById(R.id.txtcuslogusername);
        txtpassword= findViewById(R.id.txtcuslogPassword);
        btnloginn = findViewById(R.id.btnLogin);
tcLink = findViewById(R.id.TvLink);

tcLink.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(CustomerLoginActivity.this, CustomerRegistration.class);
        CustomerLoginActivity.this.startActivity(intent);

    }
});

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
ClientJasobholder clientJasobholder = retrofit.create(ClientJasobholder.class);

        btnloginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = txtusername.getText().toString();
                String password = txtpassword.getText().toString();


                Call<CustomerLogin> call =clientJasobholder.checkClient(mail);
                call.enqueue(new Callback<CustomerLogin>() {
                    @Override
                    public void onResponse(Call<CustomerLogin> call, Response<CustomerLogin> response) {

                        if (!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "invalid username password", Toast.LENGTH_SHORT).show();
                            Log.e(TAG,"Invalid data :"+response.code());
                            return;
                        }

                        CustomerLogin customerLogin = response.body();
                        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(),customerLogin.getPassword());
                        if (result.verified == false){
                            Toast.makeText(getApplicationContext(), "invalid username password", Toast.LENGTH_SHORT).show();
                            Log.e(TAG,"Invalid password :"+response.code());
                            return;
                        }

                        else {
                            Toast.makeText(getApplicationContext(), "Valid Customer", Toast.LENGTH_SHORT).show();



// set Fragmentclass Arguments
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("mail", mail);
                            Toast.makeText(CustomerLoginActivity.this, mail, Toast.LENGTH_SHORT).show();
                            startActivity(intent);






                        }
                    }

                    @Override
                    public void onFailure(Call<CustomerLogin> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error :" +t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG,t.getMessage());
                    }
                });
            }
        });

    }
}