package com.nemesiss.dev.ianime.Acitivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.nemesiss.dev.ianime.R;
import com.nemesiss.dev.ianime.Utils.AppUtils;
import com.nemesiss.dev.ianime.View.PinchImageView;


public class WorkDetailActivity extends iAnimeActivity {

    private PinchImageView pinchIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_detail);

        ImageView showBigPic = (ImageView) findViewById(R.id.works_img);
        showBigPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinchIV = findViewById(R.id.works_img);
                Glide.with(WorkDetailActivity.this).load(showBigPic.getDrawable()).into(pinchIV);
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
        final TextView saveSplash = dialogView.findViewById(R.id.saveColor);
        final TextView saveDraft = dialogView.findViewById(R.id.saveDraft);

        checkDraft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent check_draft = new Intent(WorkDetailActivity.this,DrawingActivity.class);

                startActivity(check_draft);
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
