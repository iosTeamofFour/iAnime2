package com.nemesiss.dev.ianime.Acitivity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import com.nemesiss.dev.ianime.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Target;
import java.util.LinkedList;
import java.util.List;

import static android.media.tv.TvContract.WatchNextPrograms.CONTENT_URI;

public class DrawIndexActivity extends iAnimeActivity {

    public static final int TAKE_PHOTO=1;
    public static final int CHOOSE_PHOTO=2;
    private ImageView picture;
    private Uri imageUri;

   private List<Uri> PickupImageList=new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_index);
        Button takePhoto=findViewById(R.id.take_photo);
        picture=findViewById(R.id.picture);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                File outputImage=new File(getExternalCacheDir(),"output_image.jpg");
                try{
                    if(outputImage.exists())
                    {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24){
                    imageUri= FileProvider.getUriForFile(DrawIndexActivity.this,
                            "com.example.cameraalbumtest2.fileprovider",outputImage);

                }
                else
                {
                    imageUri=Uri.fromFile(outputImage);
                }
                Intent intent=new Intent("android.media.action.IMAGE_CARTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
        });

        Button chooseFromAlbum=findViewById(R.id.choose_from_album);
        chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(DrawIndexActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(DrawIndexActivity.this,new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }
                else {
                    openAlbum();
                }

            }
        });
    }
    private void openAlbum()
    {
        Intent intent=new Intent("android.intent.action.FET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        switch (requestCode)
        {
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK)
                {
                    try{
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if(resultCode==RESULT_OK){
                    if(Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat(data);
                    }
                    else{
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults)
    {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }
                else{
                    Toast.makeText(this,"你拒绝了权限",Toast.LENGTH_SHORT).show();
                }
                break;
             default:
        }
    }

@TargetApi(19)
private void handleImageOnKitKat(Intent data) {
    String imagePath = null;
    Uri uri = data.getData();
    if (DocumentsContract.isDocumentUri(this, uri)) {
// 如果是document类型的Uri，则通过document id处理
        String docId = DocumentsContract.getDocumentId(uri);
        if("com.android.providers.media.documents".equals(uri.getAuthority())) {
            String id = docId.split(":")[1]; // 解析出数字格式的id
            String selection = MediaStore.Images.Media._ID + "=" + id;
            imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
        } else if ("com.android.providers.downloads.documents".equals(uri.
                getAuthority())) {
            Uri contentUri = ContentUris.withAppendedId(Uri.parse("content: downloads/public_downloads"), Long.valueOf(docId));
                    imagePath = getImagePath(contentUri, null);
        }
    } else if ("content".equalsIgnoreCase(uri.getScheme())) {
// 如果是content类型的Uri，则使用普通方式处理
        imagePath = getImagePath(uri, null);
    } else if ("file".equalsIgnoreCase(uri.getScheme())) {
// 如果是file类型的Uri，直接获取图片路径即可
        imagePath = uri.getPath();
    }
    displayImage(imagePath); // 根据图片路径显示图片
}
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }
    private String getImagePath(Uri uri, String selection) {
        String path = null;
// 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.
                        Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private void CallPhotoPickerToPick()
    {
        RxGalleryFinal.with(this)
                .imageLoader(ImageLoaderType.GLIDE)
                .image()
                .maxSize(1)
                .multiple()
                .subscribe(this.getMultiImageSelectHandle())
                .openGallery();

    }

    public RxBusResultDisposable<ImageMultipleResultEvent> getMultiImageSelectHandle()
    {
        return new RxBusResultDisposable<ImageMultipleResultEvent>() {
            @Override
            protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                List<MediaBean> mb=imageMultipleResultEvent.getResult();
                for(MediaBean mediaBean:mb)
                {
                    Uri currUri=Uri.fromFile(new File(mediaBean.getOriginalPath()));
                    AppendImage(currUri,false);
                }
                //adapter
            }
        };
    }
    public void AppendImage(Uri imageUri,boolean shouldNotifyDataSetChanged)
    {
        if(PickupImageList.size()!=10)
        {
        PickupImageList.add(PickupImageList.size()-1,imageUri);
        }
    }
}
