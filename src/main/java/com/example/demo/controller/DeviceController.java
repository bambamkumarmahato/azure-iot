package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.DeviceDetails;
import com.example.demo.entity.TelementryDataHelper;
import com.example.demo.services.DeviceClientService;
import com.example.demo.services.DeviceServiceImp;
import com.example.demo.services.DeviceTwinStatusCallBack;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.microsoft.azure.sdk.iot.service.Device;
import com.microsoft.azure.sdk.iot.service.devicetwin.DeviceTwinDevice;

@RestController
public class DeviceController {

	@Autowired
	private DeviceServiceImp deviceServiceimp;
	@Autowired
	private DeviceClientService deviceTwinService;
	
	private final Gson gson = new Gson();
	
	@PostMapping("/addDevice")
	public Device createDemoDevice(@RequestBody DeviceDetails deviceDetails) throws Exception, IOException{
		return deviceServiceimp.addDevice(deviceDetails);
	}
	
	@DeleteMapping("/deleteDevice/{deviceId}")
	public void deleteDevice(@PathVariable String deviceId) throws Exception, IOException{
		deviceServiceimp.removeDevice(deviceId);
	}
	
	@GetMapping("/getAllDevice")
	public List<Device> getAllDevice() throws Exception, IOException{
		return deviceServiceimp.getDeviceList();
	}
	
	@GetMapping("/getDevice/{deviceId}")
	public Device getDevice(@PathVariable String deviceId) throws Exception, IOException{
		return deviceServiceimp.getDevice(deviceId);
	}
	
	@PostMapping("/enableDevice/{deviceId}")
	public Device enableDevice(@PathVariable String deviceId) throws Exception, IOException{
		return deviceServiceimp.enableDevice(deviceId);
	}
	
	@PostMapping("/disableDevice/{deviceId}")
	public Device disableDevice(@PathVariable String deviceId) throws Exception, IOException{
		return deviceServiceimp.disableDevice(deviceId);
	}
	
	@PostMapping("/updateDesiredProperties/{devId}")
	public DeviceTwinDevice updateDesiredProperties(@RequestBody String desiredProperties, @PathVariable String devId) throws Exception, IOException{
		JsonElement element = gson.fromJson (desiredProperties, JsonElement.class);
    	JsonObject devicePropJ = element.getAsJsonObject();
    	
		return deviceServiceimp.updateDesiredProperties(devicePropJ, devId);
	}
	
	@PostMapping("/updateDeviceTwin/{devId}")
	public DeviceTwinDevice updateDeviceTwin(@RequestBody String desiredProperties, @PathVariable String devId) throws Exception, IOException{
		JsonElement element = gson.fromJson (desiredProperties, JsonElement.class);
    	JsonObject devicePropJ = element.getAsJsonObject();
    	
		return deviceServiceimp.updateDeviceTwin(devicePropJ, devId);
	}
	
	@PostMapping("/sendReportedProperty")
	public String sendReportedProperty(@RequestBody TelementryDataHelper helper) throws Exception, IOException{
		return deviceTwinService.sendReportedData(helper);
	}
}
