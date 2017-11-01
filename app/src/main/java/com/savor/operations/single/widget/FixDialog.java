package com.savor.operations.single.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.api.utils.FileUtils;
import com.common.api.utils.ShowMessage;
import com.savor.operations.single.R;
import com.savor.operations.single.SavorApplication;
import com.savor.operations.single.bean.DamageConfig;
import com.savor.operations.single.bean.Hotel;
import com.savor.operations.single.bean.PositionListInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 维修对话框
 * Created by hezd on 2017/3/9.
 */

public class FixDialog extends Dialog implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    public static final int REQUEST_CODE_IMAGE = 100;
    private final OperationType type;
    private final PositionListInfo.PositionInfo.BoxInfoBean boxInfo;
    private Hotel mHotel;
    private DamageConfig mDamageConfig;
    private OnSubmitBtnClickListener mOnSubmitListener;
    private RadioGroup mResovleRg;
    private EditText mCommentEt;
    private RelativeLayout mDamageLayout;

    private Activity mContext;
    private TextView mCancelBtn;
    private TextView mSubmitBtn;
    private List<String> selectedDamages = new ArrayList<>();
    private FixState currentFixSate = FixState.UNSELECTED;
    private AlertDialog alertDialog;
    private TextView mSelectDescTv;
    private Button mUploadBtn;
    private ImageView mImageIv;
    private String copyPath;
    private ProgressBar loadingPb;

    public void startLoading() {
        loadingPb.setVisibility(View.VISIBLE);
    }

    public void loadFinish() {
        loadingPb.setVisibility(View.GONE);
    }

    public enum FixState {
        /**为选择*/
        UNSELECTED,
        /**已解决*/
        RESOLVED,
        /**未解决*/
        UNRESOLVED,
    }

    public enum OperationType {
        /**小平台*/
        TYPE_SMALL,
        /**机顶盒*/
        TYPE_BOX,
    }

    public FixDialog(@NonNull Activity context, OnSubmitBtnClickListener listener, OperationType type, PositionListInfo.PositionInfo.BoxInfoBean fixHistoryResponse, DamageConfig damageConfig, Hotel hotel) {
        super(context);
        this.mContext = context;
        this.mOnSubmitListener = listener;
        this.mDamageConfig = damageConfig;
        this.mHotel = hotel;
        this.type = type;
        this.boxInfo = fixHistoryResponse;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_fix);

        getViews();
        setViews();
        setListeners();
    }

    private void getViews() {
        mResovleRg = findViewById(R.id.rg_resovle);
        mCommentEt = findViewById(R.id.et_desc);
        mDamageLayout = findViewById(R.id.rl_damage_layout);
        mCancelBtn =  findViewById(R.id.tv_cancel);
        mSubmitBtn =  findViewById(R.id.tv_submit);
        mSelectDescTv = findViewById(R.id.tv_select_desc);
        mUploadBtn = findViewById(R.id.btn_upload);
        mImageIv = findViewById(R.id.iv_image);
        loadingPb = findViewById(R.id.pb_loading);
    }

    private void setViews() {

    }

    private void setListeners() {
        mDamageLayout.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
        mSubmitBtn.setOnClickListener(this);
        mResovleRg.setOnCheckedChangeListener(this);
        mUploadBtn.setOnClickListener(this);
    }

    public void updateImage(String url) {
        Glide.with(mContext).load(url).centerCrop().into(mImageIv);

        String cachePath = ((SavorApplication)mContext.getApplication()).imagePath;
        File dir = new File(cachePath);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        String box_id = boxInfo.getBid();
        long timeMillis = System.currentTimeMillis();
        String key = box_id+"_"+timeMillis+".jpg";
        copyPath = dir.getAbsolutePath()+File.separator+key;

        File sFile = new File(url);
        FileUtils.copyFile(sFile, copyPath);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_upload:
                clearCacheImage();
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                mContext.startActivityForResult(intent, REQUEST_CODE_IMAGE);
                break;
            case R.id.tv_cancel:
                clearCacheImage();
                dismiss();
                break;
            case R.id.tv_submit:
                if(mOnSubmitListener!=null) {
                    String comment = mCommentEt.getText().toString();
                    if(currentFixSate == FixDialog.FixState.UNSELECTED) {
                        ShowMessage.showToast(mContext,"请选择修复状态已解决或未解决");
                        return;
                    }
                    if(TextUtils.isEmpty(comment)&&selectedDamages.size()==0) {
                        ShowMessage.showToast(mContext,"请选择维修记录或填写备注");
                        return;
                    }
                    if(TextUtils.isEmpty(copyPath)) {
                        ShowMessage.showToast(mContext,"请选择拍照图片");
                        return;
                    }
                    mOnSubmitListener.onSubmitClick(type, boxInfo,currentFixSate,selectedDamages,comment,mHotel,copyPath);
                }
//                dismiss();
                break;
            case R.id.rl_damage_layout:
                if(alertDialog == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    List<DamageConfig.DamageInfo> list = mDamageConfig.getList();
                    final String[] sex = new String[mDamageConfig.getList().size()];
                    final String[] ids  = new String[mDamageConfig.getList().size()];
                    for(int i =0;i<list.size();i++) {
                        sex[i] = list.get(i).getReason();
                        ids[i] = list.get(i).getId()+"";
                    }
                    //    设置一个单项选择下拉框
                    /**
                     * 第一个参数指定我们要显示的一组下拉单选框的数据集合
                     * 第二个参数代表索引，指定默认哪一个单选框被勾选上，1表示默认'女' 会被勾选上
                     * 第三个参数给每一个单选项绑定一个监听器
                     */
                    builder.setMultiChoiceItems(sex, null, new DialogInterface.OnMultiChoiceClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            String currentDamage = ids[which];
                            if(isChecked) {
                                if(!selectedDamages.contains(currentDamage)) {
                                    selectedDamages.add(currentDamage);
                                }
                            }else {
                                if(selectedDamages.contains(currentDamage)) {
                                    selectedDamages.remove(currentDamage);
                                }
                            }
                        }
                    });
                    builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(selectedDamages==null||selectedDamages.size()==0) {
                                mSelectDescTv.setText("故障说明与维修记录");
                            }else {
                                mSelectDescTv.setText("已选择"+selectedDamages.size()+"项");
                            }
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog = builder.create();
                }
                alertDialog.show();

        }
    }

    private void clearCacheImage() {
        if(!TextUtils.isEmpty(copyPath)) {
            new File(copyPath).delete();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_resolved:
                currentFixSate = FixState.RESOLVED;
                break;
            case R.id.rb_unresolved:
                currentFixSate = FixState.UNRESOLVED;
                break;
        }
    }

    public interface OnSubmitBtnClickListener {
        /**
         * 提交
         * @param isResolve 是否已解决
         * @param damageDesc 故障原因
         * @param comment 评论
         */
        void onSubmitClick(OperationType type, PositionListInfo.PositionInfo.BoxInfoBean boxInfoBean, FixState isResolve, List<String> damageDesc, String comment, Hotel hotel,String imagePath);
    }
}
