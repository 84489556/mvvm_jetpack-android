package com.reactlibrary.view.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.netease.neliveplayer.playerkit.sdk.constant.CauseCode;
import com.reactlibrary.utils.AnimationUtil;
import com.reactlibrary.utils.CommonUtil;
import com.reactlibrary.utils.ImageLoaders;
import com.yd.video.R;

public abstract class VideoBaseView extends RelativeLayout implements View.OnTouchListener,SeekBar.OnSeekBarChangeListener{
    private static final String TAG = VideoBaseView.class.getSimpleName();

    public Context mContext;
    //手指放下的位置
    protected int mDownPosition;

    //手势调节音量的大小
    protected int mGestureDownVolume;

    //手势偏差值
    protected int mThreshold = 80;

    //手动改变滑动的位置
    protected int mSeekTimePosition;

    //手动滑动的起始偏移位置
    protected int mSeekEndOffset;

    //退出全屏显示的案件图片
    protected int mShrinkImageRes = -1;

    //全屏显示的案件图片
    protected int mEnlargeImageRes = -1;

    //触摸显示后隐藏的时间
    protected int mDismissControlTime = 2500;

    //触摸的X
    protected float mDownX;

    //触摸的Y
    protected float mDownY;

    //移动的Y
    protected float mMoveY;

    //亮度
    protected float mBrightnessData = -1;

    //触摸滑动进度的比例系数
    protected float mSeekRatio = 1;

    //触摸的是否进度条
    protected boolean mTouchingProgressBar = false;

    //是否改变音量
    protected boolean mChangeVolume = false;

    //是否改变播放进度
    protected boolean mChangePosition = false;

    //触摸显示虚拟按键
    protected boolean mShowVKey = false;

    //是否改变亮度
    protected boolean mBrightness = false;

    //是否首次触摸
    protected boolean mFirstTouch = false;

    //是否隐藏虚拟按键
    protected boolean mHideKey = true;

    //是否需要显示流量提示
    protected boolean mNeedShowWifiTip = true;

    //是否支持非全屏滑动触摸有效
    protected boolean mIsTouchWiget = true;

    //是否支持全屏滑动触摸有效
    protected boolean mIsTouchWigetFull = true;

    //是否点击封面播放
    protected boolean mThumbPlay;

    //锁定屏幕点击
    protected boolean mLockCurScreen;

    //是否需要锁定屏幕
    protected boolean mNeedLockFull;

    //lazy的setup
    protected boolean mSetUpLazy = false;

    //seek touch
    protected boolean mHadSeekTouch = false;

    protected boolean mPostProgress = false;
    protected boolean mPostDismiss = false;
    protected boolean isShowDragProgressTextOnSeekBar = false;


    //屏幕宽度
    protected int mScreenWidth;

    //屏幕高度
    protected int mScreenHeight;
    //View宽度
    protected int mViewWidth;

    //View高度
    protected int mViewHeight;
    //当前是否全屏
    protected boolean mIfCurrentIsFullscreen = false;
    //当前的播放状态
    protected int mCurrentState = -1;
    //正常
    public static final int CURRENT_STATE_NORMAL = 0;
    //准备中
    public static final int CURRENT_STATE_PREPAREING = 1;
    //播放中
    public static final int CURRENT_STATE_PLAYING = 2;
    //开始缓冲
    public static final int CURRENT_STATE_PLAYING_BUFFERING_START = 3;
    //暂停
    public static final int CURRENT_STATE_PAUSE = 5;
    //自动播放结束
    public static final int CURRENT_STATE_AUTO_COMPLETE = 6;
    //错误状态
    public static final int CURRENT_STATE_ERROR = 7;
    //当前的播放位置
    protected int mCurrentPosition;
    //音频焦点的监听
    protected AudioManager mAudioManager;

    //亮度dialog
    protected Dialog mBrightnessDialog;

    //音量dialog
    protected Dialog mVolumeDialog;

    //触摸进度dialog
//    protected Dialog mProgressDialog;

    //触摸进度条的progress
    protected ProgressBar mDialogProgressBar;

    //音量进度条的progress
    protected ProgressBar mDialogVolumeProgressBar;

    //亮度文本
    protected ProgressBar mBrightnessDialogBar;

    //触摸移动显示文本
    protected TextView mDialogSeekTime;

    //触摸移动显示全部时间
    protected TextView mDialogTotalTime;

    //触摸移动方向icon
    protected ImageView mDialogIcon;

    protected Drawable mBottomProgressDrawable;

    protected Drawable mBottomShowProgressDrawable;

    protected Drawable mBottomShowProgressThumbDrawable;

    protected Drawable mVolumeProgressDrawable;

    protected Drawable mDialogProgressBarDrawable;

    protected int mDialogProgressHighLightColor = -11;

    protected int mDialogProgressNormalColor = -11;

    protected float mSpeed = 1.0f;

    private SeekBar mProgressBar;
    private TextView mCurrentTimeTextView;
    private TextView mTotalTimeTextView;
    private ImageView mPauseScreenplay;
    private ImageView fullScreen;
    private ImageView backImageView;
    private TextView videoTitle;
    private TextView aboutVideo;
    public RelativeLayout surfaceContainer;
    LinearLayout topLay;
    View bottomLay;
    View bottomLayO;
    View bottomLayL;
    View videoQuickProLay;
//    private MediaInfo mediaInfo;
    private int VIEDOPAUSEPALY = 0;
    String orientation = "PORTRAIT";
    long clickTime=0;
    int delayTime = 3*1000;
    public boolean isShowBack = false;
    public int videoDuration;
    private ImageView loadingImg;
    private AnimationDrawable animaition;
    private AlphaAnimation alphaAniHide;
    private View loadingLay;
    private View netMobile;
    private View netNo;
    public int networkType = -2;//网络状态
    public int netType = 0;//网络状态
    public int errorReply = 0;//加载失败重试次数
    private ImageView vodCoverImg;
    private View netPlay;
    private TextView errPlay;
    private TextView vodErrTip;
    //底部进度调
    protected ProgressBar mBottomProgressBar;
    protected ImageView mLockScreen;
    protected TextView mVideoSpeed;
    protected View mVideoSpeedLay;
    protected TextView mVideoSpeed20;
    protected TextView mVideoSpeed15;
    protected TextView mVideoSpeed125;
    protected TextView mVideoSpeed10;
    protected TextView mVideoSpeed05;
    protected TextView videoSpeedTip;
    protected ImageView videoBackNet;
    protected ImageView videoBackNoNet;

    private String mCoverImgUrl = "";

    View rootView;

    public interface MSG {
        int MSG_ON_INIT = 1;
        int MSG_ON_COMPLETION = 2;
        int MSG_ON_SEEK = 3;
        int MSG_ON_ERROR = 4;
        int MSG_ON_PAUSE = 5;
        int MSG_ON_RESUME = 6;

        int MSG_ON_HID = 11;
        int MSG_ON_SHOW = 12;

