package my.dev.libs.config.config_loader.strategy;

import my.dev.libs.config.config_loader.ConfigProperties;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public interface  LoaderSupport {
    ConfigProperties loadProperties(String name);
}
