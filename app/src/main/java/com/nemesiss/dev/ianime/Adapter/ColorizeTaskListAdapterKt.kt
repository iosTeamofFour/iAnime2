package com.nemesiss.dev.ianime.Adapter

import android.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.nemesiss.dev.ianime.Acitivity.iAnimeActivity
import com.nemesiss.dev.ianime.Model.Model.Drafting.ColorizeTask
import com.nemesiss.dev.ianime.R
import com.nemesiss.dev.ianime.Utils.AppUtils
import kotlinx.android.synthetic.main.single_task_item.view.*

public class ColorizeTaskListAdapterKt(initTasks: List<ColorizeTask>, val activity: iAnimeActivity) :
    RecyclerView.Adapter<ColorizeTaskViewHolder>() {

    var tasks: List<ColorizeTask> = initTasks

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ColorizeTaskViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.single_task_item, viewGroup, false)
        return ColorizeTaskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(viewHolder: ColorizeTaskViewHolder, i: Int) {
        val task = tasks[i]
        viewHolder.ItemBtn.text = AppUtils.UnixStampToFmtString(task.createdAt.toLong())
        viewHolder.ItemBtn.setOnClickListener {
            if (task.isFinished) {
                BuildConfirmDialog()
            }
        }
    }

    private fun BuildConfirmDialog(Index : Int, ) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("请选择一个动作")
        builder.setMessage("针对已完成上色的作品")
        builder.setNeutralButton("移除",null)
        builder.setPositiveButton("选择",null)
        builder.setNegativeButton("取消",null)
        builder.create().show()
    }

}


class ColorizeTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var ItemBtn: Button = itemView.WorkTaskItem
}