package com.nemesiss.dev.ianime.Acitivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.nemesiss.dev.ianime.Adapter.WorksCardViewAdapter;
import com.nemesiss.dev.ianime.Model.Model.Response.Data.WorksInfo;
import com.nemesiss.dev.ianime.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.nemesiss.dev.ianime.Application.iAnimeApplication.getContext;

public class WorksIndexActivity extends AppCompatActivity {

    private EditText editText;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );

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
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editText.setHint("");
                } else {
                    editText.setHint("我的作品");
                    //收起软键盘
                    InputMethodManager manager = ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
                    if (manager != null)
                        manager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                }
            }
        });

        ImageView avatar = findViewById(R.id.avatar);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        View view = findViewById(R.id.DrawerLayout);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setHint("我的作品");
                v.requestFocus();
            }
        });

        ImageView imageView = findViewById(R.id.search_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WorksIndexActivity.this, "开始搜索", Toast.LENGTH_SHORT).show();
            }
        });


        //navigationView.setCheckedItem(R.id.index);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                return true;
            }
        });

        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        worksCardViewAdapter=new WorksCardViewAdapter(worksInfoList);
        recyclerView.setAdapter(worksCardViewAdapter);
    }

    private void initWorks() {
        worksInfoList.clear();
        for (int i = 0; i < 2; i++) {
            worksInfoList.add(worksInfos[i]);
        }
    }
}
