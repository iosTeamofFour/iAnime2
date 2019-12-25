package com.nemesiss.dev.ianime.Acitivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.nemesiss.dev.ianime.Adapter.WorksCardViewAdapter;
import com.nemesiss.dev.ianime.Model.Model.Response.WorksInfo.WorksInfo;
import com.nemesiss.dev.ianime.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.nemesiss.dev.ianime.Application.iAnimeApplication.getContext;

public class WorksIndexActivity extends iAnimeActivity {

    private EditText editText;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Runnable ShouldHandleMenuClicked=null;
    private float CurrentSlideOffset=0.0f;

    private WorksInfo[] worksInfos = {
            new WorksInfo(R.drawable.image0, "first", sdf.format(new Date())),
            new WorksInfo(R.drawable.image1, "second", sdf.format(new Date())),
            new WorksInfo(R.drawable.image2, "third", sdf.format(new Date())),
            new WorksInfo(R.drawable.image3, "second", sdf.format(new Date())),
    };
    private List<WorksInfo> worksInfoList = new ArrayList<>();
    private WorksCardViewAdapter worksCardViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_works_index);

        initWorks();
        findView();
    }

    private void findView() {
        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.nav_view);

        editText = findViewById(R.id.search_editText);
//        editText.setOnClickListener(v->{
//            editText.setFocusable(true);
//        });
        editText.setOnFocusChangeListener((v, hasFocus) -> {

            if (hasFocus) {
                editText.setHint("");
            } else {
                editText.setHint("点击搜索作品");
                //收起软键盘
                InputMethodManager manager = ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
                if (manager != null)
                    manager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });

        FloatingActionButton createNewWork = findViewById(R.id.create_new_work);
        createNewWork.setOnClickListener(v -> {
            Intent intent = new Intent(WorksIndexActivity.this, DrawingActivity.class);
            startActivity(intent);
        });

        ImageView avatar = findViewById(R.id.avatar);
        avatar.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        View view = findViewById(R.id.DrawerLayout);
        view.setOnClickListener(v -> {
            editText.setHint("点击搜索作品");
            v.requestFocus();
        });

        ImageView imageView = findViewById(R.id.search_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WorksIndexActivity.this, "开始搜索", Toast.LENGTH_SHORT).show();
                editText.setFocusable(false);
                InputMethodManager manager = ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
                if (manager != null)
                    manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(CurrentSlideOffset>slideOffset&&slideOffset<0.015f&&ShouldHandleMenuClicked!=null){
                    ShouldHandleMenuClicked.run();
                    ShouldHandleMenuClicked=null;
                    runOnUiThread(()->{
                        navigationView.setCheckedItem(R.id.menu_none);
                    });
                }
                CurrentSlideOffset=slideOffset;
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.index:
                        ShouldHandleMenuClicked=()->{
                          startActivity(new Intent(WorksIndexActivity.this, MyAndOthersIndexActivity.class));
                        };
                        break;
                    case R.id.discover:
                        ShouldHandleMenuClicked=()->{
                            Intent intent=new Intent(WorksIndexActivity.this,DiscoverActivity.class);
                            startActivity(intent);
                        };
                        break;
                    case R.id.love:
                        ShouldHandleMenuClicked=()->{

                        };
                        break;
                    case R.id.attention:
                        ShouldHandleMenuClicked=()->{
                        };
                        break;
                    case R.id.info:
                        ShouldHandleMenuClicked=()->{
                            Intent intent=new Intent(WorksIndexActivity.this,PersonIndexActivity.class);
                            startActivity(intent);
                        };
                        break;
                    case R.id.setting:
                        ShouldHandleMenuClicked=()->{
                        };
                        break;

                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        worksCardViewAdapter = new WorksCardViewAdapter(worksInfoList);
        recyclerView.setAdapter(worksCardViewAdapter);
    }



    private void initWorks() {
        worksInfoList.clear();
        for (int i = 0; i < 4; i++) {
            worksInfoList.add(worksInfos[i]);
        }
    }
}
