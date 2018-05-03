package com.fhwn.ma.frontend.clientapp.Dao;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class DataCollectorDAO implements IDataCollectorDAO{

	@Override
	public double getCpuUsage() {
		double cpuLoadDouble = 0;

        java.lang.management.OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        for (Method method: operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getName().contains("SystemCpuLoad") && Modifier.isPublic(method.getModifiers())) {
                Object cpuLoad;
                try {
                    cpuLoad = method.invoke(operatingSystemMXBean);
                } catch (Exception e) {
                    cpuLoad = e;
                }
                cpuLoadDouble = (double) cpuLoad;

                return cpuLoadDouble;
            }
        }
        return cpuLoadDouble;
	}

	@Override
	public double getMemoryUsage() {
	
		 long memorySize = (long) (((com.sun.management.OperatingSystemMXBean)
	                ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize() / 1048576000.0);

		
	        return memorySize;
	}

	

}
