package com.nemesiss.dev.ianime.Adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.nemesiss.dev.ianime.Model.Model.Response.Data.WorksInfoWithIcon;
import com.nemesiss.dev.ianime.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyExtendableListViewAdapter extends BaseExpandableListAdapter {

   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public String[] groupString = {"最受欢迎的线稿", "最受欢迎的上色"};
    public  WorksInfoWithIcon[][] childString = {
            { new WorksInfoWithIcon(R.drawable.image0, "first", "zoom", sdf.format(new Date())),
                    new WorksInfoWithIcon(R.drawable.image1, "second", "zoom", sdf.format(new Date()))},
            {new WorksInfoWithIcon(R.drawable.image2, "third", "zoom", sdf.format(new Date())),
                    new WorksInfoWithIcon(R.drawable.image3, "second", "zoom", sdf.format(new Date()))}
    };

    private WorksCardViewWithIconAdapter worksCardViewWithIconAdapter;
    private static List<WorksInfoWithIcon> worksInfoWithIconList = new ArrayList<>();

    public MyExtendableListViewAdapter()
    {
        initWorks();
    }

    @Override
    // 获取分组的个数
    public int getGroupCount() {
        return groupString.length;
    }

    //获取指定分组中的子选项的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return childString[groupPosition].length;
    }

    //        获取指定的分组数据
    @Override
    public Object getGroup(int groupPosition) {
        return groupString[groupPosition];
    }

    //获取指定分组中的指定子选项数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childString[groupPosition][childPosition];
    }

    //获取指定分组的ID, 这个ID必须是唯一的
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //获取子选项的ID, 这个ID必须是唯一的
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
    @Override
    public boolean hasStableIds() {
        return true;
    }
    /**
     *
     * 获取显示指定组的视图对象
     *
     * @param groupPosition 组位置
     * @param isExpanded 该组是展开状态还是伸缩状态
     * @param convertView 重用已有的视图对象
     * @param parent 返回的视图对象始终依附于的视图组
     */
// 获取显示指定分组的视图
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.partent_item,parent,false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvTitle = (TextView)convertView.findViewById(R.id.label_group_normal);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
        groupViewHolder.tvTitle.setText(groupString[groupPosition]);
        return convertView;

    }
    /**
     *
     * 获取一个视图对象，显示指定组中的指定子元素数据。
     *
     * @param groupPosition 组位置
     * @param childPosition 子元素位置
     * @param isLastChild 子元素是否处于组中的最后一个
     * @param convertView 重用已有的视图(View)对象
     * @param parent 返回的视图(View)对象始终依附于的视图组
     * @return
     * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean, android.view.View,
     *      android.view.ViewGroup)
     */

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;

        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.recyclerView = convertView.findViewById(R.id.child_recyclerView);
            convertView.setTag(childViewHolder);

        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        GridLayoutManager layoutManager = new GridLayoutManager(parent.getContext(), 2);
        childViewHolder.recyclerView.setLayoutManager(layoutManager);
        worksCardViewWithIconAdapter= new WorksCardViewWithIconAdapter(worksInfoWithIconList);
        childViewHolder.recyclerView.setAdapter(worksCardViewWithIconAdapter);

        return convertView;
    }

    //指定位置上的子元素是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        TextView tvTitle;
    }

    static class ChildViewHolder {
       RecyclerView recyclerView;

    }

    public  void initWorks() {
        worksInfoWithIconList.clear();
        for (int i = 0; i < 1; i++) {
            worksInfoWithIconList.add(childString[0][0]);
        }
    }
}

