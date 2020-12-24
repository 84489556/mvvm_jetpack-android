package com.reactlibrary.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.netease.neliveplayer.playerkit.common.log.LogUtil;
import com.netease.neliveplayer.playerkit.receiver.Observer;
import com.netease.neliveplayer.playerkit.receiver.PhoneCallStateObserver;
import com.netease.neliveplayer.playerkit.sdk.PlayerManager;
import com.netease.neliveplayer.playerkit.sdk.VodPlayer;
import com.netease.neliveplayer.playerkit.sdk.VodPlayerObserver;
import com.netease.neliveplayer.playerkit.sdk.model.MediaInfo;
import com.netease.neliveplayer.playerkit.sdk.model.SDKInfo;
import com.netease.neliveplayer.playerkit.sdk.model.SDKOptions;
import com.netease.neliveplayer.playerkit.sdk.model.StateInfo;
import com.netease.neliveplayer.playerkit.sdk.model.VideoBufferStrategy;
import com.netease.neliveplayer.playerkit.sdk.model.VideoOptions;
import com.netease.neliveplayer.playerkit.sdk.model.VideoScaleMode;
import com.netease.neliveplayer.proxy.config.NEPlayerConfig;
import com.reactlibrary.utils.CacheUtils;

import java.util.Map;

/**
 * 早知晓播放器
 */
public class NEVideoZzxView {
    String TAG = NEVideoZzxView.class.getSimpleName();
    public static final int SHOW_PROGRESS = 0x01;
    public static final int UPDATE_PROGRESS = 0x02;
    public static final int FINISH_PROGRESS = 0x03;
    public static final int RELEASE_PROGRESS = 0x04;
    public static final int PAUSE_PROGRESS = 0x05;

    private boolean mHardware = true;
    protected VodPlayer player;
    protected String mVideoPath=""; //文件路径
    protected boolean isPauseInBackgroud;
    Context mContext;
    private MediaInfo mediaInfo;
    private static NEVideoZzxView mVideoView;
    private Handler myHandler;//首页弹出层回调专用

    public static NEVideoZzxView getInstance(Activity reactContext){
        if (mVideoView == null){
            mVideoView = new NEVideoZzxView(reactContext);
        }
        return mVideoView;
    }

    /**
     * 获得播放器view，首页的布局使用，如果为空，将不显示首页的效果
     * @return
     */
    public static NEVideoZzxView getVideoView(){
        if (mVideoView == null){
            return null;
        }
        if (mVideoView.player == null || mVideoView.mediaInfo == null ){
            mVideoView.release();
            return  null;
        }
       return mVideoView;
    }

    public NEVideoZzxView(Context context) {
        mContext = context;
        init();
        PhoneCallStateObserver.getInstance().observeLocalPhoneObserver(localPhoneObserver, true);

    }
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

