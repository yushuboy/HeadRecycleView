package com.axaet.myrecycleview.adapter.holder;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.axaet.myrecycleview.R;
import com.axaet.myrecycleview.adapter.TopPagerAdapter;
import com.axaet.myrecycleview.adapter.base.BaseViewHolder;
import com.axaet.myrecycleview.beans.DataModel;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * author: yuShu
 * date:2016/11/7
 * time:14:44
 */

public class HeaderViewHolder extends BaseViewHolder {

    public ViewPager viewPager;
    private TopPagerAdapter pagerViewAdapter;


    public HeaderViewHolder(View itemView) {
        super(itemView);
        viewPager = (ViewPager) itemView.findViewById(R.id.view_pager);
        pagerViewAdapter = new TopPagerAdapter(viewPager.getContext());
        viewPager.setAdapter(pagerViewAdapter);
        CirclePageIndicator indicator = (CirclePageIndicator) itemView.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
    }

    @Override
    public void bindViewHolder(DataModel dataModel) {
        pagerViewAdapter.addImages(dataModel.list);
    }
}
