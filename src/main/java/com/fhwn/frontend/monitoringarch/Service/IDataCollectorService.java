package com.fhwn.frontend.monitoringarch.Service;

import com.fhwn.frontend.monitoringarch.Dto.WorkloadDTO;
import com.fhwn.frontend.monitoringarch.Entity.WorkloadData;

public interface IDataCollectorService {
	
	WorkloadData getWorkload();
	
	public WorkloadDTO uploadWorkload(WorkloadDTO workloadDTO);

}
