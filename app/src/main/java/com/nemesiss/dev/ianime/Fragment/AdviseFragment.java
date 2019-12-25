package com.nemesiss.dev.ianime.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nemesiss.dev.ianime.Adapter.WorksCardViewWithIconAdapter;
import com.nemesiss.dev.ianime.Model.Model.Response.WorksInfo.WorksInfoWithIcon;
import com.nemesiss.dev.ianime.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AdviseFragment extends Fragment {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private WorksInfoWithIcon[] worksInfoWithIcons = {
            new WorksInfoWithIcon(R.drawable.image0, "first", "zoom", sdf.format(new Date())),
            new WorksInfoWithIcon(R.drawable.image1, "second", "zoom", sdf.format(new Date())),
            new WorksInfoWithIcon(R.drawable.image2, "third", "zoom", sdf.format(new Date())),
            new WorksInfoWithIcon(R.drawable.image3, "second", "zoom", sdf.format(new Date())),
    };
    private List<WorksInfoWithIcon> worksInfoWithIconList = new ArrayList<>();
    private WorksCardViewWithIconAdapter worksCardViewWithIconAdapter;

    public AdviseFragment() {
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

        View view = inflater.inflate(R.layout.fragment_advise, container, false);
        initWorks();
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
        RecyclerView recyclerView = getView().findViewById(R.id.fragment_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        worksCardViewWithIconAdapter = new WorksCardViewWithIconAdapter(worksInfoWithIconList);
        recyclerView.setAdapter(worksCardViewWithIconAdapter);
    }

    private void initWorks() {
        worksInfoWithIconList.clear();
        for (int i = 0; i <10 ; i++) {
            worksInfoWithIconList.add(worksInfoWithIcons[i%4]);
        }
    }
}
