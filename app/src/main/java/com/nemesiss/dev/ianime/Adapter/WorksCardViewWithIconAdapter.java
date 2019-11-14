package com.nemesiss.dev.ianime.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.nemesiss.dev.ianime.Model.Model.Response.Data.WorksInfoWithIcon;
import com.nemesiss.dev.ianime.R;
import java.util.List;

public class WorksCardViewWithIconAdapter extends RecyclerView.Adapter<WorksCardViewWithIconAdapter.ViewHolder> {
    private Context context;
    private List<WorksInfoWithIcon> worksInfoWithIcons;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView nameText;
        TextView authorText;
        TextView dateText;
        public ViewHolder(View view)
        {
            super(view);
            cardView=(CardView)view;
            imageView=view.findViewById(R.id.worksImage);
            nameText=view.findViewById(R.id.nameText);
            authorText=view.findViewById(R.id.authorNameText);
            dateText=view.findViewById(R.id.dateText);
        }
    }
    public WorksCardViewWithIconAdapter(List<WorksInfoWithIcon> worksInfoWithIcons)
    {
        this.worksInfoWithIcons=worksInfoWithIcons;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType)
    {
        if(context==null)
        {
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder ,int position)
    {
        WorksInfoWithIcon worksInfoWithIcon=worksInfoWithIcons.get(position);
        holder.nameText.setText(worksInfoWithIcon.getName());
        holder.authorText.setText(worksInfoWithIcon.getAuthorName());
        holder.dateText.setText(worksInfoWithIcon.getDate());
        Glide.with(context).load(worksInfoWithIcon.getImageID()).into(holder.imageView);
    }

    @Override
    public int getItemCount()
    {
        return worksInfoWithIcons.size();
    }
}

