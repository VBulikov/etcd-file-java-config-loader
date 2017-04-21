package my.dev.libs.config.config_loader;

import my.dev.libs.config.config_loader.strategy.EtcdLoader;
import my.dev.libs.config.config_loader.strategy.LoaderSupport;
import my.dev.libs.config.config_loader.strategy.PropertyFileLoader;
import org.apache.log4j.Logger;

import java.net.URL;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public class ConfigLoader {
    final static Logger logger = Logger.getLogger(ConfigLoader.class);

    public static ConfigProperties load(String name, String ... urls) {
        URL[] uurls = new URL[urls.length];
        for(int i = 0; i < urls.length; i++){
            try {
                uurls[i] = new URL(urls[i]);
            }catch (Exception e){
                logger.debug(e);
            }
        }
        LoaderSupport etcdLoader = new EtcdLoader(uurls);
        ConfigProperties config = etcdLoader.loadProperties(name);
        return config;
    }


    public static ConfigProperties loadFromFile(String name, String fileName) {
        //validate input

        LoaderSupport fileLoader = new PropertyFileLoader(fileName);
        ConfigProperties config = fileLoader.loadProperties(name);
        return config;
    }



}
