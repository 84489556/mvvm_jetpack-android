package com.test;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bokecc.livemodule.bean.CCVideoInfo;
import com.reactlibrary.interfaces.IVideoPlayCallBack;
import com.reactlibrary.utils.FLog;
import com.reactlibrary.view.CCLiveReplayView;
import com.reactlibrary.view.CCVideoView;
import com.yd.video.R;

import java.util.Locale;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class VideoTestActivity extends AppCompatActivity {
    private String TAG = VideoTestActivity.class.getSimpleName();
    CCLiveReplayView videoCcReplay;
    CCVideoView videoCcVideo;
    //大咖
    String dKurl = "http://vodeweylgie.vod.126.net/vodeweylgie/9f3d2d21-52bf-4d92-bdb3-88a53fc21376.mp4";
    //小白
    String xBurl = "http://vodeweylgie.vod.126.net/vodeweylgie/6j2cIVdk_2587964943_hd.mp4";
    //早知晓
    String zZXurl = "http://vodeweylgie.vod.126.net/vodeweylgie/MRBxpPvO_3250009546_hd.mp4";
    //盘后课


    String temp ;

    private int widthPx,heightPx;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_test);
        videoCcReplay = findViewById(R.id.video_cc);
        videoCcVideo = findViewById(R.id.video_cc_video);
        rg = findViewById(R.id.rg);

        DisplayMetrics outMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        getWindowManager().getDefaultDisplay().getRealMetrics(outMetrics);
        this.widthPx = outMetrics.widthPixels;
        this.heightPx = outMetrics.heightPixels;

        initVideo(dKurl);
//        initReplayVideo();
        String style = "light-content";
