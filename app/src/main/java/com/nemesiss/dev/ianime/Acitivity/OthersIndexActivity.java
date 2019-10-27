package com.nemesiss.dev.ianime.Acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.nemesiss.dev.ianime.Adapter.WorksCardViewAdapter;
import com.nemesiss.dev.ianime.Adapter.WorksImageAdapter;
import com.nemesiss.dev.ianime.Model.Model.Response.Data.WorksImage;
import com.nemesiss.dev.ianime.Model.Model.Response.Data.WorksInfo;
import com.nemesiss.dev.ianime.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OthersIndexActivity extends AppCompatActivity {



    private WorksImage[] worksImage = {
            new WorksImage(R.drawable.image0),
            new WorksImage(R.drawable.image1),
            new WorksImage(R.drawable.image2),
            new WorksImage(R.drawable.image3)
    };
    private List<WorksImage> worksImageList = new ArrayList<>();
    private WorksImageAdapter worksImageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_index);

        initWorks();
        findView();
    }
    private void findView() {
        RecyclerView recyclerView=findViewById(R.id.others_recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        worksImageAdapter=new WorksImageAdapter(worksImageList);
        recyclerView.setAdapter(worksImageAdapter);
    }

    private void initWorks() {
        worksImageList.clear();
        for (int i = 0; i < 3; i++) {
            worksImageList.add(worksImage[i%4]);
        }
    }
}