        int VIEW_SHOW_LOADING = 13;
        int VIEW_CLOSE_LOADING = 14;
        int VIEW_NET_UPDATE = 15;
        int VIEW_NO_NET_CLICK = 16;
        int VIEW_NO_PLAYER_START = 17;
        int VIEW_HID_COVER_IMG = 18;
        int VIEW_SHOW_COVER_IMG = 19;
        int VIEW_SPEED_HID = 20;
    }

    public VideoBaseView(Context context) {
        super(context);
        this.mContext = context;
        initViews();
        initS();
    }

    public VideoBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initViews();
    }

    public VideoBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initViews();
    }

    public VideoBaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        initViews();
    }

    protected void initS(){
        if (isInEditMode())
            return;
        mScreenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = mContext.getResources().getDisplayMetrics().heightPixels;
        mAudioManager = (AudioManager) mContext.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
    }
    public void initViews() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.view_video_common_lay,null);
        rootView =v;
        videoTitle = v.findViewById(R.id.video_title);
        backImageView = v.findViewById(R.id.video_back);
        backImageView.setOnClickListener(myOnclick);
        videoBackNet = v.findViewById(R.id.video_back_net);
        videoBackNet.setOnClickListener(myOnclick);
        videoBackNoNet = v.findViewById(R.id.video_back_no_net);
        videoBackNoNet.setOnClickListener(myOnclick);
        topLay = v.findViewById(R.id.video_top_lay);

        fullScreen = (ImageView) v.findViewById(R.id.fullScreen);
        fullScreen.setOnClickListener(myOnclick);
        aboutVideo = v.findViewById(R.id.aboutVideo);
        aboutVideo.setOnClickListener(myOnclick);
        mCurrentTimeTextView = (TextView) v.findViewById(R.id.palyingTime);
        mTotalTimeTextView = (TextView) v.findViewById(R.id.playAllTime);
        mPauseScreenplay = (ImageView) v.findViewById(R.id.pauseResumeImg);
        mPauseScreenplay.setOnClickListener(myOnclick);
        mProgressBar = (SeekBar) v.findViewById(R.id.videoSeekBar);
        mProgressBar.setOnSeekBarChangeListener(this);

        bottomLay = v.findViewById(R.id.video_bottom_lay);
        bottomLayO = v.findViewById(R.id.video_bottom_o_lay);
        bottomLayL = v.findViewById(R.id.video_bottom_l_lay);
        videoQuickProLay = v.findViewById(R.id.video_quick_pro_lay);


        vodCoverImg = v.findViewById(R.id.vod_cover_img);
        loadingLay = v.findViewById(R.id.vod_loading_lay);
        loadingImg = v.findViewById(R.id.vod_loading_img);
        netMobile = v.findViewById(R.id.vod_net_mobile);
        netPlay = v.findViewById(R.id.vod_net_play);
        netPlay.setOnClickListener(myOnclick);
        netNo = v.findViewById(R.id.vod_net_no);
        errPlay = v.findViewById(R.id.vod_err_play);
        vodErrTip = v.findViewById(R.id.vod_err_tip);
        errPlay.setOnClickListener(myOnclick);


        mLockScreen = (ImageView) v.findViewById(R.id.lock_screen);
        mLockScreen.setOnClickListener(myOnclick);
        mBottomProgressBar = (ProgressBar) v.findViewById(R.id.bottom_progressbar);
        surfaceContainer = v.findViewById(R.id.surface_container);
        initVideoView(surfaceContainer);
        if (surfaceContainer != null) {
            surfaceContainer.setOnClickListener(myOnclick);
            surfaceContainer.setOnTouchListener(this);
        }
        mVideoSpeed =  v.findViewById(R.id.video_speed);
        mVideoSpeed.setOnClickListener(myOnclick);
        mVideoSpeedLay = v.findViewById(R.id.video_speed_lay);
        mVideoSpeedLay.setOnClickListener(myOnclick);
        mVideoSpeed20 = v.findViewById(R.id.video_speed_20);
        mVideoSpeed20.setOnClickListener(myOnclick);
        mVideoSpeed15 = v.findViewById(R.id.video_speed_15);
        mVideoSpeed15.setOnClickListener(myOnclick);
        mVideoSpeed125 = v.findViewById(R.id.video_speed_125);
        mVideoSpeed125.setOnClickListener(myOnclick);
        mVideoSpeed10 = v.findViewById(R.id.video_speed_10);
        mVideoSpeed10.setOnClickListener(myOnclick);
        mVideoSpeed05 = v.findViewById(R.id.video_speed_05);
        mVideoSpeed05.setOnClickListener(myOnclick);
        videoSpeedTip = v.findViewById(R.id.video_speed_tip);

        //隐藏
        alphaAniHide = new AlphaAnimation(1, 0);
        alphaAniHide.setDuration(1000);
//        myHandler = new Handler(callback);
        mHandler.sendEmptyMessageDelayed(MSG.VIEW_SHOW_LOADING,50);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mContext.registerReceiver(netBroadcastReceiver, filter);

        addView(v);
    }
    private void initVideoView(ViewGroup root){
        try {
            if (getVideoLayout(mContext) != null)
                root.addView(getVideoLayout(mContext));

        } catch (InflateException e) {
            e.printStackTrace();
        }
    }
    boolean isSeekBarTouch =false;
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (isSeekBarTouch) {
            int pos = progress * Math.max(videoDuration,1) / 100;
            showProgressDialog(1,pos,videoDuration);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Log.e(TAG,"onStartTrackingTouch");
        isSeekBarTouch = true;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (null != iVideoCommonControl) {
            int pos = seekBar.getProgress();
            pos = pos * Math.max(videoDuration,1) / 100;
            iVideoCommonControl.iSeekTo(pos);

            isSeekBarTouch = false;
            dismissProgressDialog();
        }
    }
    @SuppressLint("HandlerLeak")
    public Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case MSG.MSG_ON_INIT:
