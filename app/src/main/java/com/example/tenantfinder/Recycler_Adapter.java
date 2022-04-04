package com.example.tenantfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.Recycler_viewholder> {

    private Context mycont;
    private List<Property_modal> property;

    public Recycler_Adapter(Context mycont, List<Property_modal> property) {
        this.mycont = mycont;
        this.property = property;
    }

    @NonNull
    @Override
    public Recycler_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mycont);
        View v = inflater.inflate(R.layout.recycler_property,null);
        return  new Recycler_viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_viewholder holder, int position) {
        Property_modal p = property.get(position);

        holder.tvdeschead.setText(p.getDeschead());
        holder.tvdesc.setText(p.getDescription());
        holder.tvaddresshead.setText(p.getAddresshead());
        holder.tvaddress.setText(p.getAddress());
        holder.tvcontacthead.setText(p.getContacthead());
        holder.tvcontactemailhead.setText(p.getEmailhead());
        holder.tvemail.setText(p.getContact_email());
        holder.tvcontactphonehead.setText(p.getPhonehead());
        holder.tvphone.setText(String.valueOf(p.getPhone_no()));
    }

    @Override
    public int getItemCount() {

        return property.size();
    }

    class Recycler_viewholder extends RecyclerView.ViewHolder{

        TextView tvdeschead, tvaddresshead, tvcontacthead, tvcontactemailhead, tvcontactphonehead,tvdesc, tvaddress, tvemail, tvphone;


        public Recycler_viewholder(@NonNull View itemView) {
            super(itemView);

            tvdeschead = itemView.findViewById(R.id.descview);
            tvdesc = itemView.findViewById(R.id.desc);
            tvaddresshead = itemView.findViewById(R.id.addressview);
            tvaddress = itemView.findViewById(R.id.address);
            tvcontacthead = itemView.findViewById(R.id.contact_view);
            tvcontactemailhead = itemView.findViewById(R.id.contact_email_view);
            tvemail = itemView.findViewById(R.id.contact_email);
            tvcontactphonehead = itemView.findViewById(R.id.contact_phone_view);
            tvphone = itemView.findViewById(R.id.contact_phone);

        }
    }
}
