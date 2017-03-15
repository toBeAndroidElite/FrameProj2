package com.android.loter.ui.fragment.third;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.loter.R;
import com.android.loter.ui.base.BaseMainFragment;
import com.android.loter.ui.fragment.LiveFragment;

/**
 * Created by we-win on 2017/3/14.
 */

public class LoterThirdFragment extends BaseMainFragment {

    public static LoterThirdFragment newInstance() {

        Bundle args = new Bundle();
        LoterThirdFragment fragment = new LoterThirdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int bindLayout() {
        return R.layout.loter_fragment_first;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_first_container, LiveFragment.newInstance());
        }
    }

}
