package com.bokecc.livemodule.bean;

/**
 * @desc 迈视播放器参数
 */
public class CCVideoInfo {
    private boolean isShowBack;
    private String roomId;

    private String userId;
    private String liveId;
    private String nickName;
    private String password;
    private int netType;
    private String coverImage;

    public boolean isShowBack() {
        return isShowBack;
    }

    public void setShowBack(boolean showBack) {
        isShowBack = showBack;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNetType() {
        return netType;
    }

    public void setNetType(int netType) {
        this.netType = netType;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
