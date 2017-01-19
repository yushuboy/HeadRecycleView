package com.axaet.myrecycleview.application;

import android.app.Application;

import com.antfortune.freeline.FreelineCore;
import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;

/**
 * author: yuShu
 * date:2016/11/9
 * time:11:43
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FreelineCore.init(this);
        //卡顿检测
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
        //内存检测
        LeakCanary.install(this);
    }
}
