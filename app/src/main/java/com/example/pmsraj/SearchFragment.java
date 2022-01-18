
package com.example.pmsraj;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchFragment extends Fragment {
    private static final String TAG ="SearchFragment";
    private static final String BASE_URL = "http://192.168.8.155:8080";
    EditText editTextSearch;


   // private ArrayList<Drug> carModels=new ArrayList<>();
private RecyclerView recyclerView;
    private DrugAdapter drugAdapter;


    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_search, container, false);

       recyclerView = view.findViewById(R.id.rv_drugs);


       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setHasOptionsMenu(true);
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build();

       DrugJsonHolder drugJsonHolder = retrofit.create(DrugJsonHolder.class);
        Call<List<Drug>> call = drugJsonHolder.getDrugs();

        call.enqueue(new Callback<List<Drug>>() {
            @Override
            public void onResponse(Call<List<Drug>> call, Response<List<Drug>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Error"+response.code(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG,"API Response error :"+ response.code());
                    return;
                }
                else {
                    List<Drug> drugList = response.body();
                   drugAdapter = new DrugAdapter(drugList,getContext());

                    recyclerView.setAdapter(drugAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Drug>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



       return view;

    }



//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.search_menu, menu);
//        MenuItem search = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
//        search(searchView);
//
//
//
//
//    }


//
//    private void search(SearchView searchView) {
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (drugAdapter!=null)
//                    drugAdapter.getFilter().filter(newText);
//                return true;
//            }
//        });
//    }


}