package com.fhwn.ma.frontend.clientapp.Entity;

public class ConfigFile {
	
	private Long client_id;
	private String client_alias;
	private String client_ip;
	private String client_port;
	private String server_ip;
	private String server_port;
	private String server_alias;
	private double dataColFreq;
	private double dataUploadFreq;
	
	public ConfigFile() {}

	public Long getClient_id() {
		return client_id;
	}

	public void setClient_id(Long client_id) {
		this.client_id = client_id;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public String getClient_port() {
		return client_port;
	}

	public void setClient_port(String client_port) {
		this.client_port = client_port;
	}

	public String getServer_ip() {
		return server_ip;
	}

	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	public String getServer_port() {
		return server_port;
	}

	public void setServer_port(String server_port) {
		this.server_port = server_port;
	}

	public double getDataColFreq() {
		return dataColFreq;
	}

	public void setDataColFreq(double dataColFreq) {
		this.dataColFreq = dataColFreq;
	}

	public double getDataUploadFreq() {
		return dataUploadFreq;
	}

	public void setDataUploadFreq(double dataUploadFreq) {
		this.dataUploadFreq = dataUploadFreq;
	}

	public String getClient_alias() {
		return client_alias;
	}

	public void setClient_alias(String client_alias) {
		this.client_alias = client_alias;
	}

	public String getServer_alias() {
		return server_alias;
	}

	public void setServer_alias(String server_alias) {
		this.server_alias = server_alias;
	}

	@Override
	public String toString() {
		return "ConfigFile [client_id=" + client_id + ", client_alias=" + client_alias + ", client_ip=" + client_ip
				+ ", client_port=" + client_port + ", server_ip=" + server_ip + ", server_port=" + server_port
				+ ", server_alias=" + server_alias + ", dataColFreq=" + dataColFreq + ", dataUploadFreq="
				+ dataUploadFreq + "]";
	}

}
