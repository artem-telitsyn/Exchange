package my.pack;

import my.pack.Account.Account;
import my.pack.Account.AccountManager;
import my.pack.Account.History;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Boolean flagExit = true;
        HashMap accountByLogin = new HashMap<String, Account>();
        ExchangeCurrency exchangeCurrency = new ExchangeCurrency(new HashMap<String, BigDecimal>());
        AccountManager accountManager = new AccountManager();
        DateTime dateTime = new DateTime();
        History history = new History(dateTime);
        Account account = null;
        PreActions preActions = new PreActions();
        preActions.createAcounts(accountByLogin);
        Menu menu = new Menu(account, accountManager, history, new DepositCurrency(exchangeCurrency, accountManager),
                exchangeCurrency, accountByLogin);
        InputReader inputReader = new InputReader();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        menu.help(account);
        while (flagExit) {
            flagExit = menu.selectItemFromMenu(inputReader.read(reader));
        }
    }
}
