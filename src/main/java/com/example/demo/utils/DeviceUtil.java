package com.example.demo.utils;

import java.io.IOException;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.service.RegistryManager;

public class DeviceUtil {
	
	private static String connString = "HostName=IoT-NxT-Hub.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=GHgKuy6Ie3IGHKliLQ9IHRJhwRuPzYhITn4s4PXZTjk=";
	private static IotHubClientProtocol protocol = IotHubClientProtocol.AMQPS;
	
	public static RegistryManager registerDevice() throws IOException{
		RegistryManager registerManager = RegistryManager.createFromConnectionString (connString);
		return registerManager;
	}
	
}
