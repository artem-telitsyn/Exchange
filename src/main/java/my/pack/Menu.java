package my.pack;

import my.pack.Account.AccountManager;
import my.pack.Account.Account;
import my.pack.CommandMenu.*;

import java.math.BigDecimal;
import java.util.*;

public class Menu {

    private Account account;
    public static Permission permission;
    private ListCommandForPermission listCommandForPermission;
    private final HashMap accountLogin;
    private final AccountManager accountManager;
    private final DepositCurrency depositCurrency;
    private final ExchangeCurrency exchangeCurrency;

    public Menu(Account account, AccountManager accountManager, DepositCurrency depositCurrency, ExchangeCurrency exchangeCurrency, HashMap accountLogin, ListCommandForPermission listCommandForPermission) {
        this.account = account;
        this.accountManager = accountManager;
        this.depositCurrency = depositCurrency;
        this.exchangeCurrency = exchangeCurrency;
        this.accountLogin = accountLogin;
        this.listCommandForPermission = listCommandForPermission;
    }

    public void printHelp(Account account) {
        CommandMenu[] listCommand;
        if (account == null) {
            permission = Permission.UNAUTHORISED;
        } else {
            permission = account.getRole();
        }
        listCommand = listCommandForPermission.getListCommandForPermission(account);
        switch (permission) {
            case CLIENT:
                printListCommandForPermission(listCommand);
                break;
            case ADMIN:
                printListCommandForPermission(listCommand);
                break;
            default:
                printListCommandForPermission(listCommand);
                break;
        }
    }

    public boolean checkPermission(Account account, CommandMenu command) {
        CommandMenu[] listCommand = listCommandForPermission.getListCommandForPermission(account);
        if (command == CommandMenu.DEFAULT) {
            System.out.println(command.getDescription());
            return false;
        } else if (account != null && command != null && permission != null) {
            if (compareCommand(command, listCommand)) {
                return true;
            }
            System.out.println("Недостаточно прав");
            return false;
        } else if (account == null && command != null && (permission == Permission.UNAUTHORISED)) {
            if (compareCommand(command, listCommand)) {
                return true;
            }
            System.out.println("Недостаточно прав");
            return false;
        }
        System.out.println("Недостаточно прав");
        return false;
    }

    public boolean selectItemFromMenu(InputReaderDto inputReaderDto) {
        CommandMenu command = CommandMenu.getMenu(inputReaderDto.getCommand());
        if (checkPermission(account, command)) {
            switch (command) {
                case HELP:
                    printHelp(account);
                    return true;
                case CREATE_ACCOUNT:
                    accountManager.createAccount(accountLogin, inputReaderDto.getParameter()[0], inputReaderDto.getParameter()[1], permission);
                    return true;
                case ACCOUNT_STATUS_CURRENCY:
                    accountManager.getCurrentAccountCurrencyStatus(account, inputReaderDto.getParameter()[0]);
                    return true;
                case DEPOSIT_RUB:
                    depositCurrency.depositRub(account, amountCurrency(inputReaderDto.getParameter()[0]));
                    return true;
                case EXCHANGE_RATE:
                    exchangeCurrency.getExchangeRate();
                    return true;
                case PURCHASE_RUB_USD:
                    accountManager.purchaseRubUsd(account, amountCurrency(inputReaderDto.getParameter()[0]));
                    return true;
                case PURCHASE_USD_RUB:
                    accountManager.purchaseUsdRub(account, amountCurrency(inputReaderDto.getParameter()[0]));
                    return true;
                case PURCHASE_RUB_EUR:
                    accountManager.purchaseRubEur(account, amountCurrency(inputReaderDto.getParameter()[0]));
                    return true;
                case PURCHASE_EUR_RUB:
                    accountManager.purchaseEurRub(account, amountCurrency(inputReaderDto.getParameter()[0]));
                    return true;
                case PURCHASE_USD_EUR:
                    accountManager.purchaseUsdEur(account, amountCurrency(inputReaderDto.getParameter()[0]));
                    return true;
                case PURCHASE_EUR_USD:
                    accountManager.purchaseEurUsd(account, amountCurrency(inputReaderDto.getParameter()[0]));
                    return true;
                case PURCHASE_CURRENCY:
                    accountManager.purchaseCurrency(account, amountCurrency(inputReaderDto.getParameter()[0]), inputReaderDto.getParameter()[1], inputReaderDto.getParameter()[2]);
                    return true;
                case LOGOUT:
                    account = accountManager.logOut(account);
                    return true;
                case LOGIN:
                    account = accountManager.logIn(accountLogin, inputReaderDto.getParameter()[0]);
                    return true;
                case EXIT:
                    return false;
                default:
                    System.out.println("Данная команда не поддерживается. Для ввывода списка команд введите help");
                    return true;
            }
        }
        return true;
    }

    public boolean compareCommand(CommandMenu command, CommandMenu[] listCommand) {
        for (int i = 0; i < listCommand.length; i++) {
            if (command == listCommand[i]) {
                return true;
            }
        }
        return false;
    }

    public void printListCommandForPermission (CommandMenu[] listCommand) {
        for (int i = 0; i < listCommand.length; i++) {
            if (listCommand[i] != CommandMenu.DEFAULT) {
                System.out.println(listCommand[i].getDescription());
            }
        }
    }

    private BigDecimal amountCurrency(String stringAmountCorrency) {
        return new BigDecimal(stringAmountCorrency);
    }
}
