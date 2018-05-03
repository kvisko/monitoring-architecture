package com.fhwn.ma.frontend.clientapp.WebService;

import org.springframework.http.ResponseEntity;

import com.fhwn.ma.frontend.clientapp.Dto.FrequencyDTO;
import com.fhwn.ma.frontend.clientapp.Dto.WorkloadDTO;

public interface IServiceCommunicator {
	
	void uploadWorkloadDTO(WorkloadDTO data);
	
	ResponseEntity<?> setFrequencies(FrequencyDTO frequencyDTO);
	
	ResponseEntity<?> changeFrequencies(FrequencyDTO frequencyDTO);
	
	Double echoResponse(Double val);


}
