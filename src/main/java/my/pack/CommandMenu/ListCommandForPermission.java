package my.pack.CommandMenu;

import my.pack.Account.Account;

// Список команд не меняется по ходу жизни всего приложения. Может стоит внести список всех доступных команд под конкретный enum
// с ролью пользователя?
public class ListCommandForPermission {

    public CommandMenu[] getListCommandForPermission(Account account) {
        if (account != null) {
            switch (account.getRole()) {
                case ADMIN:
                    return new CommandMenu[]{CommandMenu.HELP, CommandMenu.LOGOUT, CommandMenu.CREATE_ACCOUNT, CommandMenu.DEFAULT, CommandMenu.EXIT};
                case CLIENT:
                    return new CommandMenu[]{CommandMenu.HELP, CommandMenu.LOGOUT, CommandMenu.ACCOUNT_STATUS_CURRENCY, CommandMenu.DEPOSIT_RUB,
                            CommandMenu.EXCHANGE_RATE, CommandMenu.PURCHASE_USD_RUB, CommandMenu.PURCHASE_RUB_USD, CommandMenu.PURCHASE_EUR_RUB, CommandMenu.PURCHASE_RUB_EUR,
                            CommandMenu.PURCHASE_EUR_USD, CommandMenu.PURCHASE_USD_EUR, CommandMenu.EXIT, CommandMenu.DEFAULT};
                default:
                    return new CommandMenu[]{CommandMenu.HELP, CommandMenu.LOGOUT, CommandMenu.EXIT, CommandMenu.DEFAULT};
            }
        } else {
            return new CommandMenu[]{CommandMenu.HELP, CommandMenu.CREATE_ACCOUNT, CommandMenu.LOGIN, CommandMenu.EXIT, CommandMenu.DEFAULT};
        }
    }
}
