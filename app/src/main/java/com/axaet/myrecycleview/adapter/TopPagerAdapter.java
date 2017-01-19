package com.axaet.myrecycleview.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.axaet.myrecycleview.MeActivity;
import com.axaet.myrecycleview.util.ImageLoader;
import com.axaet.myrecycleview.R;

import java.util.List;

/**
 * author: yuShu
 * date:2016/11/7
 * time:14:46
 */

public class TopPagerAdapter extends PagerAdapter {

    private List<String> mList;
    private Context mContext;
    private LayoutInflater inflater;

    public TopPagerAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }


    public void addImages(List<String> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 4 : mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = inflater.inflate(R.layout.item_top_pager, container, false);
        ImageView ivImage = (ImageView) view.findViewById(R.id.image_view);
        if (this.mList != null) {
            ImageLoader.displayImage(mList.get(position), ivImage);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, MeActivity.class));
                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        Log.i("yushu", "viewpager: =" + position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
