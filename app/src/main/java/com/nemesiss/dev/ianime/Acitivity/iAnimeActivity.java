package com.nemesiss.dev.ianime.Acitivity;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.nemesiss.dev.ianime.R;

import java.util.ArrayList;

public class iAnimeActivity extends AppCompatActivity {

    private static ArrayList<Activity> activities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        activities.remove(this);
    }

    public static ArrayList<Activity> GetAllActivities()
    {
        return activities;
    }

    public static void FinishAllActivities()
    {
        if (activities != null && activities.size() > 0)
        {
            for (Activity activity : activities)
            {
                if (!activity.isFinishing())
                {
                    activity.finish();
                }
            }
        }
    }
    public static Activity GetActivity(Class<? extends iAnimeActivity> activityClass) {
        for (Activity activity : activities) {
            if(activity.getClass().equals(activityClass)){
                return activity;
            }
        }
        return null;
    }
}
