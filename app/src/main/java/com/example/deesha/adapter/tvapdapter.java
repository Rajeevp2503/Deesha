package com.example.deesha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.deesha.R;
import com.example.deesha.model.TvModel;

import java.util.ArrayList;

public class tvapdapter extends RecyclerView.Adapter<tvapdapter.tvviewholder>{
    public ArrayList<TvModel> arrayList;
    private Context context;

    public tvapdapter(ArrayList<TvModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public tvviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row,parent,false);

        return new tvviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull tvviewholder holder, int position) {
        TvModel tvModel = arrayList.get(position);
        holder.name.setText(tvModel.getName());
        holder.desc.setText(tvModel.getOverview());
        holder.date.setText(tvModel.getFirst_air_date());
        holder.ratingBar.setRating(Float.parseFloat(tvModel.getVote_average())/2);
        Glide.with(context)
                .load(arrayList.get(position).getPoster_path())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class tvviewholder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        ImageView img;
        RatingBar ratingBar;
        TextView name;
        TextView desc;
        TextView date;

        public tvviewholder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.tvshow);
             name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            desc = itemView.findViewById(R.id.desc);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            img = itemView.findViewById(R.id.photo);
        }
    }
}
