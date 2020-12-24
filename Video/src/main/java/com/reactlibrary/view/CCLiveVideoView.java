package com.reactlibrary.view;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bokecc.livemodule.live.DWLiveCoreHandler;
import com.bokecc.livemodule.live.DWLiveVideoListener;
import com.bokecc.livemodule.view.ResizeTextureView;
import com.bokecc.sdk.mobile.live.DWLive;
import com.bokecc.sdk.mobile.live.DWLivePlayer;
import com.bokecc.sdk.mobile.live.Exception.DWLiveException;
import com.reactlibrary.utils.ImageLoaders;
import com.yd.video.R;

import java.io.IOException;
import java.util.Calendar;

import tv.danmaku.ijk.media.player.IMediaPlayer;


/**
 * CC 直播视频展示控件
 * 说明: 此处存在Surface动态初始化失败的问题，后续考虑怎么优化
 *
 */
public class CCLiveVideoView extends RelativeLayout implements DWLiveVideoListener {
    private final String TAG = CCLiveVideoView.class.getSimpleName();
    private ImageView liveBg;

    private interface MSG {
        int MSG_ON_INIT = 1;
        int MSG_ON_STOP = 2;
        int MSG_ON_POSITION = 3;
        int MSG_ON_VIDEOSIZE = 4;
        int MSG_ON_PAGE = 5;
        int MSG_ON_SEEK = 6;
        int MSG_ON_AUDIOLEVEL = 7;
        int MSG_ON_ERROR = 8;
        int MSG_ON_PAUSE = 9;
        int MSG_ON_RESUME = 10;
        int MSG_ON_HID = 11;
        int MSG_ON_SHOW = 12;

        int VIEW_CLOSE_LOADING = 22;
    }
    private Activity mContext;

    private WindowManager wm;

    private View mRootView;

    /**
     * 视频显示容器
     */
    private ResizeTextureView mVideoContainer;

    /**
     * 视频加载进度
     */
    private ProgressBar mVideoProgressBar;

    /**
     * 直播播放器
     */
    private DWLivePlayer player;

    private Surface surface;

    /**
     * 缓存切换视频文档时的图像防止黑屏
     */
    private Bitmap tempBitmap;

    /**
     * 直播视频通知接口
     */
    private OnPreparedCallback preparedCallback;

    /**
     * 添加此字段的意义在于：
     * 部分手机HOME到桌面回来时不触发onSurfaceTextureAvailable，需要由onResume来触发一次调用逻辑。
     * 此字段在调用开始播放的时候使用，后面无论播放是否开始都需要在合适的时机恢复为false.
     */
    boolean hasCallStartPlay = false;
    private View mVideoNoplayTip;
    private Handler myHandler;

    private ImageView fullScreen;
    private ImageView backImageView;
    LinearLayout topLay;
    RelativeLayout bottomLay;
    private String orientation = "PORTRAIT";
    private boolean isShowBack = false;
    private long delayTime = 3000;
    private AlphaAnimation alphaAniHide;

    private int startLiveTime;
    private int endLiveTime;


    /**
     * 直播视频通知接口
     */
    public interface OnPreparedCallback {
        /**
         * 视频播放器已经转备好了
         *
         * @param videoView
         */
        void onPrepared(CCLiveVideoView videoView);
    }

    public CCLiveVideoView(Activity context) {
        super(context);
        initViews(context);
    }

