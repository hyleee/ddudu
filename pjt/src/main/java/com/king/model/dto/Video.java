package com.king.model.dto;

public class Video {

	private int videoId;
	private String videoTitle;
	private String bodyPart;
	private String youtubeId;
	private String channelName;
	private int viewCount;

	public Video(int videoId, String videoTitle, String bodyPart, String youtubeId, String channelName, int viewCount) {
		super();
		this.videoId = videoId;
		this.videoTitle = videoTitle;
		this.bodyPart = bodyPart;
		this.youtubeId = youtubeId;
		this.channelName = channelName;
		this.viewCount = viewCount;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getBodyPart() {
		return bodyPart;
	}

	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

}
