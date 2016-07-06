package com.stories.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Story {
	private String owner;
	private String title;
	private long timestamp;
	private String description;
	private long upvote;
	
	public Story(String owner, String title, String description){
		this.owner = owner;
		this.title = title;
		this.description = description;
		this.setTimestamp(System.currentTimeMillis());
		this.upvote = 0;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUpvote() {
		return upvote;
	}

	public void setUpvote(long upvote) {
		this.upvote = upvote;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
