package com.example.demo.entity;

public class DeviceDetails {

	private String deviceId;
	private String deviceName;

	public DeviceDetails(String deviceId, String deviceName) {
	super();
	this.deviceId = deviceId;
	this.deviceName = deviceName;
	}

	public String getdeviceId() {
		return deviceId;
	}

	public void setdeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getdeviceName() {
		return deviceName;
	}

	public void setdeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
}
