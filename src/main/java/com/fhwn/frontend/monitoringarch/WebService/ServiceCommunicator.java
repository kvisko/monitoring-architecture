package com.fhwn.frontend.monitoringarch.WebService;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fhwn.frontend.monitoringarch.MonitoringArchApplication;
import com.fhwn.frontend.monitoringarch.Dto.ClientConfigDTO;
import com.fhwn.frontend.monitoringarch.Dto.FrequencyDTO;
import com.fhwn.frontend.monitoringarch.Dto.WorkloadDTO;
import com.fhwn.frontend.monitoringarch.Entity.WorkloadData;
import com.fhwn.frontend.monitoringarch.Service.TaskTrigger;
import com.fhwn.frontend.monitoringarch.Service.DataCollectorService;
import com.fhwn.frontend.monitoringarch.Service.MainService;
import com.fhwn.frontend.monitoringarch.Util.ConnectionData;

@RestController
@RequestMapping("/client")
public class ServiceCommunicator implements IServiceCommunicator {

	private static final String GET_CLIENT_SETTINGS = "getClienSettings/";
	private static final String UPLOAD_WORKLOAD_DATA = "uploadClientData";
	private static final String GET_CLIENT_BY_ID = "getClientById/";
	private static final String CREATE_NEW_CLIENT = "newClient/";
	private static final String ADD_CLIENT_DATA = "addClientData";
	private static final String CLIENT_DTO = "newClientDTO";

	@Autowired
	public MainService mainService;
	@Autowired
	public DataCollectorService dataCollectorService;

	@Override
	public void uploadWorkloadData(WorkloadDTO clientData) {

		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

		System.out.println("ServiceCommunicator: Begin POST request");
		System.out.println("---SEND WORKLOAD DATA---");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String postUrl = ConnectionData.HOST + ConnectionData.CLIENT + ADD_CLIENT_DATA;

		HttpEntity<Object> request = new HttpEntity<>(clientData, headers);
		restTemplate.exchange(postUrl, HttpMethod.POST, request, new ParameterizedTypeReference<WorkloadDTO>() {
		});

		System.out.println("ServiceCommunicator: Response for Post Request: ");
		System.out.println(request.getBody());
		System.out.println("--- WORKLOAD DATA SENT: "+ clientData.count() +" items sent ---");

		
	}

	public void uploadWorkloadDataTest(WorkloadData data) {

		RestTemplate restTemplate = new RestTemplate();

		System.out.println("Begin POST request");
		String postUrl = ConnectionData.HOST + ConnectionData.CLIENT + CREATE_NEW_CLIENT/* + UPLOAD_WORKLOAD_DATA */;
		// WorkloadData dataTest = new WorkloadData(data);
		// WorkloadData data = new WorkloadData();
		ResponseEntity<String> postResponse = restTemplate.postForEntity(postUrl, data, String.class);
		System.out.println("Response for Post Request: " + "\n" + postResponse.getBody());
	}
	
	@RequestMapping(value = "/changeFrequencies", method = RequestMethod.POST)
	public ResponseEntity<?> changeFrequencies(@RequestBody FrequencyDTO frequencyDTO) {

		System.out.println("ServiceCommunicator: Accept POST request: setFrequencies");
		
		// TODO poslati trenutne collectedData pre menjanja freqs
		
		mainService.updateFrequencies(frequencyDTO);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/setFrequencies", method = RequestMethod.POST)
	public ResponseEntity<?> setFrequencies(@RequestBody FrequencyDTO frequencyDTO) {

		System.out.println("ServiceCommunicator: Accept POST request: setFrequencies");
		
		System.out.println("--- CHANGE FREQUENCY PARAMETERS ---");

		double colFreq = (frequencyDTO.getCollectionFrequency() *1000);
		double uploadFreq = (frequencyDTO.getUploadFrequency() * 1000);

		TaskTrigger taskTrigger = MonitoringArchApplication.getTaskTrigger();
		
		System.out.println("-- current colFreq: "+taskTrigger.getDataCollectionFrequency()/1000+" second(s) -- new colFreq: "+colFreq/1000+" second(s)");
		System.out.println("-- current upFreq: "+taskTrigger.getDataUploadFrequency()/1000+" second(s)-- new upFreq: "+uploadFreq/1000+" second(s)");
						
		MonitoringArchApplication.getNewTaskTrigger(colFreq, uploadFreq);
		
		MonitoringArchApplication.startTaskTrigger();
		
		System.out.println("--- FREQUENCY PARAMETERS CHANGED ---");

		return new ResponseEntity<>(HttpStatus.OK);
	}

	public FrequencyDTO getClientSettingsById(Long id) {

		// retrieving current frequency values from the database

		RestTemplate restTemplate = new RestTemplate();

		System.out.println("Begin GET request");
		String getUrl = ConnectionData.HOST + ConnectionData.CLIENT + GET_CLIENT_SETTINGS + id;
		ResponseEntity<FrequencyDTO> getResponse = restTemplate.getForEntity(getUrl, FrequencyDTO.class);
		System.out.println("Response for GET Request: " + getResponse.getBody());

		FrequencyDTO frequencyDTO = getResponse.getBody();
		double dataCollectionFrequency = frequencyDTO.getCollectionFrequency();
		double dataUploadFrequency = frequencyDTO.getUploadFrequency();

		FrequencyDTO frequencyDTOsettings = new FrequencyDTO(dataCollectionFrequency, dataUploadFrequency);

		return frequencyDTOsettings;
	}

	@RequestMapping(value = "sendEcho", method = RequestMethod.POST)
	public Double sendEcho(@RequestBody Double value) {

		System.out.println("Incoming echo request");

		Double responseValue = value * 2;

		return responseValue;

	}

	@RequestMapping(value = "setConfiguration", method = RequestMethod.POST)
	public ResponseEntity<?> setConfiguration(@RequestBody ClientConfigDTO clientConfigDTO) {

		mainService.setConfiguration(clientConfigDTO);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
