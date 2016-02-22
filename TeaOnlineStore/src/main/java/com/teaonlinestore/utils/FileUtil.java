package com.teaonlinestore.utils;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.apache.log4j.Logger;

public class FileUtil {
	private static final Logger LOG = Logger.getLogger(FileUtil.class);

	public static Properties getProperties(String fileName) {
		Properties properties = new Properties();
		InputStream input = null;
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Reader reader = new InputStreamReader(classLoader.getResourceAsStream(fileName), "UTF-8");
			properties.load(reader);
		} catch (IOException ex) {
			LOG.error("Filed load Properties failed", ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOG.error("Failed close FileInputStream", e);
				}
			}
		}
		return properties;
	}
		
}
