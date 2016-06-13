package npp.robot.core;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by mac on 28.05.16.
 */
public class GeneralProperties {

    private static final Properties properties = new Properties();
    static {
        try {
            properties.load(GeneralProperties.class.getClassLoader().getResourceAsStream("robot.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Double getDouble(String name) {
        return getDouble(name, null);
    }

    public static Double getDouble(String name, Double def) {
        String property = properties.getProperty(name);
        if (property == null)
            return def;
        return Double.parseDouble(property);
    }


    public static Integer getInteger(String name) {
        return getInteger(name, null);
    }

    public static Integer getInteger(String name, Integer def) {
        String property = properties.getProperty(name);
        if (property == null)
            return def;
        return Integer.parseInt(property);
    }
}
