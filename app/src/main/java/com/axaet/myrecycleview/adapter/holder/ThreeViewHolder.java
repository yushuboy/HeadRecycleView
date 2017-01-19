package com.axaet.myrecycleview.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.axaet.myrecycleview.util.ImageLoader;
import com.axaet.myrecycleview.R;
import com.axaet.myrecycleview.adapter.base.BaseViewHolder;
import com.axaet.myrecycleview.beans.DataModel;

/**
 * Created by Administrator on 2016/11/6.
 */

public class ThreeViewHolder extends BaseViewHolder {

    private TextView txtTitle;
    private ImageView imgOne;
    private ImageView imgTwo;
    private ImageView imgThree;


    public ThreeViewHolder(View itemView) {
        super(itemView);
        txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
        imgOne = (ImageView) itemView.findViewById(R.id.img_one);
        imgTwo = (ImageView) itemView.findViewById(R.id.img_two);
        imgThree = (ImageView) itemView.findViewById(R.id.img_three);
    }

    @Override
    public void bindViewHolder(DataModel dataModel) {
        txtTitle.setText(dataModel.title);
        ImageLoader.displayImage(dataModel.url1, imgOne);
        ImageLoader.displayImage(dataModel.url2, imgTwo);
        ImageLoader.displayImage(dataModel.url3, imgThree);
    }
}
