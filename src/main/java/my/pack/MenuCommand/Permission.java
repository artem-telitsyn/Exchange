package my.pack.MenuCommand;

import my.pack.Account.Account;

import java.util.Arrays;
import java.util.List;

public enum Permission {
    UNAUTHORISED("Неавторизованный клиент или неверная роль"),
    CLIENT("Клиент"),
    ADMIN("Админ");

    private String description;

    private static List<MenuCommand> listAdminCommand = Arrays.asList(MenuCommand.HELP, MenuCommand.LOGOUT,
            MenuCommand.CREATE_ACCOUNT, MenuCommand.CHANGE_RATE, MenuCommand.DEFAULT, MenuCommand.EXIT);
    private static List<MenuCommand> listClientCommand = Arrays.asList(MenuCommand.HELP, MenuCommand.LOGOUT,
            MenuCommand.ACCOUNT_STATUS_CURRENCY, MenuCommand.DEPOSIT_RUB, MenuCommand.EXCHANGE_RATE,
            MenuCommand.PURCHASE_CURRENCY, MenuCommand.EXIT, MenuCommand.DEFAULT);
    private static List<MenuCommand> listBaseCommand = Arrays.asList(MenuCommand.HELP, MenuCommand.LOGOUT,
            MenuCommand.EXIT, MenuCommand.DEFAULT);
    private static List<MenuCommand> listUnauthorisedCommand = Arrays.asList(MenuCommand.HELP,
            MenuCommand.CREATE_ACCOUNT, MenuCommand.LOGIN, MenuCommand.EXIT, MenuCommand.DEFAULT);

    Permission(String value) {
        this.description = value;
    }

    public String getDescription() {
        return description;
    }

    public Permission getPermission(String permission) {
        switch (permission) {
            case "client":
                return Permission.CLIENT;
            case "admin":
                return Permission.ADMIN;
            default:
                return Permission.UNAUTHORISED;
        }
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
