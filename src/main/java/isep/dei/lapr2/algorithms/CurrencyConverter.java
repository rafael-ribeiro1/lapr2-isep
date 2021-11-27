package isep.dei.lapr2.algorithms;

import isep.dei.lapr2.model.ExternalConverterCurrency;

/**
 * Algorithm to convert amount in euros to the currency of specific country
 */
public class CurrencyConverter implements ExternalConverterCurrency {

    /**
     * Enumerator of the available countries. Each country has a currency associated
     */
    private enum Country {
        POR(Currency.EUR) {
            @Override
            public String toString() {
                return "Portugal";
            }
        },
        USA(Currency.USD) {
            @Override
            public String toString() {
                return "USA";
            }
        },
        LUX(Currency.EUR) {
            @Override
            public String toString() {
                return "Luxembourg";
            }
        },
        CAN(Currency.CAD) {
            @Override
            public String toString() {
                return "Canada";
            }
        },
        BRA(Currency.REAL) {
            @Override
            public String toString() {
                return "Brazil";
            }
        },
        UK(Currency.GBP) {
            @Override
            public String toString() {
                return "United Kingdom";
            }
        },
        SWI(Currency.CHF) {
            @Override
            public String toString() {
                return "Switzerland";
            }
        },
        FRA(Currency.EUR) {
            @Override
            public String toString() {
                return "France";
            }
        },
        SPA(Currency.EUR) {
            @Override
            public String toString() {
                return "Spain";
            }
        },
        SWE(Currency.SWK) {
            @Override
            public String toString() {
                return "Sweden";
            }
        },
        RPC(Currency.RMB) {
            @Override
            public String toString() {
                return "China";
            }
        },
        JPN(Currency.YEN) {
            @Override
            public String toString() {
                return "Japan";
            }
        };

        /**
         * Currency associated to the country
         */
        private Currency currency;

        /**
         * Constructor to each item of enumerator
         * @param currency the currency of the country
         */
        private Country(Currency currency) {
            this.currency = currency;
        }

        /**
         * Returns the currency of the country
         * @return the currency of the country
         */
        public Currency getCurrency() {
            return currency;
        }
    }

    /**
     * Enumerator with the available currencies. Each currency has a symbol and a price in euros
     * (Prices from 4th June, 2020)
     */
    private enum Currency {
        EUR("€", 1.00),
        USD("US$", 1.12),
        REAL("R$", 5.67),
        CAD("CA$", 1.51),
        CHF("Fr", 1.08),
        GBP("£", 0.89),
        SWK("kr", 10.43),
        RMB("¥", 7.97),
        YEN("¥", 123.74);

        /**
         * String symbol of the currency
         */
        private String symbol;
        /**
         * Price in euros of the currency
         */
        private double priceEUR;

        /**
         * Constructor for each item of the enumerator
         * @param symbol the symbol of the currency
         * @param priceEUR the price in euros of the currency
         */
        private Currency(String symbol, double priceEUR) {
            this.symbol = symbol;
            this.priceEUR = priceEUR;
        }

        /**
         * Returns the string symbol of the currency
         * @return the symbol of the currency
         */
        public String getSymbol() {
            return symbol;
        }

        /**
         * Returns the price in euros of the currency
         * @return the price in euros of the currency
         */
        public double getPriceEUR() {
            return priceEUR;
        }
    }

    /**
     * Converts an amount in euros to the currency of a country
     * @param amountEUR amount in euros
     * @param country country to convert the currency.
     * @return a string with the amount converted and the symbol
     */
    @Override
    public String convertCurrency(double amountEUR, String country) {
        Country[] countrys = Country.values();
        Currency selCurr = Currency.EUR;
        for (Country c: countrys) {
            if (c.toString().equals(country)) {
                selCurr = c.getCurrency();
            }
        }
        double conv = amountEUR * selCurr.getPriceEUR();
        return String.format("%.2f%s", conv, selCurr.getSymbol());
    }

    /**
     * Returns a list with the available countries in the algorithm
     * @return a string list with the available countries
     */
    @Override
    public String[] getAvalilableCountrys() {
        Country[] countrysEnum = Country.values();
        String[] countrys = new String[countrysEnum.length];
        for (int i = 0; i < countrys.length; i++) {
            countrys[i] = countrysEnum[i].toString();
        }
        return countrys;
    }

}
