package com.example.demo.interfaces;

import java.io.IOException;
import java.util.List;

import com.example.demo.entity.DeviceDetails;
import com.google.gson.JsonObject;
import com.microsoft.azure.sdk.iot.service.Device;
import com.microsoft.azure.sdk.iot.service.devicetwin.DeviceTwinDevice;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;

public interface DeviceServiceInterface {

	public Device addDevice(DeviceDetails e) throws IOException ,IotHubException;
	
	public void removeDevice(String device) throws IOException,IotHubException;
	
	public List<Device> getDeviceList() throws IOException,IotHubException;
	
	public Device getDevice(String deviceId) throws IOException,IotHubException;
	
	public Device enableDevice(String deviceId) throws IOException,IotHubException;
	
	public Device disableDevice(String deviceId) throws IOException,IotHubException;
	
	public DeviceTwinDevice updateDesiredProperties(JsonObject devicePropJ, String deviceId) throws IOException, IotHubException;
	
	public DeviceTwinDevice updateDeviceTwin(JsonObject devicePropJ, String deviceId) throws IOException, IotHubException;
	
//	public void sendReportedData() throws IOException, IotHubException;
}
