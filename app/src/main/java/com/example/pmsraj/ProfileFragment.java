package com.example.pmsraj;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileFragment extends Fragment {

Button btn;
private EditText txtmail,txtcontact,txtname,txtpassword;
    private static final String TAG ="ProfileFragment ";
    private static final String BASE_URL = "http://192.168.8.155:8080";
    private String mail;

    public ProfileFragment(String mail) {
        this.mail=mail;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_profile, container, false);





        btn = view.findViewById(R.id.btnupcusregister);
txtmail=view.findViewById(R.id.txtupcusemail);
txtcontact=view.findViewById(R.id.txtupcuscontact);
txtname = view.findViewById(R.id.txtupcusname);
txtpassword=view.findViewById(R.id.txtupcuspassword);

String mail = this.mail;

        Retrofit retrofit = new Retrofit.Builder() .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClientJasobholder clientJasobholder = retrofit.create(ClientJasobholder.class);

        Call<Client> call = clientJasobholder.getClient(mail);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Invalid Mail:" + response.code(), Toast.LENGTH_SHORT).show();
//                            Log.e(TAG,"error:"+ response.code());

                }

                Client client = response.body();
                txtname.setText(client.getName());
                txtcontact.setText(client.getPhone());
                txtmail.setText(client.getMail());
                txtpassword.setText(client.getCustomer_id().toString());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {

            }

        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name= txtname.getText().toString();
                String email= txtmail.getText().toString();
                String contact = txtcontact.getText().toString();
                String password = txtpassword.getText().toString();
                Integer id = Integer.parseInt(password);

                if (name.equals("")||email.equals("")||contact.equals("")||password.equals("")){
                    Toast.makeText(getContext(), "Fill Required fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Client client = new Client();
                client.setMail(email);
                client.setPhone(contact);
                client.setName(name);


                Retrofit retrofit = new Retrofit.Builder() .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ClientJasobholder clientJasobholder = retrofit.create(ClientJasobholder.class);


                Call<ResponseBody> call = clientJasobholder.updateCustomer(id,client);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getContext(), "Update Sucssesfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getContext(), "Update Faliur", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


       return  view;
    }



    }