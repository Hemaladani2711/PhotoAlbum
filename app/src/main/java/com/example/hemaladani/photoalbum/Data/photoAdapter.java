package com.example.hemaladani.photoalbum.Data;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemaladani.photoalbum.R;
import com.example.hemaladani.photoalbum.View.ImageDetailView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hemaladani on 10/5/17.
 */

public class photoAdapter extends RecyclerView.Adapter<photoAdapter.photoViewHolder>  {


    Context mContext;
    View.OnClickListener onClickListener;
    List<Photos> items;

    public photoAdapter(List<Photos> list){
        items=list;
    }
    public class photoViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView nameTextView,titleTextView;

        public photoViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
            nameTextView=(TextView)itemView.findViewById(R.id.nameTextView);
            titleTextView=(TextView)itemView.findViewById(R.id.titleTextView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    sendData(getAdapterPosition());

                }
            });
        }

        public void bindGalleryItems(Photos item){
            nameTextView.setText(item.getmImageName());
            titleTextView.setText(item.getmTitle());
        }

        public void sendData(int pos){
            Photos photos=items.get(pos);
            Bundle b=new Bundle();
            b.putSerializable("photos",photos);

            Intent i=new Intent(mContext,ImageDetailView.class);
            i.putExtra("p",b);
            mContext.startActivity(i);
        }
        public void bindDrawable(Drawable drawable){
            imageView.setImageDrawable(drawable);
        }

    }

    @Override
    public photoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View view= LayoutInflater.from(mContext).inflate(R.layout.list_potrait_item,parent,false);
        return new photoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(photoViewHolder holder, int position) {
        Photos photos=items.get(position);
        holder.bindGalleryItems(photos);
        Drawable placeholder=mContext.getResources().getDrawable(R.mipmap.ic_launcher_round,null);
        holder.bindDrawable(placeholder);
        Picasso.with(mContext).load(photos.getmUrl()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }




}
