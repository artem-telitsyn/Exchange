package my.pack.MenuCommand;

import my.pack.Account.Account;

import java.util.Arrays;
import java.util.List;

public enum Permission {
    UNAUTHORISED("Неавторизованный клиент или неверная роль", "unauthorised"),
    CLIENT("Клиент", "client"),
    ADMIN("Админ", "admin");

    private String description;
    private String permission;

    private static List<MenuCommand> listAdminCommand = Arrays.asList(MenuCommand.HELP, MenuCommand.LOGOUT,
            MenuCommand.CREATE_ACCOUNT, MenuCommand.EXCHANGE_RATE, MenuCommand.CHANGE_RATE, MenuCommand.MAX_THREE_TRANSACTION_OF_CLIENTS, MenuCommand.TRANSACTION_HISTORY_BY_LOGIN, MenuCommand.DEFAULT, MenuCommand.EXIT);
    private static List<MenuCommand> listClientCommand = Arrays.asList(MenuCommand.HELP, MenuCommand.LOGOUT,
            MenuCommand.ACCOUNT_STATUS_CURRENCY, MenuCommand.DEPOSIT_RUB, MenuCommand.EXCHANGE_RATE,
            MenuCommand.PURCHASE_CURRENCY, MenuCommand.TRANSACTION_HISTORY, MenuCommand.TRANSACTION_HISTORY_ORDER_BY_AMOUNT, MenuCommand.EXIT, MenuCommand.DEFAULT);
    private static List<MenuCommand> listBaseCommand = Arrays.asList(MenuCommand.HELP, MenuCommand.LOGOUT,
            MenuCommand.EXIT, MenuCommand.DEFAULT);
    private static List<MenuCommand> listUnauthorisedCommand = Arrays.asList(MenuCommand.HELP,
            MenuCommand.CREATE_ACCOUNT, MenuCommand.LOGIN, MenuCommand.EXIT, MenuCommand.DEFAULT);

    Permission(String description, String permission) {
        this.description = description;
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public static Permission getPermission(String permis) {
        for (Permission perm : Permission.values()) {
            if (perm.permission.equals(permis)) {
                return perm;
            }
        }
        return Permission.UNAUTHORISED;
    }

    public List<MenuCommand> getListCommandForPermission(Account account) {
        if (account != null) {
            switch (account.getRole()) {
                case ADMIN:
                    return  listAdminCommand;
                case CLIENT:
                    return listClientCommand;
                default:
                return listBaseCommand;
            }
        } else {
            return listUnauthorisedCommand;
        }
    }

}
