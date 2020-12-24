package com.reactlibrary.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.net.ConnectivityManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ProgressBar;

import com.bokecc.livemodule.bean.CCVideoInfo;
import com.bokecc.livemodule.replay.DWReplayCoreHandler;
import com.bokecc.livemodule.replay.DWReplayRoomListener;
import com.bokecc.sdk.mobile.live.Exception.DWLiveException;
import com.bokecc.sdk.mobile.live.pojo.Marquee;
import com.bokecc.sdk.mobile.live.pojo.TemplateInfo;
import com.bokecc.sdk.mobile.live.replay.DWLiveReplay;
import com.bokecc.sdk.mobile.live.replay.DWLiveReplayLoginListener;
import com.bokecc.sdk.mobile.live.replay.DWReplayPlayer;
import com.bokecc.sdk.mobile.live.replay.pojo.ReplayLoginInfo;
import com.reactlibrary.interfaces.IVideoPlayCallBack;
import com.reactlibrary.utils.FLog;
import com.reactlibrary.view.base.VideoBaseView;
import com.yd.video.R;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * CC 回放视频展示控件
 */
public  class CCVideoLayout extends VideoBaseView implements DWReplayRoomListener {
    private static final String TAG = "CCVideoLayout";
    private Context mContext;
    private TextureView mTextureView;
    private ProgressBar mVideoProgressBar;
    private DWReplayPlayer player;

    private SurfaceTexture mSurfaceTexture;
    private int videoDuration = 0 ;
    private Bitmap mPauseCover ;
    private IVideoPlayCallBack videoPlayCallBack;//视频播放状态和进度回调


    public DWReplayPlayer getPlayer() {
        return player;
    }
    public CCVideoLayout(Context context) {
        super(context);
        this.mContext = context;

        setiVideoCommonControl(videoCommonControl);
        initPlayer();
    }

