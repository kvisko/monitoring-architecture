package com.fhwn.ma.frontend.clientapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fhwn.ma.frontend.clientapp.Dao.ConfigManager;
import com.fhwn.ma.frontend.clientapp.Entity.ConfigFile;
import com.fhwn.ma.frontend.clientapp.Service.MainService;
import com.fhwn.ma.frontend.clientapp.Service.TaskTrigger;
import com.fhwn.ma.frontend.clientapp.WebService.ServiceCommunicator;

@SpringBootApplication
public class ClientApp {

	ServiceCommunicator serviceCommunicator;
	//static ConfigManager configMgr = new ConfigManager();
	static ConfigFile config = ConfigManager.readConfigFile();
	
	static TaskTrigger taskTrigger = new TaskTrigger(config.getDataColFreq(), config.getDataUploadFreq());

	public static TaskTrigger getTaskTrigger() {
		return taskTrigger;
	}

	public static TaskTrigger getNewTaskTrigger(double colFreq, double uploadFreq) {

		System.out.println("MonitoringArchApplication.getNewTaskTrigger");

		taskTrigger.cancel();

		taskTrigger = new TaskTrigger(colFreq, uploadFreq);
		return taskTrigger;
	}

	public static void startTaskTrigger() {

		System.out.println("MonitoringArchApplication.startTaskTrigger");

		taskTrigger.execute();

	}

	public static void main(String[] args) {
		SpringApplication.run(ClientApp.class, args);
		System.out.println("SpringApplication.run");
		

		MainService mainService = new MainService();
		
		Long clientId = config.getClient_id();

		// proveriti da li je klijent registrovan i vratiti id parametar, koji se
		// kasnije prosledjuje serviceCommunicatoru
		// FrequencyDTO frequencyDTO = serviceCommunicator.getClientSettingsById(5L);
		
		 //MonitoringArchApplication.startTaskTrigger();

		 mainService.init(config.getDataColFreq(), config.getDataUploadFreq());

		System.out.println("SpringApplication.run.....");
	}
}
