package com.example.demo.utils;

import java.io.IOException;
import java.net.URISyntaxException;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;

public class DeviceClientUtil {

	
	private static IotHubClientProtocol protocol = IotHubClientProtocol.MQTT;
	
	public static DeviceClient createDeviceClient(String deviceId, String primaryKey) throws IOException{
		String iotHubConnectionString = "HostName=IoT-NxT-Hub.azure-devices.net;DeviceId="+deviceId+";SharedAccessKey="+primaryKey;
		System.out.println("iotHubConnectionString is :"+iotHubConnectionString);
		DeviceClient client = null;
		try {
			client = new DeviceClient(iotHubConnectionString, protocol);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return client;
	}
}
