package com.axaet.myrecycleview.adapter.holder;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.axaet.myrecycleview.R;
import com.axaet.myrecycleview.adapter.base.BaseViewHolder;
import com.axaet.myrecycleview.beans.DataModel;
import com.axaet.myrecycleview.util.AppUtil;
import com.axaet.myrecycleview.util.ImageLoader;

/**
 * Created by Administrator
 * on 2016/11/6.
 */

public class OneViewHolder extends BaseViewHolder {

    private ImageView imgHead;
    private TextView txtTitle;
    private TextView txtTime;
    private AbsoluteSizeSpan sizeSpan1;
    private AbsoluteSizeSpan sizeSpan2;
    private ForegroundColorSpan colorSpan;

    public OneViewHolder(View itemView) {
        super(itemView);
        imgHead = (ImageView) itemView.findViewById(R.id.img_head);
        txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
        txtTime = (TextView) itemView.findViewById(R.id.txt_time);
        sizeSpan1 = new AbsoluteSizeSpan(AppUtil.dip2px(txtTime.getContext(), 12));
        sizeSpan2 = new AbsoluteSizeSpan(AppUtil.dip2px(txtTime.getContext(), 14));
        colorSpan = new ForegroundColorSpan(txtTime.getContext().getResources().getColor(R.color.gplus_color_4));
    }

    @Override
    public void bindViewHolder(DataModel dataModel) {
        SpannableStringBuilder style = new SpannableStringBuilder(dataModel.time);
        style.setSpan(sizeSpan1, 0, 20, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(colorSpan, 20, dataModel.time.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(sizeSpan2, 20, dataModel.time.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        txtTime.setText(style);
        txtTitle.setText(dataModel.title);
        ImageLoader.displayImage(dataModel.url1, imgHead);
    }
}
