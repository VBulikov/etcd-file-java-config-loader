package my.dev.libs.config.config_loader;

import java.net.URL;
import java.util.Map;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public interface ConfigProperties {
    /**
     * Get the unique canonical name for this configuration
     * @return
     */
    String getName();

    /**
     * Get the URL this configuration is loaded from
     * @return
     */
    URL[] getLocation();

    /**
     * Return a non-null map of configuration properties
     * @return
     */
    Map<String,ConfigProperty> getProperties();

    /**
     * Get non-null configuration property; return a nul-wrapper property if not found
     * @param name
     * @return
     */
    ConfigProperty getProperty(String name);



}
