package com.burakkaygusuz.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    private static final Logger log = LoggerFactory.getLogger(PropertyUtils.class);
    private static final Properties properties = new Properties();

    public static Properties readPropertiesFile(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            log.error(String.format("File does not exist: %s", e.getMessage()));
        } catch (IOException e) {
            log.error(String.format("An error occurred while reading the file: %s", e.getMessage()));
        }
        return properties;
    }
}
