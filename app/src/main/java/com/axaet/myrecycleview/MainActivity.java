package com.axaet.myrecycleview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

import com.axaet.myrecycleview.adapter.NewsListHeaderAdapter;
import com.axaet.myrecycleview.beans.DataModel;
import com.axaet.myrecycleview.util.AppUtil;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, NewsListHeaderAdapter.OnNewsListItemClickListener {

    private ArrayList<DataModel> models = new ArrayList<>();
    private NewsListHeaderAdapter newsListAdapter;
    private boolean mIsAllLoaded;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View hintView;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x01) {
                newsListAdapter.hideFooter();
                isNetworkAvailable();
                mIsAllLoaded = false;
            } else if (msg.what == 0x02) {
                newsListAdapter.clear();
                isNetworkAvailable();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSwipeRefresh();
        initRecyclerView();
        isNetworkAvailable();
    }

    private void isNetworkAvailable() {
        if (AppUtil.isNetworkAvailable(getApplicationContext())) {
            //有网络隐藏并加载数据
            if (hintView != null) {
                hintView.setVisibility(View.GONE);
            }
            initData();
        } else {
            //无网络显示失败
            if (hintView == null) {
                ViewStub viewStub = (ViewStub) findViewById(R.id.view_stub);
                hintView = viewStub.inflate();
                TextView textLoadError = (TextView) hintView.findViewById(R.id.text_load_error);
                textLoadError.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        swipeRefreshLayout.setRefreshing(true);
                        isNetworkAvailable();
                    }
                });
            }
            hintView.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(this, "没有网络", Toast.LENGTH_LONG).show();
        }
    }


    private void initSwipeRefresh() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.gplus_colors));
    }


    /**
     * 初始化recycleView
     */
    private void initRecyclerView() {
        final RecyclerView recycleView = (RecyclerView) findViewById(R.id.recycle_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = recycleView.getAdapter().getItemViewType(position);
                if (type == DataModel.TYPE_TWO) {
                    return 1;
                }
                return layoutManager.getSpanCount();
            }
        });
        recycleView.setLayoutManager(layoutManager);
//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
//        recycleView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));//item间隔
        newsListAdapter = new NewsListHeaderAdapter(this);
        newsListAdapter.setItemClickListener(this);
        recycleView.setAdapter(newsListAdapter);
        //下拉加载更多
        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();

                if (!mIsAllLoaded && visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition >= totalItemCount - 1) {
                    mIsAllLoaded = true;
                    handler.sendEmptyMessageDelayed(0x01, 3000);//模拟加载数据
                    newsListAdapter.showFooter();
                    recyclerView.scrollToPosition(newsListAdapter.getItemCount() - 1);
                }
            }
        });
    }


    private void initData() {
        models.clear();
        for (int i = 0; i < 10; i++) {
            DataModel dataModel = new DataModel();
            dataModel.title = i + ". 这是标题--认错时人的大脑在想什么？认错时人的大脑在想什么？认错时人的大脑在想什么？";
            dataModel.time = new Date().toString();
            dataModel.url1 = "http://c.hiphotos.bdimg.com/album/w%3D2048/sign=10a72dd37af40ad115e4c0e3631413df/f7246b600c338744f1243597500fd9f9d62aa073.jpg";
            dataModel.url2 = "http://img3.fengniao.com/travel/2_960/1850.jpg";
            dataModel.url3 = "http://pic35.nipic.com/20131121/2531170_145358633000_2.jpg";
//            if (i % 3 == 0) {
//                dataModel.type = DataModel.TYPE_ONE;
//            } else if (i % 3 == 1) {
//                dataModel.type = DataModel.TYPE_TWO;
//            } else if (i % 3 == 2) {
//                dataModel.type = DataModel.TYPE_THREE;
//            }
            if (i < 3) {
                dataModel.type = DataModel.TYPE_ONE;
            } else if (i < 7) {
                dataModel.title = i + ". 认错时人的大脑在想什么？";
                dataModel.type = DataModel.TYPE_TWO;
            } else if (i < 10) {
                dataModel.type = DataModel.TYPE_THREE;
            }
            if (i == 0) {
                dataModel.list.add("http://dl.bizhi.sogou.com/images/2012/03/14/140763.jpg");
                dataModel.list.add("http://pic23.nipic.com/20120831/10705080_085930351178_2.jpg");
                dataModel.list.add("http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg");
                dataModel.list.add("http://pic.nipic.com/2007-12-30/2007123082518248_2.jpg");
            }
            models.add(dataModel);
        }
        newsListAdapter.addMore(models);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(0x02, 3000);
    }

    @Override
    public void onItemClick(View view, int position, boolean isPhoto) {
        Toast.makeText(this, isPhoto + "position=" + position, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onPause() {
        super.onPause();
        newsListAdapter.stopViewpagerBanner();
    }
}
