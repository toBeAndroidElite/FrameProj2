package com.android.loter.ui.fragment.fifth.child;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.adapter.MyOrderFragmentPagerAdapter;
import com.android.loter.ui.base.BaseBackFragment;
import com.android.loter.ui.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by we-win on 2017/3/21.
 */

public class MyOrderFragment extends BaseBackFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.vp_task)
    ViewPager mVpTask;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public static BaseFragment newInstance() {
        MyOrderFragment myOrderFragment = new MyOrderFragment();
        return myOrderFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_my_order;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("我的订单");
        MyOrderFragmentPagerAdapter myOrderFragmentPagerAdapter = new MyOrderFragmentPagerAdapter(getChildFragmentManager());
        mVpTask.setAdapter(myOrderFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mVpTask);

        //防止频繁的销毁视图
        mVpTask.setOffscreenPageLimit(4);
    }

    @Override
    protected void bindEvent() {

    }

}
