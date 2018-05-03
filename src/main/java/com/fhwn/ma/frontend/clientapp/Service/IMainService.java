package com.fhwn.ma.frontend.clientapp.Service;

import com.fhwn.ma.frontend.clientapp.Dto.FrequencyDTO;

public interface IMainService {
	
	void updateFrequencies(FrequencyDTO frequencyDTO);
	
	void init(double collectionFrequency, double uploadFrequency);

}
