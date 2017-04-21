package my.dev.libs.config.config_loader.strategy;

import my.dev.libs.config.config_loader.ConfigProperties;
import my.dev.libs.config.config_loader.ConfigProperty;
import my.dev.libs.config.config_loader.impl.StaticConfigImpl;
import my.dev.libs.config.config_loader.impl.StaticPropertyImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public class PropertyFileLoader implements LoaderSupport {
    private static final Logger logger = LoggerFactory.getLogger(PropertyFileLoader.class);
    private final String fileName;


    public PropertyFileLoader(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public ConfigProperties loadProperties(String name) {
        Properties appProperties = new Properties();
        boolean tryout = false;
        try {
            FileInputStream fileInputStream = new FileInputStream(this.fileName);
            appProperties.load(fileInputStream);
            fileInputStream.close();
        }  catch (IOException e) {
            appProperties.toString();
            StringWriter exceptionTrace = new StringWriter();
            logger.info("User properties file {} NOT found - falling back to default properties file",
                    this.fileName);
            tryout = true;
        }
        if(tryout) {
            appProperties = new Properties();
            try {
                InputStream inputStream =
                        Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");

                appProperties.load(inputStream);
            } catch (IOException e) {
                appProperties.toString();
                StringWriter exceptionTrace = new StringWriter();
                e.printStackTrace(new PrintWriter(exceptionTrace));
                logger.error(exceptionTrace.toString());
                logger.error("Properties file {} NOT found.", this.fileName);
                System.exit(-1);
            }
        }
        Map<String, ConfigProperty> config = new HashMap<String, ConfigProperty>();
        for(Iterator<Map.Entry<Object, Object>> propIterator = appProperties.entrySet().iterator(); propIterator.hasNext();){
            Map.Entry<Object, Object> property = propIterator.next();
            config.put(property.getKey().toString(), new StaticPropertyImpl(property.getKey().toString(), property.getValue()));
        }


        return new StaticConfigImpl(name, config); //TODO - implement
    }
}
