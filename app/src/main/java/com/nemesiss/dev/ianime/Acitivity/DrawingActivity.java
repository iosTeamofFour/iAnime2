package com.nemesiss.dev.ianime.Acitivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import com.bumptech.glide.Glide;
import com.dingmouren.colorpicker.ColorPickerDialog;
import com.dingmouren.colorpicker.OnColorPickerListener;
import com.nemesiss.dev.ianime.Application.iAnimeApplication;
import com.nemesiss.dev.ianime.R;
import com.nemesiss.dev.ianime.Services.DownLoadImageService;
import com.nemesiss.dev.ianime.Utils.AppUtils;
import com.nemesiss.dev.ianime.View.MyDrawView;
import com.nemesiss.dev.ianime.View.PinchImageView;

import java.io.File;
import java.util.List;

import static com.nemesiss.dev.ianime.View.MyDrawView.circles;
import static com.nemesiss.dev.ianime.View.MyDrawView.rects;

public class DrawingActivity extends iAnimeActivity {


    private boolean supportAlpha; //是否支持透明度
    public static int myColor = Color.BLUE;
    public static int tag = 0; //0为不打点状态， 1为锚点，2为标记点

    private PinchImageView PinchIv;
    private MyDrawView myDrawView;
    private ImageButton anchor;
    private ImageButton back;
    private ImageButton markPoint;

    private Uri currUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        PinchIv = findViewById(R.id.imageView);
        LoadImage();

        myDrawView = findViewById(R.id.myDrawView);
        myDrawView.SetBackgroundPinchIv(PinchIv);

        ImageButton paint = findViewById(R.id.paint);
        paint.setOnClickListener(v->{
                ColorPickerDialog mColorPickerDialog = new ColorPickerDialog(
                        DrawingActivity.this,
                        myColor,
                        supportAlpha,
                        mOnColorPickerListener
                ).show();
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(v->{
                tag = 0;
                myDrawView.SetCanDrawDot(false);
                back.setImageResource(R.mipmap.keyboard_arrow_left_blue);
                anchor.setImageResource(R.mipmap.target);
                markPoint.setImageResource(R.mipmap.dot_circle);
        });

        anchor = findViewById(R.id.anchor);
        anchor.setOnClickListener(v->{
                tag = 1;
                myDrawView.SetCanDrawDot(true);
                back.setImageResource(R.mipmap.keyboard_arrow_left);
                anchor.setImageResource(R.mipmap.target_blue);
                markPoint.setImageResource(R.mipmap.dot_circle);
        });

        markPoint = findViewById(R.id.markPoint);
        markPoint.setOnClickListener(v ->{
                tag = 2;
                myDrawView.SetCanDrawDot(true);
                back.setImageResource(R.mipmap.keyboard_arrow_left);
                anchor.setImageResource(R.mipmap.target);
                markPoint.setImageResource(R.mipmap.dot_circle_blue);
        });

        ImageButton camera = findViewById(R.id.camera);
        camera.setOnClickListener(v->{
                OpenGallery(v);
                circles.clear();
                rects.clear();
        });

        ImageButton exit=findViewById(R.id.exit);
        exit.setOnClickListener(v -> {
            Intent intent=new Intent(DrawingActivity.this,WorksIndexActivity.class);
            startActivity(intent);
        });


    }

    public static OnColorPickerListener mOnColorPickerListener = new OnColorPickerListener() {
        @Override
        public void onColorCancel(ColorPickerDialog dialog) {//取消选择的颜色

        }

        @Override
        public void onColorChange(ColorPickerDialog dialog, int color) {//实时监听颜色变化

        }

        @Override
        public void onColorConfirm(ColorPickerDialog dialog, int color) {//确定的颜色
            myColor = color;
        }
    };

    private void LoadImage() {
        Glide.with(this)
                .load(AppUtils.GetAssetsUrl("image0.jpg"))
                .into(PinchIv);
    }

    public void OpenGallery(View view) {

        RxGalleryFinal
                .with(this)
                .imageLoader(ImageLoaderType.PICASSO)
                .image()
                .maxSize(1)
                .multiple()
                .subscribe(MultiImageSelectHandler())
                .openGallery();
    }

    public RxBusResultDisposable<ImageMultipleResultEvent> MultiImageSelectHandler() {
        return new RxBusResultDisposable<ImageMultipleResultEvent>() {
            @Override
            protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                List<MediaBean> mb = imageMultipleResultEvent.getResult();
                for (MediaBean mediaBean : mb) {
                    currUri = Uri.fromFile(new File(mediaBean.getOriginalPath()));
                    //Log.d("PickedImage", "被选择的图片: " + currUri.getPath());
                    Glide.with(DrawingActivity.this).load(currUri).into(PinchIv);
                    myDrawView.InitDrawView();
                    myDrawView.DrawDotsToCanva();
                    onDownLoad(currUri.getPath());
                }
            }
        };
    }

    private void onDownLoad(String url) {
        if(ContextCompat.checkSelfPermission(DrawingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(DrawingActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        else
        {
            call(url);
        }

    }
    private void call(String url)
    {
        DownLoadImageService service = new DownLoadImageService(getApplicationContext(),
                url,
                new DownLoadImageService.ImageDownLoadCallBack() {

                    @Override
                    public void onDownLoadSuccess(Bitmap bitmap) {
                        // 在这里执行图片保存方法
                    }

                    @Override
                    public void onDownLoadFailed() {
                        // 图片保存失败
                    }
                });
        //启动图片下载线程
        new Thread(service).start();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantresults){
        switch (requestCode)
        {
            case 1:
                if(grantresults.length>0&&grantresults[0]==PackageManager.PERMISSION_GRANTED){
                    call(currUri.getPath());
                }
                else{
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
                default:
        }
    }

}
