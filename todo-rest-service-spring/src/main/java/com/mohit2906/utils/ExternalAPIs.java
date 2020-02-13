package com.mohit2906.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


public class ExternalAPIs
{
	public WebClient.Builder webClientBuilder = WebClient.builder();
	
	public LinkedHashMap<String,Object> getMeetUpApiResponse(){
		
		return (LinkedHashMap<String, Object>) webClientBuilder.build()
			.get()
			.uri("http://stream.meetup.com/2/rsvps")
			.retrieve()
			.bodyToFlux(Object.class)
			.blockFirst();
		
	}
	
	public JSONObject getMeetUpApiCustomResponse() {
		Map<String,Object> meetUpResponse = getMeetUpApiResponse();
		JSONObject customObj = new JSONObject();
		System.out.println(meetUpResponse);
		meetUpResponse.forEach((key,value) -> {
			if(key == "venue" || key == "response" || key == "member" || key == "event") {
				System.out.println(key.getClass().getName() + " ---------- > " + value.getClass().getName());
				customObj.put(key, value);
			}
		});
		
//		System.out.println(customObj);
		return customObj;
		
	}
	
}
