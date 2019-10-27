package com.nemesiss.dev.ianime.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.nemesiss.dev.ianime.Model.Model.Response.Data.WorksImage;
import com.nemesiss.dev.ianime.R;
import java.util.List;

public class WorksImageAdapter extends RecyclerView.Adapter<WorksImageAdapter.ViewHolder> {
    private Context context;
    private List<WorksImage> worksImages;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;

        public ViewHolder(View view)
        {
            super(view);
            cardView=(CardView)view;
            imageView=view.findViewById(R.id.Image);

        }
    }
    public WorksImageAdapter(List<WorksImage>worksImage)
    {
        this.worksImages=worksImage;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType)
    {
        if(context==null)
        {
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.works_image_cardview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder ,int position)
    {
        WorksImage worksImage= worksImages.get(position);
        Glide.with(context).load(worksImage.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount()
    {
        return worksImages.size();
    }
}