        player.registerPlayerObserver(playerObserver, true);
        player.start();
//        player.setupRenderView(textureView, VideoScaleMode.FIT);

    }
    public void setHardwareDecoder(boolean b) {
        mHardware = b;
    }

    public void setEnableBackgroundPlay(boolean b) {
    }
    public void setVideoPath(String playUrl) {
        if (player == null ){
            this.mVideoPath = playUrl;
            initPosition();
            initPlayer();
        }else{
            if(!TextUtils.isEmpty(playUrl) && !playUrl.equals(this.mVideoPath)){
                switchContentUrl(playUrl);
            }
        }
    }
    public void initPosition(){
        //早知晓做进度缓存
        Map data = CacheUtils.getData(mContext);
        if (data.containsKey("url")) {
            String url = (String) data.get("url");
            int cur = (int) data.get("position");
            if (TextUtils.equals(url,mVideoPath)){
                seekTo(cur);
            }
        }
    }

    public void setMediaType(String mediaType) {
    }

    public void start() {
        if (player == null ){
            initPlayer();
        }
        if (player.isPlaying()) {
            return;
        }
        player.registerPlayerObserver(playerObserver, true);
        player.start();

    }

    public void pause() {
        if (player.isPlaying()) {
            player.pause();

        } else {
//            player.start();
        }
        if (myHandler != null){
            myHandler.sendEmptyMessage(PAUSE_PROGRESS);
        }
    }

    /**
     * 只供首页使用，不给rn传状态
     */
    public void startOrPause(){
        if (player.isPlaying()) {
            player.pause();
        } else {
            player.start();
        }
    }

    public void release() {
        nativeRelease();
        if (myHandler != null){
            myHandler.sendEmptyMessage(RELEASE_PROGRESS);
        }

    }
    public void nativeRelease(){
        if (player == null) {
            return;
        }
        try {
            if (mediaInfo != null){//早知晓保存进度
                int anyPosition= (int) player.getCurrentPosition();
                CacheUtils.setData(mContext,anyPosition,mVideoPath);
            }
            LogUtil.i(TAG, "releasePlayer");
            player.registerPlayerObserver(playerObserver, false);
            PhoneCallStateObserver.getInstance().observeLocalPhoneObserver(localPhoneObserver, false);
            player.setupRenderView(null, VideoScaleMode.NONE);

            player.stop();
            player = null;
            mVideoView = null;
            mediaInfo = null;


        }catch (Exception e){

        }

    }
    public void rnRelease(){
        LogUtil.i(TAG, "rnRelease");

    }

    public void switchContentUrl(String url) {
        if (player != null) {
            this.mVideoPath = url;
            initPosition();
            player.switchContentUrl(url);
            player.start();
        }
    }


    public void seekTo(long position) {
        if (player != null ) {
            player.seekTo(position);
        } else {

        }
    }
    private VodPlayerObserver playerObserver = new VodPlayerObserver() {

        @Override
        public void onCurrentPlayProgress(long currentPosition, long duration, float percent, long cachedPosition) {
            LogUtil.i(TAG, "onCurrentPlayProgress"+currentPosition);

            if(myHandler != null) {
                Message msg = new Message();
                msg.obj = currentPosition;
                msg.what = UPDATE_PROGRESS;
                myHandler.sendMessage(msg);
            }
        }

        @Override
        public void onSeekCompleted() {
            LogUtil.i(TAG, "onSeekCompleted");
//            mHandler.sendEmptyMessageDelayed(SHOW_PROGRESS, 1000);
        }

        @Override
        public void onCompletion() {
            LogUtil.i(TAG, "onCompletion");

            Message msg = new Message();
            msg.what = FINISH_PROGRESS;
            if (myHandler != null)
                myHandler.sendMessage(msg);
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
            Log.e(TAG,"onPreparing:");
        }

        @Override
        public void onPrepared(MediaInfo info) {
            mediaInfo = info;
            Log.e(TAG,"onPrepared:"+info.getDuration());

        }

        @Override
        public void onError(int code, int extra) {
            Log.e(TAG,"onError:"+code);
//            mBuffer.setVisibility(View.INVISIBLE);
//            if (code == CauseCode.CODE_VIDEO_PARSER_ERROR) {
//                showToast("视频解析出错");
//            } else {
//                AlertDialog.Builder build = new AlertDialog.Builder(mContext);
//                build.setTitle("播放错误").setMessage("错误码：" + code).setPositiveButton("确定", null).setCancelable(false)
//                        .show();
//            }
            pause();
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
        }

        @Override
        public void onBufferingEnd() {
            //缓冲结束
//            mBuffer.setVisibility(View.GONE);
        }

        @Override
        public void onBuffering(int percent) {
            LogUtil.d(TAG, "缓冲中..." + percent + "%");
//            mProgressBar.setSecondaryProgress(percent);
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
//    private void dispatchEvent(String eventName, WritableMap eventData){
////        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
////                this.reactContext.getId(),//native和js两个视图会依据getId()而关联在一起
////                eventName,//事件名称
////                eventData
////        );
//        if (mEvent != null){
//            mEvent.dispatchEvent(eventName, eventData);
//        }
//    }
    public void setHandler(Handler mHandler) {
        if (myHandler != null){
            myHandler.removeCallbacksAndMessages(null);
            myHandler = null;
        }
        if (player == null || mediaInfo == null){
            return;
        }
        myHandler = mHandler;
        Message msg = new Message();
        mediaInfo.setCurrentPosition(player.getCurrentPosition());
        mediaInfo.setPlaying(player.isPlaying());
        msg.obj = mediaInfo;
        msg.what = SHOW_PROGRESS;
        myHandler.sendMessage(msg);

    }
    public void clearHandler(){
//        showToast("clearHandler");
        if (myHandler != null){
            myHandler.removeCallbacksAndMessages(null);
            myHandler = null;
        }
    }
    private String getTime(int time) {
        return  String.format("%02d", time % 3600 / 60) + ":"
                + String.format("%02d", time % 3600 % 60);
    }
}
