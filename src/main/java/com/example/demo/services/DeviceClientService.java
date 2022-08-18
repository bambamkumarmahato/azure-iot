package com.example.demo.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TelementryDataHelper;
import com.example.demo.utils.DeviceClientUtil;
import com.example.demo.utils.DeviceUtil;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.Message;
import com.microsoft.azure.sdk.iot.device.DeviceTwin.Property;
import com.microsoft.azure.sdk.iot.device.exceptions.IotHubException;
import com.microsoft.azure.sdk.iot.service.Device;
import com.microsoft.azure.sdk.iot.service.RegistryManager;

@Service
public class DeviceClientService {
	
	DeviceClient client = null;
	RegistryManager registryManager = new RegistryManager();
	
//	Device dataCollector = new Device() {
//		@Override
//		public void PropertyCall(String propertyKey, Object propertyValue, Object context) {
//			System.out.println(propertyKey + " changed to " + propertyValue);
//		}
//	};
		
	public String sendReportedData(TelementryDataHelper helper) throws IOException, IotHubException {
		String returnStr = "";
		try {
			String msgStr = helper.serialize();
			System.out.println("msgStr is :"+msgStr);
			Message msg = new Message(msgStr);
			msg.setContentEncoding("UTF-8");
			msg.setContentType("Application/json");
			registryManager = DeviceUtil.registerDevice();
			Device device = registryManager.getDevice(helper.getDeviceId());
			client = DeviceClientUtil.createDeviceClient(helper.getDeviceId(), device.getPrimaryKey());
			client.open();
			
			ContextCallBack contextCallBack =  new ContextCallBack();
			client.startDeviceTwin(new DeviceTwinStatusCallBack(), null, contextCallBack, null);
			
			Set<Property> reportedProp = new HashSet<Property>();
			reportedProp.add(new Property("region", helper.getRegion()));
			reportedProp.add(new Property("temperature", helper.getTemperature()));
			System.out.println("reportedProp is :"+reportedProp);
//			dataCollector.setReportedProp(reportedProp);
			client.sendReportedProperties(reportedProp);
			client.sendEventAsync(msg, null, null);
			returnStr = "Successful";
		} catch (Exception e) {
			System.out.println("On exception, shutting down \n" + " Cause: " + e.getCause() + " \n" + e.getMessage());
//			dataCollector.clean();
			client.closeNow();
			System.out.println("Shutting down...");
			returnStr = "Unsuccessful";
		}
		return returnStr;
	}
}
