package com.example.demo.services;

import com.microsoft.azure.sdk.iot.device.DeviceTwin.Device;
import com.microsoft.azure.sdk.iot.device.DeviceTwin.PropertyCallBack;

public class ContextCallBack implements  PropertyCallBack {

//	Device dataCollector = new Device() {
//		@Override
//		public void PropertyCall(String propertyKey, Object propertyValue, Object context) {
//			System.out.println(propertyKey + " changed to " + propertyValue);
//		}
//	};

	@Override
	public void PropertyCall(Object propertyKey, Object propertyValue, Object context) {
		// TODO Auto-generated method stub
		
	}
}
