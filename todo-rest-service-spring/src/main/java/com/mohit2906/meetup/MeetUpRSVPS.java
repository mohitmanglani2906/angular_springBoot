package com.mohit2906.meetup;

import javax.persistence.Entity;
import javax.persistence.Id;


public class MeetUpRSVPS
{
	
	
	public Object venue_name;
	public Object response;
	public Object member_name;
	public Object picUrl;
	public Object event_name;
	public Object event_url;
	public Object latitude ;
	public Object longitude;
	
	public MeetUpRSVPS() {
		super();
	}
	
	public MeetUpRSVPS(Object venue_name, Object response, Object member_name, Object picUrl,
			Object event_name, Object event_url, Object latitude, Object longitude) {
		super();
		this.venue_name = venue_name;
		this.response = response;
		this.member_name = member_name;
		this.picUrl = picUrl;
		this.event_name = event_name;
		this.event_url = event_url;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Object getVenue_name() {
		return venue_name;
	}

	public void setVenue_name(Object venue_name) {
		this.venue_name = venue_name;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Object getMember_name() {
		return member_name;
	}

	public void setMember_name(Object member_name) {
		this.member_name = member_name;
	}

	public Object getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(Object picUrl) {
		this.picUrl = picUrl;
	}

	public Object getEvent_name() {
		return event_name;
	}

	public void setEvent_name(Object event_name) {
		this.event_name = event_name;
	}

	public Object getEvent_url() {
		return event_url;
	}

	public void setEvent_url(Object event_url) {
		this.event_url = event_url;
	}

	public Object getLatitude() {
		return latitude;
	}

	public void setLatitude(Object latitude) {
		this.latitude = latitude;
	}

	public Object getLongitude() {
		return longitude;
	}

	public void setLongitude(Object longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "MeetUpRSVPS [venue_name=" + venue_name + ", response=" + response + ", member_name=" + member_name
				+ ", picUrl=" + picUrl + ", event_name=" + event_name + ", event_url=" + event_url + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}
	
}
