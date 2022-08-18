package com.example.demo.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.entity.DeviceDetails;
import com.example.demo.interfaces.DeviceServiceInterface;
import com.example.demo.utils.DeviceTwinUtil;
import com.example.demo.utils.DeviceUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.service.Device;
import com.microsoft.azure.sdk.iot.service.DeviceStatus;
import com.microsoft.azure.sdk.iot.service.RegistryManager;
import com.microsoft.azure.sdk.iot.service.devicetwin.DeviceTwin;
import com.microsoft.azure.sdk.iot.service.devicetwin.DeviceTwinDevice;
import com.microsoft.azure.sdk.iot.service.devicetwin.Pair;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;

@Service
public class DeviceServiceImp implements DeviceServiceInterface {
	
	RegistryManager registrymanager = new RegistryManager();
	DeviceTwinDevice device = new DeviceTwinDevice();
	DeviceTwin deviceTwin = new DeviceTwin();
	DeviceClient client = null;
	
	@Override
	public Device addDevice(DeviceDetails e) throws IOException ,IotHubException{
		
			registrymanager = DeviceUtil.registerDevice();
			Device device = Device.createFromId(e.getdeviceName(), null, null);
			device = registrymanager.addDevice(device);
			return device;
	}
	
	public void removeDevice(String deviceId) throws IOException, IotHubException{
		
			registrymanager = DeviceUtil.registerDevice();
//			Device device = Device.findById(deviceId)
			registrymanager.removeDevice(deviceId);
	}
	
	public List<Device> getDeviceList() throws IOException, IotHubException{
		registrymanager = DeviceUtil.registerDevice();
		return registrymanager.getDevices(Integer.valueOf(5));
	}
	
	public Device getDevice(String deviceId) throws IOException, IotHubException{
		registrymanager = DeviceUtil.registerDevice();
		return registrymanager.getDevice(deviceId);
	}
	
	public Device enableDevice(String deviceId) throws IOException, IotHubException{
		registrymanager = DeviceUtil.registerDevice();
		Device device = registrymanager.getDevice(deviceId);
		device.setStatus(DeviceStatus.Enabled);
		return registrymanager.updateDevice(device);
	}
	
	public Device disableDevice(String deviceId) throws IOException, IotHubException{
		registrymanager = DeviceUtil.registerDevice();
		Device device = registrymanager.getDevice(deviceId);
		device.setStatus(DeviceStatus.Disabled);
		registrymanager.updateDevice(device);
		return device;
	}
	
	@Override
	public DeviceTwinDevice updateDesiredProperties(JsonObject devicePropJ, String deviceId) throws IOException, IotHubException {
		try {
			deviceTwin = DeviceTwinUtil.createDeviceTwin();
			device = DeviceTwinUtil.createDeviceTwinDevice(deviceId);
			deviceTwin.getTwin(device);
			
			String dpTags = device.desiredPropertiesToString();
			Set<Pair> tags = new HashSet<Pair>();
			
			for(Map.Entry<String, JsonElement> entry : devicePropJ.entrySet()){
				if(!dpTags.contains(entry.getKey())) {
					tags.add(new Pair(entry.getKey().toString(), entry.getValue().toString().replace("\"", "")));
				}
			}
			device.setDesiredProperties(tags);
			
			deviceTwin.updateTwin(device);
	
			System.out.println("device :"+device);
			
		} catch (IotHubException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return device;
	}
	
	@Override
	public DeviceTwinDevice updateDeviceTwin(JsonObject devicePropJ, String deviceId) throws IOException, IotHubException {
		try {
			deviceTwin = DeviceTwinUtil.createDeviceTwin();
			device = DeviceTwinUtil.createDeviceTwinDevice(deviceId);
			deviceTwin.getTwin(device);
			
			String dtTags = device.tagsToString();
			Set<Pair> tags = new HashSet<Pair>();
				
			for(Map.Entry<String, JsonElement> entry : devicePropJ.entrySet()){
				if(!dtTags.contains(entry.getKey())) {
					tags.add(new Pair(entry.getKey().toString(), entry.getValue().toString().replace("\"", "")));
				}
			}
			device.setTags(tags);
			deviceTwin.updateTwin(device);
	
			System.out.println("device :"+device);
			
		} catch (IotHubException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return device;
	}

//	@Override
//	public void sendReportedData() throws IOException, IotHubException {
//		try {
//			Device dataCollector = new Device();
//			client = DeviceClientUtil.createDeviceClient();
//			client.open();
//			client.startDeviceTwin(new DeviceTwinStatusCallBack(), null, dataCollector, null);
//
//			// Create a reported property and send it to your IoT hub.
//			dataCollector.setReportedProp(new Property("connectivityType", "cellular"));
//			client.sendReportedProperties(dataCollector.getReportedProp());
//		} catch (Exception e) {
//			System.out.println("On exception, shutting down \n" + " Cause: " + e.getCause() + " \n" + e.getMessage());
//			dataCollector.clean();
//			client.closeNow();
//			System.out.println("Shutting down...");
//		}
//	}
	
}
