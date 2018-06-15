package my.pack.CommandMenu;

public enum CommandMenu {

    HELP("help - Вызвать список доступных команд", "help"),
    LOGIN("login <логин> - Вход в систему под пользователем с логином <логин>", "login"),
    LOGOUT("logout - Выйти из системы", "logout"),
    CREATE_ACCOUNT("createAccount <логин> <роль> - Создание счета для нового пользователя <логин> с правами <ролью>", "createAccount"),
    ACCOUNT_STATUS_CURRENCY("accountStatusCurrency <валюта> - Проверить состояния <валюта> части счета (ALL для проверки общего счета)", "accountStatusCurrency"),
    DEPOSIT_RUB("depositRub <сумма> - <Сумма> положить на счет рубли", "depositRub"),
    EXCHANGE_RATE("exchangeRate - Узнать курс валют", "exchangeRate"),
    PURCHASE_USD_RUB("purchaseUsdRub <сумма> - <Сумма> купить USD за RUB", "purchaseUsdRub"),
    PURCHASE_RUB_USD("purchaseRubUsd <сумма> - <Сумма> купить RUB за USD", "purchaseRubUsd"),
    PURCHASE_EUR_RUB("purchaseEurRub <сумма> - <Сумма> купить EUR за RUB", "purchaseEurRub"),
    PURCHASE_RUB_EUR("purchaseRubEur <сумма> - <Сумма> купить RUB за EUR", "purchaseRubEur"),
    PURCHASE_EUR_USD("purchaseEurUsd <сумма> - <Сумма> купить EUR за USD", "purchaseEurUsd"),
    PURCHASE_USD_EUR("purchaseUsdEur <сумма> - <Сумма> купить USD за EUR", "purchaseUsdEur"),
    PURCHASE_CURRENCY("purchaseCurrency <сумма> <1 валюта> <2 валюта> - Купить <1 валюта> на <сумма> <2 валюта>", "purchaseCurrency"),
    EXIT("exit - Выйти из программы", "exit"),
    DEFAULT("Данная команда не поддерживается", "default");

    private String description;
    private String command;

    CommandMenu(String value) {
        this.description = value;
    }

    CommandMenu(String value, String command) {
        this.description = value;
        this.command = command;
    }

/*    public CommandMenu findCommandByName(){
        return ;
    }*/

    public static CommandMenu getMenu(String command) {
        switch (command) {
            case "help":
                return CommandMenu.HELP;
            case "login":
                return CommandMenu.LOGIN;
            case "logout":
                return CommandMenu.LOGOUT;
            case "createAccount":
                return CommandMenu.CREATE_ACCOUNT;
            case "depositRub":
                return CommandMenu.DEPOSIT_RUB;
            case "accountStatusCurrency":
                return CommandMenu.ACCOUNT_STATUS_CURRENCY;
            case "exchangeRate":
                return CommandMenu.EXCHANGE_RATE;
            case "purchaseUsdRub":
                return CommandMenu.PURCHASE_USD_RUB;
            case "purchaseRubUsd":
                return CommandMenu.PURCHASE_RUB_USD;
            case "purchaseEurRub":
                return CommandMenu.PURCHASE_EUR_RUB;
            case "purchaseRubEur":
                return CommandMenu.PURCHASE_RUB_EUR;
            case "purchaseEurUsd":
                return CommandMenu.PURCHASE_EUR_USD;
            case "purchaseUsdEur":
                return CommandMenu.PURCHASE_USD_EUR;
            case "purchaseCurrency":
                return CommandMenu.PURCHASE_CURRENCY;
            case "exit":
                return CommandMenu.EXIT;
            default:
                return CommandMenu.DEFAULT;
        }
    }

    public String getDescription() {
        return description;
    }

}

