package com.fhwn.ma.frontend.clientapp.WebService;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fhwn.ma.frontend.clientapp.ClientApp;
import com.fhwn.ma.frontend.clientapp.Dto.ClientConfigDTO;
import com.fhwn.ma.frontend.clientapp.Dto.FrequencyDTO;
import com.fhwn.ma.frontend.clientapp.Dto.WorkloadDTO;
import com.fhwn.ma.frontend.clientapp.Entity.WorkloadData;
import com.fhwn.ma.frontend.clientapp.Service.DataCollectorService;
import com.fhwn.ma.frontend.clientapp.Service.MainService;
import com.fhwn.ma.frontend.clientapp.Service.TaskTrigger;
import com.fhwn.ma.frontend.clientapp.Util.ConnectionData;

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
	
	public void uploadWorkloadData(WorkloadData data) {

		RestTemplate restTemplate = new RestTemplate();

		System.out.println("Begin POST request");
		String postUrl = ConnectionData.HOST + ConnectionData.CLIENT + CREATE_NEW_CLIENT/* + UPLOAD_WORKLOAD_DATA */;
		// WorkloadData dataTest = new WorkloadData(data);
		// WorkloadData data = new WorkloadData();
		ResponseEntity<String> postResponse = restTemplate.postForEntity(postUrl, data, String.class);
		System.out.println("Response for Post Request: " + "\n" + postResponse.getBody());
	}
	
	@Override
	public void uploadWorkloadDTO(WorkloadDTO clientData) {

		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

		System.out.println("ServiceCommunicator: Begin POST request");
		System.out.println("---SEND WORKLOAD DATA---");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String postUrl = ConnectionData.HOST + ConnectionData.CLIENT + ADD_CLIENT_DATA;
		
		System.out.println("Sending data to: " + postUrl);
		
		clientData.setClientId(9976L);

		HttpEntity<Object> request = new HttpEntity<>(clientData, headers);
		restTemplate.exchange(postUrl, HttpMethod.POST, request, new ParameterizedTypeReference<WorkloadDTO>() {
		});

		System.out.println("ServiceCommunicator: Response for Post Request: ");
		System.out.println(request.getBody());
		System.out.println("--- WORKLOAD DATA SENT: "+ clientData.count() +" items sent ---");

		
	}

	@RequestMapping(value = "/changeFrequencies", method = RequestMethod.POST)
	@Override
	public ResponseEntity<?> changeFrequencies(@RequestBody FrequencyDTO frequencyDTO) {

		System.out.println("ServiceCommunicator: Accept POST request: setFrequencies");
		
		// TODO poslati trenutne collectedData pre menjanja freqs da se ne bi gubili podaci pri promeni frequencija
		
		mainService.updateFrequencies(frequencyDTO);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(path = "/echoResponse/{val}", method = RequestMethod.GET)
	@Override
	public Double echoResponse(@PathVariable Double val) {

		System.out.println("Incoming echo request..");

		Double responseValue = val * 2;
		
		System.out.println("Echo response is " + responseValue);


		return responseValue;
	}

	@RequestMapping(path = "/setConfiguration/{ip}/{port}", method = RequestMethod.GET)
	public ResponseEntity<?> setConfiguration(@PathVariable String ip, @PathVariable String port) {

		System.out.println("POST: Incoming setConfiguration request..");
		System.out.println("New configuration is:");
		
		ClientConfigDTO clientConfig = new ClientConfigDTO(ip, port);
		System.out.println(clientConfig);

		
		mainService.setConfiguration(clientConfig);
		
		//TODO restartuj application server i aplikaciju da bi uvazila novi port i novi app

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// DEPRECATED
	@RequestMapping(value = "/setFrequencies", method = RequestMethod.POST)
	public ResponseEntity<?> setFrequencies(@RequestBody FrequencyDTO frequencyDTO) {

		System.out.println("ServiceCommunicator: Accept POST request: setFrequencies");
		
		System.out.println("--- CHANGE FREQUENCY PARAMETERS ---");

		double colFreq = (frequencyDTO.getCollectionFrequency() *1000);
		double uploadFreq = (frequencyDTO.getUploadFrequency() * 1000);

		TaskTrigger taskTrigger = ClientApp.getTaskTrigger();
		
		System.out.println("-- current colFreq: "+taskTrigger.getDataCollectionFrequency()/1000+" second(s) -- new colFreq: "+colFreq/1000+" second(s)");
		System.out.println("-- current upFreq: "+taskTrigger.getDataUploadFrequency()/1000+" second(s)-- new upFreq: "+uploadFreq/1000+" second(s)");
						
		ClientApp.getNewTaskTrigger(colFreq, uploadFreq);
		
		ClientApp.startTaskTrigger();
		
		System.out.println("--- FREQUENCY PARAMETERS CHANGED ---");

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
