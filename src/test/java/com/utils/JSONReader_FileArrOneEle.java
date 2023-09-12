package com.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader_FileArrOneEle {
	JSONObject json;
	JSONParser parser = new JSONParser();
	public JSONReader_FileArrOneEle(String jsonfile, String index) throws FileNotFoundException, IOException, ParseException {
		JSONArray jsonarr = (JSONArray) (parser.parse(new FileReader(jsonfile)));
		json = (JSONObject) (jsonarr.get(Integer.parseInt(index))); 
	}
	
	public String getVal(String key) {
		return (String) json.get(key);
	}
}
