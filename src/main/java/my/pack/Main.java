package my.pack;

import my.pack.Account.Account;
import my.pack.Account.AccountManager;
import my.pack.CommandMenu.ListCommandForPermission;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static Boolean flagExit = true;

    public static void main(String[] args) {

        HashMap accountLogin = new HashMap<String, Account>();
        ExchangeCurrency exchangeCurrency = new ExchangeCurrency();
        Account account = null;
        // 1. зачем передавать account равный null?
        // 2. Переносы строк при инициализации конструктора
        Menu menu = new Menu(account, new AccountManager(exchangeCurrency), new DepositCurrency(), exchangeCurrency, accountLogin, new ListCommandForPermission());
        InputReader inputReader = new InputReader();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        menu.printHelp(account);
        // Видимо flagExit меняется внутри меню, что по сути является side effectom, да и вообще крайне неожиданно.
        // лучше возвращать из метода флаг о том, продолжать ли дальше считывать, или нет
        while (flagExit) {
            menu.selectItemFromMenu(inputReader.inputReader(reader));
        }
    }
}
