package com.nemesiss.dev.ianime.Acitivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.nemesiss.dev.ianime.Model.Model.Drafting.AnchorPoint;
import com.nemesiss.dev.ianime.Model.Model.Drafting.HintPoint;
import com.nemesiss.dev.ianime.Model.Model.Drafting.Point;
import com.nemesiss.dev.ianime.Model.Model.Request.PostColorRequestInfo;
import com.nemesiss.dev.ianime.R;
import com.nemesiss.dev.ianime.Services.DownLoadImageService;
import com.nemesiss.dev.ianime.Tasks.GetQueryColorProgressTask;
import com.nemesiss.dev.ianime.Tasks.PostColorTask;
import com.nemesiss.dev.ianime.Utils.AppUtils;
import com.nemesiss.dev.ianime.View.MyDrawView;
import com.nemesiss.dev.ianime.View.PinchImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


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
    private String receipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        PinchIv = findViewById(R.id.imageView);
        myDrawView = findViewById(R.id.myDrawView);
        myDrawView.SetBackgroundPinchIv(PinchIv);

        LoadImage();

        ImageButton paint = findViewById(R.id.paint);
        paint.setOnClickListener(v -> {
            ColorPickerDialog mColorPickerDialog = new ColorPickerDialog(
                    DrawingActivity.this,
                    myColor,
                    supportAlpha,
                    mOnColorPickerListener
            ).show();
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            tag = 0;
            myDrawView.SetCanDrawDot(false);
            back.setImageResource(R.mipmap.keyboard_arrow_left_blue);
            anchor.setImageResource(R.mipmap.target);
            markPoint.setImageResource(R.mipmap.dot_circle);
        });

        anchor = findViewById(R.id.anchor);
        anchor.setOnClickListener(v -> {
            tag = 1;
            myDrawView.SetCanDrawDot(true);
            back.setImageResource(R.mipmap.keyboard_arrow_left);
            anchor.setImageResource(R.mipmap.target_blue);
            markPoint.setImageResource(R.mipmap.dot_circle);
        });

        markPoint = findViewById(R.id.markPoint);
        markPoint.setOnClickListener(v -> {
            tag = 2;
            myDrawView.SetCanDrawDot(true);
            back.setImageResource(R.mipmap.keyboard_arrow_left);
            anchor.setImageResource(R.mipmap.target);
            markPoint.setImageResource(R.mipmap.dot_circle_blue);
        });

        ImageButton camera = findViewById(R.id.camera);
        camera.setOnClickListener(v -> {
            OpenGallery(v);
        });

        ImageButton exit = findViewById(R.id.exit);
        exit.setOnClickListener(v -> {
            Intent intent = new Intent(DrawingActivity.this, WorksIndexActivity.class);
            startActivity(intent);
        });

        ImageButton publish = findViewById(R.id.publish);
        publish.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(DrawingActivity.this);
            dialog.setTitle("提交上色请求");
            dialog.setMessage("确定要提交上色请求吗？");
            dialog.setCancelable(false);
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    startColor();

                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();
        });


    }

    private List<List<Float>> getColorPointsList() {
        double DrawviewWidth = (double) myDrawView.getWidth(), DrawviewHeight = (double) myDrawView.getHeight();

        List<List<Float>> pointsForColorize = new ArrayList<>();

        for (Map.Entry<Point, AnchorPoint> pe : myDrawView.anchors.entrySet()) {
            AnchorPoint ap = pe.getValue();
            List<Float> list = Arrays.asList(
                    (float) ap.getPercentageX(DrawviewWidth),
                    (float) ap.getPercentageY(DrawviewHeight),
                    (float) ap.Color.R,
                    (float) ap.Color.G,
                    (float) ap.Color.B,
                    0f);
            pointsForColorize.add(list);
        }

        for (Map.Entry<Point, HintPoint> pe : myDrawView.hints.entrySet()) {
            HintPoint ap = pe.getValue();
            List<Float> list = Arrays.asList(
                    (float) ap.getPercentageX(DrawviewWidth),
                    (float) ap.getPercentageY(DrawviewHeight),
                    (float) ap.Color.R,
                    (float) ap.Color.G,
                    (float) ap.Color.B,
                    2f);
            pointsForColorize.add(list);
        }
        return pointsForColorize;

    }


    private void startColor() {
        PostColorRequestInfo requestInfo = new PostColorRequestInfo(currUri.getPath(),getColorPointsList());
        new PostColorTask(TaskRet -> {
            if (TaskRet != null) {
                if (TaskRet.getStatusCode() == 0) {
                    ProgressDialog progressDialog = new ProgressDialog(DrawingActivity.this);
                    progressDialog.setTitle("上色过程");
                    progressDialog.setMessage("上色中......请稍等");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    if (queryColor()) {
                        progressDialog.dismiss();
                    }
                }
                if (TaskRet.getStatusCode() == -1) {
                    Toast.makeText(DrawingActivity.this, "服务器端无法正确解析客户端的Base64", Toast.LENGTH_SHORT).show();
                }

            }
        }).execute(requestInfo);
    }

    private boolean queryColor() {
        new GetQueryColorProgressTask(TaskRet -> {
            if (TaskRet != null) {
                if (TaskRet.getStatusCode() == 0) {
                    Toast.makeText(DrawingActivity.this, "上色完成,已保存至个人作品类", Toast.LENGTH_SHORT).show();

                }
                if (TaskRet.getStatusCode() == 1) {
                    //正在上色
                }
                if (TaskRet.getStatusCode() == 2) {
                    //正在排队等待上色
                }
                if (TaskRet.getStatusCode() == -1) {
                    Toast.makeText(DrawingActivity.this, "回执ID无效", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute(receipt);

        return true;
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
        if (ContextCompat.checkSelfPermission(DrawingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DrawingActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            call(url);
        }

    }

    private void call(String url) {
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantresults) {
        switch (requestCode) {
            case 1:
                if (grantresults.length > 0 && grantresults[0] == PackageManager.PERMISSION_GRANTED) {
                    call(currUri.getPath());
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

}
