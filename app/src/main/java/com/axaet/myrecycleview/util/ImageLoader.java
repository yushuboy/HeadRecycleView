package com.axaet.myrecycleview.util;

import android.widget.ImageView;

import com.axaet.myrecycleview.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * author: yuShu
 * date:2016/11/8
 * time:08:56
 */

public class ImageLoader {
    public static void displayImage(String url, ImageView imageView) {
        Glide.with(imageView.getContext()).load(url).asBitmap().centerCrop().error(R.mipmap.ic_launcher)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }
}
