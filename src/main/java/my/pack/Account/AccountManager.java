package my.pack.Account;

import my.pack.CommandMenu.Permission;
import my.pack.ExchangeCurrency;
import my.pack.Menu;

import java.math.BigDecimal;
import java.util.HashMap;

public class AccountManager {

    private ExchangeCurrency exchangeCurrency;
    private BigDecimal amount;

    public AccountManager(ExchangeCurrency exchangeCurrency) {
        this.exchangeCurrency = exchangeCurrency;
    }

    public void createAccount(HashMap<String, Account> accountByLogin, String login, String role, Permission permission) {

        if (login != null && role != null && (role.equals("admin") || role.equals("client"))) {
            Account account = new Account(new HashMap<String, BigDecimal>());
            accountByLogin.put(login, account);
            Permission per = permission.getPermission(role);
            account.setRole(per);
            account.setLogin(login);
            System.out.println("Создан аккаунт с ролью " + per.getValue());
        } else if (login == null) {
            System.out.println("Необходимо указать логин");
        } else if (role == null) {
            System.out.println("Необходимо указать роль");
        } else if (role != null) {
            System.out.println("Роль может быть либо client, либо admin");
        } else {
            System.out.println("При создании аккаунта что то пошло не так, проверьте введенные данные");
        }
    }

    public Account logIn(HashMap<String, Account> accountByLogin, String login) {
        if (login != null) {
            Account account = accountByLogin.get(login);
            if (account != null) {
                Menu.permission = account.getRole();
                System.out.println("Пользователь с логином " + login + " успешно вошел в систему");
                return account;
            } else {
                System.out.println("Клиента с таким логином нет в системе");
                return null;
            }
        } else {
            System.out.println("Необходимо указать логин");
            return null;
        }
    }

    public Account logOut(Account account) {
        if (account != null) {
            System.out.println("Осуществлен выход из системы");
        } else {
            System.out.println("Вход в систему не осуществлен");
        }
        Menu.permission = Permission.UNAUTHORISED;
        return account = null;
    }

    public void getCurrentAccountCurrencyStatus(Account account, String currency) {
        if (account != null) {
  /*          if (currency.equals("ALL")) {
                System.out.println(account.getRub() + " RUB");
                System.out.println(account.getUsd() + " USD");
                System.out.println(account.getEur() + " EUR");
            } else if (currency.equals("RUB")) {
                System.out.println(account.getRub() + " RUB");
            } else if (currency.equals("EUR")) {
                System.out.println(account.getEur() + " EUR");
            } else if (currency.equals("USD")) {
                System.out.println(account.getUsd() + " USD");
            } else if (currency.equals(null)) {
                System.out.println("Укажите валюту");
            } else {
                System.out.println("Данная валюта не поддерживается");
            }*/

            if (currency.equals("ALL")) {
                System.out.println(account.getAccountCurrency("RUB") + " RUB");
                System.out.println(account.getAccountCurrency("USD") + " USD");
                System.out.println(account.getAccountCurrency("EUR") + " EUR");
            } else if (currency.equals("RUB")) {
                System.out.println(account.getAccountCurrency("RUB") + " RUB");
            } else if (currency.equals("EUR")) {
                System.out.println(account.getAccountCurrency("EUR") + " EUR");
            } else if (currency.equals("USD")) {
                System.out.println(account.getAccountCurrency("USD") + " USD");
            } else if (currency.equals(null)) {
                System.out.println("Укажите валюту");
            } else {
                System.out.println("Данная валюта не поддерживается");
            }


        } else {
            System.out.println("Необходимо создать счет");
        }
    }

    public void purchaseUsdRub(Account account, BigDecimal amountRub) {
        try {
            if ((account.getRub().compareTo(amountRub)) >= 0) {
                amount = amountRub.divide(exchangeCurrency.getUsdRurRate(), 2,2);
                account.setRub(account.getRub().subtract(amountRub));
                account.setUsd(account.getUsd().add(amount));
            } else {
                System.out.println("Недостаточно средств на счету");
            }
        } catch (NullPointerException e) {
            System.out.println("Необходимо создать счет");
        }
    }

