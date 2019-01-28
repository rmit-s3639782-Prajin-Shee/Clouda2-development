package com.example.s3639782.clouda2.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s3639782.clouda2.R;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ListItem listItem = listItems.get(i);
        viewHolder.RecHead.setText(listItem.getHeading());
        viewHolder.RecDesc.setText(listItem.getDesc());
        viewHolder.viewInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "hello", Toast.LENGTH_LONG).show();
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            RecHead = (TextView)itemView.findViewById(R.id.RecHeading);
            RecDesc = (TextView)itemView.findViewById(R.id.RecDesc);
            viewInc = (Button)itemView.findViewById(R.id.viewInc);
        }
    }
}
