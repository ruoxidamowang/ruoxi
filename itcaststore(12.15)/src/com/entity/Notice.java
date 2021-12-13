package com.entity;

public class Notice {
	private int n_id;
	private String title;
	private String details;
	private String n_time;
	public int getN_id() {
		return n_id;
	}
	public void setN_id(int n_id) {
		this.n_id = n_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getN_time() {
		return n_time;
	}
	public void setN_time(String n_time) {
		this.n_time = n_time;
	}
	public Notice(int n_id, String title, String details, String n_time) {
		super();
		this.n_id = n_id;
		this.title = title;
		this.details = details;
		this.n_time = n_time;
	}
	public Notice() {
		super();
	}
	
	
}
