package my.pack;

import my.pack.Account.AccountManager;
import my.pack.Account.Account;
import my.pack.CommandMenu.*;

import java.math.BigDecimal;
import java.util.*;

public class Menu {

    private Account account;
    public static Permission accountPermission;
    private CommandMenu commandMenu;
    private final HashMap accountLogin;
    private final AccountManager accountManager;
    private final DepositCurrency depositCurrency;
    private final ExchangeCurrency exchangeCurrency;

    public Menu(Account account, AccountManager accountManager, DepositCurrency depositCurrency,
                ExchangeCurrency exchangeCurrency, HashMap accountLogin) {
        this.account = account;
        this.accountManager = accountManager;
        this.depositCurrency = depositCurrency;
        this.exchangeCurrency = exchangeCurrency;
        this.accountLogin = accountLogin;
    }

    public void printHelp(Account account) {
        if (account == null) {
            accountPermission = Permission.UNAUTHORISED;
        } else {
            accountPermission = account.getRole();
        }
        List<CommandMenu> listCommand = accountPermission.getListCommandForPermission(account);
        switch (accountPermission) {
            case CLIENT:
                printListCommandForPermission(listCommand);
                break;
            case ADMIN:
                printListCommandForPermission(listCommand);
                break;
            case UNAUTHORISED:
                printListCommandForPermission(listCommand);
                break;
            default:
                printListCommandForPermission(listCommand);
                break;
        }
    }

    public boolean checkPermission(Account account, CommandMenu command) {
        List<CommandMenu> listCommand = accountPermission.getListCommandForPermission(account);
        if (command == CommandMenu.DEFAULT) {
            System.out.println(command.getDescription());
            return false;
        } else if (account != null && command != null && accountPermission != null) {
            if (compareCommand(command, listCommand)) {
                return true;
            }
            System.out.println("Недостаточно прав");
            return false;
        } else if (account == null && command != null && (accountPermission == Permission.UNAUTHORISED)) {
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
        CommandMenu command = commandMenu.findCommandByName(inputReaderDto.getCommand());
        if (checkPermission(account, command)) {
            switch (command) {
                case HELP:
                    printHelp(account);
                    return true;
                case CREATE_ACCOUNT:
                    if (inputReaderDto.getParameter().size() >= 3) {
                        accountManager.createAccount(accountLogin, inputReaderDto.getParameter().get(1), inputReaderDto.getParameter().get(2), accountPermission);
                    } else {
                        System.out.println("Не указан одни из параметров");
                    }
                    return true;
                case ACCOUNT_STATUS_CURRENCY:
                    if (inputReaderDto.getParameter().size() >= 2) {
                        accountManager.getCurrentAccountCurrencyStatus(account, inputReaderDto.getParameter().get(1));
                    } else {
                        System.out.println("Не указан параметр");
                    }
                    return true;
                case DEPOSIT_RUB:
                    if (inputReaderDto.getParameter().size() >= 2) {
                        depositCurrency.depositRub(account, amountCurrency(inputReaderDto.getParameter().get(1)));
                    } else {
                        System.out.println("Не указан параметр");
                    }
                    return true;
                case EXCHANGE_RATE:
                    exchangeCurrency.getExchangeRate();
                    return true;
                case CHANGE_RATE:
                    if (inputReaderDto.getParameter().size() >= 3) {
                        exchangeCurrency.setCurrencyRate(inputReaderDto.getParameter().get(1), amountCurrency(inputReaderDto.getParameter().get(2)));
                    } else {
                        System.out.println("Не указан одни из параметров");
                    }
                    return true;
                case PURCHASE_CURRENCY:
                    if (inputReaderDto.getParameter().size() >= 4) {
                        depositCurrency.purchaseCurrency(account, amountCurrency(inputReaderDto.getParameter().get(1)), inputReaderDto.getParameter().get(2), inputReaderDto.getParameter().get(3));
                    } else {
                        System.out.println("Не указан одни из параметров");
                    }
                    return true;
                case LOGOUT:
                    account = accountManager.logOut(account);
                    return true;
                case LOGIN:
                    if (inputReaderDto.getParameter().size() >= 2) {
                        account = accountManager.logIn(accountLogin, inputReaderDto.getParameter().get(1));
                    } else {
                        System.out.println("Не указан параметр");
                    }
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

    public boolean compareCommand(CommandMenu command, List<CommandMenu> listCommand) {
        for (int i = 0; i < listCommand.size(); i++) {
            if (command == listCommand.get(i)) {
                return true;
            }
        }
        return false;
    }

    public void printListCommandForPermission(List<CommandMenu> listCommand) {
        for (int i = 0; i < listCommand.size(); i++) {
            if (listCommand.get(i) != CommandMenu.DEFAULT) {
                System.out.println(listCommand.get(i).getDescription());
            }
        }
    }

    private BigDecimal amountCurrency(String stringAmountCorrency) {
        return new BigDecimal(stringAmountCorrency);
    }
}
