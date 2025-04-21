package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader class is responsible for reading configuration properties from a file.
 * It loads the properties at the time of class loading and provides a method to retrieve property values.
 */
public class ConfigReader {
    private static final Properties properties;

    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties")) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file");
        }
    }

    /**
     * Retrieves the value of a property by its key.
     *
     * @param key the key of the property to retrieve
     * @return the value of the property
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}