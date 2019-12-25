package com.nemesiss.dev.ianime.Acitivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import com.nemesiss.dev.ianime.R;

public class WorkDetailActivity extends iAnimeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_detail);
    }
//    @Override
//    protected void onStart(){
//        super.onStart();
//        ActionBar actionBar = this.getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        return super.onOptionsItemSelected(item);
//    }
}
