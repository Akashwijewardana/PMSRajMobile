package com.example.pmsraj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import at.favre.lib.crypto.bcrypt.BCrypt;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerRegistration extends
        AppCompatActivity {
    private EditText txtname,txtemail,txtcontact,txtusername,txtpassword;
    private Button btnRegister;
    private static final String TAG ="CustomerRegisterActivity";
    private static final String BASE_URL = "http://192.168.8.155:8080";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);




        txtname = findViewById(R.id.txtupcusname);
        txtemail = findViewById(R.id.txtupcusemail);
        txtcontact = findViewById(R.id.txtupcuscontact);
        txtusername= findViewById(R.id.txtcususername);
        txtpassword = findViewById(R.id.txtupcuspassword);
        btnRegister= findViewById(R.id.btnupcusregister);

        Retrofit retrofit = new Retrofit.Builder() .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClientJasobholder clientJasobholder = retrofit.create(ClientJasobholder.class);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name= txtname.getText().toString();
                String email= txtemail.getText().toString();
                String contact = txtcontact.getText().toString();
                String username= txtemail.getText().toString();
                String password = txtpassword.getText().toString();

                if (name.equals("")||email.equals("")||contact.equals("")||username.equals("")||password.equals("")){
                    Toast.makeText(CustomerRegistration.this, "Fill Required fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                String cryptedpassword = BCrypt.withDefaults().hashToString(12,password.toCharArray());

                CustomerLogin customerLogin= new CustomerLogin(username,cryptedpassword);

                Client client = new Client(name,contact,email,customerLogin);

                Toast.makeText(CustomerRegistration.this, ""+client.getCustomerLogin().getPassword(), Toast.LENGTH_SHORT).show();

                Call<ResponseBody> call = clientJasobholder.saveCustomer(client);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(CustomerRegistration.this, "Customer Registration Fail:"+response.code(), Toast.LENGTH_SHORT).show();
//                            Log.e(TAG,"error:"+ response.code());
                            return;
                        }
                        Toast.makeText(CustomerRegistration.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CustomerRegistration .this, CustomerLoginActivity.class);
                        CustomerRegistration .this.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(CustomerRegistration.this, "Registration fail", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}