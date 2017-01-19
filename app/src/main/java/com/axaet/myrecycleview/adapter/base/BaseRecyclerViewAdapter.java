/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.axaet.myrecycleview.adapter.base;

import android.content.Context;
import android.support.annotation.AnimRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.axaet.myrecycleview.beans.DataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 咖枯
 * @version 1.0 2016/8/6
 */
public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    protected static final int TYPE_FOOTER = -2;
    private int mLastPosition = -1;
    protected boolean mIsShowFooter;
    private List<T> mList = new ArrayList<>();
    private LayoutInflater inflater;

    public BaseRecyclerViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }


    protected View getView(ViewGroup parent, int layoutId) {
        return inflater.inflate(layoutId, parent, false);
    }

    @Override
    public int getItemCount() {
        int itemSize = mList.size();
        //如果有尾布局并显示，则添加一个item
        if (mIsShowFooter) {
            itemSize += 1;
        }
        return itemSize;
    }

    protected void setItemAppearAnimation(RecyclerView.ViewHolder holder, int position, @AnimRes int type) {
        if (position > mLastPosition/* && !isFooterPosition(position)*/) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), type);
            holder.itemView.startAnimation(animation);
            mLastPosition = position;
        }
    }

    protected boolean isFooterPosition(int position) {
        return (getItemCount() - 1) == position;
    }


    protected T getItem(int position) {
        return this.mList.get(position);
    }


    public void addMore(List<T> data) {
        int startPosition = mList.size();
        mList.addAll(data);
        notifyItemRangeInserted(startPosition, mList.size());
    }

    public void delete(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> items) {
        mList.clear();
        addMore(items);
    }


    public void clear() {
        this.mList.clear();
        notifyDataSetChanged();
    }


    public void showFooter() {
        mIsShowFooter = true;
        notifyItemInserted(getItemCount());
    }

    public void hideFooter() {
        mIsShowFooter = false;
        notifyItemRemoved(getItemCount());
    }

    protected class FooterViewHolder extends BaseViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindViewHolder(DataModel dataModel) {
        }
    }
}
