package com.qiankun.fweibo.modules.a;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.qiankun.fweibo.R;
import com.qiankun.fweibo.base.BaseFragment;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by QKun on 2017/12/5.
 * 参考：https://github.com/crazycodeboy/TakePhoto
 * 跳转Activity 浏览图片 https://github.com/chrisbanes/PhotoView
 */

public class AFragment extends BaseFragment implements TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.zhaoxiang)
    Button mZhaoxiang;
    @BindView(R.id.xiangce)
    Button mXiangce;
    @BindView(R.id.iv)
    ImageView iv;

    @BindView(R.id.reycyer_view)
    RecyclerView mReycyerView;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    public final static int mMessageFlag = 0x1010;
    private ArrayList<TImage> mTImages = new ArrayList<>();
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case mMessageFlag:
                    //images 集合
                    mCameraAdapter.getData().addAll((ArrayList<TImage>)msg.obj);
                    mCameraAdapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private CameraAdapter mCameraAdapter;

    public static AFragment newInstance() {
        AFragment aFragment = new AFragment();
        return aFragment;
    }

    @Override
    protected int layoutResId() {
        return R.layout.fragment_a;
    }

    @Override
    protected void initView() {
        mReycyerView.setLayoutManager(new LinearLayoutManager(mContext));
        mCameraAdapter = new CameraAdapter(R.layout.camer_item, mTImages);
        mReycyerView.setAdapter(mCameraAdapter);
    }

    @OnClick({R.id.zhaoxiang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhaoxiang:
                showTakePhotoDialog();
                break;
        }
    }

    private void showTakePhotoDialog() {
        final String items[] = {"拍照", "相册"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("选择头像");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                takeOrPickPhoto(which == 0 ? true : false);
            }
        });
        builder.create().show();
    }

    private void takeOrPickPhoto(boolean isTakePhoto) {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        TakePhoto takePhoto = getTakePhoto();

        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        if (isTakePhoto) {//拍照
            if (true) {//是否裁剪
                takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
            } else {
                takePhoto.onPickFromCapture(imageUri);
            }
        } else {//选取图片
            int limit = 10;//选择图片的个数。
            if (limit > 1) {
                //当选择图片大于1个时是否裁剪
                if (true) {
                    takePhoto.onPickMultipleWithCrop(limit, getCropOptions());
                } else {
                    takePhoto.onPickMultiple(limit);
                }
                return;
            }
            //是否从文件中选取图片
            if (false) {
                if (true) {//是否裁剪
                    takePhoto.onPickFromDocumentsWithCrop(imageUri, getCropOptions());
                } else {
                    takePhoto.onPickFromDocuments();
                }
                return;
            } else {
                if (true) {//是否裁剪
                    takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                } else {
                    takePhoto.onPickFromGallery();
                }
            }
        }
    }

    /**
     * 拍照相关的配置
     *
     * @param takePhoto
     */
    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        //是否使用Takephoto自带的相册
        if (false) {
            builder.setWithOwnGallery(true);
        }
        //纠正拍照的旋转角度
        if (true) {
            builder.setCorrectImage(true);
        }
        takePhoto.setTakePhotoOptions(builder.create());
    }

    /**
     * 配置 压缩选项
     *
     * @param takePhoto
     */
    private void configCompress(TakePhoto takePhoto) {
        int maxSize = 102400;
        int width = 800;
        int height = 800;
        //是否显示 压缩进度条
        boolean showProgressBar = true;
        //压缩后是否保存原图
        boolean enableRawFile = true;
        CompressConfig config;
        if (false) {
            //使用自带的压缩工具
            config = new CompressConfig.Builder()
                    .setMaxSize(maxSize)
                    .setMaxPixel(width >= height ? width : height)
                    .enableReserveRaw(enableRawFile)
                    .create();
        } else {
            //使用开源的鲁班压缩工具
            LubanOptions option = new LubanOptions.Builder()
                    .setMaxHeight(height)
                    .setMaxWidth(width)
                    .setMaxSize(maxSize)
                    .create();
            config = CompressConfig.ofLuban(option);
            config.enableReserveRaw(enableRawFile);
        }
        takePhoto.onEnableCompress(config, showProgressBar);
    }

    /**
     * 配置 裁剪选项
     *
     * @return
     */
    private CropOptions getCropOptions() {
        int height = 100;
        int width = 100;

        CropOptions.Builder builder = new CropOptions.Builder();

        //按照宽高比例裁剪
        builder.setAspectX(width).setAspectY(height);
        //是否使用Takephoto自带的裁剪工具
        builder.setWithOwnCrop(false);
        return builder.create();
    }

    @Override
    protected void initData() {
//        ApiManager.getApiManager().getWechatApi()
//                .getWechat("115ffcbdaa52e18ed750e644d757af15", 1, 20)
//                .compose(this.<BaseResponse<WechatBean>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<WechatBean>(mContext, true) {
//                    @Override
//                    protected void onSuccess(WechatBean wechatBean) {
//                        LogUtils.i(wechatBean.getTotalPage() + "");
//                    }
//                });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /***********************************InvokeListener 权限开始**********/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(mContext, type, invokeParam, this);
    }

    /***********************************InvokeListener 权限结束**********/

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(mContext), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }


    /***********************************TakePhoto.TakeResultListener**********/
    @Override
    public void takeSuccess(TResult result) {
        LogUtils.i("takeSuccess：" + result.getImage().getCompressPath());
        File file = new File(result.getImages().get(0).getCompressPath());
//        String absolutePath = file.getAbsolutePath();
        ArrayList<TImage> images = result.getImages();
        Message message = mHandler.obtainMessage();
        message.what = mMessageFlag;
        message.obj = images;
        mHandler.sendMessage(message);


    }

    @Override
    public void takeFail(TResult result, String msg) {
        LogUtils.i("takeFail:" + msg);
    }

    @Override
    public void takeCancel() {
    }


    /***********************************TakePhoto.TakeResultListener****************************************/
}
