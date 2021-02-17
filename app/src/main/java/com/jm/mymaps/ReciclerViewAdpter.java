package com.jm.mymaps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReciclerViewAdpter extends RecyclerView.Adapter<ReciclerViewAdpter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mImages;
    private ArrayList<String> mText;

    public ReciclerViewAdpter(Context mContext, ArrayList<String> mImages, ArrayList<String> mText) {
        this.mContext = mContext;
        this.mImages = mImages;
        this.mText = mText;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_recyclerView.setText(mText.get(position));
        holder.edit_item_recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mText.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView tv_recyclerView;
        ImageButton edit_item_recycler;
        RelativeLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            tv_recyclerView = itemView.findViewById(R.id.tv_recyclerView);
            edit_item_recycler= itemView.findViewById(R.id.edit_item_recycler);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
