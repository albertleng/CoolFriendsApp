package com.coolfriend.springboot.coolfriendsapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="comment_user_id")
	private int commentUserId;
	
	@Column(name="commented_user_id")
	private int commentedUserId;
	
	@Column(name="read_status")
	private boolean readStatus;
	
	@Column(name="event_datetime")
	private Date dateTime;
	
	@Column(name="comment_name")
	private String commentName;
	
	@Column(name="commented_name")
	private String commentedName;
	
	@Column(name="message")
	private String message;
	

	
	
	public Comment() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommentUserId() {
		return commentUserId;
	}

	public void setCommentUserId(int commentUserId) {
		this.commentUserId = commentUserId;
	}

	public int getCommentedUserId() {
		return commentedUserId;
	}

	public void setCommentedUserId(int commentedUserId) {
		this.commentedUserId = commentedUserId;
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

	public String getCommentName() {
		return commentName;
	}

	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}

	public String getCommentedName() {
		return commentedName;
	}

	public void setCommentedName(String commentedName) {
		this.commentedName = commentedName;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentUserId=" + commentUserId + ", commentedUserId=" + commentedUserId
				+ ", readStatus=" + readStatus + ", dateTime=" + dateTime + ", commentName=" + commentName
				+ ", commentedName=" + commentedName + ", message=" + message + "]";
	}

	
	

	
}
