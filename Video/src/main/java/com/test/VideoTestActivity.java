package com.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bokecc.livemodule.bean.CCVideoInfo;
import com.reactlibrary.interfaces.IVideoPlayCallBack;
import com.reactlibrary.view.CCVideoLayout;
import com.reactlibrary.view.NEVideoView;
import com.yd.video.R;

public class VideoTestActivity extends AppCompatActivity {
    NEVideoView neVideoView;
    CCVideoLayout videoCc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_test);
        neVideoView = findViewById(R.id.video_ne);
        videoCc = findViewById(R.id.video_cc);

        initVideo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (neVideoView != null){
            neVideoView.release();
        }
        if (videoCc != null){
            videoCc.destroy();
        }
    }

    private void initVideo() {
        neVideoView.setVideoPath("http://vodeweylgie.vod.126.net/vodeweylgie/9f3d2d21-52bf-4d92-bdb3-88a53fc21376.mp4");
        neVideoView.setVideoPlayCallBack(videoPlayCallBack);
        neVideoView.start();

//        CCVideoInfo info = new CCVideoInfo();
//        info.setLiveId("CD428CB9B00F5B28");
//        info.setRoomId("2496D473181E83A19C33DC5901307461");
//        info.setUserId( "AE903AFD89036D43");
//        info.setNetType(1);
//        info.setNickName("123");
//        videoCc.setParam(info);
//        videoCc.start();
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