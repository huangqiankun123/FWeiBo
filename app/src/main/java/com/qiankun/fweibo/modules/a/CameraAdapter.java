package com.qiankun.fweibo.modules.a;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jph.takephoto.model.TImage;
import com.qiankun.fweibo.R;

import java.io.File;
import java.util.List;

/**
 * Created by QKun on 2017/12/7.
 */

public class CameraAdapter extends BaseQuickAdapter<TImage,BaseViewHolder> {
    public CameraAdapter(int layoutResId, List<TImage> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TImage item) {

        Glide.with(mContext).load(new File(item.getCompressPath())).into((ImageView) helper.getView(R.id.iv_icon));
    }
}
