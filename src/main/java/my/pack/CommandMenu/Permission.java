package my.pack.CommandMenu;

import my.pack.Account.Account;

import java.util.Arrays;
import java.util.List;

public enum Permission {
    UNAUTHORISED("Неавторизованный клиент или неверная роль"),
    CLIENT("Клиент"),
    ADMIN("Админ");

    private String description;

    private static List<CommandMenu> listAdminCommand = Arrays.asList(CommandMenu.HELP, CommandMenu.LOGOUT, CommandMenu.CREATE_ACCOUNT, CommandMenu.CHANGE_RATE, CommandMenu.DEFAULT, CommandMenu.EXIT);
    private static List<CommandMenu> listClientCommand = Arrays.asList(CommandMenu.HELP, CommandMenu.LOGOUT, CommandMenu.ACCOUNT_STATUS_CURRENCY, CommandMenu.DEPOSIT_RUB, CommandMenu.EXCHANGE_RATE, CommandMenu.PURCHASE_CURRENCY, CommandMenu.EXIT, CommandMenu.DEFAULT);
    private static List<CommandMenu> listBaseCommand = Arrays.asList(CommandMenu.HELP, CommandMenu.LOGOUT, CommandMenu.EXIT, CommandMenu.DEFAULT);
    private static List<CommandMenu> listUnauthorisedCommand = Arrays.asList(CommandMenu.HELP, CommandMenu.CREATE_ACCOUNT, CommandMenu.LOGIN, CommandMenu.EXIT, CommandMenu.DEFAULT);

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

    public List<CommandMenu> getListCommandForPermission(Account account) {
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
