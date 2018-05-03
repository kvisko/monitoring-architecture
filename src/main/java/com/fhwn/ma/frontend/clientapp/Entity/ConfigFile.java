package com.fhwn.ma.frontend.clientapp.Entity;

public class ConfigFile {
	
	private String client_id;
	private String client_ip;
	private String client_port;
	private String server_ip;
	private String server_port;
	private String dataColFreq;
	private String dataUploadFreq;
	
	public ConfigFile() {}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
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

	public String getDataColFreq() {
		return dataColFreq;
	}

	public void setDataColFreq(String dataColFreq) {
		this.dataColFreq = dataColFreq;
	}

	public String getDataUploadFreq() {
		return dataUploadFreq;
	}

	public void setDataUploadFreq(String dataUploadFreq) {
		this.dataUploadFreq = dataUploadFreq;
	}

	@Override
	public String toString() {
		return "ConfigFile [client_id=" + client_id + ", client_ip=" + client_ip + ", client_port=" + client_port
				+ ", server_ip=" + server_ip + ", server_port=" + server_port + ", dataColFreq=" + dataColFreq
				+ ", dataUploadFreq=" + dataUploadFreq + "]";
	}

}
