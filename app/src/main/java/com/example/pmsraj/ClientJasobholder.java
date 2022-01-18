package com.example.pmsraj;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ClientJasobholder {

    @POST("savecst")
    Call<ResponseBody> saveCustomer (@Body Client client);

    @GET("getCusbyMail/{mail}")
    Call<CustomerLogin> checkClient(@Path("mail") String mail);


    @POST("addnewprescriptions")
    Call<ResponseBody> savePrescriptions (@Body Prescriptions prescriptions);

    @GET("getCusbyEmail/{mail}")
    Call<Client> getClient(@Path("mail") String mail);


    @POST("updateCustomers/{id}")
    Call<ResponseBody> updateCustomer (@Path("id") int customer_id, @Body Client client);


    @POST("saveRequest")
    Call<ResponseBody> saveRequest (@Body Request request);

    }



