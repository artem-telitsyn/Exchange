package my.pack.Account;

import my.pack.CommandMenu.Permission;

import java.math.BigDecimal;
import java.util.HashMap;

public class Account {

    private String login;
    private Permission role;
    private HashMap<String, BigDecimal> accountCurrency;
    private BigDecimal rub;
    private BigDecimal usd;
    private BigDecimal eur;

    Account(HashMap accountCurrency) {
        this.accountCurrency = accountCurrency;
        this.accountCurrency.put("RUB", BigDecimal.valueOf(0));
        this.accountCurrency.put("USD", BigDecimal.valueOf(0));
        this.accountCurrency.put("EUR", BigDecimal.valueOf(0));
        this.rub = new BigDecimal(0);
        this.usd = new BigDecimal(0);
        this.eur = new BigDecimal(0);
    }

    public BigDecimal getAccountCurrency (String currency) {
        return accountCurrency.get(currency);
    }

    public void setAccountCurrency (String currency, BigDecimal amount) {
        this.accountCurrency.put(currency, amount);
    }

    public BigDecimal getRub() {
        return rub;
    }

    public void setRub(BigDecimal rub) {
        this.rub = rub;
    }

    public BigDecimal getUsd() {
        return usd;
    }

    public void setUsd(BigDecimal usd) {
        this.usd = usd;
    }

    public BigDecimal getEur() {
        return eur;
    }

    public void setEur(BigDecimal eur) {
        this.eur = eur;
    }

    public Permission getRole() {
        return role;
    }

    public void setRole(Permission role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

