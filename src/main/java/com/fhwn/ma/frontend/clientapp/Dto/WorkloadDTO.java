package com.fhwn.ma.frontend.clientapp.Dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fhwn.ma.frontend.clientapp.Entity.WorkloadData;

public class WorkloadDTO {
	
	private Long clientId;
	
	@JsonProperty(value = "clientData")
	private List<WorkloadData> clientData = new ArrayList<>();
	
	public WorkloadDTO() {}
	
	public WorkloadDTO(List<WorkloadData> clientData){
        this.clientData= clientData;
    }
	
	public void removeData() {
		WorkloadData lastWorkloadData = this.clientData.get(this.clientData.size()-1);
		this.clientData.clear();
		this.clientData.add(lastWorkloadData);
	}
	
	public void addWorkloadData(WorkloadData data) {
		this.clientData.add(data);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nClient ID :" + this.getClientId());
		builder.append("\n");
		
		for(WorkloadData data:clientData) {
			builder.append(data);
			builder.append("\n");
		}
		return builder.toString();
	}
	
	public int count() {
		
		return this.clientData.size();
	}


	public Long getClientId() {
		return clientId;
	}


	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
}
