package com.example.demo.services;

import com.microsoft.azure.sdk.iot.device.IotHubEventCallback;
import com.microsoft.azure.sdk.iot.device.IotHubStatusCode;

public class DeviceTwinStatusCallBack implements IotHubEventCallback {
	
	@Override
    public void execute(IotHubStatusCode status, Object context) {
      System.out.println("IoT Hub responded to device twin operation with status " + status.name());
    }
}
