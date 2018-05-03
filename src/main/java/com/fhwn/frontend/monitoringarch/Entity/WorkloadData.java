package com.fhwn.frontend.monitoringarch.Entity;

import java.util.Date;

public class WorkloadData {
	
	private double cpuUsage;
	private double memoryUsage;
	private Date timestamp;
	
	
	public double getCpuUsage() {
		return cpuUsage;
	}
	public void setCpuUsage(double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	public double getMemoryUsage() {
		return memoryUsage;
	}
	public void setMemoryUsage(double memoryUsage) {
		this.memoryUsage = memoryUsage;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public double getCpuUsageInPercentage(){
        return this.cpuUsage*100;
    }

    public double getMemoryUsageByDataUnit(String dataUnit){
        double memoryUsageByDataUnit = 0;
        double mb=1024*1024;

        if(dataUnit == "mb"){
            memoryUsageByDataUnit = this.memoryUsage/mb;
        }
        return memoryUsageByDataUnit;
    }

    public double getMemoryUsageInPercentage(){
        double memoryUsageInPercentage = 0;
        // prvi korak saznaj ukupnu kolicinu rama
        // drugi korak uzmi trenutni usage
        // treci korak konvertuj u procente

        return memoryUsageInPercentage;
    }

    @Override
    public String toString(){

        StringBuilder builder = new StringBuilder("timestamp: ");
        builder.append(this.getTimestamp());
        builder.append("  cpu usage: ");
        builder.append(this.getCpuUsageInPercentage());
        builder.append("  ram memory: ");
        builder.append(this.getMemoryUsageByDataUnit("mb"));

        return builder.toString();

    }

}
