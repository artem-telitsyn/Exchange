package my.pack;

import my.pack.Account.Account;
import my.pack.Account.AccountManager;
import my.pack.CommandMenu.ListCommandForPermission;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Boolean flagExit = true;
        HashMap accountByLogin = new HashMap<String, Account>();
        ExchangeCurrency exchangeCurrency = new ExchangeCurrency( new HashMap<String, BigDecimal>());
        Account account = null;
        Menu menu = new Menu(account, new AccountManager(exchangeCurrency), new DepositCurrency(),
                exchangeCurrency, accountByLogin, new ListCommandForPermission());
        InputReader inputReader = new InputReader();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        menu.printHelp(account);
        while (flagExit) {
            flagExit = menu.selectItemFromMenu(inputReader.read(reader));
        }
    }
}
