package com.example.pmsraj;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PrescriptionsFragment extends Fragment {

   private  EditText name,email,contact,des;
   private Button btn;

    private static final String TAG ="PrescriptionFragment ";
    private static final String BASE_URL = "http://192.168.8.155:8080";
    private String mail;

    public PrescriptionsFragment(String mail) {
        this.mail=mail;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prescriptions, container, false);


        name = view.findViewById(R.id.txtrqcusname);
        email = view.findViewById(R.id.txtrqcusemail);
        contact = view.findViewById(R.id.txtrqcuscontact);
        des = view.findViewById(R.id.txtusrequest);
        btn = view.findViewById(R.id.btnreqsend);



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
                name.setText(client.getName());
                contact.setText(client.getPhone());
                email.setText(client.getMail());

            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {

            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cus_email = email.getText().toString();

                Integer cus_contact = Integer.parseInt(contact.getText().toString());

                String req = des.getText().toString();

              Integer req_status =1;


              String response ="";


                SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ssSS");
                Date myDate = new Date();
                String request_date= timeStampFormat.format(myDate);
                String phatmacist_id = "";

              String  response_date ="";

                Retrofit retrofit = new Retrofit.Builder() .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ClientJasobholder clientJasobholder = retrofit.create(ClientJasobholder.class);

                Request request = new Request(cus_email,  cus_contact, req, req_status, phatmacist_id, request_date,  response_date,response);

                Call<ResponseBody> call = clientJasobholder.saveRequest(request);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getContext(), "Request Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getContext(), "Request Unsucseefully", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });



        return  view;




    }
}