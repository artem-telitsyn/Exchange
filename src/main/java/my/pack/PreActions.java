package my.pack;

import my.pack.Account.Account;
import my.pack.Account.AccountHistory;
import my.pack.MenuCommand.Permission;

import java.math.BigDecimal;

import java.util.HashMap;

import java.util.TreeMap;

public class PreActions {
    public void createAcounts(HashMap<String, Account> accountByLogin) {
        HashMap<String, String> listLogin = new HashMap<String, String>();
        listLogin.put("c1", "client");
        listLogin.put("c2", "client");
        listLogin.put("c3", "client");
        listLogin.put("a", "admin");
        for (String login: listLogin.keySet()) {
            Account account = new Account(new HashMap<Currency, BigDecimal>(), new TreeMap<Long, AccountHistory>());
            accountByLogin.put(login, account);
            Permission per = Permission.getPermission(listLogin.get(login));
            account.setRole(per);
            account.setLogin(login);
            System.out.println("Создан аккаунт " + login + " с ролью " + per.getDescription());
        }
    }
}