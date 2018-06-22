package my.pack;

import java.math.BigDecimal;
import java.util.HashMap;

public class ExchangeCurrency {

    private HashMap<String, BigDecimal> currencyRate;

    public ExchangeCurrency (HashMap currencyRate) {
        this.currencyRate = currencyRate;
        this.currencyRate.put("USD", BigDecimal.valueOf(60));
        this.currencyRate.put("EUR", BigDecimal.valueOf(70));
        this.currencyRate.put("RUB", BigDecimal.valueOf(1));
    }

    public BigDecimal getCurrencyRate(String currency) {
        return currencyRate.get(currency);
    }

    public void setCurrencyRate(String currency, BigDecimal currencyRate) {
        if (getCurrencyRate(currency)!= null) {
            this.currencyRate.put(currency, currencyRate);
            getExchangeRate();
        } else{
            System.out.println("Данная валюта не поддерживается");
        }

    }

    public void getExchangeRate() {
        System.out.println("Курс валют");
        System.out.println("USD/RUR: " + currencyRate.get("USD"));
        System.out.println("EUR/RUR: " + currencyRate.get("EUR"));
    }
}
