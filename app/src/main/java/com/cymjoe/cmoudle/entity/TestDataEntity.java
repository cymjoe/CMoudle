package com.cymjoe.cmoudle.entity;

public class TestDataEntity {

    String downLoadList;
    private long classId;
    private boolean flgLock;
    private String categoryName;
    private int enableType;
    private boolean isCheck;
    private boolean play;
    boolean isShowPlay;
    private int favoriteId;
    private int enable;
    private long countOfLearnPerson;
    private String audioUrl;
    private long enableDate;

    @Override
    public String toString() {
        return "TestDataEntity{" +
                "downLoadList='" + downLoadList + '\'' +
                ", classId=" + classId +
                ", flgLock=" + flgLock +
                ", categoryName='" + categoryName + '\'' +
                ", enableType=" + enableType +
                ", isCheck=" + isCheck +
                ", play=" + play +
                ", isShowPlay=" + isShowPlay +
                ", favoriteId=" + favoriteId +
                ", enable=" + enable +
                ", countOfLearnPerson=" + countOfLearnPerson +
                ", audioUrl='" + audioUrl + '\'' +
                ", enableDate=" + enableDate +
                '}';
    }

    public String getDownLoadList() {
        return downLoadList;
    }

    public void setDownLoadList(String downLoadList) {
        this.downLoadList = downLoadList;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public boolean isFlgLock() {
        return flgLock;
    }

    public void setFlgLock(boolean flgLock) {
        this.flgLock = flgLock;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getEnableType() {
        return enableType;
    }

    public void setEnableType(int enableType) {
        this.enableType = enableType;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public boolean isShowPlay() {
        return isShowPlay;
    }

    public void setShowPlay(boolean showPlay) {
        isShowPlay = showPlay;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public long getCountOfLearnPerson() {
        return countOfLearnPerson;
    }

    public void setCountOfLearnPerson(long countOfLearnPerson) {
        this.countOfLearnPerson = countOfLearnPerson;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public long getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(long enableDate) {
        this.enableDate = enableDate;
    }
}