//                    isPlayCompletion = false;
                    mSpeed = 1.0f;
                    mCurrentState = CURRENT_STATE_PLAYING;
                    if(mContext != null  && msg.obj != null && mProgressBar != null){
                        int max = 0;
                        if (msg.obj instanceof Long){
                            max = (int)(long) msg.obj;
                        }else if (msg.obj instanceof Integer){
                            max = (int) msg.obj;
                        }

                        Log.e(TAG,"MSG_ON_INIT max:" + max);
                        if (max<1){
                            return;
                        }
                        videoDuration = (int) max ;
                        mProgressBar.setProgress(0);
                        if (mBottomProgressBar != null) {
                            mBottomProgressBar.setProgress(0);
                        }
                        mTotalTimeTextView.setText(getTime(videoDuration/ 1000));
//                        videoDuration = max/ 1000;
//                        player.seekTo(lastPostion);
                        mPauseScreenplay.setImageResource(R.mipmap.pause);

//                        WritableMap event = Arguments.createMap();
//                        event.putInt("allTime",max);//key用于js中的nativeEvent
//                        dispatchEvent("sendStreamState",event);
                        //控制按钮的显示
                        backImageView.setVisibility(isShowBack ? View.VISIBLE : View.INVISIBLE);
                        startProgressTimer();
                        if (iVideoCommonControl != null){
                            iVideoCommonControl.iPrepared(videoDuration);
                        }
                        mVideoSpeed.setText("倍速");
                    }
                break;
                case MSG.MSG_ON_COMPLETION:
                    VIEDOPAUSEPALY = 1;
                    mCurrentState = CURRENT_STATE_AUTO_COMPLETE;
                    if (mProgressBar != null) {
                        mPauseScreenplay.setImageResource(R.mipmap.play);
                        mProgressBar.setProgress(100);
//                    if(reactContext != null && mediaInfo != null){
//
//                        dispatchEvent( "sendVodOverState",null);
//                        WritableMap event = Arguments.createMap();
//                        event.putInt("currentPosition", mSeekBarPlayViedo.getMax());
//                        dispatchEvent("sendCurrentPosition", event);
//
//                        isPlayCompletion = true;
//                        sensorTrackEnd();//自动播放完后 提交神策统计
//                    }
                        if (mBottomProgressBar != null) {
                            mBottomProgressBar.setProgress(100);
                        }
                        cancelProgressTimer();
                        if (iVideoCommonControl != null) {
                            iVideoCommonControl.iVodPlayFinish();
                        }
                    }
                    break;
                case MSG.MSG_ON_PAUSE:
                    mCurrentState = CURRENT_STATE_PAUSE;
                    VIEDOPAUSEPALY = 1;
                    mPauseScreenplay.setImageResource(R.mipmap.play);

                    if (animaition != null){
                        loadingLay.setVisibility(View.INVISIBLE);
                    }
                    cancelProgressTimer();
                    showCoverImg();
                    break;
                case MSG.MSG_ON_RESUME:
                    VIEDOPAUSEPALY = 0;
                    mCurrentState = CURRENT_STATE_PLAYING;
                    mPauseScreenplay.setImageResource(R.mipmap.pause);
                    startProgressTimer();
                    break;
                case MSG.MSG_ON_SEEK:
                    if(mContext != null && mProgressBar != null  ){
                        if (iVideoCommonControl != null){
                            iVideoCommonControl.updatePosition();
                        }
//                        WritableMap event = Arguments.createMap();
//                        event.putInt("currentPosition", anyPosition);
//                        dispatchEvent("sendCurrentPosition", event);
//                        lastPostion = anyPosition;
                    }
                    break;
                case MSG.MSG_ON_ERROR:
                    mCurrentState = CURRENT_STATE_ERROR;
                    cancelProgressTimer();
                    if (errorReply > 3){
                        vodErrTip.setText(getResources().getString(R.string.video_err));
                        netNo.setVisibility(VISIBLE);
                        break;
                    }
                    errorReply++;
                    int code = msg.obj instanceof Integer ? (int)msg.obj : 0;
                    if (code == CauseCode.CODE_HTTP_CONNECT_ERROR ||code == CauseCode.CODE_BUFFERING_ERROR ||
                            code == CauseCode.NELP_EN_PREPARE_TIMEOUT_ERROR){
                        if (iVideoCommonControl != null){
                            iVideoCommonControl.iStart();
                        }
                    }
                    break;
                case MSG.MSG_ON_HID:
                    bottomLay.setVisibility(View.INVISIBLE);
                    topLay.setVisibility(View.INVISIBLE);

                    topLay.setAnimation(AnimationUtil.moveLocationToViewTop());
                    bottomLay.setAnimation(AnimationUtil.moveToViewBottom());
//                    mLockScreen.setVisibility(View.INVISIBLE);
                    hidStatBar();
                    break;
                case MSG.MSG_ON_SHOW:
                    if (mLockCurScreen){
                        mLockScreen.setVisibility(View.VISIBLE);
                        return;
                    }
                    bottomLay.setVisibility(View.VISIBLE);
                    topLay.setVisibility(View.VISIBLE);

                    topLay.setAnimation(AnimationUtil.moveTopToViewLocation());
                    bottomLay.setAnimation(AnimationUtil.moveToViewLocation());
