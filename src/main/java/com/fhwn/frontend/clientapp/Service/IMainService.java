package com.fhwn.frontend.clientapp.Service;

import com.fhwn.frontend.clientapp.Dto.FrequencyDTO;

public interface IMainService {
	
	void updateFrequencies(FrequencyDTO frequencyDTO);
	
	void init(double collectionFrequency, double uploadFrequency);

}
