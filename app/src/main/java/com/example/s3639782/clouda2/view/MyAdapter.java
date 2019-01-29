package com.example.s3639782.clouda2.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s3639782.clouda2.R;
import com.example.s3639782.clouda2.controller.RegisterUser;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final ListItem listItem = listItems.get(i);
        viewHolder.RecHead.setText(listItem.getHeading());
        viewHolder.RecDesc.setText(listItem.getDesc());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), listItem.getHeading().toString()+" "+listItem.getDesc().toString(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(context.getApplicationContext(),IncidentActivity.class);
                i.putExtra("heading", listItem.getHeading().toString());
                i.putExtra("desc", listItem.getDesc().toString());
                i.putExtra("address", listItem.getAddress().toString());
                i.putExtra("date", listItem.getDate());
                i.putExtra("time", listItem.getTime());
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView RecHead;
        public TextView RecDesc;
        public Button viewInc;
        public CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            RecHead = (TextView)itemView.findViewById(R.id.RecHeading);
            RecDesc = (TextView)itemView.findViewById(R.id.RecDesc);
            viewInc = (Button)itemView.findViewById(R.id.viewInc);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

        }
    }
}
