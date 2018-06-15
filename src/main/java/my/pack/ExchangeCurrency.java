package my.pack;

import java.math.BigDecimal;
import java.util.HashMap;

public class ExchangeCurrency {

    private BigDecimal usdRurRate;
    private BigDecimal eurRurRate;
    private BigDecimal eurUsdRate;
    private HashMap<String, BigDecimal> currencyRate;

    public ExchangeCurrency (HashMap currencyRate) {
        this.currencyRate = currencyRate;
        this.currencyRate.put("USD/RUB", BigDecimal.valueOf(60));
        this.currencyRate.put("EUR/RUB", BigDecimal.valueOf(70));
        this.currencyRate.put("RUB/USD", BigDecimal.valueOf(1).divide((BigDecimal) currencyRate.get("USD/RUB"),2,2));
        this.currencyRate.put("RUB?EUR", BigDecimal.valueOf(1).divide((BigDecimal) currencyRate.get("EUR/RUB"),2,2));
        this.usdRurRate = new BigDecimal(60);
        this.eurRurRate = new BigDecimal(70);
        this.eurUsdRate = new BigDecimal(0.857);
    }

    public BigDecimal getEurRurRate() {
        return eurRurRate;
    }

    public void setEurRurRate(BigDecimal eurRurRate) {
        this.eurRurRate = eurRurRate;
    }

    public BigDecimal getUsdRurRate() {
        return usdRurRate;
    }

    public void setUsdRurRate(BigDecimal usdRurRate) {
        this.usdRurRate = usdRurRate;
    }

    public BigDecimal getEurUsdRate() {
        return eurUsdRate;
    }

    public void setEurUsdRate(BigDecimal eurUsdRate) {
        this.eurUsdRate = eurUsdRate;
    }

    public BigDecimal getCurrencyRate(String currency) {
        return currencyRate.get(currency);
    }

    public void setCurrencyRate(String currency, BigDecimal currencyRate) {
        this.currencyRate.put(currency, currencyRate);
    }

    public void getExchangeRate() {
        System.out.println("Курс валют");
        System.out.println("USD/RUR: " + getUsdRurRate());
        System.out.println("EUR/RUR: " + getEurRurRate());
        System.out.println("EUR/USD: " + getEurUsdRate());
    }
}