//                    mLockScreen.setVisibility(View.VISIBLE);
                    showStatBar();
                    break;

                case MSG.VIEW_SHOW_LOADING:
                    if(animaition == null){
                        loadingLay.setVisibility(View.VISIBLE);
                        try {
                            animaition = (AnimationDrawable)loadingImg.getDrawable();
                            if (animaition != null) {
                                animaition.setOneShot(false);
                                animaition.start();
                            }
                        }catch (Exception e){
                            loadingLay.setVisibility(View.VISIBLE);
                        }

                    }
                    break;
                case MSG.VIEW_CLOSE_LOADING:
                    if (loadingLay.getVisibility() == VISIBLE) {
                        try {
                            loadingLay.startAnimation(alphaAniHide);
                            alphaAniHide.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    loadingLay.setVisibility(View.INVISIBLE);
                                    if (animaition != null && animaition.isRunning()) {
                                        animaition.stop();
                                    }
                                    animaition = null;
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        }catch(Exception e){
                            loadingLay.setVisibility(View.INVISIBLE);
                            if (animaition != null && animaition.isRunning()) {
                                animaition.stop();
                            }
                            animaition = null;
                        }
                        mHandler.sendEmptyMessage(MSG.MSG_ON_SHOW);
                        mHandler.sendEmptyMessageDelayed(MSG.MSG_ON_HID,delayTime);
                        mHandler.sendEmptyMessageDelayed(MSG.VIEW_HID_COVER_IMG,1500);
                    }
                    break;
                case MSG.VIEW_NET_UPDATE:
                    int temp = (int) msg.obj;
                    if (temp == networkType){
                        break;
                    }

                    switch (temp) {
                        case -1:
                            //没有网络
                            if (iVideoCommonControl != null){
                                iVideoCommonControl.iPause();
                            }
                            vodErrTip.setText(getResources().getString(R.string.video_no_net));
                            netMobile.setVisibility(INVISIBLE);
                            netNo.setVisibility(VISIBLE);
                            cancelProgressTimer();
                            break;
                        case ConnectivityManager.TYPE_MOBILE:
                        case ConnectivityManager.TYPE_MOBILE_DUN:
                            //手机流量
                            netMobile.setVisibility(VISIBLE);
                            netNo.setVisibility(INVISIBLE);
                            loadingLay.setVisibility(INVISIBLE);
                            if (iVideoCommonControl != null){
                                iVideoCommonControl.iPause();
                            }
                            cancelProgressTimer();
//                            pause();
                            break;
                        case ConnectivityManager.TYPE_WIFI:
                            loadingLay.setVisibility(VISIBLE);
                            netMobile.setVisibility(INVISIBLE);
                            netNo.setVisibility(INVISIBLE);
//                                 loadingLay.setVisibility(INVISIBLE);
                            Log.e(TAG,"VIEW_NET_UPDATE TYPE_WIFI networkType:" + temp);
                            if (iVideoCommonControl != null){
                                iVideoCommonControl.iStart();
                            }
                            break;
                    }
                    networkType = temp;
                    break;
                case MSG.VIEW_NO_NET_CLICK:
                    switch (networkType) {
                        case -1:
                            //没有网络
                            if (iVideoCommonControl != null){
                                iVideoCommonControl.iPause();
                            }
                            cancelProgressTimer();
                            break;
                        case ConnectivityManager.TYPE_MOBILE:
                        case ConnectivityManager.TYPE_MOBILE_DUN:
                            //手机流量
                        case ConnectivityManager.TYPE_WIFI:
                            loadingLay.setVisibility(VISIBLE);
                            netMobile.setVisibility(INVISIBLE);
                            netNo.setVisibility(INVISIBLE);
                            if (iVideoCommonControl != null){
                                iVideoCommonControl.iStart();
                            }
                            break;
                    }
                    break;
                case MSG.VIEW_NO_PLAYER_START:
                    if (iVideoCommonControl != null){
                        iVideoCommonControl.iStart();
                    }
                    break;
                case MSG.VIEW_HID_COVER_IMG:
                    vodCoverImg.setVisibility(INVISIBLE);
                    break;
                case MSG.VIEW_SHOW_COVER_IMG:
                    if (!TextUtils.isEmpty(mCoverImgUrl)) {
                        vodCoverImg.setVisibility(VISIBLE);
                    }
                    break;
                case MSG.VIEW_SPEED_HID:
                    videoSpeedTip.setVisibility(INVISIBLE);
                    break;
            }
        }
    };

    public void showStatBar(){
        // 显示状态栏
        if (TextUtils.equals("LANDSCAPE" ,orientation)) {
            ((Activity)mContext).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

    }
    public void hidStatBar(){
        // 隐藏状态栏
        if (TextUtils.equals("LANDSCAPE" ,orientation)) {
            ((Activity)mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
    public void lockToPortrait() {
        final Activity activity = ((Activity)mContext);
        if (activity == null) {
            return;
        }
        orientation = "PORTRAIT";
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        backImageView.setVisibility(isShowBack ? View.VISIBLE : View.INVISIBLE);
//        fullScreen.setImageResource(R.mipmap.fullscreen);
        if(iVideoCommonControl != null){
            iVideoCommonControl.iSendOrientationChange(orientation);
        }

        updateViewFlat();
    }

    public void lockToLandscape() {
        final Activity activity = ((Activity)mContext);
        if (activity == null) {
            return;
        }
        orientation = "LANDSCAPE";
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        backImageView.setVisibility(View.VISIBLE);
//        fullScreen.setImageResource(R.mipmap.smallscreen);
        if(iVideoCommonControl != null){
            iVideoCommonControl.iSendOrientationChange(orientation);
        }
        updateViewFlat();

    }
    private void updateViewFlat(){
        if (TextUtils.equals("PORTRAIT",orientation)){
            mVideoSpeedLay.setVisibility(INVISIBLE);

            mCurrentTimeTextView = (TextView) rootView.findViewById(R.id.palyingTime);
            mTotalTimeTextView = (TextView) rootView.findViewById(R.id.playAllTime);
            mPauseScreenplay = (ImageView) rootView.findViewById(R.id.pauseResumeImg);
            mPauseScreenplay.setOnClickListener(myOnclick);
            mProgressBar = (SeekBar) rootView.findViewById(R.id.videoSeekBar);
            mProgressBar.setOnSeekBarChangeListener(this);
            mPauseScreenplay.setImageResource(VIEDOPAUSEPALY == 0 ? R.mipmap.pause : R.mipmap.play);
            bottomLayL.setVisibility(INVISIBLE);
            bottomLayO.setVisibility(VISIBLE);

            //调整头部按钮据上高度
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) topLay.getLayoutParams();
            layoutParams.height = CommonUtil.dip2px(mContext,45);
            topLay.setLayoutParams(layoutParams);
            topLay.setPadding(0,0,0,0);

        }else{
            int pro = mProgressBar.getProgress();
            mCurrentTimeTextView = (TextView) rootView.findViewById(R.id.playingTime_l);
            mTotalTimeTextView = (TextView) rootView.findViewById(R.id.playAllTime_l);
            mPauseScreenplay = (ImageView) rootView.findViewById(R.id.pauseResumeImg_l);
            mPauseScreenplay.setOnClickListener(myOnclick);
            mProgressBar = (SeekBar) rootView.findViewById(R.id.videoSeekBar_l);
            mProgressBar.setOnSeekBarChangeListener(this);
            mProgressBar.setProgress(pro);
            mPauseScreenplay.setImageResource(VIEDOPAUSEPALY == 0 ? R.mipmap.pause : R.mipmap.play);

            bottomLayL.setVisibility(VISIBLE);
            bottomLayO.setVisibility(INVISIBLE);
            mHandler.sendEmptyMessage(MSG.MSG_ON_HID);
            mHandler.sendEmptyMessageDelayed(MSG.MSG_ON_SHOW,800);

            //调整头部按钮据上高度
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) topLay.getLayoutParams();
            layoutParams.height = CommonUtil.dip2px(mContext,45+24);
            topLay.setLayoutParams(layoutParams);
            topLay.setPadding(0,CommonUtil.dip2px(mContext,24),0,0);
        }
    }
    private OnClickListener myOnclick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int vId = v.getId();
            if (vId == mPauseScreenplay.getId()){
                if (VIEDOPAUSEPALY == 0) {
                    if(iVideoCommonControl != null){
                        iVideoCommonControl.iPause();
                    }
                } else if (VIEDOPAUSEPALY == 1) {
                    if(iVideoCommonControl != null){
                        iVideoCommonControl.iStart();
                    }
                }
            }else if (vId == fullScreen.getId()){
                mHandler.sendEmptyMessage(MSG.MSG_ON_SHOW);
                if ("PORTRAIT".equals(orientation)){
                    lockToLandscape();
                }else{
                    lockToPortrait();
                }

            }else if (vId == backImageView.getId() || vId == videoBackNet.getId() || vId == videoBackNoNet.getId()){
                if ("PORTRAIT".equals(orientation) || aboutVideo.getVisibility() == VISIBLE){//如果在竖屏情况下，点击按钮，是返回操作,指标课，直接返回
                    if(iVideoCommonControl != null){
                        iVideoCommonControl.iClickBackView();
                    }
                }else{
                    lockToPortrait();
                }
            }else if (aboutVideo != null && vId == aboutVideo.getId()){
                if (iVideoCommonControl != null){
                    iVideoCommonControl.iAboutListPress();
                }
//                dispatchEvent("sendHiddenAboutListPress",null);
            }else if (vId == netPlay.getId()){
                mHandler.sendMessage(mHandler.obtainMessage(MSG.VIEW_NET_UPDATE,ConnectivityManager.TYPE_WIFI)) ;
//                start();
                if (iVideoCommonControl != null){
                    iVideoCommonControl.iClickMobileStatePlay();
                }
//
//                WritableMap event = Arguments.createMap();
//                event.putInt("type", 1);
//                dispatchEvent("setUseMobileNetState", event);

            } else if (vId == errPlay.getId()){
                mHandler.sendEmptyMessage(MSG.VIEW_NO_NET_CLICK) ;
            } else if (vId == mLockScreen.getId()){
                lockTouchLogic();
            } else if (vId == mVideoSpeedLay.getId()){
                mVideoSpeedLay.setVisibility(INVISIBLE);
            } else if (vId == mVideoSpeed.getId()){
                mHandler.sendEmptyMessage(MSG.MSG_ON_HID);
                mVideoSpeedLay.setVisibility(VISIBLE);
                updateSpeedView(false);
            } else if (vId == mVideoSpeed20.getId()){
                setSpeed(2);
                updateSpeedView(true);
            } else if (vId == mVideoSpeed15.getId()){
                setSpeed(1.5f);
                updateSpeedView(true);
            } else if (vId == mVideoSpeed125.getId()){
                setSpeed(1.25f);
                updateSpeedView(true);
            } else if (vId == mVideoSpeed10.getId()){
                setSpeed(1);
                updateSpeedView(true);
            } else if (vId == mVideoSpeed05.getId()){
                setSpeed(0.5f);
                updateSpeedView(true);
            }
        }
    };
    private void updateSpeedView(boolean isClick){
        mVideoSpeed20.setTextColor(getResources().getColor(R.color.white));
        mVideoSpeed15.setTextColor(getResources().getColor(R.color.white));
        mVideoSpeed125.setTextColor(getResources().getColor(R.color.white));
        mVideoSpeed10.setTextColor(getResources().getColor(R.color.white));
        mVideoSpeed05.setTextColor(getResources().getColor(R.color.white));

        if (mSpeed == 2.0){
            mVideoSpeed20.setTextColor(getResources().getColor(R.color.red));
        }else if (mSpeed == 1.5){
            mVideoSpeed15.setTextColor(getResources().getColor(R.color.red));
        }else if (mSpeed == 1.25){
            mVideoSpeed125.setTextColor(getResources().getColor(R.color.red));
        }else if (mSpeed == 1.0){
            mVideoSpeed10.setTextColor(getResources().getColor(R.color.red));
        }else if (mSpeed == 0.5){
            mVideoSpeed05.setTextColor(getResources().getColor(R.color.red));
        }
        if (isClick) {
            String tip = String.format("已为你开启 %s倍速 播放",  (mSpeed == 1.25 ? "" :" " ) + mSpeed);
            SpannableString spannableString = new SpannableString(tip);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFFF6375")),
                    6, tip.indexOf("速") + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            videoSpeedTip.setText(spannableString);
            videoSpeedTip.setVisibility(VISIBLE);
            mHandler.sendEmptyMessageDelayed(MSG.VIEW_SPEED_HID, 3000);
            mVideoSpeedLay.setVisibility(INVISIBLE);
        }
        if (mSpeed == 1.0) {
            mVideoSpeed.setText("倍速");
        }else {
            mVideoSpeed.setText(String.format("%sX", "" + mSpeed));
        }
    }
    private void setSpeed(float mSpeed){
        this.mSpeed = mSpeed;
        if (iVideoCommonControl != null){
            iVideoCommonControl.iSpeed(mSpeed);
        }
    }
    /**
     * 处理锁屏屏幕触摸逻辑
     */
    protected void lockTouchLogic() {
        if (mLockCurScreen) {
            mLockScreen.setImageResource(R.drawable.unlock);
            mLockCurScreen = false;
            onClickUiToggle(true);
        } else {
            mLockScreen.setImageResource(R.drawable.lock);
            mLockCurScreen = true;
            hideAllWidget();
        }
    }
    public void setTitle(String title){
        videoTitle.setText(title);
    }
    public void aboutButVisibility(int vis){
        aboutVideo.setVisibility(vis);

    }
    public void fullScreenButVisibility(int vis){
        fullScreen.setVisibility(vis);
    }
    /**
     * 设置是否判断网络状态
     * @param type 0、判断；1不判断
     */
    public void setNetType(int type) {
        netType = type;
    }
    public void setCoverImage(String coverImgUrl) {
        if (vodCoverImg != null && !TextUtils.isEmpty(coverImgUrl)){
            vodCoverImg.setVisibility(VISIBLE);
            ImageLoaders.displayImage( coverImgUrl,vodCoverImg, 0);
        }
        this.mCoverImgUrl = coverImgUrl;
    }
    public void showCoverImg(){
        if (iVideoCommonControl != null){
            try {
                Bitmap temp = iVideoCommonControl.iPauseCoverBitMap();
                if (temp != null && !temp.isRecycled()){
                    vodCoverImg.setImageBitmap(temp);
                    Log.e(TAG,"showCoverImg");
                }else {
                    setCoverImage(this.mCoverImgUrl);
                }
            }catch (Exception ignored){
                setCoverImage(this.mCoverImgUrl);
            }
        }else {
            setCoverImage(this.mCoverImgUrl);
        }
        mHandler.sendEmptyMessage(MSG.VIEW_SHOW_COVER_IMG);
    }
    private BroadcastReceiver netBroadcastReceiver = new BroadcastReceiver() {

        private ConnectivityManager mConnectivityManager;
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                if (mConnectivityManager == null) {
                    mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                }
                updateAndSendConnectionType();

            }
        }

        @SuppressLint("MissingPermission")
        private void updateAndSendConnectionType() {
            try {
                int networkType = -1;
                NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isConnected()) {
                } else {
                    // Get the connection type
                    networkType = networkInfo.getType();
                    switch (networkType) {
                        case ConnectivityManager.TYPE_BLUETOOTH:
                            break;
                        case ConnectivityManager.TYPE_ETHERNET:
                            break;
                        case ConnectivityManager.TYPE_MOBILE:
                        case ConnectivityManager.TYPE_MOBILE_DUN:
                            if (netType == 1){//手机流量下，点击了继续播放按钮，rn过来的状态
                                networkType = ConnectivityManager.TYPE_WIFI;
                            }
                            break;
                        case ConnectivityManager.TYPE_WIFI:
                            break;
                        case ConnectivityManager.TYPE_WIMAX:
                            break;
                        case ConnectivityManager.TYPE_VPN:
                            break;
                    }
                }
                Log.e(TAG,"networkType: "+ networkType);
                mHandler.sendMessage(mHandler.obtainMessage(MSG.VIEW_NET_UPDATE,networkType)) ;
            } catch (SecurityException e) {

            }

        }
    };
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mContext != null){
            try {
                mContext.unregisterReceiver(netBroadcastReceiver);
            }catch (Exception e){
                Log.e(TAG,e.getLocalizedMessage());
            }
        }
        cancelProgressTimer();
    }

    protected void setViewShowState(View view, int visibility) {
        if (view != null) {
            view.setVisibility(visibility);
        }
    }
    protected void setTextAndProgress(int secProgress) {
        setTextAndProgress(secProgress, false);
    }
    /**
     * 获取当前播放进度
     */
    public int getCurrentPositionWhenPlaying() {
        int position = 0;
        if (mCurrentState == CURRENT_STATE_PLAYING || mCurrentState == CURRENT_STATE_PAUSE) {
            try {
                position = (int) getCurrentPosition();
            } catch (Exception e) {
                e.printStackTrace();
                return position;
            }
        }
        if (position == 0 && mCurrentPosition > 0) {
            return (int) mCurrentPosition;
        }
        return position;
    }
    protected void setSecondaryProgress(int secProgress) {
//        if (mProgressBar != null) {
//            if (secProgress != 0 && !getGSYVideoManager().isCacheFile()) {
//                mProgressBar.setSecondaryProgress(secProgress);
//            }
//        }
//        if (mBottomProgressBar != null) {
//            if (secProgress != 0 && !getGSYVideoManager().isCacheFile()) {
//                mBottomProgressBar.setSecondaryProgress(secProgress);
//            }
//        }
    }
    protected void setProgressAndTime(int progress, int secProgress, int currentTime, int totalTime, boolean forceChange) {

//        if (mGSYVideoProgressListener != null && mCurrentState == CURRENT_STATE_PLAYING) {
//            mGSYVideoProgressListener.onProgress(progress, secProgress, currentTime, totalTime);
//        }
        if (mProgressBar == null || mTotalTimeTextView == null || mCurrentTimeTextView == null) {
            return;
        }
        if (mHadSeekTouch) {
            return;
        }
        if (!mTouchingProgressBar) {
            if (progress != 0 || forceChange) mProgressBar.setProgress(progress);
        }
        if (getBufferedPercentage() > 0) {
            secProgress = getBufferedPercentage();
        }
        if (secProgress > 94) secProgress = 100;
        setSecondaryProgress(secProgress);
        mTotalTimeTextView.setText(CommonUtil.stringForTime(totalTime));
        if (currentTime > 0)
            mCurrentTimeTextView.setText(CommonUtil.stringForTime(currentTime));

        if (mBottomProgressBar != null) {
            if (progress != 0 || forceChange) mBottomProgressBar.setProgress(progress);
            setSecondaryProgress(secProgress);
        }
        mHandler.sendEmptyMessage(MSG.MSG_ON_SEEK);
    }
    protected void setTextAndProgress(int secProgress, boolean forceChange) {
        int position = getCurrentPositionWhenPlaying();
        int duration = videoDuration;
        int progress = position * 100 / (duration == 0 ? 1 : duration);
        setProgressAndTime(progress, secProgress, position, duration, forceChange);
    }
    Runnable progressTask = new Runnable() {
        @Override
        public void run() {
            if (mCurrentState == CURRENT_STATE_PLAYING || mCurrentState == CURRENT_STATE_PAUSE) {
                setTextAndProgress(0);
            }
            if (mPostProgress) {
                postDelayed(this, 1000);
            }
        }
    };
    protected Context getActivityContext() {
        return CommonUtil.getActivityContext(getContext());
    }
    protected void touchSurfaceDown(float x, float y) {
        mTouchingProgressBar = true;
        mDownX = x;
        mDownY = y;
        mMoveY = 0;
        mChangeVolume = false;
        mChangePosition = false;
        mShowVKey = false;
        mBrightness = false;
        mFirstTouch = true;
    }
    protected void startProgressTimer() {
        cancelProgressTimer();
        mPostProgress = true;
        postDelayed(progressTask, 300);
    }

    protected void cancelProgressTimer() {
        mPostProgress = false;
        removeCallbacks(progressTask);
    }
    protected void touchSurfaceMoveFullLogic(float absDeltaX, float absDeltaY) {
        int curWidth = 0;
        if (getActivityContext() != null) {
            curWidth = CommonUtil.getCurrentScreenLand((Activity) getActivityContext()) ? mScreenHeight : mScreenWidth;
        }
        if (absDeltaX > mThreshold || absDeltaY > mThreshold) {
            cancelProgressTimer();
            if (absDeltaX >= mThreshold) {
                //防止全屏虚拟按键
                int screenWidth = CommonUtil.getScreenWidth(getContext());
                if (Math.abs(screenWidth - mDownX) > mSeekEndOffset) {
                    mChangePosition = true;
                    mDownPosition = getCurrentPositionWhenPlaying();
                } else {
                    mShowVKey = true;
                }
            } else {
                int screenHeight = CommonUtil.getScreenHeight(getContext());
                boolean noEnd = Math.abs(screenHeight - mDownY) > mSeekEndOffset;
                if (mFirstTouch) {
                    mBrightness = (mDownX < curWidth * 0.5f) && noEnd;
                    mFirstTouch = false;
                }
                if (!mBrightness) {
                    mChangeVolume = noEnd;
                    mGestureDownVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                }
                mShowVKey = !noEnd;
            }
        }
    }
    /**
     * 滑动改变亮度
     *
     * @param percent
     */
    protected void onBrightnessSlide(float percent) {
        mBrightnessData = ((Activity) (mContext)).getWindow().getAttributes().screenBrightness;
        if (mBrightnessData <= 0.00f) {
            mBrightnessData = 0.50f;
        } else if (mBrightnessData < 0.01f) {
            mBrightnessData = 0.01f;
        }
        WindowManager.LayoutParams lpa = ((Activity) (mContext)).getWindow().getAttributes();
        lpa.screenBrightness = mBrightnessData + percent;
        if (lpa.screenBrightness > 1.0f) {
            lpa.screenBrightness = 1.0f;
        } else if (lpa.screenBrightness < 0.01f) {
            lpa.screenBrightness = 0.01f;
        }
        showBrightnessDialog(lpa.screenBrightness);
        ((Activity) (mContext)).getWindow().setAttributes(lpa);
    }
    protected void touchSurfaceMove(float deltaX, float deltaY, float y) {
        int curWidth = 0;
        int curHeight = 0;
        if (getActivityContext() != null) {
            if (mViewHeight < 1){
                mViewWidth = getWidth();
                mViewHeight = getHeight();
            }
            curWidth = CommonUtil.getCurrentScreenLand((Activity) getActivityContext()) ? mScreenHeight : mScreenWidth;
            curHeight = CommonUtil.getCurrentScreenLand((Activity) getActivityContext()) ? mScreenWidth : mViewHeight;
        }
        if (mChangePosition) {
            int totalTimeDuration = videoDuration;
            mSeekTimePosition = (int) (mDownPosition + (deltaX * totalTimeDuration / curWidth) / mSeekRatio);
            if (mSeekTimePosition > totalTimeDuration)
                mSeekTimePosition = totalTimeDuration;

            showProgressDialog(deltaX,mSeekTimePosition,  totalTimeDuration);
        } else if (mChangeVolume) {
            deltaY = -deltaY;
            int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            int deltaV = (int) (max * deltaY * 3 / curHeight);
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mGestureDownVolume + deltaV, 0);
            int volumePercent = (int) (mGestureDownVolume * 100 / max + deltaY * 3 * 100 / curHeight);

            showVolumeDialog(-deltaY, volumePercent);
        } else if (mBrightness) {
            if (Math.abs(deltaY) > mThreshold) {
                float percent = (-deltaY / curHeight);
                onBrightnessSlide(percent);
                mDownY = y;
            }
        }
    }
    protected void touchSurfaceUp() {

        mTouchingProgressBar = false;
        dismissProgressDialog();
        dismissVolumeDialog();
        dismissBrightnessDialog();
        if (mChangePosition  && (mCurrentState == CURRENT_STATE_PLAYING || mCurrentState == CURRENT_STATE_PAUSE)) {
            try {
                if (null != iVideoCommonControl) {

                    iVideoCommonControl.iSeekTo(mSeekTimePosition);

                }
//                seekTo(mSeekTimePosition);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int duration = videoDuration;
            int progress = mSeekTimePosition * 100 / (duration == 0 ? 1 : duration);
            if (mProgressBar != null) {
                mProgressBar.setProgress(progress);
            }
            if (mBottomProgressBar != null)
                mBottomProgressBar.setProgress(progress);
//            if (mVideoAllCallBack != null && isCurrentMediaListener()) {
//                Debuger.printfLog("onTouchScreenSeekPosition");
//                mVideoAllCallBack.onTouchScreenSeekPosition(mOriginUrl, mTitle, this);
//            }
        } else if (mBrightness) {
//            if (mVideoAllCallBack != null && isCurrentMediaListener()) {
//                Debuger.printfLog("onTouchScreenSeekLight");
//                mVideoAllCallBack.onTouchScreenSeekLight(mOriginUrl, mTitle, this);
//            }
        } else if (mChangeVolume) {
//            if (mVideoAllCallBack != null && isCurrentMediaListener()) {
//                Debuger.printfLog("onTouchScreenSeekVolume");
//                mVideoAllCallBack.onTouchScreenSeekVolume(mOriginUrl, mTitle, this);
//            }
        }
    }
    /**
     * 亮度、进度、音频
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {
            int id = v.getId();
            float x = event.getX();
            float y = event.getY();

            if (mIfCurrentIsFullscreen && mLockCurScreen && mNeedLockFull) {
                onClickUiToggle(false);
                return true;
            }

            if (id == R.id.surface_container) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        touchSurfaceDown(x, y);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float deltaX = x - mDownX;
                        float deltaY = y - mDownY;
                        float absDeltaX = Math.abs(deltaX);
                        float absDeltaY = Math.abs(deltaY);

                        if ((mIfCurrentIsFullscreen && mIsTouchWigetFull)
                                || (mIsTouchWiget && !mIfCurrentIsFullscreen)) {
                            if (!mChangePosition && !mChangeVolume && !mBrightness) {
                                touchSurfaceMoveFullLogic(absDeltaX, absDeltaY);
                            }
                        }
                        touchSurfaceMove(deltaX, deltaY, y);

                        break;
                    case MotionEvent.ACTION_UP:

                        touchSurfaceUp();

                        startProgressTimer();

                        //不要和隐藏虚拟按键后，滑出虚拟按键冲突
                        if (mHideKey && mShowVKey) {
                            return true;
                        }
                        break;
                }
                gestureDetector.onTouchEvent(event);
            }
        }catch (Exception ignore){}
        return false;
    }
    /**
     * 双击
     */
    protected GestureDetector gestureDetector = new GestureDetector(getContext().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            touchDoubleUp();
            onClickUiToggle(true);
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (!mChangePosition && !mChangeVolume && !mBrightness) {
                onClickUiToggle(false);
            }
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            touchLongPress(e);
        }
    });
    /**
     * 双击暂停/播放
     * 如果不需要，重载为空方法即可
     */
    protected void touchDoubleUp() {
//        if (!mHadPlay) {
//            return;
//        }
        mPauseScreenplay.performClick();
    }
    /**
     * 长按
     */
    protected void touchLongPress(MotionEvent e) {
    }
    /**
     * 触摸显示滑动进度dialog，如需要自定义继承重写即可，记得重写dismissProgressDialog
     */
    protected void showProgressDialog(float deltaX,int seekTimePosition, int totalTimeDuration){

        if (mDialogProgressBar == null) {
            View localView = videoQuickProLay;//LayoutInflater.from(getActivityContext()).inflate(R.layout.video_progress_dialog, null);
            if (localView.findViewById(R.id.duration_progressbar) instanceof ProgressBar) {
                mDialogProgressBar = ((ProgressBar) localView.findViewById(R.id.duration_progressbar));
                if (mDialogProgressBarDrawable != null) {
                    mDialogProgressBar.setProgressDrawable(mDialogProgressBarDrawable);
                }
            }
            mDialogSeekTime = ((TextView) localView.findViewById(R.id.tv_current));
            mDialogTotalTime = ((TextView) localView.findViewById(R.id.tv_duration));
            if (localView.findViewById(R.id.duration_image_tip) instanceof ImageView) {
                mDialogIcon = ((ImageView) localView.findViewById(R.id.duration_image_tip));
            }
//            int height = surfaceContainer.getHeight();
//            mProgressDialog = new Dialog(getActivityContext(), R.style.video_style_dialog_progress);
//            mProgressDialog.setContentView(localView);
//            mProgressDialog.getWindow().addFlags(Window.FEATURE_ACTION_BAR);
//            mProgressDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
//            mProgressDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//            mProgressDialog.getWindow().setLayout(getWidth(), height);
            if (mDialogProgressNormalColor != -11 && mDialogTotalTime != null) {
                mDialogTotalTime.setTextColor(mDialogProgressNormalColor);
            }
            if (mDialogProgressHighLightColor != -11 && mDialogSeekTime != null) {
                mDialogSeekTime.setTextColor(mDialogProgressHighLightColor);
            }
//            WindowManager.LayoutParams localLayoutParams = mProgressDialog.getWindow().getAttributes();
//            localLayoutParams.gravity = Gravity.TOP | Gravity.START;
//            localLayoutParams.width = getWidth();
//            localLayoutParams.height = height;
//            int location[] = new int[2];
//            getLocationInWindow(location);
//            localLayoutParams.x = location[0];
//            localLayoutParams.y = location[1];//location[1];
//            mProgressDialog.getWindow().setAttributes(localLayoutParams);
//            Log.e(TAG,"location[1]:"+location[1]+" getHeight:"+surfaceContainer.getHeight());

        }

//        if (!mProgressDialog.isShowing()) {
//            mProgressDialog.show();
//        }
        String seekTime = CommonUtil.stringForTime(seekTimePosition);
        String totalTime = CommonUtil.stringForTime(totalTimeDuration);
        if (mDialogSeekTime != null) {
            mDialogSeekTime.setText(seekTime);
        }
        if (mDialogTotalTime != null) {
            mDialogTotalTime.setText(" / " + totalTime);
            Log.e(TAG,"totalTime:" +totalTime);
        }
        if (totalTimeDuration > 0)
            if (mDialogProgressBar != null) {
                mDialogProgressBar.setProgress(seekTimePosition * 100 / totalTimeDuration);
            }
        if (deltaX > 0) {
            if (mDialogIcon != null) {
                mDialogIcon.setBackgroundResource(R.drawable.video_forward_icon);
            }
        } else {
            if (mDialogIcon != null) {
                mDialogIcon.setBackgroundResource(R.drawable.video_backward_icon);
            }
        }
        if (videoQuickProLay.getVisibility() ==  INVISIBLE ){
            videoQuickProLay.setVisibility(VISIBLE);
        }
        Log.e(TAG,"videoQuickProLay[1]:" +totalTime);
}
    protected void dismissProgressDialog() {
//        if (mProgressDialog != null) {
//            mProgressDialog.dismiss();
//            mProgressDialog = null;
//        }
        videoQuickProLay.setVisibility(INVISIBLE);
    }
    /**
     * 触摸音量dialog，如需要自定义继承重写即可，记得重写dismissVolumeDialog
     */
    protected void showVolumeDialog(float deltaY, int volumePercent) {
        if (mVolumeDialog == null) {
            View localView = LayoutInflater.from(getActivityContext()).inflate(R.layout.video_volume_dialog, null);
            if (localView.findViewById(R.id.volume_progressbar) instanceof ProgressBar) {
                mDialogVolumeProgressBar = ((ProgressBar) localView.findViewById(R.id.volume_progressbar));
                if (mVolumeProgressDrawable != null && mDialogVolumeProgressBar != null) {
                    mDialogVolumeProgressBar.setProgressDrawable(mVolumeProgressDrawable);
                }
            }
            mVolumeDialog = new Dialog(getActivityContext(), R.style.video_style_dialog_volume);
            mVolumeDialog.setContentView(localView);
            mVolumeDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            mVolumeDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
            mVolumeDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            mVolumeDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            WindowManager.LayoutParams localLayoutParams = mVolumeDialog.getWindow().getAttributes();
            localLayoutParams.gravity = Gravity.TOP | Gravity.START;
            localLayoutParams.width = getWidth();
            localLayoutParams.height = getHeight();
            int location[] = new int[2];
            getLocationOnScreen(location);
            localLayoutParams.x = location[0];
            localLayoutParams.y = location[1];//location[1];
            mVolumeDialog.getWindow().setAttributes(localLayoutParams);
        }
        if (!mVolumeDialog.isShowing()) {
            mVolumeDialog.show();
        }
        if (mDialogVolumeProgressBar != null) {
            mDialogVolumeProgressBar.setProgress(volumePercent);
        }
    }
    protected void dismissVolumeDialog() {
        if (mVolumeDialog != null) {
            mVolumeDialog.dismiss();
            mVolumeDialog = null;
        }
    }
    /**
     * 触摸亮度dialog，如需要自定义继承重写即可，记得重写dismissBrightnessDialog
     */
    protected void showBrightnessDialog(float percent) {
        if (mBrightnessDialog == null) {
            View localView = LayoutInflater.from(getActivityContext()).inflate(R.layout.video_brightness, null);
            mBrightnessDialogBar =  localView.findViewById(R.id.app_video_brightness);
            mBrightnessDialog = new Dialog(getActivityContext(), R.style.video_style_dialog_volume);
            mBrightnessDialog.setContentView(localView);
            mBrightnessDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            mBrightnessDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
            mBrightnessDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            mBrightnessDialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            mBrightnessDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            WindowManager.LayoutParams localLayoutParams = mBrightnessDialog.getWindow().getAttributes();
            localLayoutParams.gravity = Gravity.TOP | Gravity.END;
            localLayoutParams.width = getWidth();
            localLayoutParams.height = getHeight();
            int location[] = new int[2];
            getLocationOnScreen(location);
            localLayoutParams.x = location[0];
            localLayoutParams.y = location[1];//location[1];
            mBrightnessDialog.getWindow().setAttributes(localLayoutParams);
        }
        if (!mBrightnessDialog.isShowing()) {
            mBrightnessDialog.show();
        }
        if (mBrightnessDialogBar != null)
            mBrightnessDialogBar.setProgress( (int) (percent*1000) );
    }
    protected void dismissBrightnessDialog() {
        if (mBrightnessDialog != null) {
            mBrightnessDialog.dismiss();
            mBrightnessDialog = null;
        }
    }
    private String getTime(int time) {
        return  String.format("%02d", time % 3600 / 60) + ":"
                + String.format("%02d", time % 3600 % 60);
    }
    IVideoCommonControl iVideoCommonControl;

    public IVideoCommonControl getiVideoCommonControl() {
        return iVideoCommonControl;
    }

    public void setiVideoCommonControl(IVideoCommonControl iVideoCommonControl) {
        this.iVideoCommonControl = iVideoCommonControl;
    }

    /**
     * 显示或隐藏功能按钮
     * @param isForceShow true:不执行隐藏
     */
    public void onClickUiToggle(boolean isForceShow){
        long cur = System.currentTimeMillis();
        if (cur - clickTime < 500){
            return;
        }
        clickTime = cur;
        mVideoSpeedLay.setVisibility(INVISIBLE);
        if (bottomLay.getVisibility() == View.INVISIBLE){
            mHandler.sendEmptyMessage(MSG.MSG_ON_SHOW);
            mHandler.removeMessages(MSG.MSG_ON_HID);
            mHandler.sendEmptyMessageDelayed(MSG.MSG_ON_HID,delayTime);
        }else{
            if (!isForceShow) {
                mHandler.removeMessages(MSG.MSG_ON_HID);
                mHandler.sendEmptyMessage(MSG.MSG_ON_HID);
            }
        }
    }
    protected float getSpeed(){
        return mSpeed;
    }
    public abstract View getVideoLayout(Context context);

    protected void hideAllWidget(){
        mVideoSpeedLay.setVisibility(INVISIBLE);
        topLay.setVisibility(INVISIBLE);
        bottomLay.setVisibility(INVISIBLE);
        mHandler.removeMessages(MSG.MSG_ON_HID);
        mHandler.sendEmptyMessageDelayed(MSG.MSG_ON_HID,delayTime);
        dismissProgressDialog();
        dismissVolumeDialog();
        dismissBrightnessDialog();
    }
    protected abstract int getCurrentPosition();
    protected abstract int getBufferedPercentage();



    public interface IVideoCommonControl{
        void iPrepared(long duration);
        void iStart();
        void iPause();
        void iAboutListPress();
        void iClickBackView();
        void iClickMobileStatePlay();
        void iSendOrientationChange(String o);
        void iVodPlayFinish();
        void iSeekTo(int s);
        void iSpeed(float s);
        void updatePosition();
        Bitmap iPauseCoverBitMap();
    }
}
