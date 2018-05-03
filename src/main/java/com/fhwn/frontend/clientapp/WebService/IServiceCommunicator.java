package com.fhwn.frontend.clientapp.WebService;

import org.springframework.http.ResponseEntity;

import com.fhwn.frontend.clientapp.Dto.FrequencyDTO;
import com.fhwn.frontend.clientapp.Dto.WorkloadDTO;

public interface IServiceCommunicator {
	
	void uploadWorkloadData(WorkloadDTO data);
	
	ResponseEntity<?> setFrequencies(FrequencyDTO frequencyDTO);

}
