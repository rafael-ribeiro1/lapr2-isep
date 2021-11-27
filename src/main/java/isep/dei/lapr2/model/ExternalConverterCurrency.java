
package isep.dei.lapr2.model;

import java.io.Serializable;

/**
 *Interface of External Converter Currency
 *
 */
public interface ExternalConverterCurrency extends Serializable {
    /**
     * Method that converts the amount to the currency of country introduced to paramater
     * @param amount Amount that is supposed to convert.
     * @param country Country to convert the currency.
     * @return The amount converted to the local currency of country as paramater.
     */
    public String convertCurrency(double amount, String country);

    /**
     * Method that returns all available country's in the system.
     * @return
     */
    public String[] getAvalilableCountrys();
    
}
