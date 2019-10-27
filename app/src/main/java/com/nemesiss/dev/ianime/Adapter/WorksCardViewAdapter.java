package com.nemesiss.dev.ianime.Adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.nemesiss.dev.ianime.Model.Model.Response.Data.WorksInfo;
import com.nemesiss.dev.ianime.R;

import java.util.List;

public class WorksCardViewAdapter extends RecyclerView.Adapter<WorksCardViewAdapter.ViewHolder> {
    private Context context;
    private List<WorksInfo> worksInfos;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView nameText;
        TextView dateText;
        public ViewHolder(View view)
        {
            super(view);
            cardView=(CardView)view;
            imageView=view.findViewById(R.id.worksImage);
            nameText=view.findViewById(R.id.nameText);
            dateText=view.findViewById(R.id.dateText);
        }
    }
    public WorksCardViewAdapter(List<WorksInfo> worksInfos)
    {
        this.worksInfos=worksInfos;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int ViewType)
    {
        if(context==null)
        {
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.works_item_cardview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder ,int position)
    {
        WorksInfo worksInfo=worksInfos.get(position);
        holder.nameText.setText(worksInfo.getName());
        holder.dateText.setText(worksInfo.getDate().toString());
        Glide.with(context).load(worksInfo.getImageID()).into(holder.imageView);
    }

    @Override
    public int getItemCount()
    {
        return worksInfos.size();
    }
}
