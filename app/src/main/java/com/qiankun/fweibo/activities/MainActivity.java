package com.qiankun.fweibo.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blankj.utilcode.util.BarUtils;
import com.qiankun.fweibo.R;
import com.qiankun.fweibo.base.BaseActivity;
import com.qiankun.fweibo.modules.a.AFragment;
import com.qiankun.fweibo.modules.b.BFragment;
import com.qiankun.fweibo.modules.c.CFragment;
import com.qiankun.fweibo.modules.d.DFragment;
import com.qiankun.fweibo.modules.e.EFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.bottom_navigation)
    BottomNavigationBar mBottomNavigation;

    private AFragment mAFragment;
    private BFragment mBFragment;
    private CFragment mCFragment;
    private DFragment mDFragment;
    private EFragment mEFragment;
    private Fragment mCurrentFrag; //当前的fragment
    private FragmentManager mFragmentManager;

    @Override
    protected int layoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //处理状态栏
        BarUtils.setStatusBarAlpha(this);
        //处理Fragment
        mFragmentManager = getSupportFragmentManager();
        mAFragment = AFragment.newInstance();
        mBFragment = BFragment.newInstance();
        mCFragment = CFragment.newInstance();
        mDFragment = DFragment.newInstance();
        mEFragment = EFragment.newInstance();

        initBottomNavigation();
        switchContent(mAFragment);
    }


    private void initBottomNavigation() {
        mBottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .addItem(new BottomNavigationItem(R.mipmap.ic_home, "a").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_view_headline, "b").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.ic_live_tv, "c").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.icon_find, "d").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.icon_wechat, "e").setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .setTabSelectedListener(this)
                .initialise();
    }


    private void switchContent(Fragment to) {
        if (mCurrentFrag != to) {
            if (!to.isAdded()) {
                if (mCurrentFrag != null) {
                    mFragmentManager.beginTransaction().hide(mCurrentFrag).commit();
                }
                mFragmentManager.beginTransaction().add(R.id.fl_content,to).commit();
            } else {
                mFragmentManager.beginTransaction().hide(mCurrentFrag).show(to).commit();
            }
            mCurrentFrag = to;
        }
    }

    @Override
    protected void initData(Bundle bundle) {

    }


    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                switchContent(mAFragment);
                break;
            case 1:
                switchContent(mBFragment);
                break;
            case 2:
                switchContent(mCFragment);
                break;
            case 3:
                switchContent(mDFragment);
                break;
            case 4:
                switchContent(mEFragment);
                break;

        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
