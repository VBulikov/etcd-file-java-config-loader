package my.dev.libs.config.config_loader.impl;

import my.dev.libs.config.config_loader.ConfigProperties;
import my.dev.libs.config.config_loader.ConfigProperty;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public class StaticConfigImpl implements ConfigProperties {

    private final Map<String,ConfigProperty> config;
    private final String name;
    private final URL[] urls;

    public StaticConfigImpl(String name, Map<String, ConfigProperty> props, URL ... urls) {
        Map<String, ConfigProperty> config = new LinkedHashMap<String,ConfigProperty>();
        config.putAll(props);
        this.config = config;
        this.name = name;
        this.urls = urls;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public URL[] getLocation() {
        return urls;
    }

    @Override
    public Map<String, ConfigProperty> getProperties() {
        return config;
    }

    @Override
    public ConfigProperty getProperty(String name) {
        return config.get(name);
    }
}
