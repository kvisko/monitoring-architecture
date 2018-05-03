package com.fhwn.ma.frontend.clientapp.Service;

import com.fhwn.ma.frontend.clientapp.Dto.WorkloadDTO;
import com.fhwn.ma.frontend.clientapp.Entity.WorkloadData;

public interface IDataCollectorService {
	
	WorkloadData getWorkload();
	
	public WorkloadDTO uploadWorkload(WorkloadDTO workloadDTO);

}
