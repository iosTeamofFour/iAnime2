package com.nemesiss.dev.ianime.Acitivity;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.nemesiss.dev.ianime.R;

import java.util.ArrayList;

import static com.nemesiss.dev.ianime.Application.iAnimeApplication.getContext;

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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN) {
            View currentFocus = getCurrentFocus();
            if(IsShouldHideKeyboard(currentFocus,ev)) {
                HideKeyboard(currentFocus);
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    private boolean IsShouldHideKeyboard(View view, MotionEvent event) {
        if((view instanceof EditText)) {
            int[] coord = {0,0};
            view.getLocationInWindow(coord);
            int left = coord[0], top = coord[1], right = left + view.getWidth(), bottom = top + view.getHeight();
            int evX = (int) event.getRawX(), evY = (int) event.getRawY();
            return !((left <= evX && evX <= right) && (top <= evY && evY <= bottom));
        }
        return false;
    }

    public static void HideKeyboard(View v) {
        InputMethodManager manager = ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE));
        if (manager != null)
            manager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        v.clearFocus();
    }
}
