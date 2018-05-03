package com.fhwn.ma.frontend.clientapp.Dao;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fhwn.ma.frontend.clientapp.Entity.ConfigFile;

@Component
public class ConfigManager {
	

	public ConfigManager() {
	}
	
	public static ConfigFile readConfigFile() {
		
		ConfigFile config = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		
		String respath = "/json/config.json";

		
		TypeReference<ConfigFile> typeReference = new TypeReference<ConfigFile>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream(respath);
		
		if ( inputStream == null )
			System.out.println("resource not found: " + respath);
		
		try {
			config = mapper.readValue(inputStream, typeReference);
			System.out.println("ConfigFile read!");
			System.out.println(config);
			
			return config;
			
		} catch (IOException e){
			System.out.println("Unable to read config file: " + e.getMessage());
		}
		
		return new ConfigFile();
		
	}
	
	public void saveConfigFile() {}
	

}
