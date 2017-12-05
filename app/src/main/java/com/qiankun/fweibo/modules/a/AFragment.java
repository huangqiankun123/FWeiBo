package com.qiankun.fweibo.modules.a;

import com.qiankun.fweibo.R;
import com.qiankun.fweibo.base.BaseFragment;

/**
 * Created by QKun on 2017/12/5.
 */

public class AFragment extends BaseFragment {

    public static AFragment newInstance(){
        AFragment aFragment =new AFragment();
        return aFragment;
    }

    @Override
    protected int layoutResId() {
        return R.layout.fragment_a;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
