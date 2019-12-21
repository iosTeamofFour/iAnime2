package com.nemesiss.dev.ianime.Acitivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.dingmouren.colorpicker.ColorPickerDialog;
import com.dingmouren.colorpicker.OnColorPickerListener;
import com.nemesiss.dev.ianime.R;

public class testActivity extends AppCompatActivity {


    private boolean supportAlpha; //是否支持透明度
    public static int myColor= Color.BLUE;
    public static int tag=1; // 1为锚点，2为标记点
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ImageButton paint=findViewById(R.id.paint);

        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialog mColorPickerDialog = new ColorPickerDialog(
                        testActivity.this,
                        myColor,
                        supportAlpha,
                        mOnColorPickerListener
                ).show();
            }
        });

        ImageButton anchor=findViewById(R.id.anchor);
        anchor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag=1;
            }
        });
        ImageButton markPoint=findViewById(R.id.markPoint);
        markPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag=2;
            }
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
            myColor=color;
        }
    };
}
