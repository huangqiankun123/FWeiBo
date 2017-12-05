package com.qiankun.fweibo.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by QKun on 2017/12/4.
 */

    public abstract class BaseActivity extends RxAppCompatActivity {

    /**
     * 上次点击时间
     */
    private long lastClick = 0;

    protected BaseActivity mActivity;
    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(layoutResId());
        mBind = ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        initData(bundle);
        initView();

    }
    protected abstract @LayoutRes int layoutResId();

    protected abstract void initData(Bundle bundle);

    protected abstract void initView();




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
    }

    /**
     * 判断是否快速点击
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }


}
