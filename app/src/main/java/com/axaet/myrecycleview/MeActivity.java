package com.axaet.myrecycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


/**
 * 主要是各种优化布局的技巧
 */
public class MeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
    }

    public void show(View view){
        Toast.makeText(this,"yushu",Toast.LENGTH_SHORT).show();
    }
}
