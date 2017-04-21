package my.dev.libs.config.config_loader.strategy;

import my.dev.libs.config.config_loader.ConfigProperties;
import my.dev.libs.config.config_loader.ConfigProperty;
import my.dev.libs.config.config_loader.impl.StaticConfigImpl;
import my.dev.libs.config.config_loader.impl.StaticPropertyImpl;
import my.dev.libs.config.etcd_driver.EtcdDriver;
import my.dev.libs.config.etcd_driver.response.EtcdKeysResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

/**
 * Thin wrapper around etcd - delegate
 */
public class EtcdLoader implements LoaderSupport{

    private static final Logger logger = LoggerFactory.getLogger(EtcdLoader.class);
    private EtcdDriver etcdClient;
    private Map<String, ConfigProperty> propertyMap;
    private URL[] urls;


    public EtcdLoader(URL... urls) {
        try {
            URI[] uris = new URI[urls.length];
            for (int i = 0; i <= urls.length - 1; i++) {
                try {
                    uris[i] = urls[i].toURI();

                } catch (Exception e) {
                    StringWriter exceptionTrace = new StringWriter();
                    e.printStackTrace(new PrintWriter(exceptionTrace));
                    logger.error(exceptionTrace.toString());
                    logger.error("URL {} is NOT valid.", urls[i]);
                    System.exit(-1);
                }
            }
            this.propertyMap = new LinkedHashMap<>();
            this.urls = urls;
            etcdClient = new EtcdDriver(uris[0].toString());
        }catch (Exception e){
            return ;
        }
    }


    @Override
    public ConfigProperties loadProperties(String name) {
        logger.info("Trying to send com.ssc.etcd_driver.request");
        EtcdKeysResponse.EtcdNode rootNode;
        try {
            rootNode = etcdClient.getDir(name).recursive().send().getNode();
            watchNode(rootNode, name);
        }catch (Exception e){
            logger.debug(e.toString());
        }
        return new StaticConfigImpl(name, this.propertyMap, urls); //TODO - implement
    }


    private void watchNode(EtcdKeysResponse.EtcdNode node, String namespace){
        for(EtcdKeysResponse.EtcdNode nnode : node.getNodes()){
            if(nnode.isDir()){
                watchNode(nnode, namespace);
            }else {
                String name = nnode.getKey().substring(namespace.length()+2);
                this.propertyMap.put(name, new StaticPropertyImpl(nnode.getKey(), nnode.getValue()));
            }
        }
    }
}
