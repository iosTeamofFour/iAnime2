package com.nemesiss.dev.ianime.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import com.jakewharton.rxbinding.widget.RxSearchView;
import com.nemesiss.dev.ianime.Adapter.SearchResultAdapter;
import com.nemesiss.dev.ianime.Model.Model.Response.Data.WorksInfoWithIcon;
import com.nemesiss.dev.ianime.R;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class SearchFragment extends Fragment {

    private LinearLayoutManager linearLayoutManager;
    private SearchResultAdapter resultAdapter;
    private List<WorksInfoWithIcon> SearchResult = new ArrayList<>();
    // private StationServices stationServices = null;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_search, container, false);
        //findView();
        return view;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       findView();

    }

    private void findView() {
        // stationServices = ChargerApplication.getStationServices();
            SearchView searchView=getView().findViewById(R.id.Search_SearchBar);
            RecyclerView recyclerView=getView().findViewById(R.id.Search_SearchResultRecycleView);
            SwipeRefreshLayout swipeRefreshLayout=getView().findViewById(R.id.SearchRefreshIcon);
            swipeRefreshLayout.setEnabled(false);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        resultAdapter = new SearchResultAdapter(SearchResult, searchView, SearchFragment.this);
        recyclerView.setAdapter(resultAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });

        RxSearchView.queryTextChangeEvents(searchView)
                .observeOn(AndroidSchedulers.mainThread())
                .map(searchViewQueryTextEvent -> {
                    swipeRefreshLayout.setEnabled(true);
                    swipeRefreshLayout.setRefreshing(true);
                    return searchViewQueryTextEvent;
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .map(
                        searchViewQueryTextEvent -> {
                            boolean IsSubmitted = searchViewQueryTextEvent.isSubmitted();
                            String QueryText = searchViewQueryTextEvent.queryText().toString();
                            //List<Stations> result = stationServices.ShowInputTips(QueryText);
                            return (SearchResult);
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tips -> {
                    SearchResult.clear();
                    SearchResult.addAll(tips);
                    resultAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    swipeRefreshLayout.setEnabled(false);
                });
    }


}
