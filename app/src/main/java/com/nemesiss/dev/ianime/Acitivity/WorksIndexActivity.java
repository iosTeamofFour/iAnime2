package com.nemesiss.dev.ianime.Acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.nemesiss.dev.ianime.R;

public class WorksIndexActivity extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_works_index);
        findView();

    }
    private void findView()
    {
         editText=findViewById(R.id.search_editText);
         editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    editText.setHint("");
                }
                else
                {
                    editText.setHint("我的作品");
                }
            }
        });

        ImageView avatar=findViewById(R.id.avatar);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WorksIndexActivity.this,"hhh",Toast.LENGTH_SHORT).show();
            }
        });


        Button button=findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(WorksIndexActivity.this,"gun",Toast.LENGTH_SHORT).show();
                    }
                });

        View view=findViewById(R.id.outerLayout);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setHint("我的作品");
            }
        });
    }
}
