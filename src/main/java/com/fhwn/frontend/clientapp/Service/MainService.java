package com.fhwn.frontend.clientapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fhwn.frontend.clientapp.MonitoringArchApplication;
import com.fhwn.frontend.clientapp.Dto.ClientConfigDTO;
import com.fhwn.frontend.clientapp.Dto.FrequencyDTO;

@Component
public class MainService implements IMainService {
	
	TaskTrigger taskTrigger;
	
	public MainService() {
		
		this.taskTrigger = new TaskTrigger();
	}
	
	@Override
	public void init(double collectionFrequency, double uploadFrequency) {
		System.out.println("MainService.init");
		
		MonitoringArchApplication.startTaskTrigger();
		
	}
	
	@Override
	public void updateFrequencies(FrequencyDTO frequencyDTO) {
		
		System.out.println("MainService.updateFrequencies");

		System.out.println("--- CHANGE FREQUENCY PARAMETERS ---");

		double colFreq = (frequencyDTO.getCollectionFrequency() *1000);
		double uploadFreq = (frequencyDTO.getUploadFrequency() * 1000);

		TaskTrigger taskTrigger = MonitoringArchApplication.getTaskTrigger();
		
		System.out.println("-- current colFreq: "+taskTrigger.getDataCollectionFrequency()/1000+" second(s) -- new colFreq: "+colFreq/1000+" second(s)");
		System.out.println("-- current upFreq: "+taskTrigger.getDataUploadFrequency()/1000+" second(s)-- new upFreq: "+uploadFreq/1000+" second(s)");
						
		MonitoringArchApplication.getNewTaskTrigger(colFreq, uploadFreq);
		
		MonitoringArchApplication.startTaskTrigger();
        
		System.out.println("--- FREQUENCY PARAMETERS CHANGED ---");
	}

	
	public void setConfiguration(ClientConfigDTO clientConfigDTO) {
		System.out.println("MainService.setConfiguration");

		System.out.println("CHANGE NETWORK CONFIGURATION PARAMETERS");
		
		System.getProperties().put("server.address", clientConfigDTO.getIp());
		System.getProperties().put( "server.port", clientConfigDTO.getPort());
		
		System.out.println("NETWORK CONFIGURATION PARAMETERS CHANGED");
	}

}
