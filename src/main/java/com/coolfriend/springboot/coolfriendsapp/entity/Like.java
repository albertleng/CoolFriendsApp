package com.coolfriend.springboot.coolfriendsapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="likes")
public class Like {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="like_user_id")
	private int likeUserId;
	
	@Column(name="liked_user_id")
	private int likedUserId;
	
	@Column(name="read_status")
	private boolean readStatus;
	
	@Column(name="event_datetime")
	private Date dateTime;
	
	@Column(name="like_name")
	private String likeName;
	
	@Column(name="liked_name")
	private String likedName;
	
	public Like() {
		
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLikeUserId() {
		return likeUserId;
	}

	public void setLikeUserId(int likeUserId) {
		this.likeUserId = likeUserId;
	}

	public int getLikedUserId() {
		return likedUserId;
	}

	public void setLikedUserId(int likedUserId) {
		this.likedUserId = likedUserId;
	}

	public boolean getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(boolean readStatus) {
		this.readStatus = readStatus;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}


	public String getLikeName() {
		return likeName;
	}


	public void setLikeName(String likeName) {
		this.likeName = likeName;
	}


	public String getLikedName() {
		return likedName;
	}


	public void setLikedName(String likedName) {
		this.likedName = likedName;
	}


	@Override
	public String toString() {
		return "Like [id=" + id + ", likeUserId=" + likeUserId + ", likedUserId=" + likedUserId + ", readStatus="
				+ readStatus + ", dateTime=" + dateTime + ", likeName=" + likeName + ", likedName=" + likedName + "]";
	}

	
}
