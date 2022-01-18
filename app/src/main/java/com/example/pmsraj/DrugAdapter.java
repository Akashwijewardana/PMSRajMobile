package com.example.pmsraj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.DrugViewHolder> {

    List<Drug> allDrugs;
    Context context;

//    private ArrayList<Drug> articles=new ArrayList<>();
//    private ArrayList<Drug> mArrayList=new ArrayList<>();

    public DrugAdapter(List<Drug> allDrugs, Context context) {
        this.allDrugs = allDrugs;
        this.context = context;
    }

//    public DrugAdapter(ArrayList<Drug> articles, Context context) {
//        this.context=context;
//        this.articles=articles;
//        this.mArrayList=articles;
//    }





    @NonNull
    @Override
    public DrugViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.drug_item,parent,false);
        return  new DrugViewHolder(view);
    }

//





    @Override
    public void onBindViewHolder(@NonNull DrugViewHolder holder, int position) {

        Drug drug = allDrugs.get(position);
        holder.name.setText(drug.getDrug_name());
        holder.brand.setText(drug.getBrand_name());
        holder.generic.setText(drug.getGeneric_name());
        holder.price.setText(drug.getDrug_price().toString());
        holder.avalability.setText(drug.getStock_quantity().toString());


    }

    @Override
    public int getItemCount() {
        return allDrugs.size();
    }

    public class DrugViewHolder extends  RecyclerView.ViewHolder{

        TextView name,brand,generic,price,avalability;
        public DrugViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.drug_view_name);
            brand = itemView.findViewById(R.id.drug_view_brand);
            generic = itemView.findViewById(R.id.drug_view_generic);
            price = itemView.findViewById(R.id.drug_view_price);
            avalability = itemView.findViewById(R.id.drug_view_available_qty);

        }
    }
//
//    public void filterList(ArrayList<Drug> filterllist) {
//
//        allDrugs = filterllist;
//
//        notifyDataSetChanged();
//    }
}
