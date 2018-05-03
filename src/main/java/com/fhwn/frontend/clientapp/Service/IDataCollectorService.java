package com.fhwn.frontend.clientapp.Service;

import com.fhwn.frontend.clientapp.Dto.WorkloadDTO;
import com.fhwn.frontend.clientapp.Entity.WorkloadData;

public interface IDataCollectorService {
	
	WorkloadData getWorkload();
	
	public WorkloadDTO uploadWorkload(WorkloadDTO workloadDTO);

}
