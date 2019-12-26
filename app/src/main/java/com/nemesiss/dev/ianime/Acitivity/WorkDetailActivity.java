package com.nemesiss.dev.ianime.Acitivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.nemesiss.dev.ianime.R;
import com.nemesiss.dev.ianime.View.PinchImageView;


public class WorkDetailActivity extends iAnimeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_detail);

        ImageView showBigPic = (ImageView) findViewById(R.id.works_img);
        showBigPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkDetailActivity.this, PinchImageView.class);
                startActivity(intent);
            }
        });

        ImageView openMenu = (ImageView) findViewById(R.id.menu);
        openMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsListDialog();
            }
        });

        ImageView likeWorks = (ImageView) findViewById(R.id.ifLike);
        likeWorks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    protected void setImage(Image img){

    }
    protected void setIlluationName(String illuationName){

    }
    protected void setIlluationID(String illuationID){

    }
    protected void setUserName(String userName){

    }
    protected void setDescription(String description){

    }
    protected void setTags(){

    }
    protected void setLike(boolean like){

    }
    private void itemsListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WorkDetailActivity.this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(WorkDetailActivity.this,R.layout.dialog_item,null);
        dialog.setView(dialogView);
        dialog.show();

        final TextView checkDraft = dialogView.findViewById(R.id.checkDraft);
        final TextView saveSplash = dialogView.findViewById(R.id.saveSplash);
        final TextView saveDraft = dialogView.findViewById(R.id.saveDraft);

        checkDraft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        saveSplash.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        saveDraft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
    }
}
