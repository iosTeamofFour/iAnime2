package com.nemesiss.dev.ianime.Acitivity;

import android.support.v4.widget.NestedScrollView;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import com.nemesiss.dev.ianime.Adapter.WorksImageAdapter;
import com.nemesiss.dev.ianime.Model.Model.Response.WorksInfo.WorksImage;
import com.nemesiss.dev.ianime.R;

import java.util.ArrayList;
import java.util.List;

public class MyAndOthersIndexActivity extends iAnimeActivity {


    private WorksImage[] worksImage = {
            new WorksImage(R.drawable.image0),
            new WorksImage(R.drawable.image1),
            new WorksImage(R.drawable.image2),
            new WorksImage(R.drawable.image3)
    };
    private List<WorksImage> worksImageList = new ArrayList<>();
    private WorksImageAdapter worksImageAdapter1;
    private WorksImageAdapter worksImageAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_index);

        initWorks();
        findView();

    }

    private void findView() {
        Button button = findViewById(R.id.addAttention);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getText()=="+ 关注") {
                    button.setText("√ 已关注");
                    button.setTextColor(getResources().getColor(R.color.GrayText));
                }
                else
                {
                    button.setText("+ 关注");
                    button.setTextColor(getResources().getColor(R.color.colorAccent));
                }
            }
        });

        NestedScrollView nestedScrollView=findViewById(R.id.nestedScrollView);
        nestedScrollView.post(() -> {
            nestedScrollView.scrollTo(0,0);
        });

        RecyclerView recyclerView1 = findViewById(R.id.top_recycler_view);
        //GridLayoutManager layoutManager=new GridLayoutManager(this, 3);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(layoutManager);
        worksImageAdapter1 = new WorksImageAdapter(worksImageList);
        recyclerView1.setAdapter(worksImageAdapter1);

        RecyclerView recyclerView2=findViewById(R.id.timeline_recycler_view);
        StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        worksImageAdapter2 = new WorksImageAdapter(worksImageList);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(worksImageAdapter2);
    }

    private void initWorks() {
        worksImageList.clear();
        for (int i = 0; i < 4; i++) {
            worksImageList.add(worksImage[i % 4]);
        }
    }
}
