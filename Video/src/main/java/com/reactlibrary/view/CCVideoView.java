package com.reactlibrary.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;

import androidx.loader.content.CursorLoader;

import com.bokecc.sdk.mobile.exception.HuodeException;
import com.bokecc.sdk.mobile.play.DWIjkMediaPlayer;
import com.bokecc.sdk.mobile.play.OnDreamWinErrorListener;
import com.bokecc.sdk.mobile.play.PlayInfo;
import com.reactlibrary.interfaces.IVideoPlayCallBack;
import com.reactlibrary.utils.FLog;
import com.reactlibrary.view.base.VideoBaseView;
import com.yd.video.R;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class CCVideoView extends VideoBaseView   {
    private static final String TAG = CCVideoView.class.getSimpleName();
    private TextureView mTextureView;
    private Surface mSurface;
    private DWIjkMediaPlayer player;
    private IVideoPlayCallBack videoPlayCallBack;//视频播放状态和进度回调
    private String mUrl;
    private Bitmap mPauseCover ;

    public CCVideoView(Context context) {
        super(context);
        setiVideoCommonControl(videoCommonControl);
        initPlayer();
    }

    public CCVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setiVideoCommonControl(videoCommonControl);
        initPlayer();
    }

    public CCVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setiVideoCommonControl(videoCommonControl);
        initPlayer();
    }

    public CCVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setiVideoCommonControl(videoCommonControl);
        initPlayer();
    }

    @Override
    public View getVideoLayout(Context context) {
        View mRootView = LayoutInflater.from(context).inflate(R.layout.vodimvideo, null);

        mTextureView = mRootView.findViewById(R.id.live_video_container);
//        mVideoProgressBar = mRootView.findViewById(R.id.video_progressBar);
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
        try {
            if (player !=null) {
                return (int)player.getCurrentPosition();
            }
        }catch (Exception ignored){

        }
        return 0;
    }

    @Override
    protected int getBufferedPercentage() {
        return 0;
    }

    public void start(String url){
        try {
            if (TextUtils.isEmpty(url))
                return;
            this.mUrl = url;
            player.setVideoPlayInfo(null, null, null, null, mContext);
            player.setDataSource(url);
            player.setSurface(mSurface);
            player.prepareAsync();
        }catch (Exception e){

        }
    }
    public void start( String url ,boolean isAudioMode){
        try {
            if (TextUtils.isEmpty(url))
                return;
            this.mUrl = url;
            player.setVideoPlayInfo(null, null, null, null, mContext);
            player.setDataSource(url);
            player.setSurface(mSurface);
            player.setAudioPlay(isAudioMode);
            player.prepareAsync();
        }catch (Exception e){

        }
    }
    public void resume(){
        if (player != null && !player.isPlaying()){
            player.start();
        }
    }
    public void pause(){
        if (player != null && player.isPlaying()){
            player.pause();
            mPauseCover =  initCover();
            mHandler.sendMessage(mHandler.obtainMessage(MSG.MSG_ON_PAUSE, 0));
        }
    }
    public void stop(){

    }
    public void seekTo(int position){
        if (player != null){
            player.seekTo(position);
        }
    }
    public void setSpeed(float s){
        if (player != null){
            player.setSpeed(s);
        }
    }
    public void switchUrl(String url,boolean isAudioMode){
        if (player != null){
            try {
                this.mUrl = url;

                player.pause();
                player.stop();
                player.reset();
                player.setVideoPlayInfo(null, null, null, null, mContext);
                player.setDataSource(url);
                player.setSurface(mSurface);
                player.prepareAsync();
                player.setSpeed(1);
                player.setAudioPlay(isAudioMode);
                player.prepareAsync();
            }catch (Exception ignored){

            }

        }
    }
    public void release(){
        if (player != null ) {
            player.pause();
            player.release();
        }
        FLog.e(TAG,"release");
    }

    /**
     * 初始化播放器
     */
    private void initPlayer() {
        mTextureView.setSurfaceTextureListener(surfaceTextureListener);
        player = new DWIjkMediaPlayer();
        player.setOnPreparedListener(preparedListener);
        player.setOnInfoListener(infoListener);
        player.setOnBufferingUpdateListener(bufferingUpdateListener);
        player.setOnErrorListener(errorListener);
        player.setOnCompletionListener(completionListener);
//        player.setBufferTimeout(20);

        player.setOnDreamWinErrorListener(dreamWinErrorListener);
        //开启防录屏，会使加密视频投屏功能不能正常使用
//        player.setAntiRecordScreen(this);
        //设置CustomId
        player.setCustomId("HIHA2019");
        //获取字幕信息
//        sv_subtitle.getSubtitlesInfo(player);
        player.setOnSeekCompleteListener(new IMediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
//                if (qaView != null && qaView.isPopupWindowShown()) {
//                    player.pauseWithoutAnalyse();
//                }
            }
        });
