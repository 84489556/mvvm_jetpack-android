package com.reactlibrary.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.netease.neliveplayer.playerkit.common.log.LogUtil;
import com.netease.neliveplayer.playerkit.receiver.Observer;
import com.netease.neliveplayer.playerkit.receiver.PhoneCallStateObserver;
import com.netease.neliveplayer.playerkit.sdk.PlayerManager;
import com.netease.neliveplayer.playerkit.sdk.VodPlayer;
import com.netease.neliveplayer.playerkit.sdk.VodPlayerObserver;
import com.netease.neliveplayer.playerkit.sdk.constant.CauseCode;
import com.netease.neliveplayer.playerkit.sdk.model.MediaInfo;
import com.netease.neliveplayer.playerkit.sdk.model.SDKInfo;
import com.netease.neliveplayer.playerkit.sdk.model.SDKOptions;
import com.netease.neliveplayer.playerkit.sdk.model.StateInfo;
import com.netease.neliveplayer.playerkit.sdk.model.VideoBufferStrategy;
import com.netease.neliveplayer.playerkit.sdk.model.VideoOptions;
import com.netease.neliveplayer.playerkit.sdk.model.VideoScaleMode;
import com.netease.neliveplayer.playerkit.sdk.view.AdvanceTextureView;
import com.netease.neliveplayer.proxy.config.NEPlayerConfig;
import com.reactlibrary.interfaces.IVideoPlayCallBack;
import com.reactlibrary.utils.FLog;
import com.reactlibrary.view.base.VideoBaseView;
import com.yd.video.R;


public class NEVideoView extends VideoBaseView {
    String TAG = NEVideoView.class.getSimpleName();

    private boolean mHardware = true;
    protected VodPlayer player;
    protected String mVideoPath=""; //文件路径
    protected boolean isPauseInBackgroud;
    AdvanceTextureView textureView;
    private MediaInfo mediaInfo;
    Bitmap mPauseCover ;
    private IVideoPlayCallBack videoPlayCallBack;//视频播放状态和进度回调

    private boolean isPlayCompletion;

    public NEVideoView(Context context) {
        super(context);
        init();
        PhoneCallStateObserver.getInstance().observeLocalPhoneObserver(localPhoneObserver, true);
        setiVideoCommonControl(videoCommonControl);
//        eventTrack = EventTrack.getInstance();
    }
    public NEVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        FLog.d(TAG,"NEVideoView init ");
        init();
        PhoneCallStateObserver.getInstance().observeLocalPhoneObserver(localPhoneObserver, true);
        setiVideoCommonControl(videoCommonControl);
//        eventTrack = EventTrack.getInstance();
    }

    public NEVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        PhoneCallStateObserver.getInstance().observeLocalPhoneObserver(localPhoneObserver, true);
        setiVideoCommonControl(videoCommonControl);
