package com.android.loter.ui.fragment.fifth.child;

import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.adapter.CommonAdapter;
import com.android.loter.ui.adapter.base.ViewHolder;
import com.android.loter.ui.adapter.wrapper.HeaderAndFooterWrapper;
import com.android.loter.ui.adapter.wrapper.LoadMoreWrapper;
import com.android.loter.ui.base.BaseBackFragment;
import com.android.loter.ui.base.BaseFragment;
import com.android.loter.ui.widget.MyPtrClassicFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by we-win on 2017/3/22.
 */

public class FollowSellerFragment extends BaseBackFragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_empty)
    TextView mTvEmpty;
    @BindView(R.id.ptr_layout)
    MyPtrClassicFrameLayout mPtrLayout;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    private LinearLayoutManager mLinearLayoutManager;
    private List<String> mStringList;
    private CommonAdapter mCommonAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;

    public static BaseFragment newInstance() {
        FollowSellerFragment followSellerFragment = new FollowSellerFragment();
        return followSellerFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_follow_seller;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("我关注的商家");
        mStringList = new ArrayList<>();
        loadData();
    }

    @Override
    protected void bindEvent() {
        mPtrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (mLinearLayoutManager != null) {
                    boolean result = false;
                    if (mLinearLayoutManager.findFirstVisibleItemPosition() == 0) {
                        final View topChildView = mRecyclerView.getChildAt(0);
                        result = topChildView.getTop() == 0;
                    }
                    return result && PtrDefaultHandler
                            .checkContentCanBePulledDown(frame, content, header);
                } else {
                    return PtrDefaultHandler
                            .checkContentCanBePulledDown(frame, content, header);
                }
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (mPtrLayout.isShown()) {
                    mPtrLayout.refreshComplete();
                }
            }

        });
        //显示时间
        mPtrLayout.setLastUpdateTimeRelateObject(this);
        //viewpager滑动时禁用下拉
        mPtrLayout.disableWhenHorizontalMove(true);
    }

    private void loadData() {
        mStringList.clear();
        for (int i = 0; i < 10; i++) {
            mStringList.add("1");
        }
        if (mStringList != null && mStringList.size() > 0) {
            showData();
            mTvEmpty.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mTvEmpty.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
            if (mPtrLayout.isShown()) {
                mPtrLayout.refreshComplete();
            }
        }
    }

    private void showData() {
        if (mCommonAdapter == null) {
            mLinearLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mCommonAdapter = new CommonAdapter<String>(getActivity(), R.layout.layout_business, mStringList) {
                @Override
                protected void convert(ViewHolder holder, String s, int position) {
                    holder.setImageUrl(R.id.iv_business, "http://p0.meituan.net/movie/07b7f22e2ca1820f8b240f50ee6aa269481512.jpg");
                }
            };
            HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper(mCommonAdapter);
            headerAndFooterWrapper.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.layout_blank_line_small, mRecyclerView, false));
            mLoadMoreWrapper = new LoadMoreWrapper(headerAndFooterWrapper);
            mLoadMoreWrapper.setLoadMoreView(LayoutInflater.from(getActivity()).inflate(R.layout.footer_view_load_more, mRecyclerView, false));
            mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 3; i++) {
                                mStringList.add("1");
                            }
                            mLoadMoreWrapper.setLoadAll(false);
                            mLoadMoreWrapper.notifyDataSetChanged();
                        }
                    }, 2000);
                }
            });
            mRecyclerView.setAdapter(mLoadMoreWrapper);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            //            mRecyclerView.addItemDecoration(new DividerGridItemDecoration(_mActivity, 0));
        } else {
            mCommonAdapter.notifyDataSetChanged();
        }

        if (mPtrLayout.isShown()) {
            mPtrLayout.refreshComplete();
        }
    }

}