    public CCLiveVideoView(Activity context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public CCLiveVideoView(Activity context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    public void initViews(Activity context) {
        this.mContext = context;
        inflateViews();
        initPlayer();
    }

    /**
     * 初始化视图对象
     */
    private void inflateViews() {
        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.live_video_view_cc, null);
        mVideoContainer = mRootView.findViewById(R.id.live_video_container);
        mVideoProgressBar = mRootView.findViewById(R.id.video_progressBar);
        mVideoNoplayTip = mRootView.findViewById(R.id.tv_video_no_play_tip);
        mVideoNoplayTip.setVisibility(GONE);
        mVideoContainer.setOnClickListener(myOnclick);
        fullScreen = (ImageView) mRootView.findViewById(R.id.fullScreen);
        fullScreen.setOnClickListener(myOnclick);
        backImageView = mRootView.findViewById(R.id.video_back);
        backImageView.setOnClickListener(myOnclick);
        topLay = mRootView.findViewById(R.id.video_top_lay);
        bottomLay = mRootView.findViewById(R.id.video_bottom_lay);
        liveBg = mRootView.findViewById(R.id.live_beijing);
        myHandler = new Handler(callback);
        //隐藏
        alphaAniHide = new AlphaAnimation(1, 0);
        alphaAniHide.setDuration(1000);
        addView(mRootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }
    private Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (mContext == null) {
                return true;
            }
            switch (msg.what) {

                case MSG.MSG_ON_ERROR:
                    int errorCode = (Integer) msg.obj;

                    break;
                case MSG.MSG_ON_HID:
                    bottomLay.setVisibility(View.INVISIBLE);
                    topLay.setVisibility(View.INVISIBLE);
                    break;
                case MSG.MSG_ON_SHOW:
                    bottomLay.setVisibility(View.VISIBLE);
                    topLay.setVisibility(View.VISIBLE);
                    break;
                case MSG.VIEW_CLOSE_LOADING:
                    if (liveBg != null){
                        try {
                            liveBg.startAnimation(alphaAniHide);
                            alphaAniHide.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    liveBg.setVisibility(View.INVISIBLE);

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        }catch (Exception e){
                            liveBg.setVisibility(View.INVISIBLE);
                        }
                    }
                    break;
                default:
                    break;
            }
            return  true;
        }
    };
    private OnClickListener myOnclick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int vId = v.getId();
            if (vId == fullScreen.getId()){
                myHandler.sendEmptyMessage(MSG.MSG_ON_SHOW);
                if ("PORTRAIT".equals(orientation)){
                    lockToLandscape();
                }else{
                    lockToPortrait();
                }

            }else if (vId == backImageView.getId()){
                if ("PORTRAIT".equals(orientation)){//如果在竖屏情况下，点击按钮，是返回操作
//                    if(reactContext != null){
                        destroy();
//                        dispatchEvent("sendbackView",null);
//                    }
                }else{
                    lockToPortrait();
                }
            }else if (vId == mVideoContainer.getId()){
                Log.e(TAG,"mVideoContainer");
                myHandler.removeMessages(MSG.MSG_ON_HID);
                if (bottomLay.getVisibility() == View.INVISIBLE){
                    bottomLay.setVisibility(View.VISIBLE);
                    topLay.setVisibility(View.VISIBLE);
                    myHandler.sendEmptyMessageDelayed(MSG.MSG_ON_HID,delayTime);
                }else{
                    bottomLay.setVisibility(View.INVISIBLE);
                    topLay.setVisibility(View.INVISIBLE);
                }
            }
        }
    };
    /**
     * 初始化播放器
     */
    private void initPlayer() {
        mVideoContainer.setSurfaceTextureListener(surfaceTextureListener);
        player = new DWLivePlayer(mContext);
        player.setOnPreparedListener(onPreparedListener);
        player.setOnInfoListener(onInfoListener);
        player.setOnVideoSizeChangedListener(onVideoSizeChangedListener);
        player.setOnErrorListener(onErrorListener);

        DWLiveCoreHandler dwLiveCoreHandler = DWLiveCoreHandler.getInstance();
        if (dwLiveCoreHandler != null) {
            dwLiveCoreHandler.setPlayer(player);
            dwLiveCoreHandler.setDwLiveVideoListener(this);
        }
    }

    /**
     * 获取播放器视频对象
     */
    public DWLivePlayer getPlayer() {
        return player;
    }

    /**
     * 设置视频播放器准备状态监听回调
     *
     * @param preparedCallback
     */
    public void setPreparedCallback(OnPreparedCallback preparedCallback) {
        this.preparedCallback = preparedCallback;
    }

    /**
     * 视频播放控件进入连麦模式
     *
     * @param isVideoRtc : 是否显示连麦
     */
    public void enterRtcMode(boolean isVideoRtc) {
        // 如果是视频连麦，则将播放器停止
        if (isVideoRtc) {
            player.pause();
            player.stop();
            setVisibility(INVISIBLE);
        } else {
            // 如果是音频连麦，只需将播放器的音频关闭掉
            player.setVolume(0f, 0f);
        }
    }

    /**
     * 视频播放控件退出连麦模式
     */
    public void exitRtcMode() {
        try {
            DWLive.getInstance().restartVideo(surface);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DWLiveException e) {
            e.printStackTrace();
        }
        setVisibility(VISIBLE);
    }

    /**
     * 开始播放
     *
     */
    public synchronized void start() {
        Log.e(TAG,"---start--- hasCallStartPlay: "+hasCallStartPlay);
        if (hasCallStartPlay || null == surface) {
            return;
        }
        hasCallStartPlay = true;
        // 启动直播播放器
        DWLiveCoreHandler.getInstance().start(surface);
        if (mVideoProgressBar != null) {
//            mVideoProgressBar.setVisibility(VISIBLE);
        }

        Calendar cal = Calendar.getInstance();
        startLiveTime = (int)cal.getTimeInMillis() / 1000;//计算开始直播时间
        Log.i(TAG,"Live start() startLiveTime: " + startLiveTime);
    }

    /**
     * 停止播放
     *
     */
    public void stop() {
        DWLiveCoreHandler dwLiveCoreHandler = DWLiveCoreHandler.getInstance();
        if (dwLiveCoreHandler != null) {
            dwLiveCoreHandler.stop();
        }
    }

    public void destroy() {
        if (player != null) {
            player.pause();
            player.stop();
            player.release();
        }
        DWLiveCoreHandler dwLiveCoreHandler = DWLiveCoreHandler.getInstance();
        if (dwLiveCoreHandler != null) {
            dwLiveCoreHandler.destroy();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 文档视频切换时缓存切换前的画面
     *
     */
    public void cacheScreenBitmap() {
        tempBitmap = mVideoContainer.getBitmap();
    }

    /**
     * 恢复暂停时的图像
     *
     */
    public void showPauseCover() {
        if (tempBitmap != null
                && !tempBitmap.isRecycled() && surface != null && surface.isValid()) {
            try {
                int width = mVideoContainer.getWidth();
                int height = mVideoContainer.getHeight();
                RectF rectF = new RectF(0, 0, width, height);
                Canvas canvas = surface.lockCanvas(new Rect(0, 0, width, height));
                if (canvas != null) {
                    canvas.drawBitmap(tempBitmap, null, rectF, null);
                    surface.unlockCanvasAndPost(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != tempBitmap && !tempBitmap.isRecycled()) {
                    try {
                        tempBitmap.recycle();
                        tempBitmap = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {

        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
            Log.i(TAG, "onSurfaceTextureAvailable:");
            surface = new Surface(surfaceTexture);
            if (player.isPlaying()) {
                player.setSurface(surface);
            } else {
                if (hasCallStartPlay) {
                    return;
                }
//                start();
//                hasCallStartPlay = true;
            }
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            surface.release();
            surface = null;
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    };

    /**
     * 直播准备监听器
     */
    IMediaPlayer.OnPreparedListener onPreparedListener = new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(IMediaPlayer mp) {
            Log.e(TAG,"onPrepared:");
            post(new Runnable() {
                @Override
                public void run() {
                    // 准备正常播放了，将字段回归为false;
                    hasCallStartPlay = false;
                    if (null != surface) {
                        player.setSurface(surface);
                    }
                    player.start();
//                    mVideoProgressBar.setVisibility(VISIBLE);
                    // 通知直播视频已经准备就绪
                    if (null != preparedCallback) {
                        preparedCallback.onPrepared(CCLiveVideoView.this);
                    }
                }
            });
            //播放开始，关闭遮罩图片
            myHandler.sendEmptyMessage(MSG.VIEW_CLOSE_LOADING);
        }
    };

    IMediaPlayer.OnInfoListener onInfoListener = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer mp, int what, int extra) {
            switch (what) {
                // 缓冲开始
                case IMediaPlayer.MEDIA_INFO_BUFFERING_START:
//                    mVideoProgressBar.setVisibility(VISIBLE);
                    break;
                // 缓冲结束
                case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                    mVideoProgressBar.setVisibility(GONE);
                    break;
                case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                    mVideoProgressBar.setVisibility(GONE);
                    break;
                default:
                    break;
            }
            return false;
        }
    };

//    @Override
//    protected void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        mVideoContainer.setLayoutParams(getVideoSizeParams());
//    }

    IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sar_num, int sar_den) {
            int videoWidth = mp.getVideoWidth();
            int videoHeight = mp.getVideoHeight();
            if (videoWidth != 0 && videoHeight != 0) {
                mVideoContainer.setVideoSize(videoWidth, videoHeight);
            }
        }
    };
    IMediaPlayer.OnErrorListener onErrorListener = new IMediaPlayer.OnErrorListener(){

        @Override
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
            Log.e(TAG,"onError: i" + i + " i1:"+ i1);
            return false;
        }
    };


    // 判断当前屏幕朝向是否为竖屏
    private boolean isPortrait() {
        int mOrientation = mContext.getResources().getConfiguration().orientation;
        return mOrientation != Configuration.ORIENTATION_LANDSCAPE;
    }

//    /**
//     * 视频等比缩放
//     *
//     */
//    private RelativeLayout.LayoutParams getVideoSizeParams() {
//
//        int width = wm.getDefaultDisplay().getWidth();
//        int height;
//        if (isPortrait()) {
//            height = wm.getDefaultDisplay().getHeight() / 3;  //TODO 可以根据当前布局方式更改此参数
//        } else {
//            height = wm.getDefaultDisplay().getHeight();
//        }
//
//
//        int vWidth = player.getVideoWidth();
//        int vHeight = player.getVideoHeight();
//
//        if (vWidth == 0) {
//            vWidth = 600;
//        }
//        if (vHeight == 0) {
//            vHeight = 400;
//        }
//
//        if (vWidth > width || vHeight > height) {
//            float wRatio = (float) vWidth / (float) width;
//            float hRatio = (float) vHeight / (float) height;
//            float ratio = Math.max(wRatio, hRatio);
//
//            width = (int) Math.ceil((float) vWidth / ratio);
//            height = (int) Math.ceil((float) vHeight / ratio);
//        } else {
//            float wRatio = (float) width / (float) vWidth;
//            float hRatio = (float) height / (float) vHeight;
//            float ratio = Math.min(wRatio, hRatio);
//
//            width = (int) Math.ceil((float) vWidth * ratio);
//            height = (int) Math.ceil((float) vHeight * ratio);
//        }
//
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
//        params.addRule(RelativeLayout.CENTER_IN_PARENT);
//        return params;
//    }

    //------------------------------------- SDK 回调相关 ---------------------------------------
    // 由 DWLiveListener(DWLiveCoreHandler) --> DWLiveVideoListener(LiveVideoView)

    @Override
    public void onStreamEnd(boolean isNormal) {
        Calendar cal = Calendar.getInstance();
        endLiveTime = (int)cal.getTimeInMillis() / 1000;//计算结束直播时间
        Log.i(TAG,"Live onStreamEnd: " + endLiveTime);
        mRootView.post(new Runnable() {
            @Override
            public void run() {
                player.pause();
                player.stop();
                player.reset();
                mVideoProgressBar.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 播放状态
     *
     * @param status : 包括PLAYING, PREPARING共2种状态
     */
    @Override
    public void onLiveStatus(final DWLive.PlayStatus status) {
        if (status == null){
            return;
        }
        Log.e(TAG,"onLiveStatus:"+status);
        mRootView.post(new Runnable() {
            @Override
            public void run() {
                switch (status) {
                    case PLAYING:
                        // 直播正在播放
//                        mVideoProgressBar.setVisibility(VISIBLE);
                        break;
                    case PREPARING:
                        // 直播未开始
                        mVideoProgressBar.setVisibility(GONE);
                        // 如果判断当前直播未开始，也将字段回归为false;
                        hasCallStartPlay = false;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 禁播
     *
     * @param reason : 禁播原因
     */
    @Override
    public void onBanStream(String reason) {
        mRootView.post(new Runnable() {
            @Override
            public void run() {
                // 播放器停止播放
                if (player != null) {
                    player.stop();
                }
                // 隐藏加载控件
                if (mVideoProgressBar != null) {
                    mVideoProgressBar.setVisibility(GONE);
                }
            }
        });
    }

    /**
     * 解禁
     */
    @Override
    public void onUnbanStream() {
        if (surface != null) {
            DWLiveCoreHandler dwLiveCoreHandler = DWLiveCoreHandler.getInstance();
            if (dwLiveCoreHandler != null) {
                dwLiveCoreHandler.start(surface);
            }
        }
    }

    public void lockToPortrait() {
        final Activity activity = mContext;
        if (activity == null) {
            return;
        }
        orientation = "PORTRAIT";
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        backImageView.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        fullScreen.setImageResource(R.mipmap.fullscreen);

    }

    public void lockToLandscape() {
        final Activity activity = mContext;
        if (activity == null) {
            return;
        }
        orientation = "LANDSCAPE";
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        backImageView.setVisibility(View.VISIBLE);
        fullScreen.setImageResource(R.mipmap.smallscreen);
//        if(reactContext != null){
//            WritableMap event = Arguments.createMap();
//            event.putString("type", orientation);
//            dispatchEvent("sendOrientationChange",event);
//        }
    }
//    private void dispatchEvent(String eventName, WritableMap eventData){
//        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
//                getId(),//native和js两个视图会依据getId()而关联在一起
//                eventName,//事件名称
//                eventData
//        );
//    }
    public void setShowBack(boolean isShowBack) {
        this.isShowBack = isShowBack;
        if (backImageView != null)
            backImageView.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
    }
    public void setCoverImage(String coverImgUrl) {
        if (liveBg != null && !TextUtils.isEmpty(coverImgUrl)){
            liveBg.setVisibility(VISIBLE);
            ImageLoaders.displayImage( coverImgUrl,liveBg, 0);
        }
    }
}
