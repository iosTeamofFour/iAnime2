package com.nemesiss.dev.ianime.Services;

import android.os.AsyncTask;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nemesiss.dev.ianime.Model.Model.Response.Data.WorksInfoWithIcon;
import com.nemesiss.dev.ianime.Utils.AppUtils;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchServices {
    private SparseArray<WorksInfoWithIcon> AllWorksInfoWithIconList = null;
    private OkHttpClient client = null;
    public SearchServices()
    {
        AllWorksInfoWithIconList = new SparseArray<>();
        client = AppUtils.GetOkHttpClient().build();
    }

    public SparseArray<WorksInfoWithIcon> getAllWorksInfoWithIconList()
    {
        return AllWorksInfoWithIconList;
    }

    public void RefreshAllStationInfo()
    {
       // new GetAllStationTask().execute();
    }

    public WorksInfoWithIcon SetWorksInfo(WorksInfoWithIcon info) throws IllegalArgumentException
    {
        if(AllWorksInfoWithIconList!=null)
        {
            if(info!=null)
            {
                WorksInfoWithIcon selected = AllWorksInfoWithIconList.get(info.getImageID(),null);
                if(selected!=null)
                {
                    AllWorksInfoWithIconList.put(selected.getImageID(),info);
                }
                else AllWorksInfoWithIconList.append(info.getImageID(),info);
                return info;
            }
            else throw new IllegalArgumentException("传入待更新的作品信息为空!");
        }
        throw new IllegalArgumentException("作品不存在! 请尝试重新获取.");
    }

    public WorksInfoWithIcon GetWorksInfo(int key)
    {
        return AllWorksInfoWithIconList.get(key,null);
    }

    public List<WorksInfoWithIcon> ShowInputTips(String key)
    {
        StringBuilder keyWordBuffer = new StringBuilder();
        List<WorksInfoWithIcon> maybe = new ArrayList<>();
        if(AllWorksInfoWithIconList!=null)
        {
            int len = AllWorksInfoWithIconList.size();
            for (int i = 0; i < len; i++)
            {
                WorksInfoWithIcon s = AllWorksInfoWithIconList.valueAt(i);
                keyWordBuffer.append(s.getName()).append(s.getAuthorName()).append(s.getImageID());

                if(keyWordBuffer.indexOf(key) != -1)
                {
                    maybe.add(s);
                }
                keyWordBuffer.setLength(0);//clear the buffer.
            }
        }
        return maybe;
    }

//    public WorksInfoWithIcon UpdateWorksInfo(int id)
//    {
//        Response resp = null;
//        try
//        {
//            resp = CommonServices.SendGetRequest(client, RequestUrl.getSelectedStation(id),null,null);
//            if(GlobalUtils.ConfirmResponseSuccessful(resp))
//            {
//                String jsonResp = resp.body().string();
//                List<WorksInfoWithIcon> info = new Gson().fromJson(jsonResp,new TypeToken<List<WorksInfoWithIcon>>(){}.getType());
//                return SetStationInfo(info.get(0));
//            }
//        } catch (Exception e)
//        {
//            Log.e("SearchServices", "无法更新ID : "+id);
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public ArrayList<WorksInfoWithIcon> GetCanArriveIn15MinWorksInfoWithIcon()
//    {
//        int len = AllWorksInfoWithIconList.size();
//        ArrayList<WorksInfoWithIcon> can = new ArrayList<>();
//        for (int i = 0; i < len; i++)
//        {
//            WorksInfoWithIcon s = AllWorksInfoWithIconList.valueAt(i);
//            //15 mins, avg 45km/h => 11.25km
//            if(s.getDistanceBetweenMe()!=-1 && s.getDistanceBetweenMe() <= 20000)
//            {
//                can.add(s);
//            }
//        }
//
//        Collections.sort(can, (WorksInfoWithIcon, t1) -> {
//            int d1 = WorksInfoWithIcon.getDistanceBetweenMe(),d2 = t1.getDistanceBetweenMe();
//            if(d1 < d2) return -1;
//            else if(d1 == d2) return 0;
//            else return 1;
//        });
//        //传出的结果是经历过从小到大排序的，最后就把这些数据加入到排队队列中。传出进一步处理。
//        return can;
//    }

//    class GetAllStationTask extends AsyncTask<Void,Void, SparseArray<WorksInfoWithIcon>>
//    {
//        @Override
//        protected SparseArray<WorksInfoWithIcon> doInBackground(Void... voids)
//        {
//            try
//            {
//                Response resp = CommonServices.SendGetRequest(client,RequestUrl.getAllWorksInfoWithIcon(),null,null);
//                if(GlobalUtils.ConfirmResponseSuccessful(resp))
//                {
//                    String jsonResp = resp.body().string();
//                    Gson gson = new Gson();
//                    List<WorksInfoWithIcon> WorksInfoWithIcon = gson.fromJson(jsonResp,new TypeToken<List<WorksInfoWithIcon>>(){}.getType());
//                    SparseArray<WorksInfoWithIcon> result = new SparseArray<>();
//                    if(WorksInfoWithIcon!=null)
//                    {
//                        Log.d("WorksInfoWithIconervices","成功获取到全部充电站信息.");
//                        for (WorksInfoWithIcon s : WorksInfoWithIcon)
//                        {
//                            result.append(s.getId(),s);
//                        }
//                        return result;
//                    }
//
//                }
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//            Log.d("WorksInfoWithIconervices","获取全部充电站信息失败, 搜索功能和自动寻找充电站功能将不可用。");
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(SparseArray<WorksInfoWithIcon> result)
//        {
//            super.onPostExecute(result);
//            if(result!=null)
//            {
//                AllWorksInfoWithIconList = result;
//            }
//            else
//            {
//                Toast.makeText(ChargerApplication.getContext(),"无法获得全部的充电站信息,搜索功能和预约功能将不可用。",Toast.LENGTH_SHORT).show();
//            }
//        }
//        @Override
//        protected void onPreExecute()
//        {
//            super.onPreExecute();
//        }
//    }
}
