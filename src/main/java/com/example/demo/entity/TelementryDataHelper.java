package com.example.demo.entity;

import com.google.gson.Gson;

public class TelementryDataHelper {

	private String deviceId;
	private String deviceName;
	private String message;
	private String region;
	private Float temperature;
	
	public TelementryDataHelper(String deviceId, String deviceName, String message, String region, Float temperature) {
		super();
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.message = message;
		this.region = region;
		this.temperature = temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Float getTemperature() {
		return temperature;
	}
	
	public String serialize() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
