package com.example.mr.model;

import java.util.Date;

public class Event {
	long id;
	long karId;
	String title;
	boolean allDay;
	String ruanganNama;
	Date start;
	Date end;
	String color;
	String url;
	
	public long getKarId() {
		return karId;
	}
	public void setKarId(long karId) {
		this.karId = karId;
	}
	public String getRuanganNama() {
		return ruanganNama;
	}
	public void setRuanganNama(String ruanganNama) {
		this.ruanganNama = ruanganNama;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isAllDay() {
		return allDay;
	}
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
