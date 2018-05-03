package com.fhwn.ma.frontend.clientapp.Dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fhwn.ma.frontend.clientapp.Entity.ConfigFile;

@Component
public class ConfigManager {
	
	static String respath = "/json/config.json";

	public ConfigManager() {
	}
	
	public static ConfigFile readConfigFile() {
		
		ConfigFile config = null;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		
		
		
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
	
	public static void saveConfigFile(ConfigFile configFile) throws JsonGenerationException, JsonMappingException, IOException {
		
		System.out.println("ConfigManager.saveConfigFile");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);

		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
		
		Path newFilePath = Paths.get("src/main/resources/"+respath);
		
		writer.writeValue(new File(newFilePath.toString()), configFile);
		
		System.out.println("ConfigManager.saveConfigFile done!");

		
	}
	

}
