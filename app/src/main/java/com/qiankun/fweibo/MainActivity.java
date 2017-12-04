package com.qiankun.fweibo;

import android.os.Bundle;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.qiankun.fweibo.base.BaseActivity;
import com.qiankun.fweibo.core.ApiManager;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {


    @BindView(R.id.tbv)
    TextView mTbv;

    @Override
    protected void initView() {
        mTbv.setText("hello world");
    }

    @Override
    protected void initData(Bundle bundle) {
        ApiManager.getApiManager().getZhiHuService().getLatestZhihuDaily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhiHuDailyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZhiHuDailyBean zhiHuDailyBean) {
                        LogUtils.d(zhiHuDailyBean.getDate());
                        ToastUtils.showLong(zhiHuDailyBean.getDate());
                        mTbv.setText(zhiHuDailyBean.getDate());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected int layoutResId() {
        return R.layout.activity_main;
    }


}
