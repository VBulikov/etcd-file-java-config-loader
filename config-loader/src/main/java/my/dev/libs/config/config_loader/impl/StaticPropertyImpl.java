package my.dev.libs.config.config_loader.impl;

import my.dev.libs.config.config_loader.ConfigProperty;
import my.dev.libs.config.config_loader.ConfigPropetryFormatException;

/**
 * Created by Vladislav Bulikov on 23.01.2017.
 */

public class StaticPropertyImpl implements ConfigProperty {

    private final String name;
    private final Object value;

    public StaticPropertyImpl(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public String stringValue() {
        try {
            return String.valueOf(this.value);
        }catch (Exception e){
            throw new ConfigPropetryFormatException("Config " + this.name + " can not be read as String.");
        }
    }

    @Override
    public int intValue() throws ConfigPropetryFormatException {
        try {
            return Integer.parseInt(this.value.toString());
        }catch (Exception e){
            throw new ConfigPropetryFormatException("Config " + this.name + " can not be read as Integer.");
        }
    }

    @Override
    public boolean boolValue() throws ConfigPropetryFormatException {
        try {
            return Boolean.parseBoolean(this.value.toString());
        }catch (Exception e){
            throw new ConfigPropetryFormatException("Config " + this.name + " can not be read as Boolean.");
        }
    }

    @Override
    public double doubleValue() throws ConfigPropetryFormatException {
        try {
            return Double.parseDouble(this.value.toString());
        }catch (Exception e){
            throw new ConfigPropetryFormatException("Config " + this.name + " can not be read as Double.");
        }
    }

    @Override
    public char charValue() throws ConfigPropetryFormatException {
        try {
            return Character.valueOf(this.value.toString().charAt(0));
        }catch (Exception e){
            throw new ConfigPropetryFormatException("Config " + this.name + " can not be read as Char.");
        }
    }
}
