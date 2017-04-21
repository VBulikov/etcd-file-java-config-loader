package my.dev.libs.config.config_loader;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public interface ConfigProperty {
    /**
     * Get property name
     * @return
     */
    String getName();

    /**
     * Get property getValue
     * @return
     */
    Object getValue();

    String stringValue();
    int intValue() throws ConfigPropetryFormatException;
    boolean boolValue()throws ConfigPropetryFormatException;
    double doubleValue()throws ConfigPropetryFormatException;
    char charValue() throws ConfigPropetryFormatException;

    /**
     * Return a string in the format: name=getValue
     * @return
     */
    String toString();
}
