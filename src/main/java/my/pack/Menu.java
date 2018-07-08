package my.pack;

import my.pack.Account.AccountManager;
import my.pack.Account.Account;
import my.pack.Account.History;
import my.pack.MenuCommand.*;

import java.math.BigDecimal;
import java.util.*;

public class Menu {

    private Account account;
    public static Permission accountPermission;
    private MenuCommand menuCommand;
    private final HashMap accountByLogin;
    private final AccountManager accountManager;
    private History history;
    private final DepositCurrency depositCurrency;
    private final ExchangeCurrency exchangeCurrency;

    public Menu(Account account, AccountManager accountManager, History history, DepositCurrency depositCurrency,
                ExchangeCurrency exchangeCurrency, HashMap accountByLogin) {
        this.account = account;
        this.accountManager = accountManager;
        this.depositCurrency = depositCurrency;
        this.exchangeCurrency = exchangeCurrency;
        this.accountByLogin = accountByLogin;
        this.history = history;
    }

    public void help(Account account) {
        if (account == null) {
            accountPermission = Permission.UNAUTHORISED;
        } else {
            accountPermission = account.getRole();
        }
        List<MenuCommand> listCommand = accountPermission.getListCommandForPermission(account);
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

    public boolean checkPermission(Account account, MenuCommand command) {
        List<MenuCommand> listCommand = accountPermission.getListCommandForPermission(account);
        if (command == MenuCommand.DEFAULT) {
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
        MenuCommand command = MenuCommand.findCommandByName(inputReaderDto.getCommand());
        if (checkPermission(account, command)) {
            switch (command) {
                case HELP:
                    help(account);
                    return true;
                case CREATE_ACCOUNT:
                    if (inputReaderDto.getParameter().size() >= 3) {
                        accountManager.createAccount(accountByLogin, inputReaderDto.getParameter().get(1),
                                inputReaderDto.getParameter().get(2), accountPermission);
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
                        exchangeCurrency.setCurrencyRate(inputReaderDto.getParameter().get(1),
                                amountCurrency(inputReaderDto.getParameter().get(2)));
                    } else {
                        System.out.println("Не указан одни из параметров");
                    }
                    return true;
                case PURCHASE_CURRENCY:
                    if (inputReaderDto.getParameter().size() >= 4) {
                        depositCurrency.purchaseCurrency(account, amountCurrency(inputReaderDto.getParameter().get(1)),
                                inputReaderDto.getParameter().get(2), inputReaderDto.getParameter().get(3));
                    } else {
                        System.out.println("Не указан одни из параметров");
                    }
                    return true;
                case TRANSACTION_HISTORY:
                    history.getTransactionAccountOrderByDate(account);
                    return true;
                case TRANSACTION_HISTORY_ORDER_BY_AMOUNT:
                    history.getTransactionAccountOrderByAmount(account);
                    return true;
                case MAX_THREE_TRANSACTION_OF_CLIENTS:
                    history.getMaxThreeTransaction(accountByLogin);
                    return true;
                case TRANSACTION_HISTORY_BY_LOGIN:
                    if (inputReaderDto.getParameter().size() >=2) {
                        history.transactionHistoryByLogin(accountByLogin, inputReaderDto.getParameter().get(1));
                    } else {
                        System.out.println("Не указан параметр");
                    }
                    return true;
                case LOGOUT:
                    account = accountManager.logOut(account);
                    return true;
                case LOGIN:
                    if (inputReaderDto.getParameter().size() >= 2) {
                        account = accountManager.logIn(accountByLogin, inputReaderDto.getParameter().get(1));
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

    public boolean compareCommand(MenuCommand command, List<MenuCommand> listCommand) {
        if (listCommand.contains(command)) {
            return true;
        } else {
            return false;
        }
    }

    public void printListCommandForPermission(List<MenuCommand> listCommand) {
        for (int i = 0; i < listCommand.size(); i++) {
            if (listCommand.get(i) != MenuCommand.DEFAULT) {
                System.out.println(listCommand.get(i).getDescription());
            }
        }
    }

    private BigDecimal amountCurrency(String stringAmountCorrency) {
        return new BigDecimal(stringAmountCorrency);
    }
}
