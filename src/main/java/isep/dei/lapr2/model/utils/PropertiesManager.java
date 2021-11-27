package isep.dei.lapr2.model.utils;

import isep.dei.lapr2.algorithms.CurrencyConverter;
import isep.dei.lapr2.algorithms.EmailSender;
import isep.dei.lapr2.model.ExternalConverterCurrency;
import isep.dei.lapr2.model.ExternalEmailSender;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class that manages the properties file
 */
public class PropertiesManager {
    /**
     * Properties
     */
    private Properties props;
    /**
     * The name of the file
     */
    private static final String FILE_NAME="config.properties";

    /**
     * Initializes the properties manager
     */
    public PropertiesManager() throws IOException {
        props=new Properties();
        InputStream in=getClass().getClassLoader().getResourceAsStream(FILE_NAME);
        if(in!=null){
            props.load(in);
        }else{
            throw new FileNotFoundException("Config file not Found");
        }
    }

    /**
     * Returns the specified email sender algorithm form the file
     * @return the specified email sender algorithm form the file
     */
    public ExternalEmailSender getEmailSender(){
       try {
           Class<?> a = Class.forName(props.getProperty("EmailSender.Class"));
           return (ExternalEmailSender)a.newInstance();
       } catch(Exception e){
           return new EmailSender();
       }
    }

    /**
     * Returns the specified currency converter algorithm form the file
     * @return the specified currency converter algorithm form the file
     */
    public ExternalConverterCurrency getCurrencyConverter(){
        try{
            Class<?> a = Class.forName(props.getProperty("CurrencyConverter.Class"));
            return (ExternalConverterCurrency)a.newInstance();
        }catch (Exception e){
            return new CurrencyConverter();
        }
    }


}
