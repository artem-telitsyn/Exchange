package my.pack;

import my.pack.Account.Account;
import my.pack.Account.AccountManager;

import java.math.BigDecimal;

public class DepositCurrency {

    private ExchangeCurrency exchangeCurrency;
    private AccountManager accountManager;

    public DepositCurrency(ExchangeCurrency exchangeCurrency, AccountManager accountManager) {
        this.exchangeCurrency = exchangeCurrency;
        this.accountManager = accountManager;
    }

    public void depositRub(Account account, BigDecimal amount) {
        if (account != null) {
            BigDecimal amountOnAccount;
            amountOnAccount = account.getAccountCurrency("RUB");
            account.setAccountCurrency("RUB", amountOnAccount.add(amount));
            account.setTransactionHistory(,account.getAccountCurrency("RUB"));
            System.out.println("Успешное пополнение счета на " + amount + " RUB");
        } else {
            System.out.println("Необходимо создать счет");
        }
    }

    public void purchaseCurrency(Account account, BigDecimal amount, String firstCurrency, String secondCurrency) {
        BigDecimal amount2;
        if (account != null) {
            if ((account.getAccountCurrency(firstCurrency).compareTo(amount)) >= 0) {
                if (exchangeCurrency.getCurrencyRate(firstCurrency) != null && exchangeCurrency.getCurrencyRate(secondCurrency) != null) {
                    if (firstCurrency.equals(secondCurrency)) {
                        accountManager.getCurrentAccountCurrencyStatus(account, firstCurrency);
                    } else if (firstCurrency.equals("RUB")) {
                        amount2 = amount.divide(exchangeCurrency.getCurrencyRate(secondCurrency), 2, 1);
                        account.setAccountCurrency(firstCurrency, account.getAccountCurrency(firstCurrency).subtract(amount));
                        account.setAccountCurrency(secondCurrency, account.getAccountCurrency(secondCurrency).add(amount2));
                        accountManager.getCurrentAccountCurrencyStatus(account, "ALL");
                    } else if (secondCurrency.equals("RUB")) {
                        amount2 = amount.multiply(exchangeCurrency.getCurrencyRate(firstCurrency));
                        account.setAccountCurrency(firstCurrency, account.getAccountCurrency(firstCurrency).subtract(amount));
                        account.setAccountCurrency(secondCurrency, account.getAccountCurrency(secondCurrency).add(amount2));
                        accountManager.getCurrentAccountCurrencyStatus(account, "ALL");
                    } else {
                        //Переводим в основнуб валюту рубли
                        amount2 = amount.multiply(exchangeCurrency.getCurrencyRate(firstCurrency));
                        //Переводим в валюту
                        amount2 = amount2.divide(exchangeCurrency.getCurrencyRate(secondCurrency), 2, 1);
                        account.setAccountCurrency(firstCurrency, account.getAccountCurrency(firstCurrency).subtract(amount));
                        account.setAccountCurrency(secondCurrency, account.getAccountCurrency(secondCurrency).add(amount2));
                        accountManager.getCurrentAccountCurrencyStatus(account, "ALL");
                    }
                } else {
                    System.out.println("Невозможно совершить конвертацию. Проверьте указанные валюты");
                }
            } else {
                System.out.println("Недостаточно средств на счету");
            }

        } else {
            System.out.println("Необходимо создать счет");
        }
    }
}