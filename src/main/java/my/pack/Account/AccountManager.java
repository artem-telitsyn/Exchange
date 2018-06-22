package my.pack.Account;

import my.pack.CommandMenu.Permission;
import my.pack.Menu;

import java.math.BigDecimal;
import java.util.HashMap;

public class AccountManager {

    public void createAccount(HashMap<String, Account> accountByLogin, String login, String role, Permission permission) {

        if (login != null && role != null && (role.equals("admin") || role.equals("client"))) {
            Account account = new Account(new HashMap<String, BigDecimal>());
            accountByLogin.put(login, account);
            Permission per = permission.getPermission(role);
            account.setRole(per);
            account.setLogin(login);
            System.out.println("Создан аккаунт с ролью " + per.getDescription());
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
                Menu.accountPermission = account.getRole();
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
        Menu.accountPermission = Permission.UNAUTHORISED;
        return account = null;
    }

    public void getCurrentAccountCurrencyStatus(Account account, String currency) {
        if (account != null) {
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
}


