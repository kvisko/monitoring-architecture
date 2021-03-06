package com.fhwn.ma.frontend.clientapp.Dto;

public class ClientConfigDTO {
	
	private String ip;
	private String port;
	
	public ClientConfigDTO() {}

	public ClientConfigDTO(String ip, String port) {
		this.ip=ip;
		this.port=port;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "ClientConfigDTO [ip=" + ip + ", port=" + port + "]";
	}
	

}