//        setStatusStyle(style);
    }

    public void setStatusStyle(String style) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = getWindow().getDecorView();
            int systemUiVisibilityFlags = decorView.getSystemUiVisibility();
            if ("dark-content".equals(style)) {
                systemUiVisibilityFlags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                systemUiVisibilityFlags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decorView.setSystemUiVisibility(systemUiVisibilityFlags);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //显示状态栏
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //显示状态栏
            //FLAG_TRANSLUCENT_STATUS
            //FLAG_TRANSLUCENT_STATUS

            ViewGroup.LayoutParams params = videoCcVideo.getVisibility() == View.VISIBLE ? videoCcVideo.getLayoutParams() : videoCcReplay.getLayoutParams();
            params.height = (widthPx * 9) / 16;
            if (videoCcVideo.getVisibility() == View.VISIBLE) {
                videoCcVideo.setLayoutParams(params);
            } else {
                videoCcReplay.setLayoutParams(params);
            }

        }else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //隐藏状态栏
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

            ViewGroup.LayoutParams params = videoCcVideo.getVisibility() == View.VISIBLE ? videoCcVideo.getLayoutParams() : videoCcReplay.getLayoutParams();
            FLog.e(TAG,String.format(Locale.CHINA,"screenWidthDp:%d",newConfig.screenWidthDp));
            params.height = MATCH_PARENT;
            params.width = MATCH_PARENT;
            if (videoCcVideo.getVisibility() == View.VISIBLE) {
                videoCcVideo.setLayoutParams(params);
            } else {
                videoCcReplay.setLayoutParams(params);
            }
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        FLog.e(TAG,"onRestart");
        videoRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FLog.e(TAG,"onResume");

    }
    @Override
    protected void onPause() {
        super.onPause();
        FLog.e(TAG,"onPause");
        videoPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (neVideoView != null){
//            neVideoView.release();
//        }
        if (videoCcReplay != null){
            videoCcReplay.destroy();
        }
        if (videoCcVideo != null){
            videoCcVideo.release();
        }
    }

    private void initReplayVideo() {
        videoCcReplay.setVisibility(View.VISIBLE);
        videoCcVideo.setVisibility(View.GONE);
        videoCcVideo.pause();

        ((RadioButton)rg.getChildAt(3)).setChecked(true);
        ViewGroup.LayoutParams params = videoCcReplay.getLayoutParams();
        params.height = (widthPx * 9) / 16;
        videoCcReplay.setLayoutParams(params);
        //http://ydzb.maxtv.cn/api/view/callback?roomid=70F26B2B620664C59C33DC5901307461&userid=AE903AFD89036D43&liveid=3A437C4A20911511&recordid=5A23AEED7A100C83
        CCVideoInfo info = new CCVideoInfo();
        info.setNickName("123");
        info.setUserId("AE903AFD89036D43");
        info.setRoomId("70F26B2B620664C59C33DC5901307461");
        info.setLiveId("5A23AEED7A100C83");

        videoCcReplay.setParam(info);
        temp = "5A23AEED7A100C83";
        videoCcReplay.doLiveLogin();
//        videoCc.doLiveLogin();
    }
    private void initVideo(String url) {
        videoCcReplay.setVisibility(View.GONE);
        videoCcReplay.pause();

        videoCcVideo.setVisibility(View.VISIBLE);

        ((RadioButton)rg.getChildAt(0)).setChecked(true);
        ViewGroup.LayoutParams params = videoCcVideo.getLayoutParams();
        params.height = (widthPx * 9) / 16;
        videoCcVideo.setLayoutParams(params);
//        neVideoView.setVideoPath("http://vodeweylgie.vod.126.net/vodeweylgie/9f3d2d21-52bf-4d92-bdb3-88a53fc21376.mp4");
//        neVideoView.setVideoPlayCallBack(videoPlayCallBack);
//        neVideoView.start();

        this.temp = url;
        videoCcVideo.start(url);
    }
    private void switchUrl(String url, boolean isAudio){
        this.temp = url;
        if (videoCcVideo.getVisibility() == View.GONE){
            videoCcVideo.setVisibility(View.VISIBLE);
        }

        videoCcVideo.switchUrl(url,isAudio);
    }
    private void videoRestart(){
        if (videoCcVideo.getVisibility() == View.VISIBLE){
            videoCcVideo.resume();
        }else {
            videoCcReplay.resume();
        }
    }
    private void videoPause(){
        if (videoCcVideo.getVisibility() == View.VISIBLE){
            videoCcVideo.pause();
        }else {
            videoCcReplay.pause();
        }
    }

    public void clickView(View clickView) {
        rg.clearCheck();
        ((RadioButton)clickView).setChecked(true);

            String tv = ((TextView) clickView).getText().toString();
            if (TextUtils.equals("大咖",tv)){
                if (TextUtils.equals(dKurl, temp)){
                    return;
                }
                switchUrl(dKurl,false);
            }else if (TextUtils.equals("小白",tv)){
                if (TextUtils.equals(xBurl, temp)){
                    return;
                }
                switchUrl(xBurl,false);
            }else if (TextUtils.equals("早知晓",tv)){
                if (TextUtils.equals(zZXurl, temp)){
                    return;
                }
                switchUrl(zZXurl,true);
            }else if (TextUtils.equals("盘后课",tv)){
                if (TextUtils.equals("5A23AEED7A100C83", temp)){
                    return;
                }
                initReplayVideo();
                // http://ydzb.maxtv.cn/api/view/callback?roomid=70F26B2B620664C59C33DC5901307461&userid=AE903AFD89036D43&liveid=3A437C4A20911511&recordid=5A23AEED7A100C83
//                switchUrl("5A23AEED7A100C83","AE903AFD89036D43","");
            }


    }

    IVideoPlayCallBack videoPlayCallBack = new IVideoPlayCallBack() {
        @Override
        public void prepared(long duration) {

        }

        @Override
        public void startPlay() {

        }

        @Override
        public void pause() {

        }

        @Override
        public void setNetState(int i) {

        }

        @Override
        public void orientationChange(String orientation) {

        }

        @Override
        public void videoPlayFinish(int videoDuration) {

        }

        @Override
        public void progressUpdate(int currentPosition) {

        }
    };
}