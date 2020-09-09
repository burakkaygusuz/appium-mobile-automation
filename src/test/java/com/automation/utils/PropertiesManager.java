package com.automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    private static final Logger log = LoggerFactory.getLogger(PropertiesManager.class);
    private static final Properties properties = new Properties();

    public static Properties readPropertiesFile(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            log.info("File does not exist: " + e.getMessage());
        } catch (IOException e) {
            log.info("An error occurred while reading the file: " + e.getMessage());
        }
        return properties;
    }
}
