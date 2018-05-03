package com.fhwn.ma.frontend.clientapp.Dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fhwn.ma.frontend.clientapp.Entity.WorkloadData;

//@JsonRootName(value = "clientData")
//@JsonPropertyOrder({ "clientData"})
public class WorkloadDTO /*implements Iterable<WorkloadData>*/{
	
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
		for(WorkloadData data:clientData) {
			builder.append(data);
			builder.append("\n");
		}
		return builder.toString();
	}
	
	public int count() {
		
		return this.clientData.size();
	}


//	@Override
//	public Iterator<WorkloadData> iterator() {
//		// TODO Auto-generated method stub
//		return clientData.iterator();
//	}

}
