package com.fhwn.ma.frontend.clientapp.Service;

import java.util.Date;
import org.springframework.stereotype.Component;

import com.fhwn.ma.frontend.clientapp.Dao.DataCollectorDAO;
import com.fhwn.ma.frontend.clientapp.Dto.WorkloadDTO;
import com.fhwn.ma.frontend.clientapp.Entity.WorkloadData;
import com.fhwn.ma.frontend.clientapp.WebService.ServiceCommunicator;

@Component
public class DataCollectorService implements IDataCollectorService {

	ServiceCommunicator serviceCommunicator = new ServiceCommunicator();

	DataCollectorDAO dataCollectorDao = new DataCollectorDAO();

	@Override
	public WorkloadData getWorkload() {
		WorkloadData data = new WorkloadData();
		Date date = new Date();
		data.setTimestamp(date);
		data.setCpuUsage(dataCollectorDao.getCpuUsage());
		data.setMemoryUsage(dataCollectorDao.getMemoryUsage());

		return data;
	}

	public WorkloadDTO uploadWorkload(WorkloadDTO workloadDTO) {
		System.out.println("DataCollectorService.uploadWorkload");

		System.out.println("---UPLOADING WORKLOAD DATA---");
		serviceCommunicator.uploadWorkloadData(workloadDTO);
		
		return workloadDTO;

	}

	public WorkloadData uploadTest() {
		System.out.println("uploading test variable");

		WorkloadData data = new WorkloadData();
		Date date = new Date();
		data.setTimestamp(date);
		data.setCpuUsage(dataCollectorDao.getCpuUsage());
		data.setMemoryUsage(dataCollectorDao.getMemoryUsage());
		serviceCommunicator.uploadWorkloadDataTest(data);
		System.out.println("returning");
		return data;

	}

}
