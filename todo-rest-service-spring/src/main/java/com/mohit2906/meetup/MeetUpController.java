package com.mohit2906.meetup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mohit2906.utils.ExternalAPIs;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class MeetUpController 
{	
	MeetUpRSVPS meetupRsvp  = null;
	@Autowired
	MeetupJpaRepository meetupRepo;
	
	ExternalAPIs externApis = new ExternalAPIs();
	
	@GetMapping("/db/events/meetup")
	public List<MeetUpRSVPS> getAllMeetUpData(){
		JSONObject obj  = null;
		JSONObject obj2 = null;
		List<MeetUpRSVPS> meetupData = new ArrayList<>();
//		ArrayList<JSONObject> meetup = new ArrayList<>();
		Map<String,Object> meetup = new HashMap<String, Object>();
		try {
			String[] keys = {"k1", "k2", "k3", "k4","k5"};
			for(int i=1;i<=50;i++) {
				obj = externApis.getMeetUpApiCustomResponse();
				obj2 = obj;
				meetup.put(Integer.toString(i), obj2.toMap());
				meetupRsvp = new MeetUpRSVPS();
//				System.out.println(obj.get("venue"));
				((Map<String, Object>) obj.get("venue")).forEach((key,value) ->{
					if(key == "venue_name") {
						meetupRsvp.setVenue_name(value);
					}
					else if(key =="lon") {
						meetupRsvp.setLongitude(value);
					}
					else if(key == "lat") {
						meetupRsvp.setLatitude(value);
					}
				});
				
				meetupRsvp.setResponse(obj.get("response"));
				
				((Map<String, Object>) obj.get("member")).forEach((key,value) ->{
					if(key == "member_name") {
						meetupRsvp.setMember_name(value);
					}
					else if(key == "photo") {
						meetupRsvp.setPicUrl(value);
					}
				});
				
				((Map<String, Object>) obj.get("event")).forEach((key,value) ->{
					if(key == "event_name") {
						meetupRsvp.setEvent_name(value);;
					}
					else if(key == "event_url") {
						meetupRsvp.setEvent_url(value);
					}
				});
				System.out.println(meetupRsvp);
				
				meetupData.add(meetupRsvp);
//				Thread.sleep(3000);
				
			}
			
			
//			Set<String> keyset = obj.keySet();
//			
//			keyset.forEach(key -> {
//				System.out.println(key);
//				Map<String, Object> mapObj = (Map<String, Object>) obj.get(key);
//				if(key == "venue") {
//					mapObj.forEach((k1,value) ->{
//						if(k1 == "venue_name") {
//							meetupRsvp.setVenue_name(value);
//						}
//					});
//				}
//				else if(key == "response") {
//					meetupRsvp.setResponse(obj.get("response"));
//				}
//				else if(key == "member") {
//					mapObj.forEach((k1,value) ->{
//						if(k1 == "member_name") {
//							meetupRsvp.setMember_name(value);
//						}
//						else if(k1 == "photo") {
//							meetupRsvp.setPicUrl(value);
//						}
//					});
//				}
//				else if(key == "event") {
//					mapObj.forEach((k1,value) -> {
//						if(k1 == "event_name") {
//							meetupRsvp.setEvent_name(value);
//						}
//						else if(k1 == "event_url") {
//							meetupRsvp.setEvent_url(value);
//						}
//					});
//				}
//				
//			});

					
		}
		catch(Exception e) {
			e.printStackTrace();
//			return List<new MeetUpRSVPS()>;
		}
		
		System.out.println("Above Meet Up");
		System.out.println(meetup.getClass().getName());
		
		meetupRepo.save(new MeetUpModel(meetup));
		return meetupData;
		

	}
}
