package com.fhwn.frontend.clientapp;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fhwn.frontend.clientapp.Dto.FrequencyDTO;
import com.fhwn.frontend.clientapp.Service.MainService;
import com.fhwn.frontend.clientapp.Service.TaskTrigger;
import com.fhwn.frontend.clientapp.WebService.ServiceCommunicator;

@SpringBootApplication
public class MonitoringArchApplication {

	ServiceCommunicator serviceCommunicator;
	static TaskTrigger taskTrigger = new TaskTrigger(2000, 10000);

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
		SpringApplication.run(MonitoringArchApplication.class, args);
		System.out.println("SpringApplication.run");

		MainService mainService = new MainService();

		// 1. pozvati server i iscitati setting za frequencies
		System.out.println("Fetching client frequency settings");

		// proveriti da li je klijent registrovan i vratiti id parametar, koji se
		// kasnije prosledjuje serviceCommunicatoru
		// FrequencyDTO frequencyDTO = serviceCommunicator.getClientSettingsById(5L);
		// mainService.init(frequencyDTO.getCollectionFrequency(),
		// frequencyDTO.getUploadFrequency());
		
		 //MonitoringArchApplication.startTaskTrigger();

		 mainService.init(2000, 10000);

		System.out.println("SpringApplication.run.....");
	}
}
