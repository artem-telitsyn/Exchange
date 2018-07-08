package my.pack;

import java.math.BigDecimal;
import java.util.HashMap;

import static my.pack.Currency.*;

public class ExchangeCurrency {

    private HashMap<Currency, BigDecimal> currencyRate;

    public ExchangeCurrency (HashMap currencyRate) {
        this.currencyRate = currencyRate;
        this.currencyRate.put(USD, BigDecimal.valueOf(60));
        this.currencyRate.put(EUR, BigDecimal.valueOf(70));
        this.currencyRate.put(RUB, BigDecimal.valueOf(1));
    }

    public BigDecimal getCurrencyRate(Currency currency) {
        return currencyRate.get(currency);
    }

    public void setCurrencyRate(Currency currency, BigDecimal currencyRate) {
        if (getCurrencyRate(currency)!= null) {
            this.currencyRate.put(currency, currencyRate);
            getExchangeRate();
        } else{
            System.out.println("Данная валюта не поддерживается");
        }

    }

    public void getExchangeRate() {
        System.out.println("Курс валют");
        System.out.println(USD + "/" + RUB + ": " + currencyRate.get(USD));
        System.out.println(EUR + "/" + RUB + ": " + currencyRate.get(EUR));
    }
}