//        eventTrack = EventTrack.getInstance();
    }

    public NEVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        PhoneCallStateObserver.getInstance().observeLocalPhoneObserver(localPhoneObserver, true);
        setiVideoCommonControl(videoCommonControl);
    }

    public void setShowBack(Boolean isShowBack) {
        super.isShowBack= isShowBack;
    }

    public boolean isPlayCompletion(){
        return isPlayCompletion;
    }


    public void setSourceData(String title, boolean disableFullButton) {
        if (!TextUtils.isEmpty(title))
            super.setTitle(title);
        if (disableFullButton){
            super.aboutButVisibility(View.GONE);
            super.fullScreenButVisibility(View.VISIBLE);
        }else {
            super.aboutButVisibility(View.VISIBLE);
            super.fullScreenButVisibility(View.GONE);
            super.lockToLandscape();
        }
    }

    public IVideoPlayCallBack getVideoPlayCallBack() {
        return videoPlayCallBack;
    }

    public void setVideoPlayCallBack(IVideoPlayCallBack videoPlayCallBack) {
        this.videoPlayCallBack = videoPlayCallBack;
    }

    public void setCoverImage(String coverImgUrl) {
        super.setCoverImage(coverImgUrl);
    }

    @Override
    public View getVideoLayout(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.activity_player,null);
        textureView = v.findViewById(R.id.live_texture);
        return v;
    }

    @Override
    protected int getCurrentPosition() {
        if (player!= null){
            try {
                return (int) player.getCurrentPosition();
            }catch (Exception ignored){
            }
        }
        return 0;
    }

    @Override
    protected int getBufferedPercentage() {
        return 0;
    }



    private IVideoCommonControl videoCommonControl = new IVideoCommonControl() {
        @Override
        public void iPrepared(long duration) {
//            WritableMap event = Arguments.createMap();
//            event.putInt("allTime",(int) duration);//key用于js中的nativeEvent
//            dispatchEvent("sendStreamState",event);
            if (videoPlayCallBack != null){
                videoPlayCallBack.prepared(duration);
            }
        }

        @Override
        public void iStart() {
            NEVideoView.this.start();
//            dispatchEvent("sendPlayState",null);
            if (videoPlayCallBack != null){
                videoPlayCallBack.startPlay();
            }
        }

        @Override
        public void iPause() {
            NEVideoView.this.pause();
//            dispatchEvent("sendPauseState",null);
            if (videoPlayCallBack != null){
                videoPlayCallBack.pause();
            }
        }

        @Override
        public void iAboutListPress() {
//            dispatchEvent("sendHiddenAboutListPress",null);
        }

        @Override
        public void iClickBackView() {
//            dispatchEvent("sendbackView",null);
        }

        @Override
        public void iClickMobileStatePlay() {
//            WritableMap event = Arguments.createMap();
//            event.putInt("type", 1);
//            dispatchEvent("setUseMobileNetState", event);
            if (videoPlayCallBack != null){
                videoPlayCallBack.setNetState(1);
            }
        }

        @Override
        public void iSendOrientationChange(String orientation) {
//            WritableMap event = Arguments.createMap();
//            event.putString("type", orientation);
//            dispatchEvent("sendOrientationChange",event);
            if (videoPlayCallBack != null){
                videoPlayCallBack.orientationChange(orientation);
            }
        }

        @Override
        public void iVodPlayFinish() {
//            dispatchEvent("sendVodOverStateNe", null);
//            WritableMap event = Arguments.createMap();
//            event.putInt("currentPosition", videoDuration);
//            dispatchEvent("sendCurrentPosition", event);
            if (videoPlayCallBack != null){
                videoPlayCallBack.videoPlayFinish(videoDuration);
            }
            isPlayCompletion = true;
        }

        @Override
        public void iSeekTo(int pos) {
            NEVideoView.this.seekTo(pos);
        }

        @Override
        public void iSpeed(float speed) {
            NEVideoView.this.setSpeed(speed);
        }

        @Override
        public void updatePosition() {

//            WritableMap event = Arguments.createMap();
//            event.putInt("currentPosition", getCurrentPosition());
//            dispatchEvent("sendCurrentPosition", event);
            if (videoPlayCallBack != null){
                videoPlayCallBack.progressUpdate(getCurrentPosition());
            }
        }

        @Override
        public Bitmap iPauseCoverBitMap() {
            return mPauseCover;
        }
    };
    private void initPlayer() {
        VideoOptions options = new VideoOptions();
        options.hardwareDecode = mHardware;
        /**
         * isPlayLongTimeBackground 控制退到后台或者锁屏时是否继续播放，开发者可根据实际情况灵活开发,我们的示例逻辑如下：
         * 使用软件解码：
         * isPlayLongTimeBackground 为 false 时，直播进入后台停止播放，进入前台重新拉流播放
         * isPlayLongTimeBackground 为 true 时，直播进入后台不做处理，继续播放,
         *
         * 使用硬件解码：
         * 直播进入后台停止播放，进入前台重新拉流播放
         */
        options.isPlayLongTimeBackground = !isPauseInBackgroud;
        options.bufferStrategy = VideoBufferStrategy.ANTI_JITTER;
        player = PlayerManager.buildVodPlayer(mContext, mVideoPath, options);
        Log.e(TAG,"initPlayer:"+mVideoPath);

        player.setupRenderView(textureView, VideoScaleMode.FIT);
//        intentToStartBackgroundPlay();
//        start();
//        player.registerPlayerObserver(playerObserver, true);
//        player.start();
//        player.setupRenderView(textureView, VideoScaleMode.FIT);
    }
    public void setHardwareDecoder(boolean b) {
        mHardware = b;
    }

    public void setEnableBackgroundPlay(boolean b) {
    }
    public void setVideoPath(String playUrl) {
        Log.e(TAG,"setVideoPath："+playUrl);
        this.mVideoPath = playUrl;
        if (player == null ){
//            initPlayer();
        }else{
            mHandler.sendEmptyMessage(MSG.MSG_ON_INIT);
        }
    }
    public void setSpeed(float speed){
        player.setPlaybackSpeed(speed);
//        showToast(String.format(Locale.getDefault(), "您正在已%s速度观看视频",""+speed));
    }
    public void setMediaType(String mediaType) {
    }
    public boolean isPlaying(){
        if (player == null ) {
            return player.isPlaying();
        }
        return false;
    }
    public void start() {
        Log.e(TAG,"start");
        if (TextUtils.isEmpty(this.mVideoPath)){
            mHandler.removeMessages(MSG.VIEW_NO_PLAYER_START);
            mHandler.sendEmptyMessageDelayed(MSG.VIEW_NO_PLAYER_START,300);
            return;
        }
        if (player == null ){
            initPlayer();
        }

        if (player.isPlaying()) {
            return;
        }
        player.registerPlayerObserver(playerObserver, true);
        player.start();
//        player.registerPlayerObserver(playerObserver, true);
//        player.start();
        mHandler.sendEmptyMessage(MSG.MSG_ON_RESUME);
        mHandler.sendEmptyMessageDelayed(MSG.VIEW_SHOW_LOADING,10);
    }

    public void pause() {
        if (player != null && player.isPlaying()) {
            player.pause();
//            if(reactContext != null) {
//                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "sendPauseState", null);
//            }
           mPauseCover = textureView.initCover();
        } else {
//            player.start();
        }

        mHandler.sendEmptyMessage(MSG.MSG_ON_PAUSE);
//        VIEDOPAUSEPALY = 1;
        Log.e(TAG,"pause");
    }
    public void release() {

        LogUtil.i(TAG, "releasePlayer");
        try {
            if (player == null) {
                return;
            }
            player.registerPlayerObserver(playerObserver, false);
            PhoneCallStateObserver.getInstance().observeLocalPhoneObserver(localPhoneObserver, false);
            player.setupRenderView(null, VideoScaleMode.NONE);
            if (textureView != null) {
                textureView.releaseSurface();
            }
            textureView = null;
            player.stop();
            player = null;
//        intentToStopBackgroundPlay();
            mediaInfo = null;
            if (mHandler != null) {
                mHandler.removeCallbacksAndMessages(null);
            }

        }catch (Exception e){

        }

    }

    public void switchContentUrl(String url) {
        if (super.networkType != ConnectivityManager.TYPE_WIFI){
            return;
        }
        if (player != null) {
            super.errorReply = 0;
            this.mVideoPath = url;
            player.switchContentUrl(url);
            start();
            //显示加载中
            mHandler.sendEmptyMessage(MSG.VIEW_SHOW_LOADING);
//            player.start();
        }
    }

    public void getCurrentPositionForRN() {
        //向rn发送进度 播放器已经自带
//        mHandler.sendEmptyMessage(MSG.MSG_ON_SEEK);
    }

    public void seekTo(long position) {
        if (player != null && mediaInfo != null ) {
            int max = (int) mediaInfo.getDuration();
            if (max < (position + 500)){
                position=0;
            }
            player.seekTo(position);
        } else {

        }
    }

    protected void showToast(String msg) {
        Log.d(TAG, "showToast" + msg);
        try {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        } catch (Throwable th) {
            th.printStackTrace(); // fuck oppo
        }
    }
    private VodPlayerObserver playerObserver = new VodPlayerObserver() {

        @Override
        public void onCurrentPlayProgress(long currentPosition, long duration, float percent, long cachedPosition) {
            LogUtil.i(TAG, "onCurrentPlayProgress"+currentPosition);
        }

        @Override
        public void onSeekCompleted() {
            LogUtil.i(TAG, "onSeekCompleted");
//            mHandler.sendEmptyMessageDelayed(SHOW_PROGRESS, 1000);
        }

        @Override
        public void onCompletion() {
            LogUtil.i(TAG, "onCompletion");
            if(mHandler != null){
                mHandler.sendEmptyMessage(MSG.MSG_ON_COMPLETION);
            }
        }

        @Override
        public void onAudioVideoUnsync() {
//            showToast("音视频不同步");
        }

        @Override
        public void onNetStateBad() {
        }

        @Override
        public void onDecryption(int ret) {
        }

        @Override
        public void onPreparing() {
//            Log.e(TAG,"onPreparing:");
        }

        @Override
        public void onPrepared(MediaInfo info) {
            mediaInfo = info;
            Log.e(TAG,"onPrepared:"+info.getDuration());
            if(mContext != null){
                isPlayCompletion = false;
               mHandler.sendMessage(mHandler.obtainMessage(MSG.MSG_ON_INIT,info.getDuration()));
               try {
                   mHandler.removeMessages(MSG.VIEW_CLOSE_LOADING);
                   mHandler.sendEmptyMessageDelayed(MSG.VIEW_CLOSE_LOADING, 100);

               }catch (Exception ignored){}

            }
        }

        @Override
        public void onError(int code, int extra) {
            Log.e(TAG,"onError:"+code);
//            mBuffer.setVisibility(View.INVISIBLE);
            if (mContext != null){
                mHandler.sendMessage(mHandler.obtainMessage(MSG.MSG_ON_ERROR,code));
            }
            if (code == CauseCode.CODE_VIDEO_PARSER_ERROR) {
                showToast("视频解析出错");
            } else {
//                AlertDialog.Builder build = new AlertDialog.Builder(mContext);
//                build.setTitle("播放错误").setMessage("错误码：" + code).setPositiveButton("确定", null).setCancelable(false)
//                        .show();
            }
//            pause();

        }

        @Override
        public void onFirstVideoRendered() {
//            showToast("视频第一帧已解析");

        }

        @Override
        public void onFirstAudioRendered() {
            //            showToast("音频第一帧已解析");
        }

        @Override
        public void onBufferingStart() {
            //缓冲开始
//            mBuffer.setVisibility(View.VISIBLE);
            Log.e(TAG,"onBufferingStart");
            mHandler.sendEmptyMessageDelayed(MSG.VIEW_SHOW_LOADING,10);
        }

        @Override
        public void onBufferingEnd() {
            //缓冲结束
//            mBuffer.setVisibility(View.GONE);
            Log.e(TAG,"onBufferingEnd");
            mHandler.sendEmptyMessageDelayed(MSG.VIEW_CLOSE_LOADING,10);
        }

        @Override
        public void onBuffering(int percent) {
            LogUtil.d(TAG, "缓冲中..." + percent + "%");
//            mProgressBar.setSecondaryProgress(percent);
            Log.e(TAG,"onBuffering "+percent);
        }

        @Override
        public void onVideoDecoderOpen(int value) {
//            showToast("使用解码类型：" + (value == 1 ? "硬件解码" : "软解解码"));
        }

        @Override
        public void onStateChanged(StateInfo stateInfo) {
        }


        @Override
        public void onHttpResponseInfo(int code, String header) {
            Log.i(TAG, "onHttpResponseInfo,code:" + code + " header:" + header);
        }
    };
    //处理与电话逻辑
    private Observer<Integer> localPhoneObserver = new Observer<Integer>() {

        @Override
        public void onEvent(Integer phoneState) {
            if (phoneState == TelephonyManager.CALL_STATE_IDLE) {
                player.start();
            } else if (phoneState == TelephonyManager.CALL_STATE_RINGING) {
                player.stop();
            } else {
                Log.i(TAG, "localPhoneObserver onEvent " + phoneState);
            }

        }
    };
    private void init() {
        SDKOptions config = new SDKOptions();
        //是否开启动态加载功能，默认关闭
        //        config.dynamicLoadingConfig = new NEDynamicLoadingConfig();
        //        config.dynamicLoadingConfig.isDynamicLoading = true;
        //        config.dynamicLoadingConfig.isArmeabiv7a = true;
        //        config.dynamicLoadingConfig.armeabiv7aUrl = "your_url";
        //        config.dynamicLoadingConfig.onDynamicLoadingListener = mOnDynamicLoadingListener;
        // SDK将内部的网络请求以回调的方式开给上层，如果需要上层自己进行网络请求请实现config.dataUploadListener，
        // 如果上层不需要自己进行网络请求而是让SDK进行网络请求，这里就不需要操作config.dataUploadListener
//        config.dataUploadListener = mOnDataUploadListener;
        //是否支持H265解码回调
//        config.supportDecodeListener = mOnSupportDecodeListener;
        //这里可以绑定客户的账号系统或device_id，方便出问题时双方联调
        //        config.thirdUserId = "your_id";
        config.privateConfig = new NEPlayerConfig();
        PlayerManager.init(mContext, config);
        SDKInfo sdkInfo = PlayerManager.getSDKInfo(mContext);
        Log.i(TAG, "NESDKInfo:version" + sdkInfo.version + ",deviceId:" + sdkInfo.deviceId);
    }

}
