package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

    private static final String PROPERTIES_PATH = "/service.properties";

    public static String getServiceUrl() {
        return getProperty(PropertyKeys.SERVICE_URL.getValue());
    }

    private static String getProperty(String key) {
        Properties properties = new Properties();
        try {
            InputStream stream = PropertyUtils.class.getResourceAsStream(PROPERTIES_PATH);
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
