package com.nemesiss.dev.ianime.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import com.nemesiss.dev.ianime.Application.iAnimeApplication;
import com.nemesiss.dev.ianime.Fragment.SearchFragment;
import com.nemesiss.dev.ianime.Model.Model.Response.WorksInfo.WorksInfoWithIcon;
import com.nemesiss.dev.ianime.R;
import java.util.List;


public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>
{
    private List<WorksInfoWithIcon> _searchList = null;
    private SearchView searchView = null;
    private SearchFragment searchFragment;
    public SearchResultAdapter(List<WorksInfoWithIcon> searchList, final SearchView sv, final SearchFragment searchFragment){
        searchView = sv;
        this.searchFragment=searchFragment;
        RefreshSearchList(searchList);
        notifyDataSetChanged();
    }

    public void RefreshSearchList(List<WorksInfoWithIcon> NewSearchList)
    {
        _searchList = NewSearchList;
    }
    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        SearchResultViewHolder vh = new SearchResultViewHolder(view);
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int pos = vh.getAdapterPosition();
                WorksInfoWithIcon currStation = _searchList.get(pos);
                Context ctx = iAnimeApplication.getContext();
                Intent it = new Intent();
//                it.putExtra(ctx.getResources().getString(R.string.app_name), currStation);
//                searchActivity.setResult(Activity.RESULT_OK,it);
//                searchActivity.finish();
            }
        });
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position)
    {
        WorksInfoWithIcon CurrentPosition = _searchList.get(position);
        holder.NameTextView.setText(CurrentPosition.getName());
        holder.AuthorTextView.setText(CurrentPosition.getAuthorName());
    }
    @Override
    public int getItemCount()
    {
        return _searchList.size();
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder{

        private TextView NameTextView;
        private TextView AuthorTextView;
        private ImageView imageView;
        public SearchResultViewHolder(View itemView)
        {
            super(itemView);
            NameTextView = itemView.findViewById(R.id.nameText);
            AuthorTextView = itemView.findViewById(R.id.authorNameText);
            imageView = itemView.findViewById(R.id.worksImage);
            itemView.setOnClickListener(view ->
            {
                searchView.setQuery(NameTextView.getText().toString(), false);
            });
        }
    }
}
