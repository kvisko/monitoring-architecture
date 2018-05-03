package com.fhwn.ma.frontend.clientapp.Service;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fhwn.ma.frontend.clientapp.ClientApp;
import com.fhwn.ma.frontend.clientapp.Dao.ConfigManager;
import com.fhwn.ma.frontend.clientapp.Dto.ClientConfigDTO;
import com.fhwn.ma.frontend.clientapp.Dto.FrequencyDTO;
import com.fhwn.ma.frontend.clientapp.Entity.ConfigFile;

@Component
public class MainService implements IMainService {
	
	TaskTrigger taskTrigger;
	static ConfigFile config = ConfigManager.readConfigFile();
	
	public MainService() {
		
		this.taskTrigger = new TaskTrigger();
	}
	
	@Override
	public void init(double collectionFrequency, double uploadFrequency) {
		System.out.println("MainService.init");
		
		
		ClientApp.startTaskTrigger();
		
	}
	
	
	@Override
	public void updateFrequencies(FrequencyDTO frequencyDTO) {
		
		System.out.println("MainService.updateFrequencies");

		System.out.println("--- CHANGE FREQUENCY PARAMETERS ---");

		double colFreq = (frequencyDTO.getCollectionFrequency() *1000);
		double uploadFreq = (frequencyDTO.getUploadFrequency() * 1000);
		
		ConfigFile cf = config;
		cf.setDataColFreq(colFreq);
		cf.setDataUploadFreq(uploadFreq);
		try {
			this.saveConfigFile(cf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TaskTrigger taskTrigger = ClientApp.getTaskTrigger();
		
		System.out.println("-- current colFreq: "+taskTrigger.getDataCollectionFrequency()/1000+" second(s) -- new colFreq: "+colFreq/1000+" second(s)");
		System.out.println("-- current upFreq: "+taskTrigger.getDataUploadFrequency()/1000+" second(s)-- new upFreq: "+uploadFreq/1000+" second(s)");
						
		ClientApp.getNewTaskTrigger(colFreq, uploadFreq);
		
		ClientApp.startTaskTrigger();
        
		System.out.println("--- FREQUENCY PARAMETERS CHANGED ---");
	}

	public void saveConfigFile(ConfigFile cf) throws JsonGenerationException, JsonMappingException, IOException {
		
		ConfigManager.saveConfigFile(cf);
		
	}
	
	public void setConfiguration(ClientConfigDTO clientConfigDTO) {
		System.out.println("MainService.setConfiguration");

		System.out.println("CHANGE NETWORK CONFIGURATION PARAMETERS");
		
		ConfigFile cf = config;
		cf.setClient_ip(clientConfigDTO.getIp());
		cf.setClient_port(clientConfigDTO.getPort());
		try {
			this.saveConfigFile(cf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("NETWORK CONFIGURATION PARAMETERS CHANGED");
	}

}
