package com.android.loter.ui.fragment.fifth.child;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.loter.R;
import com.android.loter.ui.base.BaseBackFragment;
import com.android.loter.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by we-win on 2017/3/21.
 */

public class WithdrawCashFragment extends BaseBackFragment {

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public static BaseFragment newInstance() {
        WithdrawCashFragment withdrawCashFragment = new WithdrawCashFragment();
        return withdrawCashFragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_withdraw_cash;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("提现");
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
