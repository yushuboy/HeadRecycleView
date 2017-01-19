package com.axaet.myrecycleview.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.axaet.myrecycleview.beans.DataModel;

/**
 * Created by Administrator
 * on 2016/11/6.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewHolder(DataModel dataModel);


}
