package com.kotlin.note.rxdaggerdemo.model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kotlin.note.rxdaggerdemo.R;

import java.util.ArrayList;
import java.util.List;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private FlowerClickListener listener;
    private List<FlowerResponse> flowerList;


    public FlowerAdapter(FlowerClickListener listener, LayoutInflater inflater) {
        this.listener = listener;
        this.inflater = inflater;
        flowerList = new ArrayList<>();
    }

    @NonNull
    @Override
    public FlowerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.item_flower, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerAdapter.ViewHolder viewHolder, int position) {
        FlowerResponse flower = flowerList.get(position);

        viewHolder.name.setText(flower.getName());
        viewHolder.price.setText(String.format("$%.2f", flower.getPrice()));

        Glide.with(viewHolder.itemView.getContext())
                .load(Constant.PHOTO_URL + flower.getPhoto())
                .into(viewHolder.photo);
    }

    @Override
    public int getItemCount() {
        return flowerList.size();
    }

    public void addFlowers(List<FlowerResponse> flowerResponses) {
        flowerList.addAll(flowerResponses);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView photo;
        TextView name, price;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.flowerPhoto);
            name = itemView.findViewById(R.id.flowerName);
            price = itemView.findViewById(R.id.flowerPrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(getLayoutPosition(), flowerList.get(getLayoutPosition()).getName());
        }
    }

    public interface FlowerClickListener {
        void onClick(int position, String name);
    }
}
