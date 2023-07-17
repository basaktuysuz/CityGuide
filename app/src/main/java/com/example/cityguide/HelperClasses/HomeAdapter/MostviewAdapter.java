package com.example.cityguide.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguide.R;

import java.util.ArrayList;

public class MostviewAdapter extends RecyclerView.Adapter<MostviewAdapter.MostviewHolder> {
    ArrayList<MostviewHelperClass> mostviewLocations;

    public MostviewAdapter(ArrayList<MostviewHelperClass> mostviewLocations) {
        this.mostviewLocations = mostviewLocations;
    }


    @NonNull
    @Override
    public MostviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_desing, parent, false);
        MostviewHolder mostviewHolder = new MostviewHolder(view);
        return mostviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostviewHolder holder, int position) {
        MostviewHelperClass mostviewHelperClass = mostviewLocations.get(position);
        holder.image.setImageResource(mostviewHelperClass.getImage());
        holder.title.setText(mostviewHelperClass.getTitle());
        holder.desc.setText(mostviewHelperClass.getDecription());
    }

    @Override
    public int getItemCount() {
        return mostviewLocations.size();
    }

    public static class MostviewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, desc;


        public MostviewHolder(@NonNull View itemView) {
            super(itemView);

// hooks
            image = itemView.findViewById(R.id.mv_image);
            title = itemView.findViewById(R.id.mv_title);
            desc = itemView.findViewById(R.id.mv_desc);
        }// end constsructor


    }// end MostviewHolder class


}//end class MostviewAdapter