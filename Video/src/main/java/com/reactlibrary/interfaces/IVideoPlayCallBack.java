package com.reactlibrary.interfaces;

public interface IVideoPlayCallBack {
    void prepared(long duration);

    void startPlay();

    void pause();

    void setNetState(int i);

    void orientationChange(String orientation);

    void videoPlayFinish(int videoDuration);

    void progressUpdate(int currentPosition);
}
