package com.fhwn.frontend.clientapp.Service;

import java.util.Timer;
import java.util.TimerTask;

import com.fhwn.frontend.clientapp.MonitoringArchApplication;
import com.fhwn.frontend.clientapp.Dto.WorkloadDTO;
import com.fhwn.frontend.clientapp.Entity.WorkloadData;
import com.fhwn.frontend.clientapp.WebService.ServiceCommunicator;

public class TaskTrigger extends TimerTask {

	private WorkloadDTO uploadData = new WorkloadDTO();
	public ServiceCommunicator serviceCommunicator = new ServiceCommunicator();
	private long runCounter = 0;
	private double dataUploadFrequency = 2;
	private double dataCollectionFrequency = 1;
	Timer timer;

	public TaskTrigger() {
	}

	public TaskTrigger(double dataCollectionFrequency, double dataUploadFrequency) {
		this.dataCollectionFrequency = dataCollectionFrequency;
		this.dataUploadFrequency = dataUploadFrequency;
	}

	public void execute() {
		System.out.println("TaskTrigger.execute2");

		TaskTrigger taskTrigger = MonitoringArchApplication.getTaskTrigger();
		timer = new Timer();
		timer.schedule(taskTrigger, 0, (long) this.getDataCollectionFrequency());
		System.out.println("TaskTrigger.run");
		System.out.println("Run settings:");
		System.out.println("Collection freq:" + this.getDataCollectionFrequency()/1000+" second(s)");
		System.out.println("Upload freq:" + this.getDataUploadFrequency()/1000+" second(s)");
	}

	@Override
	public void run() {

		DataCollectorService dataCollectorService = new DataCollectorService();
		WorkloadData currentWorkloadData = dataCollectorService.getWorkload();

		System.out.println("Collected workload data: " + currentWorkloadData);

		if (runCounter >= this.dataUploadFrequency / dataCollectionFrequency) {
			runCounter = 0;

			System.out.println("WorkloadDTO is full, so reset the run counter to 0..");
			uploadData.addWorkloadData(currentWorkloadData);
			// dataCollectorService.uploadTest();
			dataCollectorService.uploadWorkload(uploadData);
			uploadData.removeData();
		} else {
			runCounter += 1;
			uploadData.addWorkloadData(currentWorkloadData);
		}

	}

	public double getDataUploadFrequency() {
		return dataUploadFrequency;
	}

	public void setDataUploadFrequency(double frequency) {
		this.dataUploadFrequency = frequency * 1000;
	}

	public double getDataCollectionFrequency() {
		return dataCollectionFrequency;
	}

	public void setDataCollectionFrequency(double frequency) {
		if (frequency < 0) {
			throw new IllegalArgumentException("data collection frequency must be greater than 0");
		}
		this.dataCollectionFrequency = frequency * 1000;
	}

	public Timer getTimer() {
		return timer;

	}
}
