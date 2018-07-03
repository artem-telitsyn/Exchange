package my.pack.Account;

import my.pack.MenuCommand.Permission;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class Account {

    private String login;
    private Permission role;
    private HashMap<String, BigDecimal> accountCurrency;
    private HashMap<Long, AccountHistory> transactionHistory;

    Account(HashMap accountCurrency, HashMap<Long, AccountHistory> transactionHistory) {
        this.accountCurrency = accountCurrency;
        this.accountCurrency.put("RUB", BigDecimal.valueOf(0));
        this.accountCurrency.put("USD", BigDecimal.valueOf(0));
        this.accountCurrency.put("EUR", BigDecimal.valueOf(0));
        this.transactionHistory = transactionHistory;
    }

    public BigDecimal getAccountCurrency(String currency) {
        return accountCurrency.get(currency);
    }

    public void setAccountCurrency(String currency, BigDecimal amount) {
        this.accountCurrency.put(currency, amount);
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

    public HashMap<Long, AccountHistory> getTransactionHistory() {
        return transactionHistory;
    }
}

