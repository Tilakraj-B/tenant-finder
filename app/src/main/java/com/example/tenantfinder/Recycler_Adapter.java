package com.example.tenantfinder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.Recycler_viewholder> {

    private final Context mycont;
    private final List<Property_modal> propertyarraylist;

    public Recycler_Adapter(Context mycont, List<Property_modal> propertyarraylist) {
        this.mycont = mycont;
        this.propertyarraylist = propertyarraylist;
    }



    @NonNull
    @Override
    public Recycler_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mycont);
        View v = inflater.inflate(R.layout.recycler_property,null);

        return new Recycler_viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_viewholder holder, int position) {
        Property_modal p = propertyarraylist.get(position);

        holder.nobed.setText(p.getNo_of_bedroom());
        holder.nohall.setText(p.getNo_of_hall());
        holder.nokit.setText(p.getNo_of_kitchen());
        holder.address.setText(p.getAddress());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("cardview", "cardviewclicked");
                Intent intent = new Intent(mycont, property_details.class);
                intent.putExtra("no_of_bedroom", p.getNo_of_bedroom());
                intent.putExtra("no_of_hall", p.getNo_of_hall());
                intent.putExtra("no_of_kitchen", p.getNo_of_kitchen());
                intent.putExtra("description", p.getDescription());
                intent.putExtra("address",p.getAddress());
                intent.putExtra("name",p.getName());
                intent.putExtra("email",p.getEmail());
                intent.putExtra("phone",p.getPhone());
                mycont.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {

        return propertyarraylist.size();
    }

    public static class Recycler_viewholder extends RecyclerView.ViewHolder{

        TextView address, nobed, nohall, nokit;
        CardView cardView;
        TextView description, name, email, phone;
        public Recycler_viewholder(@NonNull View itemView) {
            super(itemView);

            nobed = itemView.findViewById(R.id.noofbed);
            nohall = itemView.findViewById(R.id.noofhall);
            nokit = itemView.findViewById(R.id.noofkit);
            address = itemView.findViewById(R.id.address);
            cardView = itemView.findViewById(R.id.recyclercardview);

            Log.d("viewholder", "completed");
        }
    }
}
