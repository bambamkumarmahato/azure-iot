package com.example.demo.utils;

import java.io.IOException;

import com.microsoft.azure.sdk.iot.service.devicetwin.DeviceTwin;
import com.microsoft.azure.sdk.iot.service.devicetwin.DeviceTwinDevice;


public class DeviceTwinUtil {
	
	public static final String iotHubConnectionString = "HostName=IoT-NxT-Hub.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=GHgKuy6Ie3IGHKliLQ9IHRJhwRuPzYhITn4s4PXZTjk=";

	public static DeviceTwin createDeviceTwin() throws IOException{
		DeviceTwin twinDevice = DeviceTwin.createFromConnectionString(iotHubConnectionString);
		return twinDevice;
	}
	
	public static DeviceTwinDevice createDeviceTwinDevice(String deviceId) throws IOException{
		DeviceTwinDevice device = new DeviceTwinDevice(deviceId);
		return device;
	}
}
