package my.pack.Account;

import my.pack.Currency;
import my.pack.MenuCommand.Permission;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.TreeMap;

public class Account {

    private String login;
    private Permission role;
    private HashMap<Currency, BigDecimal> accountCurrency;
    private TreeMap<Long, AccountHistory> transactionHistory;

    public Account(HashMap<Currency, BigDecimal> accountCurrency, TreeMap<Long, AccountHistory> transactionHistory) {
        this.accountCurrency = accountCurrency;
        this.accountCurrency.put(Currency.RUB, BigDecimal.valueOf(0));
        this.accountCurrency.put(Currency.USD, BigDecimal.valueOf(0));
        this.accountCurrency.put(Currency.EUR, BigDecimal.valueOf(0));
        this.transactionHistory = transactionHistory;
    }

    public BigDecimal getAccountCurrency(Currency currency) {
        return accountCurrency.get(currency);
    }

    public void setAccountCurrency(Currency currency, BigDecimal amount) {
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

    public TreeMap<Long, AccountHistory> getAllTransactionHistory() {
        return transactionHistory;
    }

    public AccountHistory getTransactionHistory(Long time) {
        return transactionHistory.get(time);
    }

    public void setTransactionHistory(Long time, AccountHistory transactionHistory) {
        this.transactionHistory.put(time, transactionHistory);
    }
}

