package com.axaet.myrecycleview.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.axaet.myrecycleview.R;
import com.axaet.myrecycleview.adapter.base.BaseRecyclerViewAdapter;
import com.axaet.myrecycleview.adapter.base.BaseViewHolder;
import com.axaet.myrecycleview.adapter.holder.HeaderViewHolder;
import com.axaet.myrecycleview.adapter.holder.OneViewHolder;
import com.axaet.myrecycleview.adapter.holder.ThreeViewHolder;
import com.axaet.myrecycleview.adapter.holder.TwoViewHolder;
import com.axaet.myrecycleview.beans.DataModel;

/**
 * Created by Administrator
 * on 2016/11/6.
 */

public class NewsListHeaderAdapter extends BaseRecyclerViewAdapter<DataModel> {

    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;
    private static final int TYPE_THREE = 3;
    private static final int TYPE_HEADER = -1;
    private int currentItem = 0;//viewpager当前页
    private Handler handler = new Handler();
    private OnNewsListItemClickListener itemClickListener;

    public NewsListHeaderAdapter(Context context) {
        super(context);
    }


    public interface OnNewsListItemClickListener {
        void onItemClick(View view, int position, boolean isPhoto);
    }

    public void setItemClickListener(OnNewsListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    /**
     * 停止广告轮播
     */
    public void stopViewpagerBanner() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (mIsShowFooter && isFooterPosition(position)) {
            return TYPE_FOOTER;
        } else if (getItem(position).type == DataModel.TYPE_ONE) {
            return TYPE_ONE;
        } else if (getItem(position).type == DataModel.TYPE_TWO) {
            return TYPE_TWO;
        } else {
            return TYPE_THREE;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        BaseViewHolder holder;
        switch (viewType) {
            case TYPE_HEADER:
                view = getView(parent, R.layout.item_header);
                holder = new HeaderViewHolder(view);
                return holder;
            case TYPE_FOOTER:
                view = getView(parent, R.layout.item_footer);
                holder = new FooterViewHolder(view);
                setItemOnClickEvent(holder, false);
                return holder;
            case TYPE_ONE:
                view = getView(parent, R.layout.item_one);
                holder = new OneViewHolder(view);
                setItemOnClickEvent(holder, false);
                return holder;
            case TYPE_TWO:
                view = getView(parent, R.layout.item_two);
                holder = new TwoViewHolder(view);
                setItemOnClickEvent(holder, false);
                return holder;
            case TYPE_THREE:
                view = getView(parent, R.layout.item_three);
                holder = new ThreeViewHolder(view);
                setItemOnClickEvent(holder, true);
                return holder;
            default:
                throw new RuntimeException("there is no type that matches the type " +
                        viewType + " + make sure your using types correctly");
        }
    }


    private void setItemOnClickEvent(final RecyclerView.ViewHolder holder, final boolean isPhoto) {
        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(v, holder.getLayoutPosition(), isPhoto);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_HEADER:
            case TYPE_ONE:
            case TYPE_TWO:
            case TYPE_THREE:
                ((BaseViewHolder) holder).bindViewHolder(getItem(position));
                break;
        }
        setItemAppearAnimation(holder, position, R.anim.anim_bottom_in);
    }


    /**
     * 判断动画是否进行中
     */
    private boolean isShowingAnimation(RecyclerView.ViewHolder holder) {
        return holder.itemView.getAnimation() != null && holder.itemView
                .getAnimation().hasStarted();
    }


    @Override
    public void onViewAttachedToWindow(final RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder instanceof HeaderViewHolder) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (currentItem < 3) {
                        ((HeaderViewHolder) holder).viewPager.setCurrentItem(++currentItem);
                    } else {
                        currentItem = 0;
                        ((HeaderViewHolder) holder).viewPager.setCurrentItem(currentItem);
                    }
                    handler.postDelayed(this, 4000);
                }
            }, 4000);
        }
    }

    /**
     * 移除动画
     */
    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (isShowingAnimation(holder)) {
            holder.itemView.clearAnimation();
        }
        if (holder instanceof HeaderViewHolder) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