    public void purchaseRubUsd(Account account, BigDecimal amountUsd) {
        try {
            if ((account.getUsd().compareTo(amountUsd)) >= 0) {
                amount = amountUsd.multiply(exchangeCurrency.getUsdRurRate());
                account.setUsd(account.getUsd().subtract(amountUsd));
                account.setRub(account.getRub().add(amount));
            } else {
                System.out.println("Недостаточно средств на счету");
            }
        } catch (NullPointerException e) {
            System.out.println("Необходимо создать счет");
        }
    }

    public void purchaseEurRub(Account account, BigDecimal amountRub) {
        try {
            if ((account.getRub().compareTo(amountRub)) >= 0) {
                amount = amountRub.divide(exchangeCurrency.getEurRurRate(), 2,2);
                account.setRub(account.getRub().subtract(amountRub));
                account.setEur(account.getEur().add(amount));
            } else {
                System.out.println("Недостаточно средств на счету");
            }
        } catch (NullPointerException e) {
            System.out.println("Необходимо создать счет");
        }
    }

    public void purchaseRubEur(Account account, BigDecimal amountEur) {
        try {
            if ((account.getEur().compareTo(amountEur)) >= 0) {
                amount = amountEur.multiply(exchangeCurrency.getEurRurRate());
                account.setEur(account.getEur().subtract(amountEur));
                account.setRub(account.getRub().add(amount));
            } else {
                System.out.println("Недостаточно средств на счету");
            }
        } catch (NullPointerException e) {
            System.out.println("Необходимо создать счет");
        }
    }

    public void purchaseEurUsd(Account account, BigDecimal amountUsd) {
        try {
            if ((account.getUsd().compareTo(amountUsd)) >= 0) {
                amount = amountUsd.multiply(exchangeCurrency.getEurUsdRate());
                account.setUsd(account.getUsd().subtract(amountUsd));
                account.setEur(account.getEur().add(amount));
            } else {
                System.out.println("Недостаточно средств на счету");
            }
        } catch (NullPointerException e) {
            System.out.println("Необходимо создать счет");
        }
    }

    public void purchaseUsdEur(Account account, BigDecimal amountEur) {
        try {
            if ((account.getEur().compareTo(amountEur)) >=0) {
                amount = amountEur.divide(exchangeCurrency.getEurUsdRate(), 2,2);
                account.setEur(account.getEur().subtract(amountEur));
                account.setUsd(account.getUsd().add(amount));
            } else {
                System.out.println("Недостаточно средств на счету");
            }
        } catch (NullPointerException e) {
            System.out.println("Необходимо создать счет");
        }
    }

    public void purchaseCurrency (Account account, BigDecimal amount, String firstCurrency, String secondCurrency) {
        if (account != null) {
            if ((account.getAccountCurrency(secondCurrency).compareTo(amount)) >= 0) {
                BigDecimal a = exchangeCurrency.getCurrencyRate(firstCurrency+"/"+secondCurrency);
                this.amount = amount.divide(exchangeCurrency.getCurrencyRate(firstCurrency+"/"+secondCurrency),2,2);
                BigDecimal b = account.getAccountCurrency(secondCurrency).subtract(amount);
                BigDecimal c =  account.getAccountCurrency(firstCurrency).add(this.amount);
                account.setAccountCurrency(secondCurrency, account.getAccountCurrency(secondCurrency).subtract(amount));
                account.setAccountCurrency(firstCurrency, account.getAccountCurrency(firstCurrency).add(this.amount));
            } else {
                System.out.println("Недостаточно средств на счету");
            }
        } else {
            System.out.println("Необходимо создать счет");
        }
    }
}
