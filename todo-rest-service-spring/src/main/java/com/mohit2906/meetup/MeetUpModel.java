package com.mohit2906.meetup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.json.JSONObject;

import com.mohit2906.utils.JsonObjectConverter;

@Entity
public class MeetUpModel implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	
//	@Convert(converter = JsonObjectConverter.class)
//	@Column(name = "meetupData")
//	private ArrayList<JSONObject> meetupData;
	
	@Convert(converter = JsonObjectConverter.class)
	@Column(name = "meetupData", length = 100000)
	private Map<String,Object> meetupData = new HashMap<>();
	
	public MeetUpModel() {
		super();
	}
	
	public MeetUpModel(Map<String, Object> meetupData) {
		super();
		this.meetupData = meetupData;
	}

//	public MeetUpModel(ArrayList<JSONObject> meetupData) {
//		super();
//		this.meetupData = meetupData;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Map<String, Object> getMeetupData() {
		return meetupData;
	}
	public void setMeetupData(Map<String, Object> meetupData) {
		this.meetupData = meetupData;
	}
	
//	public ArrayList<JSONObject> getMeetupData() {
//		return meetupData;
//	}
//
//	public void setMeetupData(ArrayList<JSONObject> meetupData) {
//		this.meetupData = meetupData;
//	}

}