    public CCVideoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        setiVideoCommonControl(videoCommonControl);
        initPlayer();
    }

    public CCVideoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        setiVideoCommonControl(videoCommonControl);
        initPlayer();
    }


    @Override
    public View getVideoLayout(Context context) {
        View mRootView = LayoutInflater.from(context).inflate(R.layout.vodimvideo, null);

        mTextureView = mRootView.findViewById(R.id.live_video_container);
        mVideoProgressBar = mRootView.findViewById(R.id.video_progressBar);
        return mRootView;
    }

    public IVideoPlayCallBack getVideoPlayCallBack() {
        return videoPlayCallBack;
    }

    public void setVideoPlayCallBack(IVideoPlayCallBack videoPlayCallBack) {
        this.videoPlayCallBack = videoPlayCallBack;
    }

    @Override
    protected int getCurrentPosition() {
        if (getPlayer() != null){
            try {
                return (int) getPlayer().getCurrentPosition();
            }catch (Exception ignore){}
        }
        return 0;
    }

    @Override
    protected int getBufferedPercentage() {
        return 0;
    }

    private void initRoomListener() {
        DWReplayCoreHandler dwReplayCoreHandler = DWReplayCoreHandler.getInstance();
        if (dwReplayCoreHandler == null) {
            return;
        }
        dwReplayCoreHandler.setReplayRoomListener(this);
    }
    /**
     * 初始化播放器
     */
    private void initPlayer() {
        mTextureView.setSurfaceTextureListener(surfaceTextureListener);
        player = new DWReplayPlayer(mContext);
        player.setOnPreparedListener(preparedListener);
        player.setOnInfoListener(infoListener);
        player.setOnBufferingUpdateListener(bufferingUpdateListener);
        player.setOnErrorListener(errorListener);
        player.setOnCompletionListener(completionListener);
        player.setBufferTimeout(20);
        DWReplayCoreHandler dwReplayCoreHandler = DWReplayCoreHandler.getInstance();
        if (dwReplayCoreHandler != null) {
            dwReplayCoreHandler.setPlayer(player);
        }

        initRoomListener();
    }
    private IVideoCommonControl videoCommonControl = new IVideoCommonControl() {
        @Override
        public void iPrepared(long duration) {
//            WritableMap event = Arguments.createMap();
//            event.putInt("duration", (int) duration);
//            dispatchEvent("sendPrepared",event);
            if (videoPlayCallBack != null) {
                videoPlayCallBack.prepared(duration);
            }
        }

        @Override
        public void iStart() {
            if (player != null && player.isPlayable()){
                CCVideoLayout.this.start();
            }else {
                stop();
                doLiveLogin();
            }
//            dispatchEvent("seedPlayState", null);
            if (videoPlayCallBack != null){
                videoPlayCallBack.startPlay();
            }
        }

        @Override
        public void iPause() {
            CCVideoLayout.this.pause();
//            dispatchEvent("seedPauseState", null);
            if (videoPlayCallBack != null) {
                videoPlayCallBack.pause();
            }
        }

        @Override
        public void iAboutListPress() {
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
            if (videoPlayCallBack != null) {
                videoPlayCallBack.setNetState(1);
            }
        }

        @Override
        public void iSendOrientationChange(String orientation) {
//            WritableMap event = Arguments.createMap();
//            event.putString("type", orientation);
//            dispatchEvent("sendOrientationChange",event);
            if (videoPlayCallBack != null) {
                videoPlayCallBack.orientationChange(orientation);
            }
        }

        @Override
        public void iVodPlayFinish() {
//            dispatchEvent("sendVodOverState", null);
//            WritableMap event = Arguments.createMap();
//            event.putInt("currentPosition", videoDuration);
            if (videoPlayCallBack != null) {
                videoPlayCallBack.videoPlayFinish(videoDuration);
            }
        }

        @Override
        public void iSeekTo(int pos) {
            CCVideoLayout.this.seekTo(pos);
        }

        @Override
        public void iSpeed(float s) {
            CCVideoLayout.this.setSpeed(s);
        }

        @Override
        public void updatePosition() {
            if (videoPlayCallBack != null) {
                videoPlayCallBack.progressUpdate(getCurrentPosition());
            }
        }

        @Override
        public Bitmap iPauseCoverBitMap() {
            return mPauseCover;
        }
    };
    /**
     * 开始播放
     */
    public void start() {

        if (player == null){
            return;
        }
        if (player.isPlaying()) {
            return;
        }
        Log.e(TAG," start" );
        if (player.isPlayable()){
            //如果是准备完成状态，重新启动下
            player.resume();
            //
            mHandler.sendEmptyMessageDelayed(MSG.VIEW_CLOSE_LOADING,100);
        }else{

            DWReplayCoreHandler dwReplayCoreHandler = DWReplayCoreHandler.getInstance();
            if (dwReplayCoreHandler != null) {
                dwReplayCoreHandler.start(null);
            }
        }

        mHandler.sendMessage(mHandler.obtainMessage(MSG.MSG_ON_RESUME, 0));
        mHandler.sendEmptyMessageDelayed(MSG.VIEW_SHOW_LOADING,10);
    }
    public void setSpeed(float speed){
        player.setSpeed(speed);
    }

    public void pause() {
        if (player.isPlaying()) {

            DWReplayCoreHandler handler = DWReplayCoreHandler.getInstance();
            if (handler != null) {
                Log.e(TAG, " pause");
                handler.pause();
            }
            mPauseCover =  initCover();
            mHandler.sendMessage(mHandler.obtainMessage(MSG.MSG_ON_PAUSE, 0));

        }
    }
    public void stop() {
        Log.e(TAG," stop" );
        if (player != null  ) {
            Log.e(TAG," stop isPlaying");
            DWLiveReplay.getInstance().stop();
        }
    }
    public void seekTo(int msec) {
        if (player != null) {
            player.seekTo(msec);
        }
    }
    public void switchUrl(){
        if (networkType != ConnectivityManager.TYPE_WIFI){
            return;
        }
        //显示加载中
        mHandler.sendEmptyMessage(MSG.VIEW_SHOW_LOADING);
        stop();
        doLiveLogin();
        errorReply = 0;
    }
    public void destroy() {
        Log.e(TAG," destroy" );

        DWReplayCoreHandler dwReplayCoreHandler = DWReplayCoreHandler.getInstance();
        if (dwReplayCoreHandler != null) {
            dwReplayCoreHandler.destroy();
        }

    }

    TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {


        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
            if (mSurfaceTexture != null) {
                mTextureView.setSurfaceTexture(mSurfaceTexture);
            } else {
                mSurfaceTexture = surfaceTexture;
                Surface mSurface = new Surface(surfaceTexture);
                player.updateSurface(mSurface);
            }
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }
    };

    public void showProgress() {
        if (mVideoProgressBar != null) {
            mVideoProgressBar.setVisibility(VISIBLE);
        }
    }

    public void dismissProgress() {
        if (mVideoProgressBar != null) {
            mVideoProgressBar.setVisibility(GONE);
        }
    }

    /******************************************* 播放器相关监听 ***********************************/

    IMediaPlayer.OnPreparedListener preparedListener = new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(final IMediaPlayer mp) {

            mTextureView.post(new Runnable() {
                @Override
                public void run() {
                    DWReplayCoreHandler dwReplayCoreHandler = DWReplayCoreHandler.getInstance();
                    if (dwReplayCoreHandler != null) {
                        dwReplayCoreHandler.replayVideoPrepared();
                    }
                }
            });
        }
    };

    IMediaPlayer.OnInfoListener infoListener = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer mp, int what, int extra) {
            DWReplayCoreHandler dwReplayCoreHandler = DWReplayCoreHandler.getInstance();

            switch (what) {
                // 缓冲开始
                case IMediaPlayer.MEDIA_INFO_BUFFERING_START:
                    showProgress();
                    if (dwReplayCoreHandler != null) {
                        dwReplayCoreHandler.bufferStart();
                    }
                    break;
                // 缓冲结束
                case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                    dismissProgress();
                    if (dwReplayCoreHandler != null) {
                        dwReplayCoreHandler.bufferEnd();
                    }
                    break;
                case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                    dismissProgress();
                    if (dwReplayCoreHandler != null) {
                        dwReplayCoreHandler.onRenderStart();
                    }
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    IMediaPlayer.OnBufferingUpdateListener bufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(IMediaPlayer mp, int percent) {
            DWReplayCoreHandler dwReplayCoreHandler = DWReplayCoreHandler.getInstance();
            if (dwReplayCoreHandler != null) {
                dwReplayCoreHandler.updateBufferPercent(percent);
            }
        }
    };

    IMediaPlayer.OnErrorListener errorListener = new IMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(IMediaPlayer mp, int what, int extra) {
            DWReplayCoreHandler dwReplayCoreHandler = DWReplayCoreHandler.getInstance();
            dismissProgress();
            if (dwReplayCoreHandler != null) {
                dwReplayCoreHandler.playError(what);
            }
            return false;
        }
    };

    IMediaPlayer.OnCompletionListener completionListener = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer mp) {
            dismissProgress();
            DWReplayCoreHandler dwReplayCoreHandler = DWReplayCoreHandler.getInstance();
            if (dwReplayCoreHandler != null) {
                dwReplayCoreHandler.onPlayComplete();
            }
            Log.e(TAG,"completionListener::");
        }
    };
    @Override
    public void videoPrepared() {
        try{
            getPlayer().start();
            //设置视频总时长
            FLog.e(TAG,"----videoPrepared--alltime--"+getPlayer().getDuration());

            videoDuration = (int)Math.floor(getPlayer().getDuration());
            mHandler.sendMessage(mHandler.obtainMessage(MSG.MSG_ON_INIT, videoDuration));
            mHandler.sendEmptyMessageDelayed(MSG.VIEW_CLOSE_LOADING,100);
        }catch (Exception ignored){

        }
    }

    @Override
    public void startRending() {

    }

    @Override
    public void bufferStart() {
        Log.e(TAG,"onBufferingStart");
        if (mHandler != null)
            mHandler.sendEmptyMessageDelayed(MSG.VIEW_SHOW_LOADING,10);
    }

    @Override
    public void bufferEnd() {
        Log.e(TAG,"bufferEnd");
        if (mHandler != null)
            mHandler.sendEmptyMessageDelayed(MSG.VIEW_CLOSE_LOADING,10);
    }

    @Override
    public void updateBufferPercent(int percent) {
        Log.e(TAG,"updateBufferPercent "+percent);
    }

    @Override
    public void showVideoDuration(long playerDuration) {


    }

    @Override
    public void onPlayComplete() {
//        Log.e(TAG,"onPlayComplete---");
        mHandler.sendMessage(mHandler.obtainMessage(MSG.MSG_ON_COMPLETION, 0));
    }

    @Override
    public void onPlayError(int code) {
        Log.e(TAG,"code:"+code);
        mHandler.sendMessage(mHandler.obtainMessage(MSG.MSG_ON_ERROR,code));
    }

    public void init(){
        if (networkType != ConnectivityManager.TYPE_WIFI){
            return;
        }
        doLiveLogin();
    }
    /**
     * 执行直播登录操作
     */
    public void doLiveLogin() {
        FLog.e(TAG,"doLiveLogin");

        if (loginInfo == null ){
            return;
        }
        try {
            mHandler.sendEmptyMessageDelayed(MSG.VIEW_SHOW_LOADING,50);
            // 创建登录信息
            // 设置登录参数
            DWLiveReplay.getInstance().setLoginParams(new DWLiveReplayLoginListener() {

                @Override
                public void onException(final DWLiveException e) {
//                toastMsg( "登录失败" + e.getLocalizedMessage());
                }

                @Override
                public void onLogin(TemplateInfo templateInfo, Marquee marquee) {
                    FLog.e(TAG,"onLogin start");
                    start();
                }

            }, loginInfo);

            // 执行登录操作
            DWLiveReplay.getInstance().startLogin();
        }catch (Exception e){
            Log.e(TAG,"doLiveLogin error");
        }
    }
    ReplayLoginInfo loginInfo;
    public void setParam(CCVideoInfo params) {
        Log.e(TAG,"params"+params);
        String roomId = "";
        String ccId = "AE903AFD89036D43";
        String recordId="";
        String nickName = "";
        String pwd = "";
        String coverImgUrl = null;

//        String recordId = "063B56C7ECB94DE7";
//        String roomId = "683BC9ECAF3BFB6D9C33DC5901307461";
//        String ccId = "AE903AFD89036D43";
//        String nickName = "tt";
//        String pwd = "";
            super.isShowBack = params.isShowBack();
            roomId = params.getRoomId();
            ccId = params.getUserId();
            recordId = params.getLiveId();

            nickName = params.getNickName();
            pwd = params.getPassword();
            netType = params.getNetType();

            coverImgUrl = params.getCoverImage();

        loginInfo = new ReplayLoginInfo();
        loginInfo.setRecordId(recordId);
        loginInfo.setRoomId(roomId);
        loginInfo.setUserId(ccId);
        loginInfo.setViewerName(nickName);
        loginInfo.setViewerToken(pwd);

        super.setCoverImage(coverImgUrl);
//        FLog.e(TAG,"doLiveLogin=="+replayId);
//        doLiveLogin();
    }
    /**
     * 暂停时初始化位图
     */
    public Bitmap initCover() {
        if (mTextureView != null ){
            try {
                Bitmap bitmap = Bitmap.createBitmap(
                        mTextureView.getWidth(), mTextureView.getWidth(), Bitmap.Config.RGB_565);
                return mTextureView.getBitmap(bitmap);
            }catch (Exception ignored){}
        }
        return null;
    }
}
