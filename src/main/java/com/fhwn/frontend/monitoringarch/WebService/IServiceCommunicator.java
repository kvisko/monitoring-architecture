package com.fhwn.frontend.monitoringarch.WebService;

import org.springframework.http.ResponseEntity;

import com.fhwn.frontend.monitoringarch.Dto.FrequencyDTO;
import com.fhwn.frontend.monitoringarch.Dto.WorkloadDTO;

public interface IServiceCommunicator {
	
	void uploadWorkloadData(WorkloadDTO data);
	
	ResponseEntity<?> setFrequencies(FrequencyDTO frequencyDTO);

}
