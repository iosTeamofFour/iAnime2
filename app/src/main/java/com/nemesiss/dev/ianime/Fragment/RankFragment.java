package com.nemesiss.dev.ianime.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ExpandableListView;
import android.widget.Toast;
import com.nemesiss.dev.ianime.Adapter.MyExtendableListViewAdapter;
import com.nemesiss.dev.ianime.Adapter.WorksCardViewWithIconAdapter;
import com.nemesiss.dev.ianime.Model.Model.Response.Data.WorksInfoWithIcon;
import com.nemesiss.dev.ianime.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RankFragment extends Fragment {


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private ExpandableListView expandableListView;
    public String[] groupString = {"最受欢迎的线稿", "最受欢迎的上色"};
    public WorksInfoWithIcon[][] childString = {
            {new WorksInfoWithIcon(R.drawable.image0, "first", "zoom", sdf.format(new Date())),
                    new WorksInfoWithIcon(R.drawable.image1, "second", "zoom", sdf.format(new Date()))},
            {new WorksInfoWithIcon(R.drawable.image2, "third", "zoom", sdf.format(new Date())),
                    new WorksInfoWithIcon(R.drawable.image3, "second", "zoom", sdf.format(new Date()))}
    };
    public RankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.expandable_listview, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findView();
    }

    private void findView() {
        expandableListView = getView().findViewById(R.id.expend_list);

        expandableListView.setAdapter(new MyExtendableListViewAdapter());
        //默认展开第一个数组
        expandableListView.expandGroup(0);
        //设置分组的监听
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                Toast.makeText(getActivity(), groupString[groupPosition], Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //设置子项布局监听
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                return true;

            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < 2; i++) {
                    if (i != groupPosition) {
                        expandableListView.collapseGroup(i);
                    }
                }
            }
        });
    }
}
