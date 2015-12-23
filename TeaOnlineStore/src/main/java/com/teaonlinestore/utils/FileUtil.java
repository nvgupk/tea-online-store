package com.teaonlinestore.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.teaonlinestore.service.TeaManager;

public class FileUtil {
	private static final Logger LOG = Logger.getLogger(FileUtil.class);

	public static Map<String, String> getProperties() {
		Properties collumnsName = new Properties();
		InputStream input = null;
		Map<String, String> properties = new HashMap<String, String>();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			collumnsName.load(classLoader.getResourceAsStream("col_names.properties"));
			for (String name : collumnsName.stringPropertyNames()) {
				properties.put(name, collumnsName.getProperty(name));
			}
		} catch (IOException ex) {
			LOG.error("Filed load Properties file", ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOG.error("Filed close FileInputStream", e);
				}
			}
		}
		return properties;
	}
		
}
