package com.fhwn.frontend.monitoringarch.Service;

import com.fhwn.frontend.monitoringarch.Dto.FrequencyDTO;

public interface IMainService {
	
	void updateFrequencies(FrequencyDTO frequencyDTO);
	
	void init(double collectionFrequency, double uploadFrequency);

}
