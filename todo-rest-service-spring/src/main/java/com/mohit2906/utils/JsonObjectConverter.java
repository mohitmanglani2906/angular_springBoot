package com.mohit2906.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter(autoApply = true)
public class JsonObjectConverter implements AttributeConverter<Map<String,Object>, String> {

	ObjectMapper mapper = new ObjectMapper();

	
	@Override
	public String convertToDatabaseColumn(Map<String, Object> attribute) {
		try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(attribute);
        }
        catch (JsonProcessingException e)
        {
        	System.out.println("Json Processing Exception e ___" + e.getMessage());
            return null;
        }
	}

	@Override
	public Map<String, Object> convertToEntityAttribute(String dbData) {
		if(dbData == null)
			return null;
		try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return (Map<String, Object>) objectMapper.readValue(dbData, HashMap.class);
        }
        catch (IOException e) {
        	System.out.println("IO Exception ___ " + e.getMessage());
        }
        return new HashMap<>();
	}
	
	
	
//	  @Override 
//	  public String convertToDatabaseColumn(ArrayList<JSONObject> attribute) {
//	  
//	  if(attribute == null) {
//		  System.out.println("In CNull Value 1");
//		  return null;   
//	  }
//		System.out.println("___ Attribute ___ " + attribute);  
//	  	ObjectMapper mapper = new ObjectMapper();
//	  
//	  try { return mapper.writeValueAsString(attribute); } 
//	  catch(JsonProcessingException e) 
//	  { e.printStackTrace(); }
//	  
//	  	return null;
//	  }
//	  
//	  @Override 
//	  public ArrayList<JSONObject> convertToEntityAttribute(String dbData) 
//	  	{ if(dbData == null) { 
//	  		
//	  		System.out.println("In CNull Value 2");
//	  		return null; 
//	  		
//	  	}
//	  ObjectMapper mapper = new ObjectMapper(); 
//	  try 
//	  { 
//		  return mapper.readValue(dbData.toString(), ArrayList.class); 
//	  }
//	  	catch (JsonMappingException e) { e.printStackTrace(); } 
//	  	catch(JsonProcessingException e) { e.printStackTrace(); }
//	  return null; }
//	  
	 
}
