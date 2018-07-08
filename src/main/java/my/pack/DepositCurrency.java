package my.pack;

import my.pack.Account.Account;
import my.pack.Account.AccountHistory;
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
            AccountHistory accountHistory = new AccountHistory();
            amountOnAccount = account.getAccountCurrency("RUB");
            accountHistory.setCurrency("RUB");
            accountHistory.setRate(BigDecimal.valueOf(1));
            accountHistory.setAmount(amount);
            accountHistory.setNote("Пополнение");
            account.setAccountCurrency("RUB", amountOnAccount.add(amount));
            account.setTransactionHistory(System.currentTimeMillis(), accountHistory);
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
                    AccountHistory accountHistoryFirstCurrency = new AccountHistory();
                    AccountHistory accountHistorySecondCurrency = new AccountHistory();
                    if (firstCurrency.equals(secondCurrency)) {
                        accountManager.getCurrentAccountCurrencyStatus(account, firstCurrency);
                    } else if (firstCurrency.equals("RUB")) {
                        amount2 = amount.divide(exchangeCurrency.getCurrencyRate(secondCurrency), 2, 1);
                        accountHistoryFirstCurrency.setCurrency(firstCurrency);
                        accountHistoryFirstCurrency.setRate(exchangeCurrency.getCurrencyRate(firstCurrency));
                        accountHistoryFirstCurrency.setAmount(amount);
                        accountHistoryFirstCurrency.setNote("Снятие");
                        account.setAccountCurrency(firstCurrency, account.getAccountCurrency(firstCurrency).subtract(amount));
                        account.setTransactionHistory(System.currentTimeMillis(), accountHistoryFirstCurrency);
                        accountHistorySecondCurrency.setCurrency(secondCurrency);
                        accountHistorySecondCurrency.setRate(exchangeCurrency.getCurrencyRate(secondCurrency));
                        accountHistorySecondCurrency.setAmount(amount2);
                        accountHistorySecondCurrency.setNote("Пополнение");
                        account.setAccountCurrency(secondCurrency, account.getAccountCurrency(secondCurrency).add(amount2));
                        account.setTransactionHistory(System.currentTimeMillis()+1, accountHistorySecondCurrency);
                        accountManager.getCurrentAccountCurrencyStatus(account, "ALL");
                    } else if (secondCurrency.equals("RUB")) {
                        amount2 = amount.multiply(exchangeCurrency.getCurrencyRate(firstCurrency));
                        accountHistoryFirstCurrency.setCurrency(firstCurrency);
                        accountHistoryFirstCurrency.setRate(exchangeCurrency.getCurrencyRate(firstCurrency));
                        accountHistoryFirstCurrency.setAmount(amount);
                        accountHistoryFirstCurrency.setNote("Снятие");
                        account.setAccountCurrency(firstCurrency, account.getAccountCurrency(firstCurrency).subtract(amount));
                        account.setTransactionHistory(System.currentTimeMillis(), accountHistoryFirstCurrency);
                        accountHistorySecondCurrency.setCurrency(secondCurrency);
                        accountHistorySecondCurrency.setRate(exchangeCurrency.getCurrencyRate(secondCurrency));
                        accountHistorySecondCurrency.setAmount(amount2);
                        accountHistorySecondCurrency.setNote("Пополнение");
                        account.setAccountCurrency(secondCurrency, account.getAccountCurrency(secondCurrency).add(amount2));
                        accountManager.getCurrentAccountCurrencyStatus(account, "ALL");
                        account.setTransactionHistory(System.currentTimeMillis()+1, accountHistorySecondCurrency);
                    } else {
                        //Переводим в основную валюту рубли
                        amount2 = amount.multiply(exchangeCurrency.getCurrencyRate(firstCurrency));
                        //Переводим в валюту
                        amount2 = amount2.divide(exchangeCurrency.getCurrencyRate(secondCurrency), 2, 1);
                        accountHistoryFirstCurrency.setCurrency(firstCurrency);
                        accountHistoryFirstCurrency.setRate(exchangeCurrency.getCurrencyRate(firstCurrency));
                        accountHistoryFirstCurrency.setAmount(amount);
                        accountHistoryFirstCurrency.setNote("Снятие");
                        account.setAccountCurrency(firstCurrency, account.getAccountCurrency(firstCurrency).subtract(amount));
                        account.setTransactionHistory(System.currentTimeMillis(), accountHistoryFirstCurrency);
                        accountHistorySecondCurrency.setCurrency(secondCurrency);
                        accountHistorySecondCurrency.setRate(exchangeCurrency.getCurrencyRate(secondCurrency));
                        accountHistorySecondCurrency.setAmount(amount2);
                        accountHistorySecondCurrency.setNote("Пополнение");
                        account.setAccountCurrency(secondCurrency, account.getAccountCurrency(secondCurrency).add(amount2));
                        accountManager.getCurrentAccountCurrencyStatus(account, "ALL");
                        account.setTransactionHistory(System.currentTimeMillis()+1, accountHistorySecondCurrency);
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