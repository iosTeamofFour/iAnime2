package com.nemesiss.dev.ianime.Acitivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import com.nemesiss.dev.ianime.Adapter.MyFragmentPagerAdapter;
import com.nemesiss.dev.ianime.Fragment.AdviseFragment;
import com.nemesiss.dev.ianime.Fragment.RankFragment;
import com.nemesiss.dev.ianime.Fragment.SearchFragment;
import com.nemesiss.dev.ianime.R;

import java.util.ArrayList;
import java.util.List;

public class DiscoverActivity extends iAnimeActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        //初始化各控件
        initView();
    }

    private void initView() {
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.view_pager);
        //获取数据 在values/arrays.xml中进行定义然后调用
        String[] tabTitle = getResources().getStringArray(R.array.tab_titles);
        //将fragment装进列表中
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new AdviseFragment());
        fragmentList.add(new RankFragment());
        fragmentList.add(new SearchFragment());

        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, tabTitle));
        viewPager.setOffscreenPageLimit(4);//防止出现fragment布局错乱问题
        //viewPager事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //定义TabLayout
        //TabLayout的事件
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中了tab的逻辑
                viewPager.setCurrentItem(tab.getPosition());
                View view = tab.getCustomView();
                if (null != view && view instanceof TextView) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(DiscoverActivity.this, R.color.colorAccent));
                }

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中tab的逻辑
                View view = tab.getCustomView();
                if (null != view && view instanceof TextView) {
                    ((TextView) view).setTextColor(ContextCompat.getColor(DiscoverActivity.this, R.color.Grayios));
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //再次选中tab的逻辑
            }
        });
        //TabLayout加载viewpager
        //一行代码和ViewPager联动起来
        tabLayout.setupWithViewPager(viewPager);
        Drawable d = null;
        for (int i = 0; i < tabLayout.getTabCount(); i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            switch (i){
                case 0:
                    d = getResources().getDrawable(R.drawable.tab1);
                    break;
                case 1:
                    d = getResources().getDrawable(R.drawable.tab2);
                    break;
                case 2:
                    d = getResources().getDrawable(R.drawable.tab3);
                    break;

            }
            tab.setIcon(d);
        }
    }
}