//
//        initRoomListener();
    }
    TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {


        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
            mSurface = new Surface(surfaceTexture);
            player.setSurface(mSurface);

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
    /******************************************* 播放器相关监听 ***********************************/

    IMediaPlayer.OnPreparedListener preparedListener = new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(IMediaPlayer mp) {
            if (player != null) {
                PlayInfo playInfo = player.getPlayInfo();
                videoDuration = (int) Math.floor(player.getDuration());

                mHandler.sendMessage(mHandler.obtainMessage(MSG.MSG_ON_INIT, videoDuration));
                mHandler.sendEmptyMessageDelayed(MSG.VIEW_CLOSE_LOADING, 100);
            }
//           player.start();
        }
    };
    IMediaPlayer.OnInfoListener infoListener = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer mp, int what, int extra) {

            switch (what) {
                // 缓冲开始
                case IMediaPlayer.MEDIA_INFO_BUFFERING_START:
                    showProgress();
                    if (mHandler != null)
                        mHandler.sendEmptyMessageDelayed(MSG.VIEW_SHOW_LOADING,10);
                    break;
                // 缓冲结束
                case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                    dismissProgress();
                    if (mHandler != null)
                        mHandler.sendEmptyMessageDelayed(MSG.VIEW_CLOSE_LOADING,10);

                    break;
                default:
                    break;
            }
            return false;
        }
    };
    IMediaPlayer.OnBufferingUpdateListener bufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(IMediaPlayer mp, int percent) {//缓冲进度

        }
    };

    IMediaPlayer.OnErrorListener errorListener = new IMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(IMediaPlayer mp, int what, int extra) {
            dismissProgress();
            mHandler.sendMessage(mHandler.obtainMessage(MSG.MSG_ON_ERROR,what));
            return false;
        }
    };

    IMediaPlayer.OnCompletionListener completionListener = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer mp) {
            dismissProgress();
            mHandler.sendMessage(mHandler.obtainMessage(MSG.MSG_ON_COMPLETION, 0));
            Log.e(TAG,"completionListener::");
        }
    };
    OnDreamWinErrorListener dreamWinErrorListener = new OnDreamWinErrorListener(){
        @Override
        public void onPlayError(HuodeException e) {

        }
    };
    public void showProgress() {
//        if (mVideoProgressBar != null) {
//            mVideoProgressBar.setVisibility(VISIBLE);
//        }
    }

    public void dismissProgress() {
//        if (mVideoProgressBar != null) {
//            mVideoProgressBar.setVisibility(GONE);
//        }
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
                resume();
            }else {
                start(mUrl);
            }
//            dispatchEvent("seedPlayState", null);
            if (videoPlayCallBack != null){
                videoPlayCallBack.startPlay();
            }
        }

        @Override
        public void iPause() {
            pause();
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
            seekTo(pos);
        }

        @Override
        public void iSpeed(float s) {
            setSpeed(s);
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
